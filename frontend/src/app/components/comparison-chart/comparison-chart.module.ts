import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComparisonChartComponent } from './comparison-chart.component';
import {ChartModule} from 'primeng/chart';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';



@NgModule({
  declarations: [ComparisonChartComponent],
    imports: [
        CommonModule,
        ChartModule,
        ProgressSpinnerModule,
        MultiSelectModule,
        FormsModule,
    ],
  exports: [ComparisonChartComponent]
})
export class ComparisonChartModule { }
