import { Component } from '@angular/core';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent {
  activeTab: string = 'User';

  openTab(tab: string) {
    this.activeTab = tab;
  }



}
