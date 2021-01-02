import {Inject, Injectable} from '@angular/core';
import {MqttService} from 'ngx-mqtt';

@Injectable({
  providedIn: 'root',
})
export class SocketService {
  private connected = false;

  constructor(
    @Inject('BACKEND_NOTIFICATION_URL') private readonly notificationUrl: string,
    @Inject('BACKEND_NOTIFICATION_PORT') private readonly port: number,
    private readonly mqttService: MqttService) {
  }

  connectToMqtt(onConnect: () => void): void {
    try {
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


}
