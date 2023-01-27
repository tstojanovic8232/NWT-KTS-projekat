import { Component } from '@angular/core';
import {LocalService} from "../services/local.service";
import {UserService} from "../services/user.service";
import {UserRole} from "../model/user-role";
import {UserProfile} from "../model/user-profile";

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent {
  activeTab: string = 'User';
  userRole : UserRole = new UserRole();
  email:string|null;
  role:string|null;
  user : UserProfile;

  constructor(private localService:LocalService,
              private userService:UserService) {
    this.getUserData();
  }

  openTab(tab: string) {
    this.activeTab = tab;
  }

  getUserData() {
    this.email = this.localService.getData('user');
    this.role = this.localService.getData('role');
    if(this.email && this.role) {
      this.userRole.email = this.email;
      this.userRole.role = this.role;
        this.userService.getUser(this.userRole).subscribe(data => {
            this.saveUser(data as UserProfile);
            console.log(data);
          });
    }
  }

  saveUser(user:UserProfile) {
    this.user=user;
  }

}
