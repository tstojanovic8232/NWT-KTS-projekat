import { Component } from '@angular/core';
import {User} from "../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {UserLoginService} from "../services/user-login.service";

@Component({
  selector: 'app-nav-bar-client',
  templateUrl: './nav-bar-client.component.html',
  styleUrls: ['./nav-bar-client.component.css']
})
export class NavBarClientComponent {
  user: User = new User();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userLoginService: UserLoginService) { }

  ngOnInit(): void {

  }

  logout() {
    this.userLoginService.logoutUser();
    this.goToHomePage();
  }

  goToHomePage() {
    this.router.navigate(['/']);
  }
}
