import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OverviewComponent } from './overview.component';
import {ChartModule} from 'primeng/chart';
import {CardModule} from 'primeng/card';
import {PanelModule} from 'primeng/panel';
import {TableModule} from 'primeng/table';
import {SelectButtonModule} from 'primeng/selectbutton';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {TabViewModule} from 'primeng/tabview';
import {DropdownModule} from 'primeng/dropdown';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { TableComponent } from '../tableWithFilter/table.component';

@NgModule({
  declarations: [OverviewComponent, TableComponent],
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
    ],
  exports: [OverviewComponent]
})
export class OverviewModule { }
