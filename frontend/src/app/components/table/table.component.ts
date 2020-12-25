import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {Provinces} from '../../model/Provinces';
import {CovidCasesDaily} from '../../model/covid-cases-daily';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  positiveCasesPerDateData: any;
  activeCases: number;
  numberOfCases: number;
  deaths: number;
  province: any;

  constructor(private covidService: CovidService) { }

  ngOnInit(): void {
    this.loadTableData();
  }

  // tslint:disable-next-line:typedef
  private loadTableData() {
    return null;
  }



}
