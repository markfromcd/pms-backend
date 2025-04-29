import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CartItem } from './cart-item';
import { ShoppingCart } from './shopping-cart';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  // private apiUrl = "http://localhost:8080/api/carts";
  private apiUrl = "http://pms-alb-572541170.us-east-2.elb.amazonaws.com/api/carts";

  constructor(private httpClient: HttpClient) { }

  getCartById(cartId: number): Observable<ShoppingCart> {
    return this.httpClient.get<ShoppingCart>(`${this.apiUrl}/${cartId}`);
  }

  getCartByCustomerId(customerId: number): Observable<ShoppingCart> {
    return this.httpClient.get<ShoppingCart>(`${this.apiUrl}/customer/${customerId}`);
  }

  addItemToCart(cartId: number, productId: number, quantity: number): Observable<ShoppingCart> {
    return this.httpClient.post<ShoppingCart>(
      `${this.apiUrl}/${cartId}/items?productId=${productId}&quantity=${quantity}`,
      {}
    );
  }

  updateCartItem(cartId: number, itemId: number, quantity: number): Observable<ShoppingCart> {
    return this.httpClient.put<ShoppingCart>(
      `${this.apiUrl}/${cartId}/items/${itemId}?quantity=${quantity}`,
      {}
    );
  }

  removeItemFromCart(cartId: number, itemId: number): Observable<ShoppingCart> {
    return this.httpClient.delete<ShoppingCart>(`${this.apiUrl}/${cartId}/items/${itemId}`);
  }

  clearCart(cartId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiUrl}/${cartId}/items`);
  }

  getCartItem(cartId: number, itemId: number): Observable<CartItem> {
    return this.httpClient.get<CartItem>(`${this.apiUrl}/${cartId}/items/${itemId}`);
  }
}
