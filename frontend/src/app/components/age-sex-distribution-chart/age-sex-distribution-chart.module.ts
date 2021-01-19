import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgeSexDistributionChartComponent } from './age-sex-distribution-chart.component';
import {ChartModule} from "primeng/chart";



@NgModule({
  declarations: [AgeSexDistributionChartComponent],
  exports: [
    AgeSexDistributionChartComponent
  ],
  imports: [
    CommonModule,
    ChartModule
  ]
})
export class AgeSexDistributionChartModule { }
