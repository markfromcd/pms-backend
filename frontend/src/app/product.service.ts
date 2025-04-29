import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './product';
import { get } from 'http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  // private apiUrl = "http://localhost:8080/api/products";
  private apiUrl = "http://pms-alb-572541170.us-east-2.elb.amazonaws.com/api/products"

  constructor(private httpClient: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${this.apiUrl}`);
  }

  createProduct(product: Product): Observable<Object> {
    return this.httpClient.post(`${this.apiUrl}`, product);
  }

  getProductById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(`${this.apiUrl}/${id}`);
  }

  updateProduct(id: number, product: Product): Observable<Object> {
    return this.httpClient.put(`${this.apiUrl}/${id}`, product);
  }

  deleteProduct(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.apiUrl}/${id}`);
  }
}
