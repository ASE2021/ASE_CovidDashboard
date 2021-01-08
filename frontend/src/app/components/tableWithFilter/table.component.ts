import { Component, OnInit } from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {Provinces} from '../../model/Provinces';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  data: Array<any>;
  dataProvince: Array<any>;
  provinces: Provinces[]; // saving the enum Provinces

  // calculation of numbers in whole Austria
  totalCases: number;
  totalHospitalizations: number;
  totalDeaths: number;

  constructor(private covidService: CovidService) { }

  ngOnInit(): void {
    this.loadTableData();
    this.loadProvinceData();

  }


  private async loadTableData(): Promise<void> {
    this.data = await this.covidService.getGeneralSituationPerDate();
    this.dataProvince = await this.covidService.getProvinceSituationPerDate();

    this.calculateTotalCases();
    this.calculateTotalDeaths();
    this.calculateTotalHospitalizations();
  }


/*
  private loadTableData() {
    this.data = [{provinces: 'Kärnten', activeCases: 1200 , deaths: 2, hospitalizations: 50},
      {provinces: 'Steiermark', activeCases: 970, deaths: 5, hospitalizations: 35}];
  }


 */


  private loadProvinceData() {
    this.dataProvince = [{province: 'Kärnten', cases: 1200 , deaths: 2, hospitalizations: 50},
      {province: 'Steiermark', cases: 970, deaths: 5, hospitalizations: 35}];
  }



  calculateTotalCases(): void {
    let total = 0;
    for (const i of this.dataProvince) {
      total += i.cases;
    }
    this.totalCases = total;
    }

  calculateTotalDeaths(): void {
    let total = 0;
    for (const i of this.dataProvince) {
      total += i.deaths;
    }

    this.totalDeaths = total;
  }

  calculateTotalHospitalizations(): void {
    let total = 0;
    for (const i of this.dataProvince) {
      total += i.hospitalBedsSum;
    }

    this.totalHospitalizations = total;
  }




}
