import {Injectable} from '@angular/core';
import {CovidCasesDaily} from '../model/covid-cases-daily';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CovidService {

  constructor(private http: HttpClient) {
  }

  public getNewCasesPerDate(): Promise<CovidCasesDaily[]> {
    return this.http.get('/covid-cases-daily/10')
      .toPromise().then(item => item as CovidCasesDaily[]);
  }



}
