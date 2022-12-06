import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarLogOutComponent } from './nav-bar-log-out.component';

describe('NavBarLogOutComponent', () => {
  let component: NavBarLogOutComponent;
  let fixture: ComponentFixture<NavBarLogOutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavBarLogOutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavBarLogOutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
