import {Component, OnInit, ViewChild} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {CovidService} from '../../services/covid.service';
import {ChartModelBuilder} from '../../model/chart-model-builder';
import {Provinces} from '../../model/Provinces';
import {CovidCasesDaily} from '../../model/covid-cases-daily';
import {SocketService} from '../../services/socket/socket.service';
import {MessageResponse} from '../../model/MessageResponse';
import {map} from 'rxjs/operators';
import {IMqttMessage} from 'ngx-mqtt';

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
  province: any;


  constructor(private covidService: CovidService, private socketService: SocketService) {

  }

  public ngOnInit(): void {
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

  private async initializeBasicInformation(): Promise<void> {
    // TODO: get data from backend (receive object with these (or more) properties)
    this.activeCases = 41000;
    this.numberOfCases = 200000;
    this.deaths = 2000;
  }

  private async initializePositiveCasesPerDateChart(): Promise<void> {
    const data = await this.covidService.getNewCasesPerDate();
    this.positiveCasesPerDateData = new ChartModelBuilder()
      .buildBarChartModel('Positive Covid-19 cases per date',
        data.map(item => item.date.split('T')[0]),
        data.map(item => item.cases));
  }


  private async initializeHospitalBedsPerDateChart(): Promise<void> {
    const data = await  this.covidService.getHospitalBedsPerDate();
    this.hospitalBedsPerDate = new ChartModelBuilder()
      .buildHospitalLineChartModel(['Intense beds used', 'Normal beds used'],
        data.map(item => item.date.split('T')[0]),
        data.map(item => item.intenseBeds),
        data.map(item => item.normalBeds));



  }

}


