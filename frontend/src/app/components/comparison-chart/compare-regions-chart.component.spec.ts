import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CompareRegionsChartComponent} from './compare-regions-chart.component';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {MqttModule} from 'ngx-mqtt';
import {CommonModule, DatePipe} from '@angular/common';
import {ChartModule} from 'primeng/chart';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {ListboxModule} from 'primeng/listbox';
import {ButtonModule} from 'primeng/button';
import {SelectButtonModule} from 'primeng/selectbutton';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputSwitchModule} from 'primeng/inputswitch';
import {TreeTableModule} from 'primeng/treetable';
import {RippleModule} from 'primeng/ripple';
import {CheckboxModule} from 'primeng/checkbox';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {InputTextModule} from 'primeng/inputtext';

describe('ComparisonChartComponent', () => {
  let component: CompareRegionsChartComponent;
  let fixture: ComponentFixture<CompareRegionsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CompareRegionsChartComponent],
      imports: [HttpClientModule, MqttModule.forRoot({
        connectOnCreate: false,
      }), CommonModule,
        ChartModule,
        ProgressSpinnerModule,
        MultiSelectModule,
        FormsModule,
        OverlayPanelModule,
        ListboxModule,
        ButtonModule,
        SelectButtonModule,
        BrowserAnimationsModule,
        InputSwitchModule,
        SelectButtonModule,
        TreeTableModule,
        RippleModule,
        CheckboxModule,
        AutoCompleteModule,
        InputTextModule
      ],
      providers: [HttpClient, DatePipe,
        {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
        {provide: 'BACKEND_NOTIFICATION_URL', useValue: environment.backendNotificationUrl},
        {provide: 'BACKEND_NOTIFICATION_PORT', useValue: environment.backendNotificationPort}],
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompareRegionsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
