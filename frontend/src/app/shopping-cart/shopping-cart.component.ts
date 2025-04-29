import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ShoppingCart } from '../shopping-cart';
import { ShoppingCartService } from '../shopping-cart.service';
import { ProductService } from '../product.service';
import { Product } from '../product';

@Component({
  selector: 'app-shopping-cart',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.css'
})
export class ShoppingCartComponent implements OnInit {
  customerId!: number;
  cartId!: number;
  cart!: ShoppingCart;
  products: Product[] = [];
  selectedProductId?: number;
  quantity: number = 1;

  constructor(
    private cartService: ShoppingCartService,
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['id'];
    this.loadCart();
    this.loadProducts();
  }

  loadCart(): void {
    this.cartService.getCartByCustomerId(this.customerId).subscribe(
      data => {
        this.cart = data;
        this.cartId = data.id!;
      },
      error => {
        console.error('Error loading cart:', error);
      }
    );
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe(
      data => {
        this.products = data;
      },
      error => {
        console.error('Error loading products:', error);
      }
    );
  }

  addToCart(): void {
    if (!this.selectedProductId) {
      alert('Please select a product');
      return;
    }

    this.cartService.addItemToCart(this.cartId, this.selectedProductId, this.quantity).subscribe(
      data => {
        this.cart = data;
        this.selectedProductId = undefined;
        this.quantity = 1;
      },
      error => {
        console.error('Error adding item to cart:', error);
        alert('Error adding item to cart: ' + error.message);
      }
    );
  }

  updateItem(itemId: number, newQuantity: number): void {
    this.cartService.updateCartItem(this.cartId, itemId, newQuantity).subscribe(
      data => {
        this.cart = data;
      },
      error => {
        console.error('Error updating cart item:', error);
        alert('Error updating cart item: ' + error.message);
      }
    );
  }

  removeItem(itemId: number): void {
    this.cartService.removeItemFromCart(this.cartId, itemId).subscribe(
      data => {
        this.cart = data;
      },
      error => {
        console.error('Error removing cart item:', error);
        alert('Error removing cart item: ' + error.message);
      }
    );
  }

  clearCart(): void {
    this.cartService.clearCart(this.cartId).subscribe(
      () => {
        this.loadCart(); // Reload cart after clearing
      },
      error => {
        console.error('Error clearing cart:', error);
        alert('Error clearing cart: ' + error.message);
      }
    );
  }

  goToCustomerList(): void {
    this.router.navigate(['/customers']);
  }
}