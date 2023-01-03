import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserLoginService } from '../services/user-login.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar-admin',
  templateUrl: './nav-bar-admin.component.html',
  styleUrls: ['./nav-bar-admin.component.css']
})
export class NavBarAdminComponent implements OnInit {

  user: User = new User();

  constructor(
    private route: ActivatedRoute,
      private router: Router,
      private userLoginService: UserLoginService) { }

  ngOnInit(): void {

  }

  logout() {
    
  }

  goToHomePage() {
    this.router.navigate(['/']);
  }
}
