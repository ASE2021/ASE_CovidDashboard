import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {SocketService} from '../../services/socket/socket.service';
import {SelectItem, TreeNode} from 'primeng/api';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {TreeTable} from 'primeng/treetable';

@Component({
  selector: 'app-comparison-chart',
  templateUrl: './comparison-chart.component.html',
  styleUrls: ['./comparison-chart.component.scss'],
})
export class ComparisonChartComponent implements OnInit {
  chartData: any;
  elements: SelectItem[]; // Items for the multiselect "newData" etc.
  regionData: TreeNode[]; // Data for the treetable
  selectedElements: any[]; // Selected items for the multiselect "newData" etc.
  selectedDistricts: any[]; // Selected districts from the treetable
  selectedAreas: any[]; // Selected regions from the treetable
  selectedRegionNames: string; // String including the selected region names (for displaying)

  private areasToShowInChart: any[]; // All areas which should be shown (selectedAreas + selectedDistricts)
  private loadedData: {
    dates: string[]
  } = {dates: []};
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

  private async loadDataAndUpdateChart(forceUpdate: boolean): Promise<void> {
    this.loadedData = await this.covidService.getInfosForAndMap(this.loadedData, this.areasToShowInChart, forceUpdate);
    this.elementChanged();
  }

  async regionChanged(): Promise<void> {
    this.areasToShowInChart = [
      ...this.selectedAreas || [],
      ...this.selectedDistricts?.filter(item => item.parent).map(item => item.data) || [],
    ];
    this.selectedRegionNames = this.areasToShowInChart
      .map(item => item.areaName)
      .join(', ');

    this.loadDataAndUpdateChart(false);

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

  onFilter(tt: TreeTable, filterValue: string): void {
    setTimeout(() => {
      tt.filteredNodes.forEach(item => item.children.some(c => c.data.areaName.includes(filterValue)) ?
        item.expanded = true : null);
      tt.updateSerializedValue();
    }, 300);
  }
}
