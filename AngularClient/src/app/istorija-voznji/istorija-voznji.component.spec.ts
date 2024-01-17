import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IstorijaVoznjiComponent } from './istorija-voznji.component';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";

describe('IstorijaVoznjiComponent', () => {
  let component: IstorijaVoznjiComponent;
  let fixture: ComponentFixture<IstorijaVoznjiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IstorijaVoznjiComponent ],
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IstorijaVoznjiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
