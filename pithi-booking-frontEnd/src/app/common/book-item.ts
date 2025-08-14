import { Product } from './product';

export class BookItem {
  id?: string;
  name?: string;
  imageUrl?: string;
  price: number;
  quantity: number;

  constructor(product: Product) {
    this.id = product.id;
    this.name = product.name;
    this.imageUrl = product.imageUrl;
    this.price = product.price ?? 0; // or throw error if price is required
    this.quantity = 1;
  }
}



