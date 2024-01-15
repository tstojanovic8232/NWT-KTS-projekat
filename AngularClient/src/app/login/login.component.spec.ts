import {ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from "@angular/forms";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {BrowserModule} from "@angular/platform-browser";
import {of} from 'rxjs';

import {LoginComponent} from './login.component';
import {UserLoginService} from '../services/user-login.service';
import {LocalService} from '../services/local.service';
import {UserRole} from '../model/user-role';


describe('LoginComponent', () => {
    let component: LoginComponent;
    let fixture: ComponentFixture<LoginComponent>;
    let mockUserLoginService: jasmine.SpyObj<UserLoginService>;
    let mockLocalService: jasmine.SpyObj<LocalService>;

    beforeEach(async () => {
        mockUserLoginService = jasmine.createSpyObj('UserLoginService', ['LoginWithGoogle', 'LoginWithFacebook', 'loginUser']);
        mockLocalService = jasmine.createSpyObj('LocalService', ['saveData']);

        await TestBed.configureTestingModule({
            declarations: [LoginComponent],
            providers: [
                {provide: UserLoginService, useValue: mockUserLoginService},
                {provide: LocalService, useValue: mockLocalService}
            ],
            imports: [
                RouterTestingModule.withRoutes([
                    {path: 'admin', redirectTo:''},
                    {path: 'client-home', redirectTo:''},
                    {path: 'driver-home', redirectTo:''}
                    ]),
                FormsModule,
                HttpClientTestingModule,
                BrowserModule
            ]
        })
            .compileComponents();

        fixture = TestBed.createComponent(LoginComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should handle Google credential response', fakeAsync(() => {
        const mockResponse = {credential: 'fakeCredential'} as any;
        mockUserLoginService.LoginWithGoogle.and.returnValue(of({
            email: 'test@example.com',
            role: 'Klijent'
        } as UserRole));

        component.handleCredentialResponse(mockResponse);
        tick();

        expect(mockUserLoginService.LoginWithGoogle).toHaveBeenCalledWith('fakeCredential');
        expect(mockLocalService.saveData).toHaveBeenCalledWith('user', 'test@example.com');
        expect(mockLocalService.saveData).toHaveBeenCalledWith('role', 'Klijent');
    }));


    it('should handle user login', fakeAsync(() => {
        const mockUser = {email: 'test@example.com', role: 'Administrator'} as UserRole;
        mockUserLoginService.loginUser.and.returnValue(of(mockUser));

        component.user = {username: 'test', password: 'password'} as any;
        component.userLogin();
        tick();

        expect(mockUserLoginService.loginUser).toHaveBeenCalledWith(component.user);
        expect(mockLocalService.saveData).toHaveBeenCalledWith('user', 'test@example.com');
        expect(mockLocalService.saveData).toHaveBeenCalledWith('role', 'Administrator');

    }));
});
