import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {CalendarRangeSelectorComponent} from './calendar-range-selector.component';
import {CalendarModule} from 'primeng/calendar';
import {FormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';


@NgModule({
  declarations: [CalendarRangeSelectorComponent],
  imports: [
    CommonModule,
    CalendarModule,
    BrowserAnimationsModule,
    FormsModule,
  ], providers: [DatePipe], exports: [CalendarRangeSelectorComponent],
})
export class CalendarRangeSelectorModule {
}
