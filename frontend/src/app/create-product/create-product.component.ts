import { Component } from '@angular/core';
import { Product } from '../product';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-product',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-product.component.html',
  styleUrl: './create-product.component.css'
})
export class CreateProductComponent {

  product: Product = new Product();
  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {}

  saveProduct() {
    this.productService.createProduct(this.product).subscribe(data => {
      console.log(data);
      this.goToProductList();
    }, error => {
      console.error('Error creating product:', error);
    });
  }

  goToProductList() {
    this.router.navigate(['/products']);
  }

  onSubmit() {
    console.log(this.product);
    this.saveProduct();
  }
}
