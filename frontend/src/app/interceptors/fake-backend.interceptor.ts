import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {from, Observable, of} from 'rxjs';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {

  constructor(private http: HttpClient) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if (request.url.endsWith('/provinces')) {
      return of(new HttpResponse({
        body: {
          items: [
            {areaId: '1', areaName: 'Burgenland'},
            {areaId: '2', areaName: 'Kärnten'},
            {areaId: '3', areaName: 'Steiermark'},
            {areaId: '4', areaName: 'Vorarlberg'},
          ],
        },
        status: 200
      }));
    }
    return next.handle(request);
  }
}
