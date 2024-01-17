import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZapocniVoznjuComponent } from './zapocni-voznju.component';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";

describe('ZapocniVoznjuComponent', () => {
  let component: ZapocniVoznjuComponent;
  let fixture: ComponentFixture<ZapocniVoznjuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZapocniVoznjuComponent ],
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZapocniVoznjuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
