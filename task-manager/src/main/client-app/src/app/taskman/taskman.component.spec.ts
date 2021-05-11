import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskmanComponent } from './taskman.component';

describe('TaskmanComponent', () => {
  let component: TaskmanComponent;
  let fixture: ComponentFixture<TaskmanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskmanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskmanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
