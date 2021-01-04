import {Browser, LatLng, Layer, polygon} from 'leaflet';
import {LayerEventControlOptions} from './layer-event-control-options';

export class ProvinceCoordinates {
  public provinceName: string;
  public provinceId: string;
  public coordinates: number[][][];
  public border: number[][][];

  constructor(provinceName: string, provinceId: string, coordinates: number[][][], border: [][]) {
    this.provinceName = provinceName;
    this.provinceId = provinceId;
    this.coordinates = coordinates;
    this.border = border || [];
  }

  createBezirkeMapLayers(): Layer[] {
    return this.coordinates.map(item => polygon(item.map(c => c.map(i => [i[0], i[1]])), {weight: 1, opacity: 0.7}));
  }

  createBorderMapLayers(eventOptions: LayerEventControlOptions): Layer[] {
    console.log(this.border);
    console.log(this.provinceName);
    return this.border.map(region => polygon(region.map(item => new LatLng(Number(item[0]) > Number(item[1]) ? Number(item[0]) : Number(item[1]), Number(item[0]) > Number(item[1]) ? Number(item[1]) : Number(item[0]))),
      {
        attribution: this.provinceId, ...this.defaultStyle(),
      })
      .on('click', eventOptions.onClick || (event => console.log(event)))
      .on('mouseover', eventOptions.onMouseEnter || (event => this.highlightFeature(event)))
      .on('mouseout', eventOptions.onMouseOut || (event => this.resetHighlight(event))));
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

  createColoredLayers(color: string): Layer[] {
    return this.coordinates.map(item => polygon(
      item.map(c => c.map(i => [i[0], i[1]])),
      {fill: true, fillColor: color, opacity: 0.6, weight: 0}));
  }
}

