import {Component, OnInit} from '@angular/core';
import {CovidService} from "../../services/covid.service";
import {SocketService} from "../../services/socket/socket.service";
import {Area} from '../../model/area';
import {ChartModelBuilder} from '../../model/chart-model-builder';

@Component({
  selector: 'app-age-sex-distribution-chart',
  templateUrl: './age-sex-distribution-chart.component.html',
  styleUrls: ['./age-sex-distribution-chart.component.scss']
})
export class AgeSexDistributionChartComponent implements OnInit {

  ageSexDistributionData: any;

  constructor(private readonly covidService: CovidService,
              private readonly socketService: SocketService) {
  }

  ngOnInit(): void {

    this.initializeAgeSexDistributionChart();

    this.socketService.connectAndObserveNewData()
      .subscribe(() => this.initializeAgeSexDistributionChart());
  }

  private async initializeAgeSexDistributionChart(): Promise<any> {
    const dummyAreaData: Area[] = [{
      areaId: 10,
      areaName: 'Austria'
    }
    ];

    let data = await this.covidService.getAgeSexDistributionData(dummyAreaData);
    this.ageSexDistributionData = new ChartModelBuilder()
      .useBarChartStyle()
      .buildBasicChartModel(Object.keys(data['10'])
          .map(item => item[0].toUpperCase()
            + item.substring(1, item.length)
              .replace(/([A-Z])/g, ' $1')
              .trim()
              .toLowerCase()), data.ageInterval,
        Object.values(data['10']));
    console.log(this.ageSexDistributionData);
    console.log(data.ageInterval);
    console.log(Object.values(data['10']));
  }


}
