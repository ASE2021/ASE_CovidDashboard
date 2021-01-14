import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HospitalUtilizationComponent } from './hospital-utilization.component';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {ChartModule} from 'primeng/chart';



@NgModule({
  declarations: [HospitalUtilizationComponent],
  exports: [
    HospitalUtilizationComponent
  ],
  imports: [
    CommonModule,
    ProgressSpinnerModule,
    ChartModule
  ]
})
export class HospitalUtilizationModule { }
