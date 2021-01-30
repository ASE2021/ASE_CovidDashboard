import {Component, OnInit} from '@angular/core';
import {CovidService} from '../../services/covid.service';
import {SocketService} from '../../services/socket/socket.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent implements OnInit {

  data: Array<any>;

  columns: { field: string, label: string }[];
  totals: any;

  constructor(private covidService: CovidService, private socketService: SocketService) {
  }

  ngOnInit(): void {
    this.loadTableData();
    this.socketService.connectAndObserveNewData()
      .subscribe(() => this.loadTableData());
  }


  private async loadTableData(): Promise<void> {
    this.data = await this.covidService.getGeneralSituationPerDate('10');

    this.columns =
      Object.keys(this.data[0].values).map(item => ({
        label: item[0].toUpperCase()
          + item.substring(1, item.length).replace(/([A-Z])/g, ' $1')
            .trim()
            .toLowerCase(), field: item,
      }));

    this.totals =
      this.data.reduce((obj, curr) => {
        Object.entries(curr.values).forEach(item => obj[item[0]] += item[1]);
        return obj;
      }, {...this.data[0].values});
  }
}
