import {DatePipe} from '@angular/common';

export class ChartModelBuilder {
  private defaultBackgroundColors: string[] | string[][] = ['#1B2771', '#A93226', '#5B2C6F', '#0e6655', '#1D8348', '#9C640C', '#5F6A6A'];
  private colors = this.defaultBackgroundColors;
  private options = {};
  private defaultStyle: any = {};
  private datasets: any[] = [];

  constructor() {
    this.defaultStyle = {
      backgroundColor: (index) => this.colors[index],
      borderColor: (index) => '#FFFFFF',
    };
  }

  public buildBasicChartModel(chartNames: string[], labels: string[], data: any[][]): any {
    return {
      labels,
      datasets: data.map((item, index) => {

          const {backgroundColor, borderColor, ...rest} = this.defaultStyle;
          return {
            label: chartNames[index],
            data: item,
            backgroundColor: backgroundColor(index),
            borderColor: borderColor(index),
            ...rest,
            ...this.options,
          };
        },
      ),
    };
  }



  public buildModelFromResponse(object: any, keyField: string): any {
    return this.buildBasicChartModel(Object.keys(object[keyField])
        .map(item => item[0].toUpperCase()
          + item.substring(1, item.length)
            .replace(/([A-Z])/g, ' $1')
            .trim()
            .toLowerCase()), object.labels,
      Object.values(object[keyField]));
  }

  public addDataSets(datasetNames: string[], data: any[][]): ChartModelBuilder {
    this.datasets = [...this.datasets, ...data.map((item, index) => {

      const {backgroundColor, borderColor, ...rest} = this.defaultStyle;
      console.log(backgroundColor(index));
      console.log(this.options);
      console.log(rest);
      return {
        label: datasetNames[index],
        data: item,
        backgroundColor: backgroundColor(index),
        borderColor: borderColor(index),
        ...rest,
        ...this.options,
      };
    })];
    return this;
  }

  public build(labels: string[]): any {
    return {
      labels,
      datasets: this.datasets,
    };
  }

  public useBarChartStyle(): ChartModelBuilder {
    this.defaultStyle = {

      backgroundColor: (index) => this.colors[index],
      borderColor: (index) => '#FFFFFF',
    };
    return this;
  }

  public useLineChartStyle(): ChartModelBuilder {
    this.defaultStyle = {
      fill: false,
      pointRadius: 0,
      borderWidth: 2,
      backgroundColor: (index) => 'transparent',
      borderColor: (index) => this.colors[index],
    };
    return this;
  }


  public useCustomColors(colors: string[] | string[][]): ChartModelBuilder {
    this.colors = colors;
    return this;
  }

  public useCustomOptions(options: any): ChartModelBuilder {
    this.options = options;
    return this;
  }

}
