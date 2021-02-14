import {Component, NgZone, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {SocketService} from '../../services/socket/socket.service';
import {Area} from '../../model/area';
import {CovidOverview} from '../../model/covid-overview';
import {TreeNode} from 'primeng/api';


@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss'],
})

export class OverviewComponent implements OnInit {

  positiveCasesPerDateData: any;
  sexDistributionCuredData: any;
  sexDistributionDeathsData: any;
  hospitalBedsPerDate: any;
  hospitalUtilizationData: any;
  activeCases: number;
  deaths: number;
  comparison: any;
  relative = false;
  options: any;
  covidOverview: CovidOverview;
  regionData: any;
  areaAustria: Area = {areaId: 10, areaName: 'Österreich'};
  provinces: TreeNode[];
  private selectedAreaForComparison = 10;


  constructor(private covidService: CovidService,
              private socketService: SocketService,
              private ngZone: NgZone) {

  }

  public ngOnInit(): void {

    this.initializeAll();

    this.socketService.connectAndObserveNewData()
      .subscribe(() => {
        this.initializeAll();
      });
  }

  private initializeAll(): void {
    this.initializeComparisonCasesChart(this.relative, this.selectedAreaForComparison);
    this.initializePositiveCasesPerDateChart(10);
    this.initializeBasicInformation();
    this.initializeSexDistributionCharts(10);
    this.initializeHospitalBedsPerDateChart(10);
    this.loadRegionData();

  }

  private async loadRegionData(): Promise<void> {
    this.regionData = await this.covidService.loadProvincesAndDistrictsAsTableData();
    this.provinces = await this.covidService.loadProvinces();
  }

  async initializeBasicInformation(): Promise<void> {
    this.covidOverview = await this.covidService.getBasicInformation();
  }

  async initializeComparisonCasesChart(relative, areaId: number, calendar?): Promise<void> {

    const data = await this.covidService.getComparisonCasesData(areaId, relative);

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
      .buildBasicChartModel(Object.keys(data[areaId.toString(10)])
          .map(item => item[0].toUpperCase()
            + item.substring(1, item.length)
              .replace(/([A-Z])/g, ' $1')
              .trim()
              .toLowerCase()), data.labels,
        Object.values(data[areaId.toString(10)]));

    if (calendar) {
      calendar.reloadData(this.comparison);
    }
  }

  async initializePositiveCasesPerDateChart(areaId: number, calendar?): Promise<void> {
    console.log(areaId);
    const data = await this.covidService.getNewCasesPerDate([areaId.toString(10)]);
    console.log(data);
    this.positiveCasesPerDateData = new ChartModelBuilder()
      .useBarChartStyle()
      .buildBasicChartModel(['Positive Covid-19 cases per date'],
        data.labels, Object.values(data[areaId.toString(10)]));
    if (calendar) {
      calendar.reloadData(this.positiveCasesPerDateData);
    }
  }

  async initializeSexDistributionCharts(areaId: number): Promise<void> {
    const response = await this.covidService.getSexDistributionCases([areaId.toString(10)]);
    const male = response[0].data.find(item => item.sex === 'M').values;
    const female = response[0].data.find(item => item.sex === 'W').values;


    this.sexDistributionCuredData =
      new ChartModelBuilder().useCustomColors([['#1B2771', '#A93226']])
        .useBarChartStyle()
        .buildBasicChartModel(['Covid Cases Distributed per sex'],
          ['female', 'male'],
          [[
            female.find(item => item.identifier === 'cured').value,
            male.find(item => item.identifier === 'cured').value,
          ]]);


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

  async initializeHospitalBedsPerDateChart(areaId: number, calendar?): Promise<void> {
    this.hospitalBedsPerDate = new ChartModelBuilder()
      .useLineChartStyle()
      .buildModelFromResponse(
        await this.covidService.getHospitalBedsPerDate([areaId.toString(10)]),
        areaId.toString(10));
    if (calendar) {
      calendar.reloadData(this.hospitalBedsPerDate);
    }
  }


  public showRelativeComparisonData(calendar?): void {
    this.relative = !this.relative;
    this.initializeComparisonCasesChart(this.relative, this.selectedAreaForComparison, calendar);
  }


  comparisonRegionChanged(areaId: number, calendar?): void {
    this.selectedAreaForComparison = areaId;
    this.initializeComparisonCasesChart(this.relative, areaId, calendar);

  }

}
