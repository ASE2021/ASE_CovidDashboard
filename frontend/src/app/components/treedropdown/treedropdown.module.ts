import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TreedropdownComponent } from './treedropdown.component';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {TreeTableModule} from 'primeng/treetable';
import {CheckboxModule} from 'primeng/checkbox';
import {FormsModule} from '@angular/forms';
import {ButtonModule} from 'primeng/button';
import {RippleModule} from 'primeng/ripple';
import {ChartModule} from 'primeng/chart';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {MultiSelectModule} from 'primeng/multiselect';
import {ListboxModule} from 'primeng/listbox';
import {SelectButtonModule} from 'primeng/selectbutton';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputSwitchModule} from 'primeng/inputswitch';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {InputTextModule} from 'primeng/inputtext';



@NgModule({
  declarations: [TreedropdownComponent],
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
  exports: [
    TreedropdownComponent,
  ],
})
export class TreedropdownModule { }
