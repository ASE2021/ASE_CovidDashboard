import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgeSexDistributionChartComponent } from './age-sex-distribution-chart.component';
import {ChartModule} from 'primeng/chart';
import {RadioButtonModule} from 'primeng/radiobutton';
import {FormsModule} from '@angular/forms';
import {TreedropdownModule} from '../treedropdown/treedropdown.module';



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
        TreedropdownModule,
    ],
})
export class AgeSexDistributionChartModule { }
