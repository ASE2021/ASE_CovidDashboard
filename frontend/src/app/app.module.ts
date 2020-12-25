import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {ChartModule} from 'primeng/chart';
import {FakeBackendInterceptor} from './interceptors/fake-backend.interceptor';
import {AustriaMapModule} from './components/austria-map/austria-map.module';
import {TabMenuModule} from 'primeng/tabmenu';
import {OverviewModule} from './components/overview/overview.module';
import {RoutingModule} from './routing.module';
import {environment} from '../environments/environment';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputTextModule} from 'primeng/inputtext';


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
    OverviewModule, RoutingModule,
    InputTextModule,

  ],
  providers: [HttpClient,
    {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
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
