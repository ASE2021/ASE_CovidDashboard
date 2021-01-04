import {ProvinceCoordinates} from './map-coordinate-data';
import {Layer, polygon} from 'leaflet';
import {Provinces} from './Provinces';
import {LayerEventControlOptions} from './layer-event-control-options';

export class AustrianProvinceData {
  public provinceCoordinates: ProvinceCoordinates[];


  constructor(provinceCoordinates: ProvinceCoordinates[]) {
    this.provinceCoordinates = provinceCoordinates;
  }

  public getProvinceDatasFor(name: string): ProvinceCoordinates {
    return this.provinceCoordinates.find(item => item.provinceId === name);
  }

  public getAllLayers(eventOptions: LayerEventControlOptions): Layer[] {
    return this.provinceCoordinates.reduce((layers, curr) => [...layers, ...curr.createBorderMapLayers(eventOptions), ...curr.createBezirkeMapLayers()], []);
  }

  public getBorderLayers(eventOptions: LayerEventControlOptions): Layer[] {
    return this.provinceCoordinates.reduce((layers, curr) => [...layers, ...curr.createBorderMapLayers(eventOptions)], []);
  }

  public getBezirkLayersFor(provinceId: string): Layer[] {
    return this.getProvinceDatasFor(provinceId).createBezirkeMapLayers();
  }

  getColoredLayersFor(provinces: Provinces, color: string): Layer[] {
    return this.getProvinceDatasFor(provinces)
      .createColoredLayers(color);
  }
}
