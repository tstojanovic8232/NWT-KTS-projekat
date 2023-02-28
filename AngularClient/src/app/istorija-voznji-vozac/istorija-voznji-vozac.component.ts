import {Component, ElementRef, ViewChild} from '@angular/core';

@Component({
  selector: 'app-istorija-voznji-vozac',
  templateUrl: './istorija-voznji-vozac.component.html',
  styleUrls: ['./istorija-voznji-vozac.component.css']
})
export class IstorijaVoznjiVozacComponent {
  @ViewChild('myTable') table: ElementRef;
  data = [
    { clientName: 'Client A', date: '01/01/2023', price: '$100', duration: '1 hour', origin: 'City A', destination: 'City B' },
    { clientName: 'Client B', date: '02/01/2023', price: '$200', duration: '2 hours', origin: 'City B', destination: 'City C' },
    { clientName: 'Client C', date: '03/01/2023', price: '$300', duration: '3 hours', origin: 'City C', destination: 'City D' },
    { clientName: 'Client D', date: '04/01/2023', price: '$400', duration: '4 hours', origin: 'City D', destination: 'City E' },
    { clientName: 'Client E', date: '05/01/2023', price: '$500', duration: '5 hours', origin: 'City E', destination: 'City F' },
    { clientName: 'Client F', date: '06/01/2023', price: '$600', duration: '6 hours', origin: 'City F', destination: 'City G' }
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
