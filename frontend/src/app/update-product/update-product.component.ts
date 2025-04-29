import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-product',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './update-product.component.html',
  styleUrl: './update-product.component.css'
})
export class UpdateProductComponent implements OnInit {
  id!: number;
  product: Product = new Product();
  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.productService.getProductById(this.id).subscribe(data => {
      this.product = data;
    }
    , error => {
      console.error('Error fetching product:', error);
    }
    );
  }

  goToProductList() {
    this.router.navigate(['/products']);
  }

  onSubmit() {
    this.productService.updateProduct(this.id, this.product).subscribe(data => {
      console.log(data);
      this.goToProductList();
    }, error => {
      console.error('Error updating product:', error);
    });
  }
}
