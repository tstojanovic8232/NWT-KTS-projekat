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
import {IstorijaVoznjiVozacComponent} from "./istorija-voznji-vozac/istorija-voznji-vozac.component";

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {
    path: 'register', component: RegistrationComponent
  },
  {path: 'confirm', component: ConfirmComponent},

  {
    path: 'admin', component: AdminPageComponent,
    children:
      [
        {
          path: 'profile',
          component: ClientProfileComponent,
        },
        /*{
          path: 'drivers',
          component: DriversListComponent,
        },
        {
          path: 'clients',
          component: ClientsListComponent,
        },*/
      ],
  },
  {
    path: 'client-home', component: ClientPageComponent,

    children:
      [
        {path:'', component: ReservationComponent},
        {
          path: 'profile',
          component: ClientProfileComponent,
        },
        {
          path: 'receipt',
          component: BillComponent,
        },
        {
          path:'rides',
          component: IstorijaVoznjiComponent,
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
          path: 'historyClients',
          component: IstorijaVoznjiVozacComponent,
        },
        /*{
          path: 'drivers',
          component: DriversListComponent,
        },
        {
          path: 'clients',
          component: ClientsListComponent,
        },*/
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
