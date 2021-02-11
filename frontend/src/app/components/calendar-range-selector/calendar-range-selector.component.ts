import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-calendar-range-selector',
  templateUrl: './calendar-range-selector.component.html',
  styleUrls: ['./calendar-range-selector.component.scss'],
})
export class CalendarRangeSelectorComponent implements OnInit {
  rangeDates: any;

  private fullDataset: any;

  @Input() set initialDataset(value: { labels: string[], datasets: any[] }) {
    if (!this.fullDataset && value) {
      this.reloadData(value);
      this.rangeDates = [new Date(this.fullDataset.labels[0].split('.').reverse().join('-')),
        new Date(this.fullDataset.labels[this.fullDataset.labels.length - 1].split('.').reverse().join('-'))];
    }
  }

  @Output() filterDataChange = new EventEmitter<{ labels: string[], datasets: any[] }>();

  constructor(private datePipe: DatePipe) {
  }


  ngOnInit(): void {
  }

  reloadData(data): void {
    this.fullDataset = data;
    this.filterData();
  }

  filterData(): void {
    if (!this.fullDataset || !this.rangeDates || this.rangeDates.length !== 2 ||
      this.rangeDates.some(item => item == null)) {
      return;
    }
    const idxDates = [
      this.fullDataset.labels.indexOf(this.datePipe.transform(this.rangeDates[0], 'dd.MM.yyyy')),
      this.fullDataset.labels.indexOf(this.datePipe.transform(this.rangeDates[1], 'dd.MM.yyyy')),
    ];
    this.filterDataChange.emit({
      datasets: this.fullDataset.datasets.map(item => {
        const x = {...item};
        x.data = x.data.slice(idxDates[0], idxDates[1] + 1);
        return x;
      }),
      labels: this.fullDataset.labels.slice(idxDates[0], idxDates[1] + 1),
    });
  }

}
