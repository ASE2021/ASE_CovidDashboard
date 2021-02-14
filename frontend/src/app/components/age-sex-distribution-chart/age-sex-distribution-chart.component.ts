import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {SocketService} from '../../services/socket/socket.service';
import {Area} from '../../model/area';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {TreeNode} from 'primeng/api';


@Component({
  selector: 'app-age-sex-distribution-chart',
  templateUrl: './age-sex-distribution-chart.component.html',
  styleUrls: ['./age-sex-distribution-chart.component.scss'],
})
export class AgeSexDistributionChartComponent implements OnInit {

  ageSexDistributionData: any;
  selectedValue: string;
  provinces: TreeNode[];
  areaAustria: Area = {areaId: 10, areaName: 'Österreich'};
  selectedProvince = 10;

  constructor(private readonly covidService: CovidService,
              private readonly socketService: SocketService) {
  }

  ngOnInit(): void {

    this.selectedValue = 'cases';
    this.initializeAgeSexDistributionChart(this.selectedProvince);
    this.loadRegionData();

    this.socketService.connectAndObserveNewData()
      .subscribe(() => {
        this.initializeAgeSexDistributionChart(this.selectedProvince);
        this.loadRegionData();
      });
  }

  async initializeAgeSexDistributionChart(areaId: number): Promise<any> {

    const data = await this.covidService.getAgeSexDistributionData(areaId, this.selectedValue);
    this.ageSexDistributionData = new ChartModelBuilder()
      .useBarChartStyle()
      .buildBasicChartModel(Object.keys(data[areaId.toString(10)])
          .map(item => item[0].toUpperCase()
            + item.substring(1, item.length)
              .replace(/([A-Z])/g, ' $1')
              .trim()
              .toLowerCase()), data.labels,
        Object.values(data[areaId.toString(10)]));
  }

  selectionChanged(): void {
    this.initializeAgeSexDistributionChart(this.selectedProvince);
  }

  private async loadRegionData(): Promise<void> {
    this.provinces = await this.covidService.loadProvinces();
  }

  regionSelected(areaId: number): void {
    this.selectedProvince = areaId;
    this.initializeAgeSexDistributionChart(areaId);
  }
}
