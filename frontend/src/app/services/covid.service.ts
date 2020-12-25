import {Inject, Injectable} from '@angular/core';
import {CovidCasesDaily} from '../model/covid-cases-daily';
import {HttpClient} from '@angular/common/http';
import {HospitalBedsDaily} from "../model/hospital-beds-daily";

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

  public getHospitalIntenseBedsPerDate(): Promise<HospitalBedsDaily[]> {
    return this.http.get(this.apiUrl + '/hospital/intense/10')
      .toPromise().then(item => (item as {intenseBeds: HospitalBedsDaily[]}).intenseBeds)
  }

  public getHospitalNormalBedsPerDate(): Promise<HospitalBedsDaily[]> {
    return this.http.get(this.apiUrl + '/hospital/normal/10')
      .toPromise().then(item => (item as {normalBeds: HospitalBedsDaily[]}).normalBeds)
  }

}
