import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AgeSexDistributionChartComponent } from './age-sex-distribution-chart.component';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {DatePipe} from "@angular/common";
import {MqttModule} from "ngx-mqtt";


describe('AgeSexDistributionChartComponent', () => {
  let component: AgeSexDistributionChartComponent;
  let fixture: ComponentFixture<AgeSexDistributionChartComponent>;
  let backendUrl: string;
  let expectedValue = '';
  let radioB: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        MqttModule.forRoot({
          connectOnCreate: false,

        }),
      ],
      declarations: [ AgeSexDistributionChartComponent ],
      providers: [HttpClient, HttpHandler, DatePipe,
        {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
        {provide: 'BACKEND_NOTIFICATION_URL', useValue: environment.backendNotificationUrl},
        {provide: 'BACKEND_NOTIFICATION_PORT', useValue: environment.backendNotificationPort}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgeSexDistributionChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(fixture.nativeElement.querySelector('.p-radiobutton .pradiobutton-box')
      .getAttribute('aria-checked')).toEqual('false');
    expect(component).toBeTruthy();
  });


  /*
  it('show dead cases should be selected after clicking', () => {
    fixture.detectChanges();
    radioB = fixture.nativeElement.querySelector('p-radioButton')[0].getElementById('dead');
    expect(radioB.getAttribute('aria-checked')).toBe('true');
  })

   */

  afterEach(() => {
    expectedValue = "";
  });

});
