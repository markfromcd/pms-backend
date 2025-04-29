import { Component, Inject, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Product } from '../product';
import { CommonModule } from '@angular/common';
import { ProductService } from '../product.service';
import { filter } from 'rxjs';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  providers: [ProductService],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit {
  constructor(private productService: ProductService, private router: Router) { }

  products: Product[] = [];

  ngOnInit(): void {
    // this.getProducts();
    // this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe(() => {
    //   this.getProducts();
    // });
    this.productService.getProducts().subscribe((data: Product[]) => {
      this.products = data;
    });
  }

  private getProducts(){
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });
  }
  updateProduct(id: number) {
    this.router.navigate(['/update-product', id]);
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe(data => {
      console.log(data);
      // this.products = this.products.filter(product => product.id !== id);
      this.getProducts();
    }
    , error => {
      console.error('Error deleting product:', error);
    }
    );
  }

  viewProduct(id: number) {
    this.router.navigate(['/view-product', id]);
  }

}
