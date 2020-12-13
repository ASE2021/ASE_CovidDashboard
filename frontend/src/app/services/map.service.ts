import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ProvinceCoordinates} from '../model/map-coordinate-data';
import {AustrianProvinceData} from '../model/austrian-province-data';

@Injectable({
  providedIn: 'root',
})
export class MapService {

  constructor(private http: HttpClient) {
  }

  async loadCoordinateData(): Promise<AustrianProvinceData> {

    const jsonData = await this.http.get('/assets/convert.json').toPromise() as any;
    console.log(jsonData);
    const groupedData = jsonData.features.reduce((obj, curr) => {
      if (!obj[curr.properties.NAME_1]) {
        obj[curr.properties.NAME_1] = [curr];
      } else {
        obj[curr.properties.NAME_1].push(curr);
      }
      return obj;
    }, {});
    console.log(groupedData);
    return new AustrianProvinceData(Object.keys(groupedData).map(provinceKey =>
      new ProvinceCoordinates(
        provinceKey,
        groupedData[provinceKey][0].properties.GID_1,
        groupedData[provinceKey].map(item =>
          item.geometry.coordinates.map(c => c.map(i => [i[1], i[0]]))))));
    // jsonData.features.filter(item => item.properties.NAME_1 === 'Kärnten')
    //   .forEach(item => this.colors.push(
    //     polygon(item.geometry.coordinates.map(c => c.map(i => [i[1], i[0]])),
    //       {fill: true, fillColor: 'color', opacity: 0.6}),
    //   ));
  }
}
