import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

import 'leaflet-velocity/dist/leaflet-velocity.min.js';
import * as L from 'leaflet';

import DriftMarker from "leaflet-drift-marker";
import 'leaflet-routing-machine';
import 'leaflet-polylinedecorator';



import {FeatureGroup, marker} from "leaflet";
import {mark} from "@angular/compiler-cli/src/ngtsc/perf/src/clock";


const icon = L.icon({
  iconSize: [25, 41],
  iconAnchor: [10, 41],
  popupAnchor: [2, -40],
  iconUrl: "https://unpkg.com/leaflet@1.6/dist/images/marker-icon.png",
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

  // @ts-ignore



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


  constructor(private http: HttpClient, private router: Router) {

  }

  ngOnInit(): void {
    this.initMap();

  }


  updateMapFrom() {
    if (this.fromMarker) {
      this.map.removeLayer(this.fromMarker);
    }

    const url = `https://nominatim.openstreetmap.org/search?q=${this.addressFrom}&format=json`;
    this.http.get(url).subscribe(data => {
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
    if (this.fromMarker || this.toMarker) {
      this.map.removeLayer(this.fromMarker);
      this.map.removeLayer(this.toMarker);
    }
    console.log('pomeram se');


    const waypoints = this.routingControl.getWaypoints();

    const markers = waypoints.map((wp: { latLng: any; }) => {
      return new DriftMarker(wp.latLng).addTo(this.map);
    });

    const destination = waypoints[waypoints.length - 1];


    var marker = L.marker(this.fromMarker.getLatLng(), {icon: icon}).addTo(this.map);

    this.routingControl.on('routeselected', () => {
      markers.forEach((marker: { remove: () => void; }) => {
        marker.remove();
      });
    });
    var newMarker = L.marker(this.toMarker.getLatLng()).addTo(this.map);
    L.Routing.control({
      waypoints: [
        L.latLng(this.fromMarker.getLatLng()),
        L.latLng(this.toMarker.getLatLng())
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
        }, 100 * index)
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

