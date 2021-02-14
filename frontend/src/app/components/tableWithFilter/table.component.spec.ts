import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableComponent } from './table.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {CommonModule} from '@angular/common';
import {TableModule} from 'primeng/table';
import {MultiSelectModule} from 'primeng/multiselect';
import {FormsModule} from '@angular/forms';
import {ProgressSpinnerModule} from 'primeng/progressspinner';

describe('TableComponent', () => {
  let component: TableComponent;
  let fixture: ComponentFixture<TableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, CommonModule,
        TableModule,
        MultiSelectModule,
        FormsModule,
        ProgressSpinnerModule],
      declarations: [ TableComponent ],
      providers: [HttpClient, {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
        {provide: 'BACKEND_NOTIFICATION_URL', useValue: environment.backendNotificationUrl},
        {provide: 'BACKEND_NOTIFICATION_PORT', useValue: environment.backendNotificationPort}]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
