import {Component, ElementRef, ViewChild} from '@angular/core';
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserFull} from "../model/user-full";
import {UserService} from "../services/user.service";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, map, Observable} from "rxjs";


@Component({
  selector: 'app-admin-user-table',
  templateUrl: './admin-user-table.component.html',
  styleUrls: ['./admin-user-table.component.css']
})
export class AdminUserTableComponent {
  @ViewChild('myTable') table: ElementRef;

  data: UserFull[];
  isDriver: boolean;

  constructor(private localService: LocalService, private router: Router, private activatedRoute: ActivatedRoute, private userService: UserService, private http: HttpClient) {


  }

  ngOnInit() {
    if (this.router.url == '/admin/drivers') this.isDriver = true;
    else if (this.router.url == '/admin/clients') this.isDriver = false;
    else this.isDriver = false;

    if (this.isDriver) {
      this.userService.getDrivers().subscribe(
        (data) => {
          this.setData(data);
        },
        (error: HttpErrorResponse) => {
          console.log('Error occurred while fetching drivers:', error);
        }
      );
    } else {
      this.userService.getClients().subscribe(
        (data) => {
          this.setData(data);
        },
        (error: HttpErrorResponse) => {
          console.log('Error occurred while fetching clients:', error);
        }
      );
    }
  }


  reverseGeocode(latitude: number, longitude: number): Observable<string> {
    const url = `https://nominatim.openstreetmap.org/reverse?lat=${latitude}&lon=${longitude}&format=json`;

    return this.http.get(url).pipe(
      map((response: any) => {
        const {road, suburb, city} = response.address;
        const firstField = road || '';
        const secondField = city || '';
        const thirdField = suburb || '';
        return `${firstField}, ${thirdField},${secondField}`;
      }),
      catchError((error: HttpErrorResponse) => {
        throw error;
      })
    );
  }


  setData(data: any) {
    console.log(data);
    this.data = data;
    this.data.forEach(row => {
      if (row.trenutnaLokacija) {
        const [latitude, longitude] = row.trenutnaLokacija.split(',').map(parseFloat);
        this.reverseGeocode(latitude, longitude).subscribe(
          (address: string) => {
            row.trenutnaLokacija = address;

          },
          (error: HttpErrorResponse) => {
            console.log('Error occurred while reverse geocoding:', error);
          }
        );
      }
    });
  }

  blokirajKorisnika(email: string) {
    const row = this.data.find((user) => user.email === email);
    if (!row) return; // Handle case when row is not found

    row.blokiran = !row.blokiran;

    // Call your API or perform any other necessary logic to update the blocked status
    // For example, you can use the userService to update the blocked status on the server.
    this.userService.updateBlockedStatus(email).subscribe(
      (response) => {
        console.log('Blocked status updated successfully:', response);
      },
      (error: HttpErrorResponse) => {
        console.log('Error occurred while updating blocked status:', error);
      }
    );
  }

  goToRegister() {
    this.router.navigate(['/admin/drivers/add'])
  }
}
