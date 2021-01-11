import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {

  constructor(private http: HttpClient) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if (request.url.endsWith('/provinces')) {
      return of(new HttpResponse({
        body: {
          items: [
            {areaId: 1, areaName: 'Burgenland'},
            {areaId: 2, areaName: 'Kärnten'},
            {areaId: 3, areaName: 'Steiermark'},
            {areaId: 4, areaName: 'Vorarlberg'},
            {areaId: 5, areaName: 'Tirol'},
            {areaId: 6, areaName: 'Wien'},
            {areaId: 7, areaName: 'Oberösterreich'},
            {areaId: 8, areaName: 'Niederösterreich'},
            {areaId: 9, areaName: 'Salzburg'},
          ],
        },
        status: 200,
      }));
    }
    if (request.url.includes('/daily/cases')) {

      return of(new HttpResponse({
        body: {
          items: request.params.getAll('area-id').map(area =>
            ({
              areaId: area,
              areaName: 'A-' + area,
              data: getDatesBetweenDates(new Date(2020, 1, 1), new Date()).map((date, idx) =>
                ({
                  date: date.toLocaleString()
                  ,
                  values: [
                    {
                      identifier: 'activeCases',
                      value: parseInt(area, 10) + idx * (0.5 + Math.random()),
                    },
                    {
                      identifier: 'newCases',
                      value: (20 * parseInt(area, 10) + 2000 + idx) * (0.95 + (Math.random() / 10)),
                    },
                    {
                      identifier: 'deaths',
                      value: parseInt(area, 10) * idx * Math.random() * 10,
                    },
                    {
                      identifier: 'tests',
                      value: (6 + parseInt(area, 10)) * idx * Math.random() * 10,
                    },
                  ],
                })),
            })),
        },
        status: 200,
      }));
    }
    return next.handle(request);
  }
}

const getDatesBetweenDates = (startDate, endDate) => {
  let dates = [];
  const theDate = new Date(startDate);
  while (theDate < endDate) {
    dates = [...dates, new Date(theDate)];
    theDate.setDate(theDate.getDate() + 1);
  }
  return dates;
};
