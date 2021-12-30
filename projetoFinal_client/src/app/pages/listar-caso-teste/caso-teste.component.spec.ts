import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasoTesteComponent } from './caso-teste.component';

describe('CasoTesteComponent', () => {
  let component: CasoTesteComponent;
  let fixture: ComponentFixture<CasoTesteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CasoTesteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CasoTesteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
