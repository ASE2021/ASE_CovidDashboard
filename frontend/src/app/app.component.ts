import {Component, OnInit} from '@angular/core';
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
