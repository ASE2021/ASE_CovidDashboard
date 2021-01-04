import {Inject, Injectable} from '@angular/core';
import {CovidCasesDaily} from '../model/covid-cases-daily';
import {HttpClient} from '@angular/common/http';
import {HospitalBedsDaily} from '../model/hospital-beds-daily';
import {Provinces} from '../model/Provinces';
import {Observable, of} from 'rxjs';


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

  getDataForMap(): Promise<any> {
    //  return this.http.get(this.apiUrl + '/overview').toPromise();
    return of([
      {id: '1', name: 'Burgenland', active: 123, deaths: 12},
      {id: '2', name: 'Kärnten', active: 1412, deaths: 12},
      {id: '3', name: 'Niederösterreich', active: 342, deaths: 12},
      {id: '4', name: 'Oberösterreich', active: 6434, deaths: 12},
      {id: '5', name: 'Salzburg', active: 2433, deaths: 12},
      {id: '6', name: 'Steiermark', active: 1223, deaths: 12},
      {id: '7', name: 'Tirol', active: 3123, deaths: 12},
      {id: '8', name: 'Vorarlberg', active: 973, deaths: 12},
      {id: '9', name: 'Wien', active: 7123, deaths: 12}
      ]
    ).toPromise().then(result => result.map(item => ({...item, geoId: `AUT.${item.id}_1`})));
  }
}
