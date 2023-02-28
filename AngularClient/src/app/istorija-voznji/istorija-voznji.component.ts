
import { Component, ViewChild, ElementRef } from '@angular/core';

export interface Iputovanje {
clientName: string;
date: Date;
price: number;
duration: string;
origin: string;
destination: string;
}

@Component({
  selector: 'app-istorija-voznji',
  templateUrl: './istorija-voznji.component.html',
  styleUrls: ['./istorija-voznji.component.css']
})
export class IstorijaVoznjiComponent {
  @ViewChild('myTable') table: ElementRef;
  data = [
    { clientName: 'Driver A', date: '01/01/2023', price: '$100', duration: '1 hour', origin: 'City A', destination: 'City B' },
    { clientName: 'Driver B', date: '02/01/2023', price: '$200', duration: '2 hours', origin: 'City B', destination: 'City C' },
    { clientName: 'Driver C', date: '03/01/2023', price: '$300', duration: '3 hours', origin: 'City C', destination: 'City D' },
    { clientName: 'Driver D', date: '04/01/2023', price: '$400', duration: '4 hours', origin: 'City D', destination: 'City E' },
    { clientName: 'Driver E', date: '05/01/2023', price: '$500', duration: '5 hours', origin: 'City E', destination: 'City F' },
    { clientName: 'Driver F', date: '06/01/2023', price: '$600', duration: '6 hours', origin: 'City F', destination: 'City G' }
  ];

  currentPage = 1;
  itemsPerPage = 2;


  get paginatedData() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return this.data.slice(startIndex, startIndex + this.itemsPerPage);
  }

  onPageChange(event: number) {
    this.currentPage = event;
    this.table.nativeElement.scrollIntoView();
  }
  NextPage() {
    const startIndex = this.currentPage * this.itemsPerPage;
    if (startIndex < this.data.length) {
      this.currentPage++;
      this.table.nativeElement.scrollIntoView();
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.table.nativeElement.scrollIntoView();
    }
  }

}

