import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteEditorComponent } from './teste-editor.component';

describe('TesteEditorComponent', () => {
  let component: TesteEditorComponent;
  let fixture: ComponentFixture<TesteEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteEditorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
