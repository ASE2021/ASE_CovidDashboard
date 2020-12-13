import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AustriaMapComponent} from './austria-map.component';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {InputNumberModule} from 'primeng/inputnumber';
import {FormsModule} from '@angular/forms';
import {PanelModule} from 'primeng/panel';


@NgModule({
  declarations: [AustriaMapComponent],
    imports: [
        CommonModule,
        LeafletModule,
        InputNumberModule,
        FormsModule,
        PanelModule,
    ],
  exports: [AustriaMapComponent],
})
export class AustriaMapModule {
}
