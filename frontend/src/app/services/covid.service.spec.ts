import {TestBed} from '@angular/core/testing';

import { CovidService } from './covid.service';
import {HospitalBedsDaily} from "../model/hospital-beds-daily";

describe('CovidService', () => {
  let service: CovidService;
  let httpClientSpy: { get: jasmine.Spy };

  beforeEach(() => {
    TestBed.configureTestingModule({});
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    service = TestBed.inject(CovidService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('#getHospitalBedsPerDate should return expected hospital situations', () => {
    const expectedSituations: HospitalBedsDaily[] = [{date: "10.10.2020", intenseBeds: 12, normalBeds: 10},
      {date: "12.10.2020", intenseBeds: 12, normalBeds: 12}]

    httpClientSpy.get.and.returnValue(expectedSituations);

    service.getHospitalBedsPerDate().then(
      situations => expect(situations).toEqual(expectedSituations, 'expected situations'),
      fail
    );
    expect(httpClientSpy.get.calls.count()).toBe(1, 'one call');
  });
});
