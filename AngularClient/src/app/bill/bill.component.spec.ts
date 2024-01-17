import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillComponent } from './bill.component';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";

describe('BillComponent', () => {
  let component: BillComponent;
  let fixture: ComponentFixture<BillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BillComponent ],
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
