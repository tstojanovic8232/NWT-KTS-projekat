import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IstorijaVoznjiComponent } from './istorija-voznji.component';

describe('IstorijaVoznjiComponent', () => {
  let component: IstorijaVoznjiComponent;
  let fixture: ComponentFixture<IstorijaVoznjiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IstorijaVoznjiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IstorijaVoznjiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
