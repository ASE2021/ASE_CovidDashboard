import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {SexDistribution} from "../../model/sex-distribution";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss'],
})
export class OverviewComponent implements OnInit {

  positiveCasesPerDateData: any;
  sexDistributionData: any;
  activeCases: number;
  numberOfCases: number;
  deaths: number;

  constructor(private covidService: CovidService) {

  }

  public ngOnInit(): void {
    this.initializePositiveCasesPerDateChart();

    this.initializeBasicInformation();

    this.initializeSexDistributionChart()
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

  private async initializeSexDistributionChart(): Promise<void>{
    const data = await this.covidService.getSexDistribution();
    this.sexDistributionData = new ChartModelBuilder().buildPieChartModel('Covid Cases Distributed per sex',
      ['female', 'male'],
      data.map(item => item.femaleCases),
      data.map(item => item.maleCases));
  }
}
