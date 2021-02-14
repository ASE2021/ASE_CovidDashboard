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

  public getBorderLayers(eventOptions?: LayerEventControlOptions): Layer[] {
    return this.provinceCoordinates.reduce((layers, curr) =>
      [...layers, ...curr.createBorderMapLayers(eventOptions)], []);
  }


  getColoredLayersFor(provinces: Provinces, color: string): Layer[] {
    return this.getProvinceDatasFor(provinces)
      .createColoredLayers(color);
  }

  public fillWithBezirkeCovid(id: string, data: CovidDataMap[]): void {
    const districts = this.getProvinceDatasFor(id).getDistricts();
    if (districts.some(district => !district.getCovidData())) {
      districts
        .forEach(item => {
            return item.updateCovidInfo(data.find(caseInfo => caseInfo.provinceName === item.provinceName ||
              caseInfo.provinceName === item.provinceName.replace(' Stadt', '(Stadt)') ||
              caseInfo.provinceName === item.provinceName.replace(' Land', '(Land)') ||
              caseInfo.provinceName === item.provinceName + '(Stadt)' ||
              caseInfo.provinceName === item.provinceName.split(' ')[0] + '(Land)' ||
              caseInfo.provinceName === item.provinceName + ' Stadt',
            ));
          },
        );
    }
  }
  public fillWithCovidData(cases: CovidDataMap[]): void {
    console.log(cases);
    this.provinceCoordinates.forEach(item => item.updateCovidInfo(cases.find(caseInfo => caseInfo.geoId === item.provinceId)));
  }

  getBezirkLayersFor(attribution: string, eventOptions?: LayerEventControlOptions): Layer[] {
    return this.getProvinceDatasFor(attribution).getDistricts()
      .reduce((layers, curr) => [...layers, ...curr.createBorderMapLayers(eventOptions)], []);
  }

  hasCovidInformation(attribution: string): boolean {
    return this.getProvinceDatasFor(attribution).hasCovidInformationForDistricts();
  }
}
