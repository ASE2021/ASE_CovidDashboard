import {ProvinceCoordinates} from './map-coordinate-data';
import {Layer, polygon} from 'leaflet';
import {Provinces} from './Provinces';

export class AustrianProvinceData {
  public provinceCoordinates: ProvinceCoordinates[];


  constructor(provinceCoordinates: ProvinceCoordinates[]) {
    this.provinceCoordinates = provinceCoordinates;
  }

  public getProvinceDatasFor(name: string): ProvinceCoordinates {
    return this.provinceCoordinates.find(item => item.provinceName === name);
  }

  public getAllLayers(): Layer[] {
    return this.provinceCoordinates.reduce((layers, curr) => [...layers, ...curr.createMapLayers()], []);
  }

  getColoredLayersFor(provinces: Provinces, color: string): Layer[] {
    return this.getProvinceDatasFor(provinces)
      .createColoredLayers(color);
  }
}
