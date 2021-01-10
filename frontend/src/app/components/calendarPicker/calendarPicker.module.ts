import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {CalendarPickerComponent} from './calendarPicker.component';
import {CalendarModule} from 'primeng/calendar';

@NgModule({
  declarations: [CalendarPickerComponent],
  imports: [
    CommonModule,
    MultiSelectModule,
    FormsModule,
    ProgressSpinnerModule,
    CalendarModule
  ],

  exports: [CalendarPickerComponent]
})
export class CalendarPickerModule { }
