import {Component} from '@angular/core';
import {User} from "../model/user";
import {UserData} from "../model/user-data";
import {UserService} from "../services/user.service";
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {VerifyService} from "../services/verify.service";

@Component({
  selector: 'app-driver-password',
  templateUrl: './driver-password.component.html',
  styleUrls: ['./driver-password.component.css']
})
export class DriverPasswordComponent {
  update: UserData = new UserData();
  email: string;
  pass: string;
  newpass: string;
  role: string | null;


  constructor(private userService: UserService, private localService: LocalService, private route: ActivatedRoute,
              private router: Router,
              private verifyService: VerifyService) {
    this.route.queryParamMap.subscribe((queryParams) => {
      const token = queryParams.get('token');
      verifyService.getUser(token).subscribe((data: any) => {
        this.email = data;
      });
    });
  }

  updatePass() {
    let user: User = new User();
    user.email = this.email;
    if (this.pass == this.newpass) {
      user.password = this.newpass;
    } else {
      alert('Password mismatch!')
    }
    console.log(user);
    this.userService.setPass(user).subscribe(() => {
    this.router.navigate(['/login']);
    })
  }
}
