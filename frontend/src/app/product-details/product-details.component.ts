import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit {
  id!: number;
  product!: Product;

  constructor(private route: ActivatedRoute, private productService: ProductService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    
    this.product = new Product();
    this.productService.getProductById(this.id).subscribe(data => {
      this.product = data;
    }, error => {
      console.error('Error fetching product:', error);
    });
  }

}
