import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Control, DomUtil, latLng, LeafletMouseEvent, polygon, tileLayer} from 'leaflet';
import {CovidService} from '../../services/covid.service';
import {MapService} from '../../services/map.service';
import {AustrianProvinceData} from '../../model/austrian-province-data';
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
  borderLayers = [];

  cases: CovidDataMap[];
  fitBounds: any;
  control: Control;
  zoom = 7.4;

  private mapData: AustrianProvinceData;


  constructor(private covidService: CovidService, private mapService: MapService, private cd: ChangeDetectorRef) {
    this.initializeData();
    // this.getJSON().subscribe(data => {
    //   this.data = data;
    //   console.log(data);
    //   data.features.forEach(item => {
    //     this.borderLayers.push(
    //       polygon(item.geometry.coordinates.map(c => c.map(i => [i[1], i[0]])), {weight: 1}),
    //     );
    //   });
    //   this.fill('red');
    //   this.layers = [...this.borderLayers, ...this.colors];
    // });
  }

  ngOnInit(): void {
  }

  private async initializeData(): Promise<void> {
    await this.loadCovidData();
    this.loadMapData();
  }

  private fitAndZoomToProvince(bounds): void {
    this.fitBounds = bounds;
    this.cd.detectChanges();
    setTimeout(() => {
      this.zoom = 9;
      this.cd.detectChanges();
    }, 100);
  }

  private async loadAndFillCovidDestrictData(province: string): Promise<void> {
    if (!this.mapData.hasCovidInformation(province)) {
      this.mapData.fillWithBezirkeCovid(province,
        await this.covidService.getDetailedInformationForMap(province.split('.')[1].split('_')[0]));
    }
  }

  private async loadMapData(): Promise<any> {
    this.mapData = await this.mapService.loadCoordinateData();
    this.mapData.fillWithCovidData(this.cases);
    this.borderLayers = this.mapData.getBorderLayers({
      onClick: async ev => {
        this.fitAndZoomToProvince(ev.target.getBounds());
        await this.loadAndFillCovidDestrictData(ev.target.options.attribution);
        this.layers = [...this.borderLayers,
          ...this.mapData.getBezirkLayersFor(
            ev.target.options.attribution,
            {
              onMouseEnter: (e, info) => {
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

    this.layers = [...this.borderLayers];
  }

  doubleClick(event: LeafletMouseEvent): void {
    event.originalEvent.stopPropagation();
  }

  onMapReady(map: any): void {
    this.control = new Control({position: 'topleft'});

    this.control.onAdd = () => {
      const div = DomUtil.create('div', 'legend');
      div.innerHTML = '<div class="map-info-box">&nbsp;Hover over the regions to see detailed information.&nbsp;</div>';
      return div;
    };
    this.control.addTo(map);
  }

  private async loadCovidData(): Promise<any> {
    this.cases = await this.covidService.getSimpleDataForMap();
  }

}
