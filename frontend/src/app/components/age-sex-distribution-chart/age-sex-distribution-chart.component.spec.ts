import {ComponentFixture, TestBed, tick} from '@angular/core/testing';

import {AgeSexDistributionChartComponent } from './age-sex-distribution-chart.component';
import {By} from "@angular/platform-browser";
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {DatePipe} from "@angular/common";
import {MqttModule, MqttService} from "ngx-mqtt";


describe('AgeSexDistributionChartComponent', () => {
  let component: AgeSexDistributionChartComponent;
  let fixture: ComponentFixture<AgeSexDistributionChartComponent>;
  let backendUrl: string;
  let expectedValue = '';

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
    expect(component).toBeTruthy();
  });

  afterEach(() => {
    expectedValue = "";
  });

  it('#selectionChanged() should toggle #selectedValue', () => {
    expect(component.selectedValue).toBe('cases', 'cases at first');
    const de = fixture.debugElement.query(By.css("#selectedValue"));
    const el = de.nativeElement;
    fixture.detectChanges();

    el.value = "cured cases";

    var event = new Event('input', {
      'bubbles': true,
      'cancelable': true
    });
    el.dispatchEvent(event);

    tick();

    fixture.detectChanges();

    expect(component.selectedValue).toEqual("cured cases");
  });
});
