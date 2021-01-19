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

    this.socketService.connectAndObserveNewData()
      .subscribe(() => this.initializeAgeSexDistributionChart());
  }

  private async initializeAgeSexDistributionChart(): Promise<any> {
    const dummyAreaData: Area[] = [{
      areaId: 10,
      areaName: 'Austria'
    }
    ];

    let data = this.covidService.getAgeSexDistributionData(dummyAreaData);
    this.ageSexDistributionData = new ChartModelBuilder()
      .useBarChartStyle()
      .buildBasicChartModel(['Distribution of COVID cases based on age and sex'],
        data.map(item => item.ageInterval),
        data.reduce((dataArray, current) =>
          [
            [...dataArray[0], current.femaleCases, current.maleCases],
          ], [[], []]));
  }


}
