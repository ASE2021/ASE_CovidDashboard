import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {SocketService} from '../../services/socket/socket.service';
import {MessageResponse} from '../../model/MessageResponse';
import {IMqttMessage} from 'ngx-mqtt';
import {ComparisonData} from "../../model/comparison-data";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss'],
})
export class OverviewComponent implements OnInit {

  positiveCasesPerDateData: any;
  hospitalBedsPerDate: any;
  activeCases: number;
  numberOfCases: number;
  deaths: number;
  comparison: ComparisonData[]
  province: any;


  constructor(private covidService: CovidService, private socketService: SocketService) {

  }

  public ngOnInit(): void {
    this.initializeComparisonChart()
    this.initializePositiveCasesPerDateChart();
    this.initializeBasicInformation();
    this.initializeHospitalBedsPerDateChart();


    this.socketService.connectToMqtt(
      () => {
        this.socketService.observe('new-data')
          .subscribe((message: IMqttMessage) => {
            console.log(message.payload.toString());
            try {
              if ((JSON.parse(message.payload.toString()) as MessageResponse).update) {
                this.initializeComparisonChart()
                this.initializePositiveCasesPerDateChart();
                this.initializeBasicInformation();
                this.initializeHospitalBedsPerDateChart();
              }
            } catch (e) {
            }
          });
      },
    );
  }

  // TODO: get data from backend (receive object with these (or more) properties)
  private async initializeBasicInformation(): Promise<void> {
    this.activeCases = 41000;
    this.numberOfCases = 200000;
    this.deaths = 2000;
  }

  private async initializeComparisonChart(): Promise<void> {
    const data = await this.covidService.getComparisonData();
    this.comparison = new ChartModelBuilder()
      .buildBasicChartModel(['Cases', 'Cures', 'Deaths'], ['Cases', 'Cures', 'Deaths'],
        data.reduce((dataArray, current) =>
          [
            [...dataArray[0], current.cases],
            [...dataArray[1], current.cures],
            [...dataArray[2], current.deaths]
          ], [[], []]));
  }

  private async initializePositiveCasesPerDateChart(): Promise<void> {
    const data = await this.covidService.getNewCasesPerDate();
    this.positiveCasesPerDateData = new ChartModelBuilder()
      .buildBasicChartModel(['Positive Covid-19 cases per date'],
        data.map(item => item.date.split('T')[0]),
        [data.map(item => item.cases)]);
  }


  private async initializeHospitalBedsPerDateChart(): Promise<void> {
    const data = await this.covidService.getHospitalBedsPerDate();
    this.hospitalBedsPerDate = new ChartModelBuilder()
      .buildBasicChartModel(['Intense beds used', 'Normal beds used'],
        data.map(item => item.date.split('T')[0]),
        data.reduce((dataArray, current) =>
          [
            [...dataArray[0], current.intenseBeds],
            [...dataArray[1], current.normalBeds],
          ], [[], []]));

  }

}


