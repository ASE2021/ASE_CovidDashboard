import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarPickerComponent } from './calendarPicker.component';

describe('CalendarComponent', () => {
  let component: CalendarPickerComponent;
  let fixture: ComponentFixture<CalendarPickerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalendarPickerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarPickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
