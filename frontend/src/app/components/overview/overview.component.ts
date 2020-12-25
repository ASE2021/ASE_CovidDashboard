import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss'],
})
export class OverviewComponent implements OnInit {

  positiveCasesPerDateData: any;
  hospitalIntenseBedsPerDate: any;
  hospitalNormalBedsPerDate: any
  activeCases: number;
  numberOfCases: number;
  deaths: number;

  constructor(private covidService: CovidService) {

  }

  public ngOnInit(): void {
    this.initializePositiveCasesPerDateChart();

    this.initializeBasicInformation();

    this.initializeIntenseHospitalBedsPerDateChart()
    this.initializeNormalHospitalBedsPerDateChart()
  }

  private async initializeBasicInformation(): Promise<void> { // TODO: get data from backend (receive object with these (or more) properties)
    this.activeCases = 41000;
    this.numberOfCases = 200000;
    this.deaths = 2000;
  }

  private async initializePositiveCasesPerDateChart(): Promise<void> {
    const data = await this.covidService.getNewCasesPerDate();
    this.positiveCasesPerDateData = new ChartModelBuilder()
      .buildBarChartModel('Positive Covid-19 cases per date',
        data.map(item => item.date.split('T')[0]),
        data.map(item => item.cases));
  }

  private async initializeIntenseHospitalBedsPerDateChart(): Promise<void> {
    const data = await  this.covidService.getHospitalIntenseBedsPerDate();
    this.hospitalIntenseBedsPerDate = new ChartModelBuilder()
      .buildHospitalLineChartModel('Intense beds used in hospital',
        data.map(item => item.date.split('T')[0]),
        data.map(item => item.inteseBeds));
  }

  private async initializeNormalHospitalBedsPerDateChart(): Promise<void> {
    const data = await  this.covidService.getHospitalNormalBedsPerDate();
    this.hospitalNormalBedsPerDate = new ChartModelBuilder()
      .buildHospitalLineChartModel('Normal beds used in hospital',
        data.map(item => item.date.split('T')[0]),
        data.map(item => item.normalBeds));
  }
}
