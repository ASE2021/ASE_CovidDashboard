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
  provinces: Provinces[]; // saving the enum Provinces

  // calculation of numbers in whole Austria
  totalCases: number;
  totalHospitalizations: number;
  totalDeaths: number;

  constructor(private covidService: CovidService) { }

  ngOnInit(): void {
    this.loadTableData();

/*
    this.calculateTotalCases()
    this.calculateTotalDeaths()
    this.calculateTotalHospitalizations()
    */
  }


  private async loadTableData(): Promise<void> {
    this.data = await this.covidService.getGeneralSituationPerDate();
  }


/*
  private loadTableData() {
    this.data = [{provinces: 'Kärnten', activeCases: 1200 , deaths: 2, hospitalizations: 50},
      {provinces: 'Steiermark', activeCases: 970, deaths: 5, hospitalizations: 35}];
  }


 */


  /*

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



*/


}
