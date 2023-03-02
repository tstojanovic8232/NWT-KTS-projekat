import { TestBed } from '@angular/core/testing';

import { DrivingService } from './driving.service';

describe('ReservationService', () => {
  let service: DrivingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DrivingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
