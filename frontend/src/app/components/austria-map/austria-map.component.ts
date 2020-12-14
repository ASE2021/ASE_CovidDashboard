import {Component, OnInit} from '@angular/core';
import {latLng, polygon, tileLayer} from 'leaflet';
import {CovidService} from '../../services/covid.service';
import {MapService} from '../../services/map.service';
import {AustrianProvinceData} from '../../model/austrian-province-data';
import {Provinces} from '../../model/Provinces';

@Component({
  selector: 'app-austria-map',
  templateUrl: './austria-map.component.html',
  styleUrls: ['./austria-map.component.scss'],
})
export class AustriaMapComponent implements OnInit {
  options = {
    layers: [
      tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'),
    ],
    zoom: 7.3,
    center: latLng(47.5997094, 14.129943),
  };
  layers = [
    polygon([[46.8, -121.55], [46.9, -121.55], [46.9, -121.7], [46.8, -121.7]]),

  ];
  l = [];

  colors = [];
  cases: number;

  private mapData: AustrianProvinceData;


  constructor(private covidService: CovidService, private mapService: MapService) {
    this.loadMapData();
    // this.getJSON().subscribe(data => {
    //   this.data = data;
    //   console.log(data);
    //   data.features.forEach(item => {
    //     this.l.push(
    //       polygon(item.geometry.coordinates.map(c => c.map(i => [i[1], i[0]])), {weight: 1}),
    //     );
    //   });
    //   this.fill('red');
    //   this.layers = [...this.l, ...this.colors];
    // });
  }

  // tslint:disable-next-line:typedef
  private async loadMapData() {
    this.mapData = await this.mapService.loadCoordinateData();
    this.l = this.mapData.getAllLayers();
    this.layers = [...this.l, ...this.colors];
  }


  public fill(color: string): void {
    this.colors = [];

    this.colors = this.mapData.getColoredLayersFor(Provinces.Carinthia, color);
    this.layers = [...this.l, ...this.colors];
  }

  // tslint:disable-next-line:typedef
  public updateCovid(event: any) {
    console.log(event.value);

    if (event.value < 300) {
      this.fill('green');
    } else if (event.value < 500) {
      this.fill('orange');
    } else {
      this.fill('red');
    }
  }

  ngOnInit(): void {
  }


}
