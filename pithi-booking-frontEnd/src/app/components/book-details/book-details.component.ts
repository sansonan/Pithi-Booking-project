import { Component, OnInit } from '@angular/core';
import { BookItem } from 'src/app/common/book-item';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {
 
  bookItems: BookItem[] = [];
  totalPrice: number = 0.00;
  totalQuantity: number = 0;


  constructor(private bookingService: BookingService) { }

  ngOnInit(): void {
    this.listBookDetails();
  }
  listBookDetails() {
      // get a handle to the cart items
    this.bookItems = this.bookingService.bookItems;

    // subscribe to the cart totalPrice
    this.bookingService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    // subscribe to the cart totalQuantity
    this.bookingService.totalQuantity.subscribe( 
      data => this.totalQuantity = data
    );

    // compute cart total price and quantity
    this.bookingService.computeBookTotals();
  }
  remove(theBookItem: BookItem){
      this.bookingService.cancle(theBookItem);
  }


}
