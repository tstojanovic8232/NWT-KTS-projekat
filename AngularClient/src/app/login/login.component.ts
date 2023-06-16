import {Component, OnInit} from '@angular/core';
import {User} from '../model/user';
import {UserLoginService} from '../services/user-login.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserRole} from "../model/user-role";
import {LocalService} from '../services/local.service';
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
    this.authService.authState.subscribe((userr) => {
      this.userr = userr;
      this.loggedIn = (userr != null);
      console.log(this.userr)
      this.userLogin()
    });
  }


  signInWithFB(): void {
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);

  }

  userLogin() {
    console.log(this.user);
    this.userLoginService.loginUser(this.user).subscribe(data => {
      this.gotoUserPage(data as UserRole);
      this.localService.saveData('user', (data as UserRole).email)
      this.localService.saveData('role', (data as UserRole).role)
      console.log(this.localService.getData('user'))
      console.log(this.localService.getData('role'))
      console.log(data);
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

  loginUser = (data: any) => {
    console.log(data);
  };

}
