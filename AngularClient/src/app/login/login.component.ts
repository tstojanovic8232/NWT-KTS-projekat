import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserLoginService } from '../services/user-login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();

  constructor(private userLoginService: UserLoginService) { }

  ngOnInit(): void {

  }
  userLogin() {
    this.userLoginService.loginUser(this.user).subscribe(data => {
      alert("Login successful!")
    }, error => {
      alert("Please enter the correct email and password!")
    });
  }
}
