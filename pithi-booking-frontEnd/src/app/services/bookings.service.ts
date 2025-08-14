import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BookingRequest } from '../common/booking-request';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingsService {
  private apiUrl = 'http://localhost:9595/api/bookings/create';

  bookRequest: BookingRequest[] = [];

  totalPrice: Subject<number> = new Subject<number>;
  totalQuantity: Subject<number> = new Subject<number>;

  constructor(private http: HttpClient) { }

  createBooking(request: BookingRequest): Observable<any> {
    return this.http.post<any>(this.apiUrl, request);
  }



  computeBookTotals() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (let currentBookItem of this.bookRequest) {
      totalPriceValue += (currentBookItem.quantity ?? 1) * currentBookItem.price;
      totalQuantityValue += (currentBookItem.quantity ?? 1);
    }
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);
    this.logBookData(totalPriceValue, totalQuantityValue);
  }

  logBookData(totalPriceValue: number, totalQuantityValue: number) {
    console.log('Contents of the bookings');
    for (let tempBookItem of this.bookRequest) {
      const subTotalPrice = (tempBookItem.quantity ?? 1) * tempBookItem.price;
      console.log(`status: ${tempBookItem.status}, quantity=${tempBookItem.quantity ?? 1}, price=${tempBookItem.price}, subTotalPrice=${subTotalPrice}`);
    }
    console.log(`totalPrice: ${totalPriceValue.toFixed(2)}, totalQuantity: ${totalQuantityValue}`);
  }


}
