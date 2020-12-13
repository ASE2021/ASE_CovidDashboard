import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {OverviewComponent} from './components/overview/overview.component';
import {AustriaMapComponent} from './components/austria-map/austria-map.component';


export const routerConfig: Routes = [
  {
    path: 'overview',
    component: OverviewComponent
  },
  {
    path: 'map-view',
    component: AustriaMapComponent
  },
  {
    path: '',
    redirectTo: '/overview',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/overview',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routerConfig)
  ]
})
export class RoutingModule { }

