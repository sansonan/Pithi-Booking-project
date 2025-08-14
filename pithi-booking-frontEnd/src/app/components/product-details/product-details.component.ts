import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookItem } from 'src/app/common/book-item';
import { Product } from 'src/app/common/product';
import { BookingService } from 'src/app/services/booking.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product: Product = new Product();


  constructor(private productService: ProductService,
    private bookingService: BookingService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleProductDetails();
    })
  }
  handleProductDetails() {
    const theProductId: number = +this.route.snapshot.paramMap.get('id')!;

    const idParam = this.route.snapshot.paramMap.get('id');

    this.productService.getProduct(theProductId).subscribe(
      data => {
        this.product = data;
      }
    )

  }

  addToBooks(theProduct: Product) {
    console.log(`Adding to cart: ${theProduct.name}, ${theProduct.price}`);

    // TODO ... do the real work
    const theBookItem = new BookItem(theProduct);

    this.bookingService.addToBook(theBookItem);

  }

}
