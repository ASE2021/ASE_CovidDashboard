import { Component, OnInit } from '@angular/core';
import {SelectItem, TreeNode} from "primeng/api";
import {CovidService} from "../../services/covid.service";
import {SocketService} from "../../services/socket/socket.service";
import {ChartModelBuilder} from "../../model/chart-model-builder";
import {TreeTable} from "primeng/treetable";

@Component({
  selector: 'app-hospital-utilization',
  templateUrl: './hospital-utilization.component.html',
  styleUrls: ['./hospital-utilization.component.scss']
})
export class HospitalUtilizationComponent implements OnInit {

  hospitalUtilizationData: any;

  constructor(private covidService: CovidService, private socketService: SocketService) {

  }
  public ngOnInit(): void {

  this.initializeHospitalUtilisationPerProvinceChart();

  }


  private async initializeHospitalUtilisationPerProvinceChart(): Promise<void> {
    const data = await this.covidService.getHospitalUtilizationPerProvince();

    this.hospitalUtilizationData = new ChartModelBuilder()
      .buildBasicChartModel(Object.keys(data['10'])
          .map(item => item[0].toUpperCase()
            + item.substring(1, item.length)
              .replace(/([A-Z])/g, ' $1')
              .trim()
              .toLowerCase()), data.dates,
        Object.values(data['10']));
    console.log(this.hospitalUtilizationData);
    console.log(Object.values(data['10']));
  }



}
