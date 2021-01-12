import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {

  constructor(private http: HttpClient) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if (request.url.endsWith('/daily/hospital/10')) {
      return of(new HttpResponse({
        body: {
          situations: [{date: '10.10.2020', intenseBeds: 12, normalBeds: 10},
            {date: '12.10.2020', intenseBeds: 12, normalBeds: 12}],
        },
        status: 200,
      }));
    }
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
    if (request.url.endsWith('/districts')) {
      return of(new HttpResponse({
        body: {
          items: [
            {areaId: 11, areaName: 'B1'},
            {areaId: 12, areaName: 'B2'},
            {areaId: 21, areaName: 'K1'},
            {areaId: 22, areaName: 'K2'},
            {areaId: 31, areaName: 'ST1'},
            {areaId: 32, areaName: 'ST2'},
            {areaId: 41, areaName: 'V1'},
            {areaId: 42, areaName: 'V2'},
            {areaId: 51, areaName: 'T1'},
            {areaId: 52, areaName: 'T2'},
            {areaId: 61, areaName: 'W1'},
            {areaId: 62, areaName: 'W2'},
            {areaId: 71, areaName: 'O1'},
            {areaId: 72, areaName: 'O2'},
            {areaId: 81, areaName: 'N1'},
            {areaId: 82, areaName: 'N2'},
            {areaId: 91, areaName: 'SA1'},
            {areaId: 92, areaName: 'SA2'},
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
                      value: (100 - parseInt(area, 10) + 2000 + idx) * (0.95 + (Math.random() / 10)),
                    },
                    {
                      identifier: 'deaths',
                      value: parseInt(area, 10) * idx * Math.random() * 10,
                    },
                    {
                      identifier: 'tests',
                      value: (100 - parseInt(area, 10)) * idx * Math.random() * 10,
                    },
                    {
                      identifier: 'intenseBeds',
                      value: (100 - parseInt(area, 10) + Math.abs(50 - Math.abs(50 - (idx / (3))))) * (0.95 + (Math.random() / 10)),
                    },
                    {
                      identifier: 'normalBeds',
                      value: (100 - parseInt(area, 10) + Math.abs(150 - Math.abs(50 - (idx / (1.3))))) * (0.95 + (Math.random() / 10)),
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
