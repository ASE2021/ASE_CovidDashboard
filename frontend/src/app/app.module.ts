import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {ChartModule} from 'primeng/chart';
import {AustriaMapModule} from './components/austria-map/austria-map.module';
import {TabMenuModule} from 'primeng/tabmenu';
import {OverviewModule} from './components/overview/overview.module';
import {RoutingModule} from './routing.module';
import {environment} from '../environments/environment';
import {MqttModule} from 'ngx-mqtt';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ChartModule,
    AustriaMapModule,
    TabMenuModule,
    OverviewModule, RoutingModule,
    MqttModule.forRoot({
      connectOnCreate: false,

    }),
  ],
  providers: [HttpClient,
    {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
    {provide: 'BACKEND_NOTIFICATION_URL', useValue: environment.backendNotificationUrl},
    {provide: 'BACKEND_NOTIFICATION_PORT', useValue: environment.backendNotificationPort}
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: FakeBackendInterceptor,
    //   multi: true,
    // }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
