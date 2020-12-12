import {Layer, polygon} from 'leaflet';

export class ProvinceCoordinates {
  public provinceName: string;
  public provinceId: string;
  public coordinates: number[][][];

  constructor(provinceName: string, provinceId: string, coordinates: number[][][]) {
    this.provinceName = provinceName;
    this.provinceId = provinceId;
    this.coordinates = coordinates;
  }

  createMapLayers(): Layer[] {
    return this.coordinates.map(item => polygon(item.map(c => c.map(i => [i[0], i[1]])), {weight: 1, opacity: 0.7}));
  }

  createColoredLayers(color: string): Layer[] {
    return this.coordinates.map(item => polygon(
      item.map(c => c.map(i => [i[0], i[1]])),
      {fill: true, fillColor: color, opacity: 0.6, weight: 0}));
  }
}

