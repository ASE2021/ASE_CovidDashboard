import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HospitalUtilizationComponent } from './hospital-utilization.component';

describe('HospitalUtilizationComponent', () => {
  let component: HospitalUtilizationComponent;
  let fixture: ComponentFixture<HospitalUtilizationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HospitalUtilizationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HospitalUtilizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
