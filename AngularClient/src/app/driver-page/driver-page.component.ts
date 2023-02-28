import {Component, ElementRef, ViewChild} from '@angular/core';
export interface Iputovanje {
  clientName: string;
  date: Date;
  price: number;
  duration: string;
  origin: string;
  destination: string;
}

@Component({
  selector: 'app-driver-page',
  templateUrl: './driver-page.component.html',
  styleUrls: ['./driver-page.component.css']
})



export class DriverPageComponent {
  @ViewChild('myTable') table: ElementRef;
  data = [
    { clientName: 'Driver A', date: '01/01/2023', price: '$100', duration: '1 hour', origin: 'City A', destination: 'City B',dugme: 'start' },
    { clientName: 'Driver B', date: '02/01/2023', price: '$200', duration: '2 hours', origin: 'City B', destination: 'City C' },
    { clientName: 'Driver C', date: '03/01/2023', price: '$300', duration: '3 hours', origin: 'City C', destination: 'City D' },
    { clientName: 'Driver D', date: '04/01/2023', price: '$400', duration: '4 hours', origin: 'City D', destination: 'City E' },
    { clientName: 'Driver E', date: '05/01/2023', price: '$500', duration: '5 hours', origin: 'City E', destination: 'City F' },
    { clientName: 'Driver F', date: '06/01/2023', price: '$600', duration: '6 hours', origin: 'City F', destination: 'City G' }
  ];



  }

