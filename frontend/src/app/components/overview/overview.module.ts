import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OverviewComponent } from './overview.component';
import {ChartModule} from 'primeng/chart';
import {CardModule} from 'primeng/card';



@NgModule({
  declarations: [OverviewComponent],
  imports: [
    CommonModule,
    ChartModule,
    CardModule,
  ],
  exports: [OverviewComponent]
})
export class OverviewModule { }
