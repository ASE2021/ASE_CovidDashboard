import {Inject, Injectable} from '@angular/core';
import {CovidCasesDaily} from '../model/covid-cases-daily';
import {HttpClient} from '@angular/common/http';
import { CovidDeathsDaily} from "../model/covid-deaths-daily";
import {CovidHospitalizationsDaily} from "../model/covid-hospitalizations-daily";
import {Provinces} from "../model/Provinces";
import {TableDataDaily} from "../model/tableDataDaily";

@Injectable({
  providedIn: 'root',
})
export class CovidService {

  constructor(@Inject('BACKEND_API_URL') private apiUrl: string, private http: HttpClient) {
  }

  public getNewCasesPerDate(): Promise<CovidCasesDaily[]> {
    return this.http.get(this.apiUrl + '/daily/10')
      .toPromise().then(item => (item as {cases: CovidCasesDaily[]}).cases);
  }

  public getDeathsPerDate(): Promise<CovidDeathsDaily[]> {
    return this.http.get(this.apiUrl + '/daily/deaths/5')
      .toPromise().then(item => (item as {deaths: CovidDeathsDaily[]}).deaths);
  }

  public getHospitalizationsPerDate(): Promise<CovidHospitalizationsDaily[]> {
    return this.http.get(this.apiUrl + '/daily/hospitalizations/100')
      .toPromise().then(item => (item as {hospitalizations: CovidHospitalizationsDaily[]}).hospitalizations);
  }

  public getTableDataPerDate(): Promise<TableDataDaily[]> {
    return this.http.get(this.apiUrl + '/daily/tableData/100')
      .toPromise().then(item => (item as {data: TableDataDaily[]}).data);
  }

  getProvinces() {
    return this.http.get<any>(this.apiUrl + '/provinces')
      .toPromise()
      .then(res => <Provinces[]>res.data)
      .then(data => { return data; });
  }




}
