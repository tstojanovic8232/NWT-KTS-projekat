import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IstorijaVoznjiVozacComponent } from './istorija-voznji-vozac.component';

describe('IstorijaVoznjiVozacComponent', () => {
  let component: IstorijaVoznjiVozacComponent;
  let fixture: ComponentFixture<IstorijaVoznjiVozacComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IstorijaVoznjiVozacComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IstorijaVoznjiVozacComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
