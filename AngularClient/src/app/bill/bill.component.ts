import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit{
  form2={
    from:"",to:"",nap:"",tip:""
  }
ngOnInit() {
  this.form2=history.state;
}

}
