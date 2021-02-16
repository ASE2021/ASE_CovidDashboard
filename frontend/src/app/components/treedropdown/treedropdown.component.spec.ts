import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreedropdownComponent } from './treedropdown.component';

describe('TreedropdownComponent', () => {
  let component: TreedropdownComponent;
  let fixture: ComponentFixture<TreedropdownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TreedropdownComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TreedropdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
