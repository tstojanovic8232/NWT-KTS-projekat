import { Component } from '@angular/core';
import {LocalService} from "../services/local.service";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent {
constructor(private localService:LocalService) {
  console.log(localService.getData('user'));
}
}
