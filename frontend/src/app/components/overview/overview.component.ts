import {Component, OnInit, ViewChild} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {Provinces} from '../../model/Provinces';
import {CovidCasesDaily} from '../../model/covid-cases-daily';
import { TableModelBuilder } from "../../model/table-model-builder";


@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss'],
})
export class OverviewComponent implements OnInit {

  positiveCasesPerDateData: any;
  activeCases: number;
  numberOfCases: number;
  deaths: number;
  province: any;




  constructor(private covidService: CovidService) {

  }

  public ngOnInit(): void {
    this.initializePositiveCasesPerDateChart();

    this.initializeBasicInformation();

    this.initializePositiveCasesPerTableChart();

    // this.covidService.getNewCasesPerDate().then(this.province)
  }

  private async initializeBasicInformation(): Promise<void> {
    // TODO: get data from backend (receive object with these (or more) properties)
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


  private async initializePositiveCasesPerTableChart(): Promise<void> {
    const data = await this.covidService.getNewCasesPerDate();
    this.positiveCasesPerDateData = new TableModelBuilder()
      .buildTableModel('Positive Covid-19 cases per date',
        data.map(item => item.date.split('T')[0]),
        data.map(item => item.cases));
  }
}


