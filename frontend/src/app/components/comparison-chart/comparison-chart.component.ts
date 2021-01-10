import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {SocketService} from '../../services/socket/socket.service';
import {IMqttMessage} from 'ngx-mqtt';
import {MessageResponse} from '../../model/MessageResponse';
import {SelectItem} from 'primeng/api';
import {Area} from '../../model/area';
import {ChartModelBuilder} from '../../model/chart-model-builder';

@Component({
  selector: 'app-comparison-chart',
  templateUrl: './comparison-chart.component.html',
  styleUrls: ['./comparison-chart.component.scss'],
})
export class ComparisonChartComponent implements OnInit {
  chartData: any;
  provinces: Area[] = [];
  elements: SelectItem[];
  selectedElements: any;
  selectedRegions: Area[];

  loadedData: {
    dates: string[]
  } = {dates: []};
  options: any;


  constructor(private covidService: CovidService, private socketService: SocketService) {
    this.elements = [
      {label: 'new cases', value: {id: 'newCases', idx: 0}},
      {label: 'dead cases', value:  {id: 'deaths', idx: 1}},
      {label: 'taken tests', value:  {id: 'tests', idx: 2}}];
    this.options = {pointRadius: 0};
  }

  public ngOnInit(): void {

    this.loadRegions();

    this.socketService.connectToMqtt(
      () => {
        this.socketService.observe('new-data')
          .subscribe((message: IMqttMessage) => {
            console.log(message.payload.toString());
            try {
              if ((JSON.parse(message.payload.toString()) as MessageResponse).update) {

              }
            } catch (e) {
            }
          });
      },
    );
  }

  private async loadRegions(): Promise<any> {
    this.provinces = await this.covidService.getProvinces();
  }


  async regionChanged(event: any): Promise<void> {
    this.loadedData = await this.covidService.getInfosForAndMap(this.loadedData, this.selectedRegions);


    this.elementChanged();

    console.log(this.loadedData);

  }

  elementChanged(): void {

    const colors = [[
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
      ]];

    console.log(this.selectedElements);
    if (this.selectedRegions && this.selectedRegions.length > 0 && this.selectedElements && this.selectedElements.length > 0) {
      const dataToShow = {dates: this.loadedData.dates};

      this.selectedRegions.forEach(item => dataToShow[item.areaId] = this.loadedData[item.areaId]);

      console.log(dataToShow);
      console.log( this.selectedRegions);
      console.log( this.selectedElements);
      this.chartData = new ChartModelBuilder()
        .withCustomOptions({
          fill: false,
          pointRadius: 0,
          borderWidth: 2,
        })
        .withCustomColors(
          this.selectedRegions.reduce((arr, reg, idx) => [...arr, ...this.selectedElements.map((e, i) => colors[e.idx][reg.areaId])], []),
        )
        .buildBasicChartModel(this.selectedRegions.map(item => item.areaName)
            .reduce((p, c) => [...p, ...this.selectedElements.map(i => c + '_' + i.id)], []),
          this.loadedData.dates,
          this.selectedRegions.reduce((arr, reg) => [...arr, ...this.selectedElements.map(item => dataToShow[reg.areaId][item.id])], []));
    }
  }
}
