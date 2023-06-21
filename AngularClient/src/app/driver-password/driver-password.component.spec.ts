import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DriverPasswordComponent } from './driver-password.component';

describe('DriverPasswordComponent', () => {
  let component: DriverPasswordComponent;
  let fixture: ComponentFixture<DriverPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DriverPasswordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DriverPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
