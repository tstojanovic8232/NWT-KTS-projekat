import {Component, Output} from '@angular/core';
import * as L from "leaflet";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import  {FormsModule,Form,FormGroup} from "@angular/forms";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {
  from:string;
  to:string;
  protected form={
    from:"",to:"",nap:"",tip:""
  }

  private map: L.Map;
  private centroid: L.LatLngExpression = [45.2396, 19.8227]; //
  addressFrom: string;
  addressTo: string;
  private options: any;
  private markers: L.Marker[] = [];
  private markers2: L.Marker[] = [];

  private initMap(): void {
    this.map = L.map('map', {
      center: this.centroid,
      zoom: 12
    });

    this.options = {};


    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 10,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    // create 5 random jitteries and add them to map


    tiles.addTo(this.map);

  }


  constructor(private http: HttpClient,private router:Router) {

  }

  ngOnInit(): void {
    this.initMap();

  }


  updateMapFrom() {
    this.markers2 = this.markers2.filter(marker => {
      marker.remove();

      return false;
    });

    const url = `https://nominatim.openstreetmap.org/search?q=${this.addressFrom}&format=json`;
    this.http.get(url).subscribe(data => {
      // @ts-ignore
      const coordinates = data[0];
      this.setFrom(coordinates);
      this.map.setView(new L.LatLng(coordinates.lat, coordinates.lon), 18);
      const marker = L.marker([coordinates.lat, coordinates.lon], {title: this.addressFrom}).addTo(this.map);
      this.markers2.push(marker);
      // check if both addresses have been set
      if (this.markers2.length == 1 && this.markers.length==1) {
        // create routing control

        var Routing: any = require('leaflet-routing-machine');
        let control: any;
        // @ts-ignore
        control = L.Routing.control({

          draggableWaypoints:false,
          waypoints: [
            L.latLng(this.markers2[0].getLatLng().lat, this.markers2[0].getLatLng().lng),
            L.latLng(this.markers[0].getLatLng().lat, this.markers[0].getLatLng().lng)
          ]
        });
        console.log(this.markers2);
        console.log(this.markers);
        this.markers2 = this.markers2.filter(marker => {
          marker.remove();

          return false;
        });


        // add routing control to the map
        control.addTo(this.map);
      }
    });
  }

  updateMapTo() {
    this.markers = this.markers.filter(marker => {
      marker.remove();

      return false;
    });
    this.markers = [];
    const url = `https://nominatim.openstreetmap.org/search?q=${this.addressTo}&format=json`;
    this.http.get(url).subscribe(data => {
      // @ts-ignore
      const coordinates = data[0];
      this.setTo(coordinates);

      this.map.setView(new L.LatLng(coordinates.lat, coordinates.lon), 18);
      const marker = L.marker([coordinates.lat, coordinates.lon], {title: this.addressTo}).addTo(this.map);
      this.markers.push(marker);


      if (this.markers2.length == 1 && this.markers.length==1) {
        // create routing control

        var Routing: any = require('leaflet-routing-machine');
        let control: any;
        // @ts-ignore

        control = L.Routing.control({

          draggableWaypoints:false,

          waypoints: [
            L.latLng(this.markers2[0].getLatLng().lat, this.markers2[0].getLatLng().lng),
            L.latLng(this.markers[0].getLatLng().lat, this.markers[0].getLatLng().lng)
          ]


        });


        console.log(this.markers2);

        console.log(this.markers);

        // add routing control to the map
        control.addTo(this.map);
      }

    });
  }
  setFrom(data:any){
    let str:string|number=data.lat+", "+data.lon;

    this.from=str;
    this.form.from=this.addressFrom;

  }
  setTo(data:any){
    let str:string|number=data.lat+", "+data.lon;

    this.to=str;
   this.form.to=this.addressTo;
  }

  goToPayment() {
    this.router.navigate(['/client-home/receipt'],{state:this.form});

  }
}
