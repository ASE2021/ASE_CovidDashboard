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
    const borders = await this.http.get('/assets/regions.json').toPromise() as any;
    const groupedData = jsonData.features.reduce((obj, curr) => {
      if (!obj[curr.properties.NAME_1]) {
        obj[curr.properties.NAME_1] = [curr];
      } else {
        obj[curr.properties.NAME_1].push(curr);
      }
      return obj;
    }, {});
    return new AustrianProvinceData(Object.keys(groupedData).map(provinceKey =>
      new ProvinceCoordinates(
        provinceKey,
        groupedData[provinceKey][0].properties.GID_1,
        borders[groupedData[provinceKey][0].properties.GID_1],
        Object.entries(groupedData[provinceKey].reduce((obj, curr) => {
          if (!obj[curr.properties.GID_2]) {
            obj[curr.properties.GID_2] = [curr];
          } else {
            obj[curr.properties.GID_2].push(curr);
          }
          return obj;
        }, {})).map((item: [string, any[]]) => {
          console.log(item);
          return new ProvinceCoordinates(item[1][0].properties.NAME_2,
            item[0],
            item[1].reduce((coords, curr) => [...coords, ...curr.geometry.coordinates], []));
        }))));
    // jsonData.features.filter(item => item.properties.NAME_1 === 'Kärnten')
    //   .forEach(item => this.colors.push(
    //     polygon(item.geometry.coordinates.map(c => c.map(i => [i[1], i[0]])),
    //       {fill: true, fillColor: 'color', opacity: 0.6}),
    //   ));
  }
}
