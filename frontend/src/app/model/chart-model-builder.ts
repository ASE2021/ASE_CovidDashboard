export class ChartModelBuilder {
  private defaultBackgroundColors: string[] | string[][] = ['#1B2771', '#A93226', '#5B2C6F', '#0e6655', '#1D8348', '#9C640C', '#5F6A6A'];
  private colors = this.defaultBackgroundColors;
  private options = {};

  constructor() {
  }

  public buildBasicChartModel(chartNames: string[], labels: string[], data: any[][]): any {
    console.log(data);
    return {
      labels,
      datasets: data.map((item, index) => (
        {
          label: chartNames[index],
          borderColor: this.colors[index],
          data: item,
          ...this.options
        }
      )),
    };
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
