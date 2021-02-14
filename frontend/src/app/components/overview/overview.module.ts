import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {OverviewComponent} from './overview.component';
import {ChartModule} from 'primeng/chart';
import {CardModule} from 'primeng/card';
import {PanelModule} from 'primeng/panel';
import {TableModule} from 'primeng/table';
import {SelectButtonModule} from 'primeng/selectbutton';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {TabViewModule} from 'primeng/tabview';
import {DropdownModule} from 'primeng/dropdown';
import {TabMenuModule} from 'primeng/tabmenu';
import {RoutingModule} from '../../routing.module';
import {InputTextModule} from 'primeng/inputtext';
import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {TableWithFilterModule} from '../tableWithFilter/table.module';
import {CalendarModule} from 'primeng/calendar';
import {CompareRegionsChartModule} from '../comparison-chart/compare-regions-chart.module';
import {HospitalUtilizationModule} from '../hospital-utilization-chart/hospital-utilization.module';
import {AgeSexDistributionChartModule} from '../age-sex-distribution-chart/age-sex-distribution-chart.module';
import {CheckboxModule} from 'primeng/checkbox';
import {TreedropdownModule} from '../treedropdown/treedropdown.module';


@NgModule({
  declarations: [OverviewComponent],
  imports: [
    CommonModule,
    ChartModule,
    CardModule,
    PanelModule,
    SelectButtonModule,
    TabViewModule,
    NoopAnimationsModule,
    DropdownModule,
    BrowserAnimationsModule,
    TableModule,
    TabMenuModule,
    RoutingModule,
    InputTextModule,
    MultiSelectModule,
    FormsModule,
    ProgressSpinnerModule,
    TableWithFilterModule,
    CalendarModule,
    CompareRegionsChartModule,
    AgeSexDistributionChartModule,
    CheckboxModule,
    HospitalUtilizationModule,
    TreedropdownModule,
  ],

  exports: [OverviewComponent],
})
export class OverviewModule {
}
