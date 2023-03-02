import {Component, OnInit, Output} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';

import * as L from 'leaflet';
import 'leaflet-routing-machine';
import 'leaflet-polylinedecorator';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  @Output() from:string;
  @Output() to:string;
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


  constructor(private http: HttpClient) {
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
      this.fromMarker = L.marker([coordinates.lat, coordinates.lon], { title: this.addressFrom }).addTo(this.map);

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
      this.setTo(coordinates);
      this.map.setView(new L.LatLng(coordinates.lat, coordinates.lon), 18);
      this.toMarker = L.marker([coordinates.lat, coordinates.lon], { title: this.addressTo }).addTo(this.map);

      if (this.fromMarker) {
        this.addRoutingControl();
      }
    });
  }

  addRoutingControl() {
    if (this.routingControl) {
      this.map.removeControl(this.routingControl);
    }

    // @ts-ignore
    // @ts-ignore
    this.routingControl = L.Routing.control({

      waypoints: [
        this.fromMarker.getLatLng(),
        this.toMarker.getLatLng()
      ]
    });

    this.routingControl.addTo(this.map);
  }
  setFrom(data:any){
    let str:string|number=data.lat+", "+data.lon;

    this.from=str;
  }
  setTo(data:any){
    let str:string|number=data.lat+", "+data.lon;

    this.to=str;

  }
}

