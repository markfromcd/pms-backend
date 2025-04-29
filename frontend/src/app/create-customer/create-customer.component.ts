import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-customer',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-customer.component.html',
  styleUrl: './create-customer.component.css'
})
export class CreateCustomerComponent implements OnInit {
  customer: Customer = new Customer();
  constructor(private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {}

  saveCustomer() {
    this.customerService.createCustomer(this.customer).subscribe(data => {
      console.log(data);
      this.goToCustomerList();
    }, error => {
      console.error('Error creating customer:', error);
    });
  }

  goToCustomerList() {
    this.router.navigate(['/customers']);
  }

  onSubmit() {
    console.log(this.customer);
    this.saveCustomer();
  }

}
