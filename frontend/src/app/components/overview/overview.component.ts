import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {SocketService} from '../../services/socket/socket.service';
import {Area} from '../../model/area';
import {CovidOverview} from '../../model/covid-overview';


@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss'],
})

export class OverviewComponent implements OnInit {

  positiveCasesPerDateData: any;
  sexDistributionCasesData: any;
  sexDistributionDeathsData: any;
  hospitalBedsPerDate: any;
  activeCases: number;
  deaths: number;
  comparison: any;
  relative = false;
  options: any;
  covidOverview: CovidOverview;


  constructor(private covidService: CovidService, private socketService: SocketService) {

  }

  public ngOnInit(): void {

    this.initializeAll();

    this.socketService.connectAndObserveNewData()
      .subscribe(() => {
        this.initializeAll();
      });
  }

  private initializeAll(): void {
    this.initializeComparisonCasesChart(this.relative);
    this.initializePositiveCasesPerDateChart();
    this.initializeBasicInformation();
    this.initializeSexDistributionCharts();
    this.initializeHospitalBedsPerDateChart();
  }

  private async initializeBasicInformation(): Promise<void> {
    this.covidOverview = await this.covidService.getBasicInformation();
    console.log(this.covidOverview);
  }

  private async initializeComparisonCasesChart(relative): Promise<void> {
    const dummyAreaData: Area[] = [{
      areaId: 10,
      areaName: 'Austria',
    },
    ];


    const data = await this.covidService.getComparisonCasesData(dummyAreaData, relative);

    console.log(data);
    this.options = {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true,
          },
        }],
      },
    };

    this.comparison = new ChartModelBuilder()
      .buildBasicChartModel(Object.keys(data['10'])
          .map(item => item[0].toUpperCase()
            + item.substring(1, item.length)
              .replace(/([A-Z])/g, ' $1')
              .trim()
              .toLowerCase()), data.labels,
        Object.values(data['10']));
    console.log(this.comparison);
    console.log(Object.values(data['10']));
  }

  private async initializePositiveCasesPerDateChart(): Promise<void> {
    const data = await this.covidService.getNewCasesPerDate(['10']);
    this.positiveCasesPerDateData = new ChartModelBuilder()
      .useBarChartStyle()
      .buildBasicChartModel(['Positive Covid-19 cases per date'],
        data.labels, Object.values(data['10']));
  }

  private async initializeSexDistributionCharts(): Promise<void> {
    const response = await this.covidService.getSexDistributionCases(['10']);
    console.log(response);
    const male = response[0].data.find(item => item.sex === 'M').values;
    const female = response[0].data.find(item => item.sex === 'W').values;


    this.sexDistributionCasesData =
      new ChartModelBuilder().useCustomColors([['#1B2771', '#A93226']])
        .useBarChartStyle()
        .buildBasicChartModel(['Covid Cases Distributed per sex'],
          ['female', 'male'],
          [[
            female.find(item => item.identifier === 'cured').value,
            male.find(item => item.identifier === 'cured').value,
          ]]);

    console.log(this.sexDistributionCasesData);

    this.sexDistributionDeathsData = new ChartModelBuilder()
      .useCustomColors([['#1B2771', '#A93226']])
      .useBarChartStyle()
      .buildBasicChartModel(['Deaths Distributed per sex'],
        ['female', 'male'],
        [[
          female.find(item => item.identifier === 'dead').value,
          male.find(item => item.identifier === 'dead').value,
        ],
        ]);
  }

  private async initializeHospitalBedsPerDateChart(): Promise<void> {
    this.hospitalBedsPerDate = new ChartModelBuilder()
      .useLineChartStyle()
      .buildModelFromResponse(
        await this.covidService.getHospitalBedsPerDate(['10']), '10');

  }

  public showRelativeComparisonData(): void {
    this.relative = !this.relative;
    this.initializeComparisonCasesChart(this.relative);
  }
}
