import {ProvinceCoordinates} from './map-coordinate-data';
import {Layer} from 'leaflet';
import {Provinces} from './Provinces';
import {LayerEventControlOptions} from './layer-event-control-options';
import {CovidDataMap} from './covid-data-map';

export class AustrianProvinceData {
  public provinceCoordinates: ProvinceCoordinates[];


  constructor(provinceCoordinates: ProvinceCoordinates[]) {
    this.provinceCoordinates = provinceCoordinates;
  }

  public getProvinceDatasFor(name: string): ProvinceCoordinates {
    return this.provinceCoordinates.find(item => item.provinceId === name);
  }

  public getAllLayers(eventOptions?: LayerEventControlOptions): Layer[] {
    return this.provinceCoordinates.reduce((layers, curr) => [...layers, ...curr.createBorderMapLayers(eventOptions), ...curr.createBezirkeMapLayers(eventOptions)], []);
  }

  public getBorderLayers(eventOptions?: LayerEventControlOptions): Layer[] {
    return this.provinceCoordinates.reduce((layers, curr) => [...layers, ...curr.createBorderMapLayers(eventOptions)], []);
  }

  public getBezirkLayersFor(provinceId: string, eventOptions?: LayerEventControlOptions): Layer[] {
    return this.getProvinceDatasFor(provinceId).createBezirkeMapLayers(eventOptions);
  }
  public getBezirkLayer(eventOptions?: LayerEventControlOptions): Layer[] {
    return this.provinceCoordinates.reduce((layers, curr) => [...layers, ...curr.createBezirkeMapLayers(eventOptions)], []);
  }

  getColoredLayersFor(provinces: Provinces, color: string): Layer[] {
    return this.getProvinceDatasFor(provinces)
      .createColoredLayers(color);
  }

  public fillWithCovidData(cases: CovidDataMap[], casesBezirke: CovidDataMap[] ): void {
    console.log(cases);
    this.provinceCoordinates.forEach(item => item.updateCovidInfo(cases.find(caseInfo => caseInfo.geoId === item.provinceId)));
  }
}
