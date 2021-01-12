import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ComparisonChartComponent} from './comparison-chart.component';
import {ChartModule} from 'primeng/chart';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {ListboxModule} from 'primeng/listbox';
import {ButtonModule} from 'primeng/button';
import {SelectButtonModule} from 'primeng/selectbutton';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputSwitchModule} from 'primeng/inputswitch';
import {TreeTableModule} from 'primeng/treetable';
import {RippleModule} from 'primeng/ripple';
import {CheckboxModule} from 'primeng/checkbox';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {InputTextModule} from 'primeng/inputtext';

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
    BrowserAnimationsModule,
    InputSwitchModule,
    SelectButtonModule,
    TreeTableModule,
    RippleModule,
    CheckboxModule,
    AutoCompleteModule,
    InputTextModule,
  ],
  exports: [ComparisonChartComponent],
})

export class ComparisonChartModule {
}
