import {AfterViewInit, ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TreeTable} from 'primeng/treetable';
import {Area} from '../../model/area';
import {TreeNode} from 'primeng/api';


@Component({
  selector: 'app-treedropdown',
  templateUrl: './treedropdown.component.html',
  styleUrls: ['./treedropdown.component.scss'],
})
export class TreedropdownComponent implements OnInit, AfterViewInit {
  selectedDistricts: any;
  selectedRegionNames: any;
  selectedAreas: any;
  latestSelectedDistrict: any;
  latestSelectedArea: any;

  values: TreeNode[];

  @Input() set regionData(value: TreeNode[]) {
    this.values = value;
    if (this.initSelected && value) {
      if (this.initSelected.areaId <= 10) {
        this.selectedAreas = [this.values.find(item => item.data.areaId === this.initSelected.areaId).data];
      } else {
        this.selectedDistricts = [this.values.find(item => this.initSelected.areaId.toString(10)
          .startsWith(item.data.areaId.toString(10)))
          .children.find(item => item.data.areaId === this.initSelected.areaId)];
      }
    }
  }

  get regionData(): TreeNode[] {
    return this.values;
  }

  @Input() initSelected: Area;
  @Input() multipleSelection = true;
  @Output() regionChange = new EventEmitter<Area[]>();

  constructor(private cd: ChangeDetectorRef) {
  }

  ngOnInit(): void {

  }

  regionChanged(select, val): void {
    if (!this.multipleSelection && select) {
      this.selectedAreas = (this.selectedAreas || []).filter(item => item.areaId === val.areaId);
      this.selectedDistricts = (this.selectedDistricts || []).filter(item => item.data.areaId === val.areaId);
    }
    const selectedValues = [
      ...this.selectedAreas || [],
      ...this.selectedDistricts?.filter(item => item.parent).map(item => item.data) || [],
    ];

    if (this.multipleSelection || select) {
      this.regionChange.emit(selectedValues);
      this.selectedRegionNames = selectedValues.map(item => item.areaName).join(', ');
    }
    this.cd.detectChanges();
  }


  onFilter(tt: TreeTable, filterValue: any): void {
    setTimeout(() => {
      tt.filteredNodes.forEach(item => item.children.some(c => c.data.areaName.includes(filterValue)) ?
        item.expanded = true : null);
      tt.updateSerializedValue();
    }, 300);
  }

  ngAfterViewInit(): void {
    setTimeout(() => {

    }, 500);
  }
}
