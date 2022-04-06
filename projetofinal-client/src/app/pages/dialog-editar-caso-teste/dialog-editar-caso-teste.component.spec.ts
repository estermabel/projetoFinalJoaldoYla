import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEditarCasoTesteComponent } from './dialog-editar-caso-teste.component';

describe('DialogEditarCasoTesteComponent', () => {
  let component: DialogEditarCasoTesteComponent;
  let fixture: ComponentFixture<DialogEditarCasoTesteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogEditarCasoTesteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEditarCasoTesteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
