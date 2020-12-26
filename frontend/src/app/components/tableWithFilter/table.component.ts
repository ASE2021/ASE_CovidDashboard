import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';
import {CovidService} from '../../services/covid.service';
import {Provinces} from '../../model/Provinces';
import {CovidCasesDaily} from '../../model/covid-cases-daily';
import {TableData} from "../../model/tableData";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  data: Array<any>;
  //data: TableData[];
  provinces: Provinces[]; //saving the enum Provinces

  constructor(private covidService: CovidService) { }

  ngOnInit(): void {
    this.loadTableData();

    this.covidService.getNewCasesPerDate()
  }

/*
  private async loadTableData(): Promise<void> {
    await this.covidService.getDeathsPerDate();
    await this.covidService.getHospitalizationsPerDate();
    await this.covidService.getNewCasesPerDate();

  }

 */


  private loadTableData() {
    this.data = [{provinces: '1', activeCases: '12', deaths:'12', hospitalizations: '14'},
      {provinces: '1', activeCases: '12', deaths:'12', hospitalizations: '14'}];
  }





}
