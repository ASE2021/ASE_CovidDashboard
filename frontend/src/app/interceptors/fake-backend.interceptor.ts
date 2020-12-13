import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {from, Observable} from 'rxjs';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {

  constructor(private http: HttpClient) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.url.endsWith('/covid-cases-daily/10')) {
      return from(this.http.get('/assets/covid.json')
        .toPromise()
        .then(
          (result: any) =>
            new HttpResponse({
              status: 200,
              body: result.CovidFaelle_Timeline.filter(item => item.BundeslandID === 10)
                .map(item => ({date: item.Time.split('T')[0], numberOfCases: item.AnzahlFaelle})),
            })));
    }
    return next.handle(request);
  }
}
