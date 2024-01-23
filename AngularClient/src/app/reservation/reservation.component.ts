import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

import * as L from 'leaflet';
import 'leaflet-routing-machine';
import 'leaflet-polylinedecorator';

import 'leaflet-velocity/dist/leaflet-velocity.min.js';
import DriftMarker from "leaflet-drift-marker";



import {FeatureGroup, marker} from "leaflet";
import {mark} from "@angular/compiler-cli/src/ngtsc/perf/src/clock";
import {NONE_TYPE} from "@angular/compiler";
import {UserFull} from "../model/user-full";
import {UserService} from "../services/user.service";
import {LocalService} from "../services/local.service";
import {UserProfile} from "../model/user-profile";
import {UserRole} from "../model/user-role";


const icon = L.icon({
  iconSize: [25, 41],
  iconAnchor: [10, 41],
  popupAnchor: [2, -40],
  iconUrl: "assets/car.png",
  shadowUrl: "https://unpkg.com/leaflet@1.6/dist/images/marker-shadow.png"
});

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {
  km: number;
  from: string;
  to: string;
  tip: string;
  nap: string;

  private map: L.Map;
  private centroid: L.LatLngExpression = [45.2396, 19.8227]; //
  addressFrom: string;
  addressTo: string;
  private options: any;
  private markers: L.Marker[] = [];
  private markers2: L.Marker[] = [];
  private fromMarker: L.Marker;
  private toMarker: L.Marker;

  // @ts-ignore
  private routingControl: L.Routing.Control;
  private tiles: L.TileLayer;


  private pom: any;

  blocked:boolean;







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


  constructor(private http: HttpClient, private router: Router,private localservice:LocalService,private userservice:UserService){
  }

  ngOnInit(): void {
    let user:UserRole=new UserRole();
    let email=this.localservice.getData('user')
    let role=this.localservice.getData('role')
    if(email && role){
      user.email=email;
      user.role=role;
    }
   this.userservice.getBlockedStatus(user).subscribe((data:any)=>{
     this.blocked=data as boolean;

   })

    // Check if the user is blocked

    this.initMap();

  }




  updateMapFrom() {
    if (this.fromMarker) {
      this.map.removeLayer(this.fromMarker);
    }

    const url = `https://nominatim.openstreetmap.org/search?q=${this.addressFrom}&format=json`;
    this.http.get(url).subscribe(data => {
      console.log(data);
      // @ts-ignore
      const coordinates = data[0];
      this.setFrom(coordinates);
      this.map.setView(new L.LatLng(coordinates.lat, coordinates.lon), 18);
      this.fromMarker = L.marker([coordinates.lat, coordinates.lon], {title: this.addressFrom}).addTo(this.map);

      if (this.toMarker) {
        this.addRoutingControl();
      }
    });
    this.setKm(1);
  }

  updateMapTo() {
    if (this.toMarker) {
      this.map.removeLayer(this.toMarker);
    }

    const url = `https://nominatim.openstreetmap.org/search?q=${this.addressTo}&format=json`;
    this.http.get(url).subscribe(data => {
      console.log(data);
      // @ts-ignore
      const coordinates = data[0];
      this.setTo(coordinates);
      this.map.setView(new L.LatLng(coordinates.lat, coordinates.lon), 18);
      this.toMarker = L.marker([coordinates.lat, coordinates.lon], {title: this.addressTo}).addTo(this.map);

      if (this.fromMarker) {
        this.addRoutingControl();
      }
    });
    this.setKm(1);
  }


  simulateDrive() {
    console.log('pomeram se');
    const self=this;

    this.map.removeLayer(this.toMarker.remove());
    const waypoints = this.routingControl.getWaypoints();
    const markers = waypoints.map((wp: { latLng: any; }) => {
      return new DriftMarker(wp.latLng).addTo(this.map);
    });
    const destination = waypoints[waypoints.length - 1];
    var marker = L.marker(this.fromMarker.getLatLng(), {icon: icon}).addTo(this.map);

    var fromMarker = this.fromMarker;
    var toMarker = this.toMarker;


    L.Routing.control({
      waypoints: [
        L.latLng(fromMarker.getLatLng()),
        L.latLng(toMarker.getLatLng())
      ],
      lineOptions: {
        extendToWaypoints: true,
        missingRouteTolerance: 100,
        styles: [
          {
            color: '#1E90FF',
            opacity: 0.7,
            weight: 5
          }
        ]
      }
    }).on('routesfound', function (e: { routes: { coordinates: any[]; }[]; }) {
      var routes = e.routes;
      console.log(routes);

      e.routes[0].coordinates.forEach(function (coord, index) {
        setTimeout(function () {
          marker.setLatLng([coord.lat, coord.lng]);

          if (index == e.routes[0].coordinates.length - 1) {
            // Animation is complete, remove markers

          }
        }, 50 * index)
      })

    }).addTo(this.map);
  }


  addRoutingControl() {
    if (this.routingControl) {
      this.map.removeControl(this.routingControl);
    }


    let plan = L.Routing.plan(
      [
        this.fromMarker.getLatLng(),
        this.toMarker.getLatLng()
      ], {
        createMarker: (i: any, wp: any, nWps: any) => {
          return new DriftMarker(wp.latLng.toArray()).addTo(this.map);
        }

      })

    // @ts-ignore
    this.routingControl = L.Routing.control({
      waypoints: [
        this.fromMarker.getLatLng(),
        this.toMarker.getLatLng()
      ],
      plan: plan,
      routeWhileDragging: true,
      showAlternatives: true,
      lineOptions: {
        extendToWaypoints: true,
        missingRouteTolerance: 100,

        styles: [
          {
            color: '#1E90FF',
            opacity: 0.7,
            weight: 5
          }
        ]
      },


    });

    this.routingControl.addTo(this.map);


  }


  setKm(data: any) {
    const min = 1;
    const max = 10;
    const randomInt = Math.floor(Math.random() * (max - min + 1)) + min;
    this.km = randomInt;
  }

  setFrom(data: any) {
    let str: string | number = data.lat + ", " + data.lon;
    this.from = str;
  }

  setTo(data: any) {
    let str: string | number = data.lat + ", " + data.lon;
    this.to = str;
  }

  goToPayment() {
    console.log(this.from);
    console.log(this.to);
    this.router.navigate(['/client-home/receipt'], {
      state: {
        tip: this.tip,
        nap: this.nap,
        from: this.addressFrom,
        to: this.addressTo,
        km: this.km,
        coords: {from: this.from, to: this.to}
      }
    });

  }


}

