import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgeSexDistributionChartComponent } from './age-sex-distribution-chart.component';

describe('AgeSexDistributionChartComponent', () => {
  let component: AgeSexDistributionChartComponent;
  let fixture: ComponentFixture<AgeSexDistributionChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgeSexDistributionChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgeSexDistributionChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
