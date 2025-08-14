import { Component, OnInit } from '@angular/core';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-book-status',
  templateUrl: './book-status.component.html',
  styleUrls: ['./book-status.component.css']
})
export class BookStatusComponent implements OnInit {
  totalPrice: number = 0.00;
  totalQuantity: number = 0;



  constructor(private bookingService: BookingService) { }

  ngOnInit(): void {
    this.updateBookStatus();
  }

  updateBookStatus(){
    this.bookingService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    this.bookingService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );
  }

}
