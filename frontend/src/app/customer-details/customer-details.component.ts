import { Component } from '@angular/core';
import { Customer } from '../customer';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customer-details',
  standalone: true,
  imports: [],
  templateUrl: './customer-details.component.html',
  styleUrl: './customer-details.component.css'
})
export class CustomerDetailsComponent {
  id!: number;
  customer!: Customer;

  constructor(private route: ActivatedRoute, private customerService: CustomerService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    
    this.customer = new Customer();
    this.customerService.getCustomerById(this.id).subscribe(data => {
      this.customer = data;
    }, error => {
      console.error('Error fetching customer:', error);
    });
  }
}
