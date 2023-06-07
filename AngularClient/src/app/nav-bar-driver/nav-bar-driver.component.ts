import {Component} from '@angular/core';
import {User} from "../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {UserLoginService} from "../services/user-login.service";
import {UserService} from "../services/user.service";
import {LocalService} from "../services/local.service";
import {UserRole} from "../model/user-role";

@Component({
  selector: 'app-nav-bar-driver',
  templateUrl: './nav-bar-driver.component.html',
  styleUrls: ['./nav-bar-driver.component.css']
})
export class NavBarDriverComponent {
  user: User = new User();
  switchState: boolean;
  userRole: UserRole = new UserRole();


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userLoginService: UserLoginService,
    private userService: UserService,
    private localService: LocalService) {
  }

  ngOnInit(): void {
    let email = this.localService.getData('user');
    let role = this.localService.getData('role');
    if (email && role) {
      this.userRole.email = email;
      this.userRole.role = role;
    }
    this.userService.getStatus(this.userRole).subscribe(data => {
      this.setStatus(data);
    });
  }
  setStatus(data:any) {
    if (typeof data == 'boolean') {
      this.switchState=data;
    } else {
      console.log(data);
    }
  }
  logout() {
    this.userLoginService.logoutUser();
    this.goToHomePage();
  }

  goToHomePage() {
    this.router.navigate(['/']);
  }

  switchStatus() {
    let switchString = this.switchState.toString();
    this.localService.saveData("status",switchString);
    this.userService.onSwitchChange(this.switchState, this.userRole.email).subscribe();
  }

}
