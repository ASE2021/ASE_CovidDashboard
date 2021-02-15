import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {AustriaMapComponent} from './austria-map.component';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {InputNumberModule} from 'primeng/inputnumber';
import {FormsModule} from '@angular/forms';
import {PanelModule} from 'primeng/panel';
import {InputSwitchModule} from 'primeng/inputswitch';
import {ProgressBarModule} from 'primeng/progressbar';


@NgModule({
  declarations: [AustriaMapComponent],
  imports: [
    CommonModule,
    LeafletModule,
    InputNumberModule,
    FormsModule,
    PanelModule,
    InputSwitchModule,
    ProgressBarModule,
  ],
  providers: [DatePipe],
  exports: [AustriaMapComponent],
})
export class AustriaMapModule {
}
