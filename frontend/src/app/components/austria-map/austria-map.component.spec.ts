import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AustriaMapComponent } from './austria-map.component';

describe('AustriaMapComponent', () => {
  let component: AustriaMapComponent;
  let fixture: ComponentFixture<AustriaMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AustriaMapComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AustriaMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
