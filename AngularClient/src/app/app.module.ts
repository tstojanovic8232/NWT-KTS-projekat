import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms';
import {SocialLoginModule, SocialAuthServiceConfig} from '@abacritt/angularx-social-login';
import {
  FacebookLoginProvider
} from '@abacritt/angularx-social-login';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarLogOutComponent } from './nav-bar-log-out/nav-bar-log-out.component';
import { LoginComponent } from './login/login.component';
import { MapComponent } from './map/map.component';
import { HomePageComponent } from './home-page/home-page.component';
import { RegistrationComponent } from './registration/registration.component';
import { AboutComponent } from './about/about.component';
import { ClientProfileComponent } from './client-profile/client-profile.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { NavBarAdminComponent } from './nav-bar-admin/nav-bar-admin.component';
import { ClientPageComponent } from './client-page/client-page.component';
import { DriverPageComponent } from './driver-page/driver-page.component';
import { NavBarClientComponent } from './nav-bar-client/nav-bar-client.component';
import { NavBarDriverComponent } from './nav-bar-driver/nav-bar-driver.component';
import { ConfirmComponent } from './confirm/confirm.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { BillingInfoComponent } from './billing-info/billing-info.component';
import  'leaflet-routing-machine';
import { ReservationComponent } from './reservation/reservation.component';
import { BillComponent } from './bill/bill.component';
import { IstorijaVoznjiComponent } from './istorija-voznji/istorija-voznji.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { IstorijaVoznjiVozacComponent } from './istorija-voznji-vozac/istorija-voznji-vozac.component';
import { UpcomingTableComponent } from './upcoming-table/upcoming-table.component';

import { BooleanToStatusPipe } from './pipes/boolean-to-status.pipe';
import { AdminUserTableComponent } from './admin-user-table/admin-user-table.component';

import { ZapocniVoznjuComponent } from './zapocni-voznju/zapocni-voznju.component';
import { UserFullNamePipe } from './pipes/user-full-name.pipe';



// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    NavBarLogOutComponent,
    LoginComponent,
    MapComponent,
    HomePageComponent,
    RegistrationComponent,
    AboutComponent,
    ClientProfileComponent,
    AdminPageComponent,
    NavBarAdminComponent,
    ClientPageComponent,
    DriverPageComponent,
    NavBarClientComponent,
    NavBarDriverComponent,
    ConfirmComponent,
    UserInfoComponent,
    BillingInfoComponent,
    ReservationComponent,
    BillComponent,
    IstorijaVoznjiComponent,
    IstorijaVoznjiVozacComponent,
    UpcomingTableComponent,

    BooleanToStatusPipe,
    AdminUserTableComponent,
    ZapocniVoznjuComponent,
    UserFullNamePipe

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule,
    SocialLoginModule
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider('3739656142938759')
          }
        ],
        onError: (err) => {
          console.error(err);
        }
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
