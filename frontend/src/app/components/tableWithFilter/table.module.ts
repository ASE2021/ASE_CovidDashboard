import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TableModule} from 'primeng/table';

import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {TableComponent} from './table.component';

@NgModule({
  declarations: [TableComponent],
  imports: [
    CommonModule,
    TableModule,
    MultiSelectModule,
    FormsModule,
    ProgressSpinnerModule
  ],

  exports: [TableComponent]
})
export class TableWithFilterModule { }
