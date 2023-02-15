import {Component, Input, Output} from '@angular/core';
import {UserProfile} from "../model/user-profile";
import {UserData} from "../model/user-data";
import {UserService} from "../services/user.service";
import {LocalService} from "../services/local.service";
import {Router} from "@angular/router";
import {User} from "../model/user";


@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent {
  @Input() user: UserProfile=new UserProfile();
  update: UserData=new UserData();
  email: string;
  pass: string;
  oldpass: string;
  newpass: string;
  role: string | null;

  constructor(private userService: UserService, private localService: LocalService, private router: Router) {
    this.role = this.localService.getData('role');
  }

  updateData() {
    this.update.email = this.localService.getData('user');

    console.log(this.update);
    this.userService.updateUser(this.update).subscribe();
  }

  updatePass() {
    let user: User=new User();
    user.email = this.localService.getData('user');

    if(this.pass==this.newpass){
      user.password=this.newpass;
    }else{
      alert('Passwords don\'t match')
    }
    console.log(user);
    this.userService.updatePass(user).subscribe(() => {
      if (this.role == 'Klijent') {
        this.router.navigate(['/client-home/profile']);
      } else if (this.role == 'Vozac') {
        this.router.navigate(['/driver-home/profile']);
      }
    })
  }

  updateEmail() {

    let oldEmail = this.localService.getData('user');

    let email:User=new User();
    email.email=oldEmail;
    email.password=this.email;
    console.log(this.email);

    this.userService.updateEmail(email).subscribe(() => {
      this.localService.removeData('user');
      this.localService.saveData('user',this.email);
      if (this.role == 'Klijent') {
        this.router.navigate(['/client-home/profile']);
      } else if (this.role == 'Vozac') {
        this.router.navigate(['/driver-home/profile']);
      }
    })
  }
}
