import {TestBed} from '@angular/core/testing';

import {MapService} from './map.service';
import {HttpClient, HttpClientModule} from '@angular/common/http';

describe('MapService', () => {
  let service: MapService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HttpClient],
      imports: [HttpClientModule],
    });
    service = TestBed.inject(MapService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
