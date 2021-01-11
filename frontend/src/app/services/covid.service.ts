import {Inject, Injectable} from '@angular/core';
import {CovidCasesDaily} from '../model/covid-cases-daily';
import {HttpClient} from '@angular/common/http';
import {HospitalBedsDaily} from '../model/hospital-beds-daily';
import {Provinces} from '../model/Provinces';
import {Observable, of} from 'rxjs';
import {CovidDataMap} from "../model/covid-data-map";


@Injectable({
  providedIn: 'root',
})
export class CovidService {

  constructor(@Inject('BACKEND_API_URL') private apiUrl: string, private http: HttpClient) {
  }

  public getNewCasesPerDate(): Promise<CovidCasesDaily[]> {
    return this.http.get(this.apiUrl + '/daily/10')
      .toPromise().then(item => (item as { cases: CovidCasesDaily[] }).cases);
  }

  public getHospitalBedsPerDate(): Promise<HospitalBedsDaily[]> {
    return this.http.get(this.apiUrl + '/daily/hospital/10')
      .toPromise().then(item => (item as { situations: HospitalBedsDaily[] }).situations);
  }


  public getProvinces(): Promise<Provinces> {
    return this.http.get<any>(this.apiUrl + '/provinces')
      .toPromise()
      .then(res => (res as { data: Provinces }).data)
      .then(data => {
        return data;
      });
  }

  getSimpleDataForMap(): Promise<CovidDataMap[]> {
    //  return this.http.get(this.apiUrl + '/overview').toPromise();
    return of([
        {activeCases: 1220, casesPer100Thousand: 70, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '1', provinceName: 'Burgenland'},
        {activeCases: 1220, casesPer100Thousand: 200, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '2', provinceName: 'Kärnten'},
        {activeCases: 1220, casesPer100Thousand: 250, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '3', provinceName: 'Niederösterreich'},
        {activeCases: 1220, casesPer100Thousand: 160, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '4', provinceName: 'Oberösterreich'},
        {activeCases: 1220, casesPer100Thousand: 300, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '5', provinceName: 'Salzburg'},
        {activeCases: 1220, casesPer100Thousand: 234, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '6', provinceName: 'Steiermark'},
        {activeCases: 1220, casesPer100Thousand: 142, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '7', provinceName: 'Tirol'},
        {activeCases: 1220, casesPer100Thousand: 463, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '8', provinceName: 'Vorarlberg'},
        {activeCases: 1220, casesPer100Thousand: 463, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '9', provinceName: 'Wien'}
      ] as CovidDataMap[]
    ).toPromise().then(result => result.map(item => ({...item, geoId: `AUT.${item.provinceId}_1`})));
  }
  getDetailedDataForMap(): Promise<CovidDataMap[]> { // TODO: I dont want to fake data for all "Bezirke"
    //  return this.http.get(this.apiUrl + '/overview').toPromise();
    return of([
        {activeCases: 1220, casesPer100Thousand: 70, deaths: 300, intensiveBeds: 20, normalBeds: 30, provinceId: '1', provinceName: 'Burgenland'},
      ] as CovidDataMap[]
    ).toPromise().then(result => result.map(item => ({...item, geoId: `AUT.${item.provinceId}_1`})));
  }
}
