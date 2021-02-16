import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarRangeSelectorComponent } from './calendar-range-selector.component';

describe('CalendarRangeSelectorComponent', () => {
  let component: CalendarRangeSelectorComponent;
  let fixture: ComponentFixture<CalendarRangeSelectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalendarRangeSelectorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarRangeSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
