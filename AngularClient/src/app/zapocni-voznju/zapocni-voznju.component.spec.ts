import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZapocniVoznjuComponent } from './zapocni-voznju.component';

describe('ZapocniVoznjuComponent', () => {
  let component: ZapocniVoznjuComponent;
  let fixture: ComponentFixture<ZapocniVoznjuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZapocniVoznjuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZapocniVoznjuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
