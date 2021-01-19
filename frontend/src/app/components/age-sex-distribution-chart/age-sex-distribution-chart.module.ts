import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgeSexDistributionChartComponent } from './age-sex-distribution-chart.component';
import {ChartModule} from 'primeng/chart';
import {RadioButtonModule} from 'primeng/radiobutton';
import {FormsModule} from '@angular/forms';



@NgModule({
  declarations: [AgeSexDistributionChartComponent],
  exports: [
    AgeSexDistributionChartComponent
  ],
  imports: [
    CommonModule,
    ChartModule,
    RadioButtonModule,
    FormsModule,
  ]
})
export class AgeSexDistributionChartModule { }
