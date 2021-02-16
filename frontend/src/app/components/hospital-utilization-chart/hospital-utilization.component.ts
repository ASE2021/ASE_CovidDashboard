import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {SocketService} from '../../services/socket/socket.service';
import {TreeNode} from 'primeng/api';
import {Area} from '../../model/area';

@Component({
  selector: 'app-hospital-utilization',
  templateUrl: './hospital-utilization.component.html',
  styleUrls: ['./hospital-utilization.component.scss'],
})
export class HospitalUtilizationComponent implements OnInit {

  hospitalUtilizationData: any;
  options: string[];
  bedTypes: string;
  provinces: TreeNode[];
  areaAustria: Area = {areaId: 10, areaName: 'Österreich'};
  selectedProvince = 10;


  constructor(private covidService: CovidService, private socketService: SocketService) {

  }

  public ngOnInit(): void {

    this.bedTypes = '2';

    this.initializeHospitalUtilisationPerProvinceChart();
    this.loadRegionData();

    this.socketService.connectAndObserveNewData()
      .subscribe(() => {
        this.initializeHospitalUtilisationPerProvinceChart();
        this.loadRegionData();
      });

  }


  private async initializeHospitalUtilisationPerProvinceChart(): Promise<void> {
    const data = await this.covidService.getHospitalUtilizationPerProvince(this.bedTypes, this.selectedProvince);

    this.hospitalUtilizationData = new ChartModelBuilder()
      .useLineChartStyle()
      .buildBasicChartModel(Object.keys(data[this.selectedProvince.toString(10)])
          .map(item => item[0].toUpperCase()
            + item.substring(1, item.length)
              .replace(/([A-Z])/g, ' $1')
              .trim()
              .toLowerCase()), data.labels,
        Object.values(data[this.selectedProvince.toString(10)]));

  }


  selectionChanged(): void {
    this.initializeHospitalUtilisationPerProvinceChart();
  }


  private async loadRegionData(): Promise<void> {
    this.provinces = await this.covidService.loadProvinces();
  }

  regionSelected(areaId: number): void {
    this.selectedProvince = areaId;
    this.initializeHospitalUtilisationPerProvinceChart();
  }
}
