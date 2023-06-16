import {Component, OnInit} from '@angular/core';
import {User} from '../model/user';
import {UserLoginService} from '../services/user-login.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserRole} from "../model/user-role";
import {LocalService} from '../services/local.service';
import { CredentialResponse, PromptMomentNotification } from 'google-one-tap';
import {FacebookLoginProvider, SocialAuthService} from "@abacritt/angularx-social-login";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  userr: any
  loggedIn: any

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userLoginService: UserLoginService,
    private localService: LocalService, private authService: SocialAuthService) {


  }

  ngOnInit(): void {

    // @ts-ignore
    window.onGoogleLibraryLoad = () => {
      // @ts-ignore
      google.accounts.id.initialize({
        client_id: '622369180841-fbatp9ei09717bm0hu30qkb6u5mhvn73.apps.googleusercontent.com',
        callback: this.handleCredentialResponse.bind(this),
        auto_select: false,
        cancel_on_tap_outside: true
      });
      // @ts-ignore
      google.accounts.id.renderButton(
        // @ts-ignore
        document.getElementById("buttonDiv"),
        { theme: "outline", size: "medium", locale:'en'  }
      );
      // @ts-ignore
      google.accounts.id.prompt((notification: PromptMomentNotification) => {});
    };
  }

  async handleCredentialResponse(response: CredentialResponse) {
    await this.userLoginService.LoginWithGoogle(response.credential).subscribe(data => {
      this.localService.saveData('user', (data as UserRole).email)
      this.localService.saveData('role', (data as UserRole).role)
      this.gotoUserPage(data as UserRole);
    });
  }


  signInWithFB(): void {
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);

  }

  userLogin() {
    console.log(this.user);
    this.userLoginService.loginUser(this.user).subscribe(data => {
      this.localService.saveData('user', (data as UserRole).email)
      this.localService.saveData('role', (data as UserRole).role)
      this.gotoUserPage(data as UserRole);
    });
  }

  gotoUserPage(userRole: UserRole) {
    switch (userRole.role) {
      case 'Administrator':
        this.router.navigate(['/admin']);
        break;
      case 'Klijent':
        this.router.navigate(['/client-home']);
        break;
      case 'Vozac':
        this.router.navigate(['/driver-home']);
        break;
      default:
        this.router.navigate(['/']);
    }
  }



}
