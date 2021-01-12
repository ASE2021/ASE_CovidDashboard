import {TestBed} from '@angular/core/testing';

import {CovidService} from './covid.service';
import {HospitalBedsDaily} from '../model/hospital-beds-daily';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import {ChartModule} from 'primeng/chart';
import {AustriaMapModule} from '../components/austria-map/austria-map.module';
import {TabMenuModule} from 'primeng/tabmenu';
import {OverviewModule} from '../components/overview/overview.module';
import {RoutingModule} from '../routing.module';
import {InputTextModule} from 'primeng/inputtext';
import {TableModule} from 'primeng/table';
import {CalendarModule} from 'primeng/calendar';
import {SliderModule} from 'primeng/slider';
import {DialogModule} from 'primeng/dialog';
import {MultiSelectModule} from 'primeng/multiselect';
import {ContextMenuModule} from 'primeng/contextmenu';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {ToastModule} from 'primeng/toast';
import {TableWithFilterModule} from '../components/tableWithFilter/table.module';
import {ProgressBarModule} from 'primeng/progressbar';
import {MqttModule} from 'ngx-mqtt';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FakeBackendInterceptor} from '../interceptors/fake-backend.interceptor';

describe('CovidService', () => {
  let service: CovidService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        BrowserModule, BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ChartModule,
        AustriaMapModule,
        TabMenuModule,
        OverviewModule,
        RoutingModule,
        InputTextModule,
        TableModule,
        CalendarModule,
        SliderModule,
        DialogModule,
        MultiSelectModule,
        ContextMenuModule,
        DropdownModule,
        ButtonModule,
        ToastModule,
        TableWithFilterModule,
        ProgressBarModule,
        MqttModule.forRoot({
          connectOnCreate: false,

        }),
        HttpClientTestingModule,
      ],
      providers: [HttpClient,
        {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
        {
          provide: HTTP_INTERCEPTORS,
          useClass: FakeBackendInterceptor,
          multi: true,
        }],
    });
    service = TestBed.inject(CovidService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('#getHospitalBedsPerDate should return expected hospital situations', () => {
    const expectedSituations: HospitalBedsDaily[] = [{date: '10.10.2020', intenseBeds: 12, normalBeds: 10},
      {date: '12.10.2020', intenseBeds: 12, normalBeds: 12}];

    service.getHospitalBedsPerDate().then(
      situations => {
        expect(situations).toEqual(expectedSituations, 'expected situations');
      },
      fail,
    );
  });

  it('getColorsFromMatrixAt returns correct values', () => {
    const colorMatrix = [[
      '#F5A9A9', '#FE2E2E', '#B40404', '#8A0808',
      '#8A4B08', '#FF8000', '#FA8258', '#F5DA81',
      '#F6D8CE', '#F6CECE'],
      [
        '#BCF5A9', '#80FF00', '#688A08', '#21610B',
        '#088A29', '#01DF3A', '#2EFE64', '#0B3B0B', '#81F79F',
        '#088A68'],
      [
        '#A9BCF5', '#2E9AFE', '#084B8A', '#5858FA',
        '#0101DF', '#210B61', '#3A01DF',
        '#8000FF', '#AC58FA', '#BCA9F5',
      ], [
        '#FF8000', '#FAAC58', '#F5D0A9', '#8A4B08',
        '#3B240B', '#61380B', '#F7BE81', '#F7BE81',
        '#F5BCA9',
      ], [
        '#A9F5F2', '#E0F8F7', '#58FAF4', '#01DFD7',
        '#0B615E', '#0A2A29', '#045FB4', '#81DAF5',
        '#00BFFF',
      ], [
        '#F6CEF5', '#F781F3', '#FE2EF7', '#B404AE',
        '#610B5E', '#B40486', '#FF00BF', '#F781D8',
        '#E2A9F3',
      ]];

    expect(service.getColorsFromMatrixAt(0, 0)).toBe(colorMatrix[0][0]);
  });
});
