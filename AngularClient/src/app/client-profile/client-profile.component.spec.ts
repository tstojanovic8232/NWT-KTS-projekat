import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileComponent } from './client-profile.component';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";
import {UserInfoComponent} from "../user-info/user-info.component";

describe('ClientProfileComponent', () => {
  let component: ClientProfileComponent;
  let fixture: ComponentFixture<ClientProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientProfileComponent,UserInfoComponent ],
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
