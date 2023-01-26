import { Component } from '@angular/core';
import {User} from "../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {UserLoginService} from "../services/user-login.service";

@Component({
  selector: 'app-nav-bar-driver',
  templateUrl: './nav-bar-driver.component.html',
  styleUrls: ['./nav-bar-driver.component.css']
})
export class NavBarDriverComponent {
  user: User = new User();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userLoginService: UserLoginService) { }

  ngOnInit(): void {

  }

  logout() {
    this.goToHomePage();
  }

  goToHomePage() {
    this.router.navigate(['/']);
  }
}
