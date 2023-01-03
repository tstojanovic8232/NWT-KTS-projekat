import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserLoginService } from '../services/user-login.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();

  constructor(
    private route: ActivatedRoute,
      private router: Router,
      private userLoginService: UserLoginService) { }

  ngOnInit(): void {

  }
  userLogin() {
    this.userLoginService.loginUser(this.user).subscribe(result => this.gotoUserPage("admin"));
  }

  gotoUserPage(role : string) {
    switch(role)
    {
      case "admin":
        this.router.navigate(['/admin']);
        break;
      default:
        this.router.navigate(['/']);
    }

  }
}
