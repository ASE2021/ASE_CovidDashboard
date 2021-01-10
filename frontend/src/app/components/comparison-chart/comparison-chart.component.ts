import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {SocketService} from '../../services/socket/socket.service';
import {IMqttMessage} from 'ngx-mqtt';
import {MessageResponse} from '../../model/MessageResponse';
import {SelectItem} from 'primeng/api';
import {Area} from '../../model/area';

@Component({
  selector: 'app-comparison-chart',
  templateUrl: './comparison-chart.component.html',
  styleUrls: ['./comparison-chart.component.scss'],
})
export class ComparisonChartComponent implements OnInit {
  chartData: any;
  provinces: Area[] = [];


  constructor(private covidService: CovidService, private socketService: SocketService) {

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
}
