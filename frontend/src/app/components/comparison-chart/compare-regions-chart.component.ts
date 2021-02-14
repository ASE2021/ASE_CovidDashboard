import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {SocketService} from '../../services/socket/socket.service';
import {SelectItem, TreeNode} from 'primeng/api';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {Area} from '../../model/area';

@Component({
  selector: 'app-comparison-chart',
  templateUrl: './compare-regions-chart.component.html',
  styleUrls: ['./compare-regions-chart.component.scss'],
})
export class CompareRegionsChartComponent implements OnInit {
  chartData: any;
  elements: SelectItem[]; // Items for the multiselect "newData" etc.
  regionData: TreeNode[]; // Data for the treetable
  selectedElements: any[]; // Selected items for the multiselect "newData" etc.

  private areasToShowInChart: Area[]; // All areas which should be shown (selectedAreas + selectedDistricts)
  private loadedData: {
    labels: string[]
  } = {labels: []};
  private currentChartData: { colors: string[][], names: string[], labels: string[], values: number[][] } = {
    values: [],
    colors: [],
    labels: [],
    names: [],
  };


  constructor(private readonly covidService: CovidService,
              private readonly socketService: SocketService) {
    this.elements = [
      {label: 'New cases', value: {id: 'newCases', text: 'new cases', idx: 0, type: 0}},
      {label: 'Dead cases', value: {id: 'deaths', text: 'deaths', idx: 1, type: 0}},
      {label: 'Tests', value: {id: 'tests', idx: 2, text: 'tests', type: 0}},
      {label: 'Normal beds', value: {id: 'normalBeds', idx: 3, text: 'normal beds', type: 1}},
      {label: 'Intense beds', value: {id: 'intenseBeds', idx: 4, text: 'intense beds', type: 1}},
    ];
  }

  public ngOnInit(): void {

    this.loadRegions();

    this.socketService.connectAndObserveNewData()
      .subscribe(() => this.loadDataAndUpdateChart(true));

  }

  private async loadRegions(): Promise<any> {
    this.regionData = await this.covidService.loadProvincesAndDistrictsAsTableData();
  }

  private async loadDataAndUpdateChart(forceUpdate: boolean, calendar?): Promise<void> {
    this.loadedData = await this.covidService.getInfosForAndMap(this.loadedData, this.areasToShowInChart, forceUpdate);
    this.elementChanged();
    if (calendar) {
      calendar.reloadData(this.chartData);
    }
  }

  async regionChanged(val: any, calendar?): Promise<void> {
    this.areasToShowInChart = val;
    this.loadDataAndUpdateChart(false, calendar);

  }

  elementChanged(): void {
    if (this.areasToShowInChart &&
      this.selectedElements) {

      this.currentChartData = this.covidService.buildCurrentChartData(
        this.loadedData,
        this.areasToShowInChart,
        this.selectedElements);
      this.chartData = new ChartModelBuilder()
        .useCustomColors(this.currentChartData.colors)
        .useLineChartStyle()
        .buildBasicChartModel(this.currentChartData.names, this.currentChartData.labels, this.currentChartData.values);

    }
  }

}
