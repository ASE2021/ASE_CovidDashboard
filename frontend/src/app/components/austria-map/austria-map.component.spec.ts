import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AustriaMapComponent } from './austria-map.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {CommonModule, DatePipe} from '@angular/common';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {InputNumberModule} from 'primeng/inputnumber';
import {FormsModule} from '@angular/forms';
import {PanelModule} from 'primeng/panel';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MqttService} from "ngx-mqtt";

describe('AustriaMapComponent', () => {
  let component: AustriaMapComponent;
  let fixture: ComponentFixture<AustriaMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AustriaMapComponent ],
      imports: [HttpClientModule,  CommonModule,
        BrowserAnimationsModule,
        LeafletModule,
        InputNumberModule,
        FormsModule,
        PanelModule],
      providers: [HttpClient, MqttService, DatePipe,
        {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl}]

    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AustriaMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
