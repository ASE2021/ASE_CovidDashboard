export class ChartModelBuilder {
  private defaultBackgroundColors: string[] | string[][] = ['#1B2771', '#A93226', '#5B2C6F', '#0e6655', '#1D8348', '#9C640C', '#5F6A6A'];
  private colors = this.defaultBackgroundColors;
  private options = {};
  private defaultStyle: any = {};
  private datasets: any[] = [];

  constructor() {
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
            ...this.options
          };
        }
      ),
    };
  }

  public addDataSets(datasetNames: string[], data: any[][]): ChartModelBuilder {
    this.datasets = [...this.datasets, ...data.map((item, index) => {

      const {backgroundColor, borderColor, ...rest} = this.defaultStyle;
      return {
        label: datasetNames[index],
        data: item,
        backgroundColor: backgroundColor(index),
        borderColor: borderColor(index),
        ...rest,
        ...this.options
      };
    })];
    return this;
  }

  public build(labels: string[]): any {
    return {
      labels,
      datasets: this.datasets
    };
  }

  public withBarChartStyle(): ChartModelBuilder {
    this.defaultStyle = {
      borderWidth: 4,

      backgroundColor: (index) => this.colors[index],
      borderColor: (index) => '#FFFFFF',
    };
    return this;
  }

  public withLineChartStyle(): ChartModelBuilder {
    this.defaultStyle = {
      fill: false,
      pointRadius: 0,
      borderWidth: 2,
      backgroundColor: (index) => 'transparent',
      borderColor: (index) => this.colors[index]
    };
    return this;
  }


  public withCustomColors(colors: string[] | string[][]): ChartModelBuilder {
    this.colors = colors;
    return this;
  }

  public withCustomOptions(options: any): ChartModelBuilder {
    this.options = options;
    return this;
  }

}
