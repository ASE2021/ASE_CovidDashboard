import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {ChartModule} from 'primeng/chart';
import {AustriaMapModule} from './components/austria-map/austria-map.module';
import {TabMenuModule} from 'primeng/tabmenu';
import {OverviewModule} from './components/overview/overview.module';
import {RoutingModule} from './routing.module';
import {environment} from '../environments/environment';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TableModule} from 'primeng/table';
import {ToastModule} from 'primeng/toast';
import {CalendarModule} from 'primeng/calendar';
import {SliderModule} from 'primeng/slider';
import {MultiSelectModule} from 'primeng/multiselect';
import {ContextMenuModule} from 'primeng/contextmenu';
import {DialogModule} from 'primeng/dialog';
import {ButtonModule} from 'primeng/button';
import {DropdownModule} from 'primeng/dropdown';
import {RadioButtonModule} from 'primeng/radiobutton';
import {ProgressBarModule} from 'primeng/progressbar';
import {InputTextModule} from 'primeng/inputtext';
import {MqttModule} from 'ngx-mqtt';
import {TableWithFilterModule} from './components/tableWithFilter/table.module';
import {FakeBackendInterceptor} from './interceptors/fake-backend.interceptor';
import {DatePipe} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
  ],
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
    RadioButtonModule,
    MqttModule.forRoot({
      connectOnCreate: false,

    }),

  ],
  providers: [HttpClient,
    {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
    {provide: 'BACKEND_NOTIFICATION_URL', useValue: environment.backendNotificationUrl},
    {provide: 'BACKEND_NOTIFICATION_PORT', useValue: environment.backendNotificationPort},
    DatePipe
  ],
  bootstrap: [AppComponent],
})
export class AppModule {


}
