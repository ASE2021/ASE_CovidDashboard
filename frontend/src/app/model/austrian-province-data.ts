import {ProvinceCoordinates} from './map-coordinate-data';
import {Layer} from 'leaflet';
import {LayerEventControlOptions} from './layer-event-control-options';
import {CovidDataMap} from './covid-data-map';

export class AustrianProvinceData {
  public provinceCoordinates: ProvinceCoordinates[];


  constructor(provinceCoordinates: ProvinceCoordinates[]) {
    this.provinceCoordinates = provinceCoordinates;
  }

  public getProvinceDataFor(name: string): ProvinceCoordinates {
    return this.provinceCoordinates.find(item => item.provinceId === name);
  }

  public getBorderLayers(eventOptions?: LayerEventControlOptions): Layer[] {
    return this.provinceCoordinates.reduce((layers, curr) =>
      [...layers, ...curr.createBorderMapLayers(eventOptions)], []);
  }

  public fillProvinceWithDistrictCovidData(id: string, data: CovidDataMap[]): void {
    const districts = this.getProvinceDataFor(id).getDistricts();
    if (districts.some(district => !district.getCovidData())) {
      districts
        .forEach(item => {
            console.log(item);
            console.log(data);
            return item.updateCovidInfo(data.find(caseInfo => caseInfo.provinceName === item.provinceName ||
              caseInfo.provinceName === item.provinceName.replace(' Stadt', '(Stadt)') ||
              caseInfo.provinceName === item.provinceName.replace(' Land', '(Land)') ||
              caseInfo.provinceName === item.provinceName + '(Stadt)' ||
              caseInfo.provinceName === item.provinceName.split(' ')[0] + '(Land)' ||
              caseInfo.provinceName === item.provinceName + ' Stadt' ||
              caseInfo.provinceName === item.provinceName + '-Stadt' ||
              caseInfo.provinceName === item.provinceName.replace(' ', '-'),
            ));
          },
        );
    }
  }

  public fillProvincesWithCovidData(cases: CovidDataMap[]): void {
    this.provinceCoordinates.forEach(item => item.updateCovidInfo(
      cases.find(caseInfo => caseInfo.geoId === item.provinceId)));
  }

  getDestrictLayersFor(attribution: string, eventOptions?: LayerEventControlOptions): Layer[] {
    return this.getProvinceDataFor(attribution).getDistricts()
      .reduce((layers, curr) => [...layers, ...curr.createBorderMapLayers(eventOptions)], []);
  }

  hasCovidInformation(attribution: string): boolean {
    return this.getProvinceDataFor(attribution).hasCovidInformationForDistricts();
  }
}
