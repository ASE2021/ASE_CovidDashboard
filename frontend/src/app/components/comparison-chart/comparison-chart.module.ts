import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComparisonChartComponent } from './comparison-chart.component';
import {ChartModule} from 'primeng/chart';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {ListboxModule} from 'primeng/listbox';
import {ButtonModule} from 'primeng/button';
import {SelectButtonModule} from 'primeng/selectbutton';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';



@NgModule({
  declarations: [ComparisonChartComponent],
  imports: [
    CommonModule,
    ChartModule,
    ProgressSpinnerModule,
    MultiSelectModule,
    FormsModule,
    OverlayPanelModule,
    ListboxModule,
    ButtonModule,
    SelectButtonModule,
    BrowserAnimationsModule
  ],
  exports: [ComparisonChartComponent]
})
export class ComparisonChartModule { }
