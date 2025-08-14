import { Product } from "./product";
import { User } from "./user";


export class BookingRequest {
  public productId? : string;
  public customerId?: string;
  public bookingTime: string;
  public status: string;
  public message: string;
  public price: number;
  public quantity: number;
 
  

  constructor(
    product: Product,
    user: User,
    bookingTime: string,
    status: string,
    message: string
  
  ) {
    this.productId  = product.id;
    this.customerId = user.id;
    this.bookingTime = bookingTime; // Expecting an ISO string (e.g., "2025-06-20T14:30:00")
    this.status = status; // e.g., "PENDING"
    this.message = message; // e.g., "Please set up at 2 PM sharp"
    this.price = product.price ?? 0;
   this.quantity = 1;
}

}