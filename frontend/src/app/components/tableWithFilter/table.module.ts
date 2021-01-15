import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TableModule} from 'primeng/table';

import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {TableComponent} from './table.component';
import {ButtonModule} from 'primeng/button';
import {RippleModule} from 'primeng/ripple';
import { InputTextModule} from 'primeng/inputtext';
import { InputNumberModule} from 'primeng/inputnumber';

@NgModule({
  declarations: [TableComponent],
  imports: [
    CommonModule,
    TableModule,
    MultiSelectModule,
    FormsModule,
    ProgressSpinnerModule,
    ButtonModule,
    RippleModule,
    InputTextModule,
    InputNumberModule
  ],

  exports: [TableComponent]
})
export class TableWithFilterModule { }
