import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {SocketService} from '../../services/socket/socket.service';
import {MessageResponse} from '../../model/MessageResponse';
import {IMqttMessage} from 'ngx-mqtt';
import {HospitalBedsDaily} from '../../model/hospital-beds-daily';
import {SexDistribution} from '../../model/sex-distribution';
import {Area} from '../../model/area';


@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss'],
})

export class OverviewComponent implements OnInit {

  positiveCasesPerDateData: any;
  sexDistributionCasesData: SexDistribution;
  sexDistributionDeathsData: SexDistribution;
  hospitalBedsPerDate: HospitalBedsDaily;
  activeCases: number;
  numberOfCases: number;
  deaths: number;
  comparison: any;
  province: any;
  relative = false;
  options: any;


  constructor(private covidService: CovidService, private socketService: SocketService) {

  }

  public ngOnInit(): void {
    this.initializeComparisonCasesChart(this.relative);
    this.initializePositiveCasesPerDateChart();
    this.initializeBasicInformation();
    this.initializeSexDistributionCharts();
    this.initializeHospitalBedsPerDateChart();


    this.socketService.connectToMqtt(
      () => {
        this.socketService.observe('new-data')
          .subscribe((message: IMqttMessage) => {
            console.log(message.payload.toString());
            try {
              if ((JSON.parse(message.payload.toString()) as MessageResponse).update) {
                this.initializeComparisonCasesChart(this.relative);
                this.initializePositiveCasesPerDateChart();
                this.initializeBasicInformation();
                this.initializeHospitalBedsPerDateChart();
                this.initializeSexDistributionCharts();
              }
            } catch (e) {
            }
          });
      },
    );
  }

  // TODO: get data from backend (receive object with these (or more) properties)
  private async initializeBasicInformation(): Promise<void> {
    this.activeCases = 41000;
    this.numberOfCases = 200000;
    this.deaths = 2000;
  }

  private async initializeComparisonCasesChart(relative): Promise<void> {
    let data;

    const dummyAreaData: Area[] = [{
      areaId: 10,
      areaName: 'Austria'
    }
    ];

    if (relative) {
      data = await this.covidService.getComparisonCasesDataRelative(dummyAreaData);

    } else {
      data = await this.covidService.getComparisonData(dummyAreaData);
    }


    console.log(data);
    this.options = {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true
          }
        }]
      }
    };

    this.comparison = new ChartModelBuilder()
      .buildBasicChartModel(Object.keys(data['10'])
          .map(item => item[0].toUpperCase()
            + item.substring(1, item.length)
              .replace(/([A-Z])/g, ' $1')
              .trim()
              .toLowerCase()), data.dates,
        Object.values(data['10']));
    console.log(this.comparison);
    console.log(Object.values(data['10']));
  }

  private async initializePositiveCasesPerDateChart(): Promise<void> {
    const data = await this.covidService.getNewCasesPerDate();
    this.positiveCasesPerDateData = new ChartModelBuilder()
      .useBarChartStyle()
      .buildBasicChartModel(['Positive Covid-19 cases per date'],
        data.map(item => item.date.split('T')[0]),
        [data.map(item => item.cases)]);
  }

  private async initializeSexDistributionCharts(): Promise<void> {
    const data = await this.covidService.getSexDistribution();
    this.sexDistributionCasesData =
      new ChartModelBuilder().useCustomColors([['#1B2771', '#A93226']])
        .useBarChartStyle()
        .buildBasicChartModel(['Covid Cases Distributed per sex'],
          ['female', 'male'],
          data.reduce((dataArray, current) =>
            [
              [...dataArray[0], current.femaleCases, current.maleCases],
            ], [[], []]));

    this.sexDistributionDeathsData = new ChartModelBuilder()
      .useCustomColors([['#1B2771', '#A93226']])
      .useBarChartStyle()
      .buildBasicChartModel(['Deaths Distributed per sex'],
        ['female', 'male'],
        data.reduce((dataArray, current) =>
          [
            [...dataArray[0], current.femaleDeaths, current.maleDeaths],
          ], [[], []]),
      );
  }

  private async initializeHospitalBedsPerDateChart(): Promise<void> {
    const data = await this.covidService.getHospitalBedsPerDate();
    this.hospitalBedsPerDate = new ChartModelBuilder()
      .useLineChartStyle()
      .buildBasicChartModel(['intense beds', 'normal beds'],
        data.map(item => item.date.split('T')[0]),
        data.reduce((dataArray, current) =>
          [
            [...dataArray[0], current.intenseBeds],
            [...dataArray[1], current.normalBeds],
          ], [[], []]));

  }

  public showRelativeComparisonData(): void {
    this.relative = !this.relative;
    this.initializeComparisonCasesChart(this.relative);
  }
}
