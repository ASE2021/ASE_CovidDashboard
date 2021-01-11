import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComparisonChartComponent } from './comparison-chart.component';
import {ChartModule} from 'primeng/chart';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {InputSwitchModule} from "primeng/inputswitch";
import {SelectButtonModule} from "primeng/selectbutton";



@NgModule({
  declarations: [ComparisonChartComponent],
  imports: [
    CommonModule,
    ChartModule,
    ProgressSpinnerModule,
    MultiSelectModule,
    FormsModule,
    InputSwitchModule,
    SelectButtonModule,
  ],
  exports: [ComparisonChartComponent]
})
export class ComparisonChartModule { }
