import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OverviewComponent } from './overview.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {CommonModule, DatePipe} from '@angular/common';
import {ChartModule} from 'primeng/chart';
import {CardModule} from 'primeng/card';
import {PanelModule} from 'primeng/panel';
import {SelectButtonModule} from 'primeng/selectbutton';
import {TabViewModule} from 'primeng/tabview';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {DropdownModule} from 'primeng/dropdown';
import {TableModule} from 'primeng/table';
import {TabMenuModule} from 'primeng/tabmenu';
import {RoutingModule} from '../../routing.module';
import {InputTextModule} from 'primeng/inputtext';
import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {TableWithFilterModule} from '../tableWithFilter/table.module';
import {CalendarModule} from 'primeng/calendar';
import {CompareRegionsChartModule} from '../comparison-chart/compare-regions-chart.module';
import {MqttModule} from 'ngx-mqtt';

describe('OverviewComponent', () => {
  let component: OverviewComponent;
  let fixture: ComponentFixture<OverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OverviewComponent ],
      imports: [HttpClientModule, CommonModule,
        ChartModule,
        CardModule,
        PanelModule,
        SelectButtonModule,
        TabViewModule,
        NoopAnimationsModule,
        DropdownModule,
        BrowserAnimationsModule,
        TableModule,
        TabMenuModule,
        RoutingModule,
        InputTextModule,
        MultiSelectModule,
        FormsModule,
        ProgressSpinnerModule,
        TableWithFilterModule,
        CalendarModule,
        MqttModule.forRoot({connectOnCreate: false}),
        CompareRegionsChartModule],
      providers: [HttpClient, DatePipe,
        {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
        {provide: 'BACKEND_NOTIFICATION_URL', useValue: environment.backendNotificationUrl},
        {provide: 'BACKEND_NOTIFICATION_PORT', useValue: environment.backendNotificationPort}]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
