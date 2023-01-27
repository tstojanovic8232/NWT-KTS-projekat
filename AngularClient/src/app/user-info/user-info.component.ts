import {Component, Input} from '@angular/core';
import {UserProfile} from "../model/user-profile";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent {
  @Input() user: UserProfile;
}
