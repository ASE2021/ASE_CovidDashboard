export class ChartModelBuilder {
  private defaultBackgroundColors = ['#1B2771', '#A93226', '#5B2C6F', '#0E6655', '#1D8348', '#9C640C', '#5F6A6A'];
  private colors = this.defaultBackgroundColors;
  private options = {};

  constructor() {
  }

  public buildBasicChartModel(chartNames: string[], labels: string[], data: any[][], dateRange: Date[]): any {
    return {
      labels,
      datasets: data.map((item, index) => (
        {
          label: chartNames[index],
          backgroundColor: this.colors[index],
          borderColor: '#FFFFFF',
          data: item,
        }
      )),
      dateRange,
    };
  }


  public withCustomColors(colors: string[]): ChartModelBuilder {
    this.colors = colors;
    return this;
  }

  public withCustomOptions(options: any): ChartModelBuilder {
    this.options = options;
    return this;
  }

}
