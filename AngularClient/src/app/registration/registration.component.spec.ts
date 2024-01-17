import { ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';
import { RegistrationComponent } from './registration.component';
import { ActivatedRoute, Router } from '@angular/router';
import { UserRegisterService } from '../services/user-register.service';
import { of } from 'rxjs';
import { NgxPaginationModule } from "ngx-pagination";
import { AppRoutingModule } from "../app-routing.module";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { UserRegister } from "../model/user-register";
import {RegisterDriverComponent} from "../register-driver/register-driver.component";

describe('RegistrationComponent', () => {
  let component: RegistrationComponent;
  let fixture: ComponentFixture<RegistrationComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockActivatedRoute: ActivatedRoute;
  let mockUserRegisterService: jasmine.SpyObj<UserRegisterService>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockActivatedRoute = {} as ActivatedRoute;
    mockUserRegisterService = jasmine.createSpyObj('UserRegisterService', ['registerUser']);

    await TestBed.configureTestingModule({
      declarations: [ RegistrationComponent ],
      providers: [
        // Provide the mock service
        { provide: UserRegisterService, useValue: mockUserRegisterService }
      ],
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ]

      // ... other imports
    })
      .compileComponents();

    fixture = TestBed.createComponent(RegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call registerUser service method and navigate on successful registration', fakeAsync(() => {
    const mockUser = {
      name: 'John',
      lastname: 'Doe',
      password: 'password123',
      email: 'john.doe@example.com',
      phone: '123456789',
      city: 'SomeCity'
    };

    component.user = mockUser;
    component.pass = 'password123';

    // Setup the mock service to return a successful response
    mockUserRegisterService.registerUser.and.returnValue(of({ status: 'success' }));

    // Call the register method
    component.register();

    // Use tick to simulate the passage of time for asynchronous operations
    tick();

    // Verify that the registerUser method was called with the expected data
    expect(mockUserRegisterService.registerUser).toHaveBeenCalledWith(mockUser);

    // Verify that the router.navigate method was called with the expected route
  }));

  it('should show alert on password mismatch', () => {
    const mockUser = {
      name: 'John',
      lastname: 'Doe',
      password: 'password123',
      email: 'john.doe@example.com',
      phone: '123456789',
      city: 'SomeCity'
    };
    component.user = mockUser;
    component.pass = 'incorrectPassword';

    spyOn(window, 'alert'); // spy on the window.alert method

    // Call the register method
    component.register();

    // Verify that the alert method was called with the expected message
    expect(window.alert).toHaveBeenCalledWith('Password mismatch!');
  });
});
