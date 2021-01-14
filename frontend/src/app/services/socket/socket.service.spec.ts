import { TestBed } from '@angular/core/testing';

import { SocketService } from './socket.service';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {MqttModule, MqttService} from 'ngx-mqtt';

describe('SocketService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientModule, MqttModule.forRoot({connectOnCreate: false})],
    providers: [MqttService, HttpClient, {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
      {provide: 'BACKEND_NOTIFICATION_URL', useValue: environment.backendNotificationUrl},
      {provide: 'BACKEND_NOTIFICATION_PORT', useValue: environment.backendNotificationPort}]
  }));

  it('should be created', () => {
    const service: SocketService = TestBed.inject(SocketService);
    expect(service).toBeTruthy();
  });
});
