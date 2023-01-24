import { Component } from '@angular/core';
import {UserRegister} from "../model/user-register";
import {ActivatedRoute, Router} from "@angular/router";
import {UserRegisterService} from "../services/user-register.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
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
      this.userRegisterService.registerUser(this.user).subscribe(result => this.router.navigate(['/login']));

    }else{
      alert("Lozinke se ne poklapaju!");
    }

  }
}
