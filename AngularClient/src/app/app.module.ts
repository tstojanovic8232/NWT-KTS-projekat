import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms';

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
    NavBarDriverComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
