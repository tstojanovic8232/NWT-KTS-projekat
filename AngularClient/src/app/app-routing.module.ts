import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomePageComponent} from "./home-page/home-page.component";
import {RegistrationComponent} from "./registration/registration.component";
import {ClientProfileComponent} from "./client-profile/client-profile.component";
import {AdminPageComponent} from './admin-page/admin-page.component';
import {ConfirmComponent} from "./confirm/confirm.component";
import {ClientPageComponent} from "./client-page/client-page.component";
import {MapComponent} from "./map/map.component";
import {DriverPageComponent} from "./driver-page/driver-page.component";
import {ReservationComponent} from "./reservation/reservation.component";
import {BillComponent} from "./bill/bill.component";
import {IstorijaVoznjiComponent} from "./istorija-voznji/istorija-voznji.component";
import {UpcomingTableComponent} from "./upcoming-table/upcoming-table.component";

import {AboutComponent} from "./about/about.component";
import {AdminUserTableComponent} from "./admin-user-table/admin-user-table.component";

import {ZapocniVoznjuComponent} from "./zapocni-voznju/zapocni-voznju.component";
import {RegisterDriverComponent} from "./register-driver/register-driver.component";
import {DriverPasswordComponent} from "./driver-password/driver-password.component";


const routes: Routes = [
  {
    path: '', component: HomePageComponent,
    children: [
      {path: 'about', component: AboutComponent},
      {path: '', component: MapComponent},
    ]
  },
  {path: 'login', component: LoginComponent},
  {
    path: 'register', component: RegistrationComponent
  },
  {path: 'confirm', component: ConfirmComponent},
  {path: 'set-pass', component: DriverPasswordComponent},
  {
    path: 'admin', component: AdminPageComponent,
    children:
      [
        {
          path: 'profile',
          component: ClientProfileComponent,
        },
        {
          path: 'drivers',
          component: AdminUserTableComponent,
        },
        {
          path: 'drivers/add',
          component: RegisterDriverComponent,
        },
        {
          path: 'clients',
          component: AdminUserTableComponent,
        },
        {
          path: 'history',
          component: IstorijaVoznjiComponent,
          data: {
            loggedIn: false
          }
        },
      ],
  },
  {
    path: 'client-home', component: ClientPageComponent,

    children:
      [
        {path: '', component: ReservationComponent},
        {
          path: 'profile',
          component: ClientProfileComponent,
        },
        {
          path: 'receipt',
          component: BillComponent,
        },
        {
          path: 'history',
          component: IstorijaVoznjiComponent,
          data: {
            loggedIn: true,
            driver: false
          }
        },

        /*{
          path: 'clients',
          component: ClientsListComponent,
        },*/
      ],
  },
  {
    path: 'driver-home', component: DriverPageComponent,
    children:
      [
        {
          path: 'profile',
          component: ClientProfileComponent,
        },
        {
          path: 'history',
          component: IstorijaVoznjiComponent,
          data: {
            loggedIn: true,
            driver: true
          }
        },
        {
          path: '',
          component: UpcomingTableComponent,
        },
        {
          path: 'driving',
          component: ZapocniVoznjuComponent,
        },
      ],
  }
  //{ path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
