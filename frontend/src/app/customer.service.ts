import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from './customer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = "http://localhost:8080/api/customers";
  constructor(private httpClient: HttpClient) { }

  getCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(`${this.apiUrl}`);
  }

  createCustomer(customer: Customer): Observable<Object> {
    return this.httpClient.post(`${this.apiUrl}`, customer);
  }

  getCustomerById(id: number): Observable<Customer> {
    return this.httpClient.get<Customer>(`${this.apiUrl}/${id}`);
  }

  getCustomerByEmail(email: string): Observable<Customer> {
    return this.httpClient.get<Customer>(`${this.apiUrl}/email/${email}`);
  }

  updateCustomer(id: number, customer: Customer): Observable<Object> {
    return this.httpClient.put(`${this.apiUrl}/${id}`, customer);
  }

  deleteCustomer(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.apiUrl}/${id}`);
  }
}
