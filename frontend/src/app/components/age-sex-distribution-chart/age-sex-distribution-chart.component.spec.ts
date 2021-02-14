import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AgeSexDistributionChartComponent } from './age-sex-distribution-chart.component';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpClientModule, HttpHandler} from "@angular/common/http";
import {CommonModule, DatePipe} from "@angular/common";
import {MqttModule} from "ngx-mqtt";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {LeafletModule} from "@asymmetrik/ngx-leaflet";
import {InputNumberModule} from "primeng/inputnumber";
import {FormsModule} from "@angular/forms";
import {PanelModule} from "primeng/panel";
import {RadioButtonModule} from "primeng/radiobutton";


describe('AgeSexDistributionChartComponent', () => {
  let component: AgeSexDistributionChartComponent;
  let fixture: ComponentFixture<AgeSexDistributionChartComponent>;
  let backendUrl: string;
  let expectedValue = '';
  let radioB: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule,  CommonModule,
        BrowserAnimationsModule,
        LeafletModule,
        InputNumberModule,
        FormsModule,
        PanelModule,
        RadioButtonModule,
        MqttModule.forRoot({connectOnCreate: false,})],
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
    console.log('******************' + fixture.nativeElement.innerHTML);
    expect(fixture.nativeElement.querySelector('.p-radiobutton .pradiobutton-box')
      .getAttribute('aria-checked')).toEqual('false');
    expect(component).toBeTruthy();
  });

  /*

  it('show dead cases should be selected after clicking', () => {
    fixture.detectChanges();
    radioB = fixture.nativeElement.querySelector('p-radioButton .p-radiobutton-box')[2];
    expect(radioB.getAttribute('aria-checked')).toBe('true');
  })


   */


  afterEach(() => {
    expectedValue = "";
  });

});
