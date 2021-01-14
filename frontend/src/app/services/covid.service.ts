import {Inject, Injectable} from '@angular/core';
import {CovidCasesDaily} from '../model/covid-cases-daily';
import {HttpClient} from '@angular/common/http';
import {SexDistribution} from '../model/sex-distribution';
import {HospitalBedsDaily} from '../model/hospital-beds-daily';
import {GeneralSituationDaily} from '../model/general-situation-daily';
import {Area} from '../model/area';
import {AreaResponse} from '../model/area-response';
import {TreeNode} from 'primeng/api';


@Injectable({
  providedIn: 'root',
})
export class CovidService {

  constructor(@Inject('BACKEND_API_URL') private apiUrl: string, private http: HttpClient) {
  }

  public getNewCasesPerDate(): Promise<CovidCasesDaily[]> {
    return this.http.get(this.apiUrl + '/daily/10')
      .toPromise().then(item => (item as { cases: CovidCasesDaily[] }).cases);
  }

  public getHospitalBedsPerDate(): Promise<HospitalBedsDaily[]> {
    return this.http.get(this.apiUrl + '/daily/hospital/10')
      .toPromise().then(item => (item as { situations: HospitalBedsDaily[] }).situations);
  }

  public getSexDistribution(): Promise<SexDistribution[]> {
    return this.http.get(this.apiUrl + '/distribution/sex/10')
      .toPromise().then(item => (item as { cases: SexDistribution[] }).cases);
  }

  public getGeneralSituationPerDate(): Promise<GeneralSituationDaily[]> {
    return this.http.get(this.apiUrl + '/daily/generalsituation/10')
      .toPromise().then(item => (item as { situations: GeneralSituationDaily[] }).situations);
  }

  public async getHospitalUtilizationPerProvince(): Promise<any>{
    const data = this.mapResponseDataToObject(await this.http.get<any>(this.apiUrl + '/daily/hospital',
      {params: {'area-id': ['10']}})
      .toPromise()
      .then(res => (res as { items: AreaResponse[] }).items));
    return {...data};
  }

  public getProvinces(): Promise<Area[]> {
    return this.http.get<any>(this.apiUrl + '/provinces')
      .toPromise()
      .then(res => (res as { items: Area[] }).items);
  }

  public getDistricts(): Promise<Area[]> {
    return this.http.get<any>(this.apiUrl + '/districts')
      .toPromise()
      .then(res => (res as { items: Area[] }).items);
  }

  async getInfosForAndMap(data: any, regions: Area[], forceUpdate: boolean): Promise<any> {
    const casesValuesToLoad = regions.filter(item => (!data[item.areaId] || forceUpdate));

    let newData = {};
    if (casesValuesToLoad.length > 0) {
      newData = this.mapResponseDataToObject(await this.http.get<any>(this.apiUrl + '/daily/cases',
        {params: {'area-id': casesValuesToLoad.map(item => item.areaId.toString())}})
        .toPromise()
        .then(res => (res as { items: AreaResponse[] }).items));
    }
    return {...data, ...newData};
  }

  private mapResponseDataToObject(responseData: AreaResponse[]): any {
    const obj = {dates: []};
    responseData.forEach((item, idx) => {
      obj[item.areaId] = item.data.reduce((d, c) => {
        c.values.forEach(e => d[e.identifier] = [...d[e.identifier] || [], e.value]);
        if (idx === 0) {
          obj.dates.push(c.date);
        }
        return d;
      }, {});
    });
    return obj;
  }

  public buildCurrentChartData(loadedData: { dates: string[] }, selectedRegions: any[], selectedElements: any[]):
    { colors: string[][], names: string[], labels: string[], values: number[][] } {
    const dataToShow = {dates: loadedData.dates};

    selectedRegions.forEach(item => dataToShow[item.areaId] = loadedData[item.areaId]);

    const chartData: { colors: string[][], names: string[], labels: string[], values: number[][] } = {
      values: [],
      colors: [],
      labels: [],
      names: [],
    };
    let idx = 0;
    const idxFromAreaIds = selectedRegions.filter(reg => reg.areaId < 10).map(item => item.areaId);
    const idxNotAreaIds = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9].filter(n => !idxFromAreaIds.includes(n));

    chartData.colors = selectedRegions
      .reduce((arr, reg, i) => [...arr, ...selectedElements
        .map(e => this.getColorsFromMatrixAt(e.idx, reg.areaId < 10 ? reg.areaId :
          (idx >= idxNotAreaIds.length ? ([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])[idx++ % 10] : idxNotAreaIds[idx++])))], []);
    chartData.names = selectedRegions.map(item => item.areaName)
      .reduce((p, c) => [...p, ...selectedElements
        .map(i => c + ' ' + i.text)], []);
    chartData.labels = loadedData.dates;
    chartData.values = selectedRegions
      .reduce((arr, reg) => [...arr, ...selectedElements
        .map(item => dataToShow[reg.areaId][item.id])], []);
    return chartData;
  }

  public getColorsFromMatrixAt(col: number, row: number): string {
    return ([[
      '#F5A9A9', '#FE2E2E', '#B40404', '#8A0808',
      '#8A4B08', '#FF8000', '#FA8258', '#F5DA81',
      '#F6D8CE', '#F6CECE'],
      [
        '#BCF5A9', '#80FF00', '#688A08', '#21610B',
        '#088A29', '#01DF3A', '#2EFE64', '#0B3B0B', '#81F79F',
        '#088A68'],
      [
        '#A9BCF5', '#2E9AFE', '#084B8A', '#5858FA',
        '#0101DF', '#210B61', '#3A01DF',
        '#8000FF', '#AC58FA', '#BCA9F5',
      ], [
        '#FF8000', '#FAAC58', '#F5D0A9', '#8A4B08',
        '#3B240B', '#61380B', '#F7BE81', '#F7BE81',
        '#F5BCA9',
      ], [
        '#A9F5F2', '#E0F8F7', '#58FAF4', '#01DFD7',
        '#0B615E', '#0A2A29', '#045FB4', '#81DAF5',
        '#00BFFF',
      ], [
        '#F6CEF5', '#F781F3', '#FE2EF7', '#B404AE',
        '#610B5E', '#B40486', '#FF00BF', '#F781D8',
        '#E2A9F3',
      ]])[col][row];
  }

  async loadProvincesAndDistrictsAsTableData(): Promise<TreeNode[]> {
    const provinces = await this.getProvinces();
    const districts = await this.getDistricts();

    return provinces.map(p =>
      ({
        data: {
          areaName: p.areaName,
          areaId: p.areaId,
          selectable: false,
        },
        children: districts.filter(d => d.areaId.toString(10).charAt(0) === p.areaId.toString(10))
          .map(d => ({
            data: {
              areaName: d.areaName, areaId: d.areaId, selectable: true,
            },
          })),
      }));
  }



}
