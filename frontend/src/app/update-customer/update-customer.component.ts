import { Component } from '@angular/core';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-customer',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-customer.component.html',
  styleUrl: './update-customer.component.css'
})
export class UpdateCustomerComponent {
  id!: number;
  customer: Customer = new Customer();
  constructor(private customerService: CustomerService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.customerService.getCustomerById(this.id).subscribe(data => {
      this.customer = data;
    }
    , error => {
      console.error('Error fetching customer:', error);
    }
    );
  }

  goToCustomerList() {
    this.router.navigate(['/customers']);
  }

  onSubmit() {
    this.customerService.updateCustomer(this.id, this.customer).subscribe(data => {
      console.log(data);
      this.goToCustomerList();
    }, error => {
      console.error('Error updating customer:', error);
    });
  }
}
