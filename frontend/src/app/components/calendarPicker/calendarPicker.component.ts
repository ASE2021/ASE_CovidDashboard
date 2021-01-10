import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-calendarpicker',
  templateUrl: './calendarPicker.component.html',
  styleUrls: ['./calendar.component.scss']
})
export class CalendarPickerComponent implements OnInit {

  rangeDates: Date[];

  minDate: Date;
  maxDate: Date;
  invalidDates: Array<Date>;

  constructor() { }

  ngOnInit(): void {

    const today = new Date();

    const minDate = '26.02.2020';
    this.minDate = new Date(minDate);

    this.maxDate = today;

    const invalidDate = new Date();
    invalidDate.setDate(today.getDate() - 1);
    this.invalidDates = [today, invalidDate];

  }

}
