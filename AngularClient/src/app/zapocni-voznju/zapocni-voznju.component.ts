import {Component, ElementRef, Input, ViewChild} from '@angular/core';
import * as L from "leaflet";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import DriftMarker from "leaflet-drift-marker";


import 'leaflet-velocity/dist/leaflet-velocity.min.js';



import 'leaflet-routing-machine';
import 'leaflet-polylinedecorator';



import {FeatureGroup, marker} from "leaflet";
import {mark} from "@angular/compiler-cli/src/ngtsc/perf/src/clock";
import {NONE_TYPE} from "@angular/compiler";
import {DrivingService} from "../services/driving.service";
import {UserRole} from "../model/user-role";
import {LocalService} from "../services/local.service";


const icon = L.icon({
  iconSize: [25, 41],
  iconAnchor: [10, 41],
  popupAnchor: [2, -40],
  iconUrl: "assets/car.png",
  shadowUrl: "https://unpkg.com/leaflet@1.6/dist/images/marker-shadow.png"
});
@Component({
  selector: 'app-zapocni-voznju',
  templateUrl: './zapocni-voznju.component.html',
  styleUrls: ['./zapocni-voznju.component.css']
})
export class ZapocniVoznjuComponent {

  @ViewChild('c', { static: true }) myButton: ElementRef;




  // In your function


  addressFrom:string;
addressTo:string;

  private map: L.Map;
  private centroid: L.LatLngExpression = [45.2396, 19.8227]; //

  private options: any;
  private markers: L.Marker[] = [];
  private markers2: L.Marker[] = [];
  private fromMarker: L.Marker;
  private toMarker: L.Marker;

  // @ts-ignore
  private routingControl: L.Routing.Control;
  private tiles: L.TileLayer;


  private pom: any;

  // @ts-ignore
  private myObject: any;
  se: string="";
  clicked: false;
  item: string;

  buttonClicked = false;



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

    this.map.on('load', () => {
      setTimeout(() => {
        this.simulateDrive();
      }, 5000); // Delay execution for 1 second to ensure all markers and layers are loaded
    });

  }

  user:UserRole=new UserRole();

  constructor(private http: HttpClient, private router: Router,private drivingService:DrivingService,private localService:LocalService) {

  }


  ngOnInit(): void {
    this.addressFrom=history.state.from;
    this.addressTo=history.state.to;
    history.state.from=undefined;
    history.state.to=undefined;
    let email=this.localService.getData("user");
    let role=this.localService.getData("role");
    if(email && role){
      this.user.email=email;
      this.user.role=role;
    }
    this.initMap();
    this.updateMapFrom();
    this.updateMapTo();
    this.se="sss";


  }

  ngAfterViewInit() {
    // Trigger the click event on the button element
    this.myButton.nativeElement.click();
  }





  fun(){
    console.log('sdsad')
  }

  updateMapFrom() {
    if (this.fromMarker) {
      this.map.removeLayer(this.fromMarker);
    }

    const url = `https://nominatim.openstreetmap.org/search?q=${this.addressFrom}&format=json`;
    this.http.get(url).subscribe(data => {
      // @ts-ignore
      const coordinates = data[0];

      this.map.setView(new L.LatLng(coordinates.lat, coordinates.lon), 18);
      this.fromMarker = L.marker([coordinates.lat, coordinates.lon], {title: this.addressFrom}).addTo(this.map);

      if (this.toMarker) {
        this.addRoutingControl();
      }
    });

  }

  updateMapTo() {
    if (this.toMarker) {
      this.map.removeLayer(this.toMarker);
    }

    const url = `https://nominatim.openstreetmap.org/search?q=${this.addressTo}&format=json`;
    this.http.get(url).subscribe(data => {
      // @ts-ignore
      const coordinates = data[0];

      this.map.setView(new L.LatLng(coordinates.lat, coordinates.lon), 18);
      this.toMarker = L.marker([coordinates.lat, coordinates.lon], {title: this.addressTo}).addTo(this.map);

      if (this.fromMarker) {
        this.addRoutingControl();
      }
    });

  }


  simulateDrive() {
    this.drivingService.startDriving(this.user).subscribe(data=>{
      console.log(data);
    });

      console.log('pomeram se');
    const btn = document.getElementById('2') as HTMLButtonElement | null;


// ✅ Set disabled attribute

    btn?.setAttribute('disabled', '');
      const self = this;
      const waypoints = this.routingControl.getWaypoints();


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
    this.buttonClicked = true; // set buttonClicked to true

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
  isDisable(item: boolean) : boolean {
    return item  === false;
  }


  zavrsiVoznju() {
    this.drivingService.stopDriving(this.user).subscribe(()=>{
      this.router.navigate(['/driver-home']);
    });
  }
}
