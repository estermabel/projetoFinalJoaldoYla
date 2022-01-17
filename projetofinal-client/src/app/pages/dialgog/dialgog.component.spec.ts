import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialgogComponent } from './dialgog.component';

describe('DialgogComponent', () => {
  let component: DialgogComponent;
  let fixture: ComponentFixture<DialgogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialgogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialgogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
