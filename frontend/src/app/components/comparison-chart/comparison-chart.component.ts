import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {SocketService} from '../../services/socket/socket.service';
import {IMqttMessage} from 'ngx-mqtt';
import {MessageResponse} from '../../model/MessageResponse';
import {SelectItem} from 'primeng/api';
import {Area} from '../../model/area';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {UIChart} from "primeng/chart";

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
  chartTypes: SelectItem[];
  selectedChartType = 'line';
  private colorsForChart = [[
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

  private currentChartData: { colors: string[][], names: string[], labels: string[], values: { line: [][], bar: [][] } } = {
    values: {line: [], bar: []},
    colors: [],
    labels: [],
    names: []
  };

  @ViewChild('chart')
  chart: UIChart;


  constructor(private covidService: CovidService, private socketService: SocketService, private readonly cd: ChangeDetectorRef) {
    this.elements = [
      {label: 'new cases', value: {id: 'newCases', idx: 0}},
      {label: 'dead cases', value: {id: 'deaths', idx: 1}},
      {label: 'taken tests', value: {id: 'tests', idx: 2}}];
    this.chartTypes = [{label: 'Line', value: 'line'}, {label: 'Bar', value: 'bar'}];
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


    console.log(this.selectedElements);
    if (this.selectedRegions && this.selectedRegions.length > 0 && this.selectedElements && this.selectedElements.length > 0) {
      const dataToShow = {dates: this.loadedData.dates};

      this.selectedRegions.forEach(item => dataToShow[item.areaId] = this.loadedData[item.areaId]);

      console.log(dataToShow);
      console.log(this.selectedRegions);
      console.log(this.selectedElements);

      this.currentChartData.colors = this.selectedRegions.reduce((arr, reg, idx) => [...arr, ...this.selectedElements.map((e, i) => this.colorsForChart[e.idx][reg.areaId])], []);
      this.currentChartData.names = this.selectedRegions.map(item => item.areaName)
        .reduce((p, c) => [...p, ...this.selectedElements.map(i => c + '_' + i.id)], []);
      this.currentChartData.labels = this.loadedData.dates;
      this.currentChartData.values.line = this.selectedRegions.reduce((arr, reg) => [...arr, ...this.selectedElements.map(item => dataToShow[reg.areaId][item.id])], []);


      this.createChart();
    }
  }

  private createChart(): any {
    let modelBuilder = new ChartModelBuilder().withCustomColors(this.currentChartData.colors);
    switch (this.selectedChartType) {
      case 'line':
        console.log(modelBuilder
          .withLineChartStyle());
        modelBuilder = modelBuilder
          .withLineChartStyle()
          .addDataSets(this.currentChartData.names, this.currentChartData.values.line)
          .withBarChartStyle()
          .withCustomOptions({type: 'bar'})
          .addDataSets(this.currentChartData.names.map(item => item + '_' + this.selectedChartType), this.currentChartData.values.bar);
        break;
      case 'bar':
        modelBuilder = modelBuilder
          .withBarChartStyle()
          .addDataSets(this.currentChartData.names, this.currentChartData.values.bar)
          .withBarChartStyle()
          .withCustomOptions({type: 'line'})
          .addDataSets(this.currentChartData.names.map(item => item + '_' + this.selectedChartType), this.currentChartData.values.line);
        break;
    }

    this.chartData = modelBuilder.build(this.currentChartData.labels);
    console.log(this.chartData);
  }


  chartTypeSwitched(event: any): void {
    console.log(event);
    console.log(this.selectedChartType);
    console.log(this.chart);
    setTimeout(() => this.createChart(), 100);
    setTimeout(() => {
    }, 1500);
    console.log(this.chart.options);
    console.log(this.chart.type);
  }
}
