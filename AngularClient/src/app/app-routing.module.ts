import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { HomePageComponent } from "./home-page/home-page.component";
import { RegistrationComponent } from "./registration/registration.component";
import { ClientProfileComponent } from "./client-profile/client-profile.component";
import { AdminPageComponent } from './admin-page/admin-page.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
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
  }
  //{ path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
