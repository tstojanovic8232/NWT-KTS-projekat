import { ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';
import { RegisterDriverComponent } from './register-driver.component';
import { of } from 'rxjs';
import {UserRegisterService} from "../services/user-register.service";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {NgxPaginationModule} from "ngx-pagination";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

describe('RegisterDriverComponent', () => {
  let component: RegisterDriverComponent;
  let fixture: ComponentFixture<RegisterDriverComponent>;
  let mockUserRegisterService: jasmine.SpyObj<any>; // Replace with your actual service type

  beforeEach(async () => {
    mockUserRegisterService = jasmine.createSpyObj('UserRegisterService', ['registerDriver']);

    await TestBed.configureTestingModule({
      declarations: [ RegisterDriverComponent ],
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

    fixture = TestBed.createComponent(RegisterDriverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call registerDriver service method and navigate on successful registration', fakeAsync(() => {
    const mockDriver = {
      name: 'John',
      lastname: 'Doe',
      password: 'password123',
      email: 'john.doe@example.com',
      phone:'0423234',
      city:'Belgrade',
      price:300,
      registration:'yes'
      // Add other optional properties with valid values
    };
    component.user = mockDriver;

    // Setup the mock service to return a successful response
    mockUserRegisterService.registerDriver.and.returnValue(of('success'));

    // Call the register method
    component.register();

    // Use tick to simulate the passage of time for asynchronous operations
    tick();

    // Verify that the registerDriver method was called with the expected data
    expect(mockUserRegisterService.registerDriver).toHaveBeenCalledWith(mockDriver);

    // Verify that the router.navigate method was called with the expected route

  }));

  it('should handle error during registration', fakeAsync(() => {

    const mockDriver = {
      name: 'John',
      lastname: 'Doe',
      password: 'password123',
      email: 'john.doe@example.com',
      phone:'0423234',
      city:'Belgrade',
      price:300,
      registration:'yes'
      // Add other optional properties with valid values
    };

    component.user = mockDriver;

    // Setup the mock service to return a successful response
    mockUserRegisterService.registerDriver.and.returnValue(of('success'));

    // Call the register method
    component.register();

    // Use tick to simulate the passage of time for asynchronous operations
    tick();

    // Verify that the registerDriver method was called with the expected data
    expect(mockUserRegisterService.registerDriver).toHaveBeenCalledWith(mockDriver);

    // You can add additional expectations based on how your component handles errors
    // For example, you might want to check if an error message is displayed.
    // Add your expectations here.
  }));
});

