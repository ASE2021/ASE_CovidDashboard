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

  //calculation of numbers in whole Austria
  totalCases: number;
  totalHospitalizations: number;
  totalDeaths: number;

  constructor(private covidService: CovidService) { }

  ngOnInit(): void {
    this.loadTableData();

    this.covidService.getNewCasesPerDate()

    this.calculateTotalCases()
    this.calculateTotalDeaths()
    this.calculateTotalHospitalizations()
  }


  private async loadTableData(): Promise<void> {
    const deaths = await this.covidService.getDeathsPerDate();
    const hospitalizations = await this.covidService.getHospitalizationsPerDate();
    const newCases = await this.covidService.getNewCasesPerDate();

  }


/*
  private loadTableData() {
    this.data = [{provinces: 'Kärnten', activeCases: 1200 , deaths: 2, hospitalizations: 50},
      {provinces: 'Steiermark', activeCases: 970, deaths: 5, hospitalizations: 35}];
  }


 */


  calculateTotalCases() {
    let total = 0;
    for(let i of this.data) {
      total += i.activeCases;
    }

      this.totalCases = total;
    }

  calculateTotalDeaths() {
    let total = 0;
    for(let i of this.data) {
      total += i.deaths;
    }

    this.totalDeaths = total;
  }

  calculateTotalHospitalizations() {
    let total = 0;
    for(let i of this.data) {
      total += i.hospitalizations;
    }

    this.totalHospitalizations = total;
  }






}
