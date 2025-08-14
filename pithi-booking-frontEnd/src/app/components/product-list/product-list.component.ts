import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/services/product.service';
import { ActivatedRoute } from '@angular/router';
import { BookItem } from 'src/app/common/book-item';
import { BookingService } from 'src/app/services/booking.service';
import { BookingRequest } from 'src/app/common/booking-request';
import { BookingsService } from 'src/app/services/bookings.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  currentCategoryId: number = 1;
  searchMode: boolean = false;
  isPending = false;
   pendingProductIds = new Set<string>(); // ✅ Proper initialization


  // new properties for pagination
  thePageNumber: number = 1;
  thePageSize: number = 4;
  theTotalElements: number = 0;
  previousCategoryId: number = 1;

  previousKeyword: string = "";




  constructor(private productService: ProductService,
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private bookingSerivces: BookingsService

  ) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(() => {
      this.listProducts();
    })

  }

  listProducts() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');
    if (this.searchMode) {
      this.handleSearchProducts();
    }
    else {
      this.handleListProducts();
    }

  }

  handleSearchProducts() {

    const theKeyword: string = this.route.snapshot.paramMap.get('keyword')!;

    // if we have a different keyword than previous
    // then set thePageNumber to 1

    if (this.previousKeyword != theKeyword) {
      this.thePageNumber = 1;
    }

    this.previousKeyword = theKeyword;

    console.log(`keyword=${theKeyword}, thePageNumber=${this.thePageNumber}`);

    // now search for the products using keyword
    this.productService.searchProductsPaginate(this.thePageNumber - 1,
      this.thePageSize,
      theKeyword).subscribe(this.processResult());

  }


  handleListProducts() {
    //check if 'id' parameter is availbel
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');
    if (hasCategoryId) {
      // get the "id" param string. convert string to a number using the "+" symbol
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
    }
    else {
      // not category id available ... default to category id 1
      this.currentCategoryId = 1;
    }

    //checki if we have a different category than previous. 
    if (this.previousCategoryId != this.currentCategoryId) {
      this.thePageNumber = 1;
    }

    this.previousCategoryId = this.currentCategoryId;
  



    // this.productService.getProductList(this.currentCategoryId).subscribe(
    //   data => {
    //     this.products = data;
    //   }
    // )

    // now get the products for the given category id
    this.productService.getProductListPaginate(this.thePageNumber - 1,
      this.thePageSize,
      this.currentCategoryId)
      .subscribe(
        data => {
          this.products = data.list;
          this.thePageNumber = data.pagination.pageNumber + 1;
          this.thePageSize = data.pagination.pageSize;
          this.theTotalElements = data.pagination.totalElements;
        }
      );

  }

  updatePageSize(pageSize: string) {
    this.thePageSize = +pageSize;
    this.thePageNumber = 1;
    this.listProducts();
  }
  processResult() {
    return (data: any) => {
      this.products = data.list;
      this.thePageNumber = data.pagination.pageNumber + 1;
      this.thePageSize = data.pagination.pageSize;
      this.theTotalElements = data.pagination.totalElements;
    };
  }

  addToBooks(theProduct: Product) {

    console.log(`Adding to cart: ${theProduct.name}, ${theProduct.price}`);

    // TODO ... do the real work
    const theBookItem = new BookItem(theProduct);

    this.bookingService.addToBook(theBookItem);

  }

  
 

  addToBookService(theProduct?: Product) {  // Notice the '?' makes it optional
  if (!theProduct) {
    alert('No product provided!');
    return;
  }
  const productId = theProduct.id ?? '';
    // Mark this product as pending immediately
  this.pendingProductIds.add(productId);
   
  const bookingTime = new Date().toISOString();
  const status = 'PENDING';
  const message = 'Please wait to accept';
  const currentUser = '1'; // sample user ID

  const bookingRequest = {
    productId: theProduct.id,
    customerId: currentUser,
    bookingTime: bookingTime,
    status: status,
    message: message,
    price: theProduct.price ?? 0,
    quantity:1
  
  };

  this.isPending = true;

  // this.bookingSerivces.createBooking(bookingRequest).subscribe({
  //   next: (response) => {
  //     alert('Booking successful!');
      
  //   },
  //   error: (err) => {
  //     this.isPending = false; 
  //     alert('Booking failed!');
  //   }
  // });

 this.bookingSerivces.createBooking(bookingRequest).subscribe({
    next: () => {
      // 1. Update the bookingRequest array in the service
      this.bookingSerivces.bookRequest.push(bookingRequest);
      // 2. Compute the new totals
      this.bookingSerivces.computeBookTotals();
      alert('Booking successful!');
    },
    error: () => {
      alert('Booking failed!');
    }
  });





}




}
