import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HospitalUtilizationComponent } from './hospital-utilization.component';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {DatePipe} from "@angular/common";
import {MqttModule, MqttService} from "ngx-mqtt";

describe('HospitalUtilizationComponent', () => {
  let component: HospitalUtilizationComponent;
  let fixture: ComponentFixture<HospitalUtilizationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        MqttModule.forRoot({
          connectOnCreate: false,

        }),
      ],
      declarations: [ HospitalUtilizationComponent ],
      providers: [HttpClient, HttpHandler, DatePipe,
        {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
        {provide: 'BACKEND_NOTIFICATION_URL', useValue: environment.backendNotificationUrl},
        {provide: 'BACKEND_NOTIFICATION_PORT', useValue: environment.backendNotificationPort}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HospitalUtilizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
