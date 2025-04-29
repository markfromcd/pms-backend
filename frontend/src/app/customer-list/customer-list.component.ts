import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';
import { Customer } from '../customer';

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [CommonModule],
  providers: [CustomerService],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})
export class CustomerListComponent implements OnInit {
  constructor(private customerService: CustomerService, private router: Router) { }

  customers: Customer[] = [];

  ngOnInit(): void {
    // this.getProducts();
    this.customerService.getCustomers().subscribe((data: Customer[]) => {
      this.customers = data;
    });
  }
  
  private getCustomers(){
    this.customerService.getCustomers().subscribe(data => {
      this.customers = data;
    }, error => {
      console.error('Error fetching customers:', error);
    }
    );
  }

  updateCustomer(id: number) {
    this.router.navigate(['/update-customer', id]);
  }

  deleteCustomer(id: number) {
    this.customerService.deleteCustomer(id).subscribe(data => {
      console.log(data);
      this.getCustomers();
    }, error => {
      console.error('Error deleting customer:', error);
    });
  }

  viewCustomer(id: number) {
    this.router.navigate(['/view-customer', id]);
  }

  viewCustomerCart(id: number) {
    this.router.navigate(['/view-cart', id]);
  }
}
