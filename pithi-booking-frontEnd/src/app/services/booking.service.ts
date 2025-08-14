import { Injectable } from '@angular/core';
import { BookItem } from '../common/book-item';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
 

  bookItems: BookItem[] = [];

  totalPrice: Subject<number> = new Subject<number>;
  totalQuantity: Subject<number> = new Subject<number>;

  constructor() { }

  addToBook(theBookItem: BookItem) {

    let alreadyExistsInBook: boolean = false;
    // let existingBookItem: undefined;
    let existingBookItem: typeof this.bookItems[0] | undefined;

    if (this.bookItems.length > 0) {
      console.log("true 1");
      existingBookItem = this.bookItems.find(tempBookItem => tempBookItem.id === theBookItem.id);
      // check if we found it
      alreadyExistsInBook = (existingBookItem != undefined);
    }
    if (existingBookItem !== undefined) {
      console.log("true 2");
      // increment the quantity
      existingBookItem.quantity++;
    }
    else {
      console.log("true 3");
      // just add the item to the array

      this.bookItems.push(theBookItem);
    }

    // compute cart total price and total quantity
    this.computeBookTotals();

  }
  computeBookTotals() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;
    for (let currentBookItem of this.bookItems) {
      totalPriceValue += currentBookItem.quantity * currentBookItem.price;
      totalQuantityValue += currentBookItem.quantity;
    }
    // publish the new values ... all subscribers will receive the new data
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

    // log cart data just for debugging purposes
    this.logBookData(totalPriceValue, totalQuantityValue);
  }

  logBookData(totalPriceValue: number, totalQuantityValue: number) {

    console.log('Contents of the cart');
    for (let tempBookItem of this.bookItems) {
      const subTotalPrice = tempBookItem.quantity * tempBookItem.price;
      console.log(`name: ${tempBookItem.name}, quantity=${tempBookItem.quantity}, price=${tempBookItem.price}, subTotalPrice=${subTotalPrice}`);
    }

    console.log(`totalPrice: ${totalPriceValue.toFixed(2)}, totalQuantity: ${totalQuantityValue}`);
   
  }

  cancle(theBookItem: BookItem){
    const itemIndex = this.bookItems.findIndex(tempBookItem => tempBookItem.id === theBookItem.id);
    if(itemIndex > -1){
      this.bookItems.splice(itemIndex,1);
      this.computeBookTotals();
    }

  }

}




