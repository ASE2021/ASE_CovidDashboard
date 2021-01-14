import {Inject, Injectable} from '@angular/core';
import {IMqttMessage, MqttConnectionState, MqttService} from 'ngx-mqtt';
import {Observable, Subject} from 'rxjs';
import {MessageResponse} from '../../model/MessageResponse';

@Injectable({
  providedIn: 'root',
})
export class SocketService {
  private connected = false;
  private subject: Subject<any>;

  constructor(
    @Inject('BACKEND_NOTIFICATION_URL') private readonly notificationUrl: string,
    @Inject('BACKEND_NOTIFICATION_PORT') private readonly port: number,
    private readonly mqttService: MqttService) {
  }

  connectToMqtt(onConnect: () => void): void {
    try {
      if (this.mqttService.state.getValue() !== MqttConnectionState.CONNECTED) {

        this.mqttService.state.subscribe((state) => {
          console.log(state);
          if (state === 2) {
            console.log('connected to notification service');
            onConnect();
          }
        });

        this.mqttService.onError.subscribe(e => console.log(e));
        this.mqttService.onConnect.subscribe(e => console.log(e));

        // @ts-ignore
        this.mqttService.connect({
          hostname: this.notificationUrl,
          port: this.port,
          protocol: 'ws',
        });
      }

    } catch (e) {
      console.log('Not able to connect to mqtt server', e);
    }
  }

  disconnectMqtt(): void {
    try {
      if (this.mqttService.state.getValue() !== 0) {
        this.mqttService.disconnect();
      }
    } catch (e) {
      console.log(e);
    }
  }

  observe<T>(topic: string): any {
    try {
      console.log('onserve ...');
      return this.mqttService.observe(topic, { // documentation: https://github.com/mqttjs/MQTT.js#subscribe
        qos: 2,
        rap: true,
        rh: 1,
      });

    } catch (e) {
      console.log(e);
    }
  }


  connectAndObserveNewData(): Observable<any> {
    if (!this.subject) {
      this.subject = new Subject<any>();
      this.connectToMqtt(
        () => {
          this.observe('new-data')
            .subscribe((message: IMqttMessage) => {
              try {
                if ((JSON.parse(message.payload.toString()) as MessageResponse).update) {
                  this.subject.next();
                }
              } catch (e) {
              }
            });
        },
      );
    }

    return this.subject.asObservable();
  }
}
