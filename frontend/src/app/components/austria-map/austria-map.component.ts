import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Browser, Control, DomUtil, latLng, LeafletMouseEvent, polygon, tileLayer} from 'leaflet';
import {CovidService} from '../../services/covid.service';
import {MapService} from '../../services/map.service';
import {AustrianProvinceData} from '../../model/austrian-province-data';
import {Provinces} from '../../model/Provinces';
import {CovidDataMap} from '../../model/covid-data-map';

@Component({
  selector: 'app-austria-map',
  templateUrl: './austria-map.component.html',
  styleUrls: ['./austria-map.component.scss'],
})
export class AustriaMapComponent implements OnInit {
  options = {
    layers: [
      tileLayer('https://cartodb-basemaps-{s}.global.ssl.fastly.net/light_all/{z}/{x}/{y}.png'),
    ],
    zoom: 7.05,
    minZoom: 7.05,
    doubleClickZoom: false,
    interactive: false,
    zoomControl: false,
    center: latLng(47.6997094, 13.329943),
  };
  layers = [
    polygon([[46.8, -121.55], [46.9, -121.55], [46.9, -121.7], [46.8, -121.7]]),

  ];
  l = [];

  colors = [];
  cases: CovidDataMap[];
  fitBounds: any;

  private mapData: AustrianProvinceData;


  constructor(private covidService: CovidService, private mapService: MapService, private cd: ChangeDetectorRef) {
    this.initializeData();
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
  lcc: any;
  control: Control;
  zoom = 7.4;
  onlyRegions = true;

  // tslint:disable-next-line:typedef
  private async initializeData() {
    await this.loadCovidData();
    this.loadMapData();
  }

  private async loadMapData(): Promise<any> {
    this.mapData = await this.mapService.loadCoordinateData();
    this.mapData.fillWithCovidData(this.cases);
    this.l = this.mapData.getBorderLayers({
      onClick: async ev => {
        this.fitBounds = ev.target.getBounds();
        this.cd.detectChanges();
        setTimeout(() => {
          this.zoom = 9;
          this.cd.detectChanges();
        }, 100);
        console.log(ev.target.options.attribution);
        const bez = await this.covidService.getDetailedInformationForMap(ev.target.options.attribution.split('.')[1].split('_')[0]);
        this.mapData.fillWithBezirkeCovid(ev.target.options.attribution, bez);
        this.layers = [...this.l, ...this.colors, ...this.mapData.getBezirkLayersFor(ev.target.options.attribution,
          {
            onMouseEnter: (e, info) => {
              console.log(e.target.options);
              this.control.getContainer().innerHTML = info;
              this.cd.detectChanges();
            },
          })];
      },
      onMouseEnter: (ev, info) => {
        this.control.getContainer().innerHTML = info;
        this.cd.detectChanges();
      },
    });

    this.layers = [...this.l, ...this.colors];
  }

  highlightFeature(event: any): any {
    const layer = event.target;

    layer.setStyle({
      weight: 4,
      opacity: 1,
      color: 'red',
      fillColor: 'red',
      fillOpacity: 0.2,
    });

    if (!Browser.ie && !Browser.opera && !Browser.edge) {
      layer.bringToFront();
    }
  }

  resetHighlight(event: any): any {
    const layer = event.target;

    layer.setStyle(this.defaultStyle());
  }

  defaultStyle(): any {
    return {
      weight: 4,
      opacity: 1,
      color: 'red',
      fillColor: 'transparent',
    };
  }

  public fill(color: string): void {
    this.colors = [];

    this.colors = this.mapData.getColoredLayersFor(Provinces.Carinthia, color);
    this.layers = [...this.l, ...this.colors];
  }


  public updateCovid(event: any): any {
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


  // tslint:disable-next-line:typedef
  doubleClick(event: LeafletMouseEvent) {
    event.originalEvent.stopPropagation();
  }

  // tslint:disable-next-line:typedef
  onMapReady(map: any) {
    this.control = new Control({position: 'topleft'});

    // tslint:disable-next-line:no-shadowed-variable
    this.control.onAdd = (map: any) => {
      const div = DomUtil.create('div', 'legend');

      div.innerHTML = '<div class="map-info-box">Hover over the regions to see detailed information</div>';
      return div;
    };
    this.control.addTo(map);
  }

  private async loadCovidData(): Promise<any> {
    this.cases = await this.covidService.getSimpleDataForMap();
    console.log(this.cases);
  }

}
