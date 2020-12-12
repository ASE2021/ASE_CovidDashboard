import {Component, OnInit} from '@angular/core';
import {latLng, polygon, tileLayer} from 'leaflet';
import {Observable} from 'rxjs';
import {ChartModelBuilder} from './model/chart-model-builder';
import {CovidService} from './services/covid.service';
import {MenuItem} from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {

  menuItems: MenuItem[];
  constructor() {

  }

  public ngOnInit(): void {
    this.menuItems = [
      {label: 'Overview', routerLink: '/overview'},
      {label: 'Map view', routerLink: '/map-view'},
    ];
  }
}
