import { Component } from '@angular/core';
import {UserRegister} from "../model/user-register";
import {ActivatedRoute, Router} from "@angular/router";
import {UserRegisterService} from "../services/user-register.service";

@Component({
  selector: 'app-register-driver',
  templateUrl: './register-driver.component.html',
  styleUrls: ['./register-driver.component.css']
})
export class RegisterDriverComponent {
  user:UserRegister=new UserRegister();
  pass:String='';
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userRegisterService: UserRegisterService) { }

  ngOnInit(): void {

  }
  register() {
    console.log(this.user);
    console.log(this.pass);
    if(this.user.password==this.pass){
      this.userRegisterService.registerDriver(this.user).subscribe(result => this.router.navigate(['/admin/drivers']));

    }else{
      alert("Password mismatch!");
    }

  }
}
