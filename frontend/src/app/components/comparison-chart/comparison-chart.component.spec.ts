import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComparisonChartComponent } from './comparison-chart.component';

describe('ComparisonChartComponent', () => {
  let component: ComparisonChartComponent;
  let fixture: ComponentFixture<ComparisonChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComparisonChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComparisonChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
