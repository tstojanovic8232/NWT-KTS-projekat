import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarDriverComponent } from './nav-bar-driver.component';

describe('NavBarDriverComponent', () => {
  let component: NavBarDriverComponent;
  let fixture: ComponentFixture<NavBarDriverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavBarDriverComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavBarDriverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
