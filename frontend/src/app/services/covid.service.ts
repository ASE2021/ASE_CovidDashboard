import {Inject, Injectable} from '@angular/core';
import {CovidCasesDaily} from '../model/covid-cases-daily';
import {HttpClient} from '@angular/common/http';
import {SexDistribution} from '../model/sex-distribution';
import {HospitalBedsDaily} from '../model/hospital-beds-daily';
import {GeneralSituationDaily} from '../model/general-situation-daily';
import {Area} from '../model/area';
import {AreaResponse} from '../model/area-response';


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


  public getSexDistribution(): Promise<SexDistribution[]> {
    return this.http.get(this.apiUrl + '/distribution/sex/10')
      .toPromise().then(item => (item as { cases: SexDistribution[] }).cases);
  }

  public getGeneralSituationPerDate(): Promise<GeneralSituationDaily[]> {
    return this.http.get(this.apiUrl + '/daily/generalsituation/10')
      .toPromise().then(item => (item as { situations: GeneralSituationDaily[] }).situations);
  }

  public getProvinces(): Promise<Area[]> {
    return this.http.get<any>(this.apiUrl + '/provinces')
      .toPromise()
      .then(res => (res as { items: Area[] }).items);
  }

  async getInfosForAndMap(data: any, regions: Area[]): Promise<any> {
    const array = regions.filter(item => !data[item.areaId]);
    console.log(array);
    console.log(data);
    console.log(regions);

    let newData = {};
    if (array.length >= 1) {
      newData = this.mapResponseDataToObject(await this.http.get<any>(this.apiUrl + '/daily/cases',
        {params: {'area-id': array.map(item => item.areaId.toString())}})
        .toPromise()
        .then(res => (res as { items: AreaResponse[] }).items));
    }

    return {...data, ...newData};

  }

  private mapResponseDataToObject(data: AreaResponse[]): any {
    const obj = {dates: []};
    data.forEach((item, idx) => {
      obj[item.areaId] = item.data.reduce((d, c) => {
        c.values.forEach(e => d[e.identifier] = [...d[e.identifier] || [], e.value]);
        if (idx === 0) {
          obj.dates.push(c.date);
        }
        return d;
      }, {});
    });
    return obj;
  }

}
