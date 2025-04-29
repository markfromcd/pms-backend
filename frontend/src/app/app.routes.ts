import { Routes } from '@angular/router';
import { Product } from './product';
import { ProductListComponent } from './product-list/product-list.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';


export const routes: Routes = [
    {path: 'products', component: ProductListComponent},
    {path: '', redirectTo: 'products', pathMatch: 'full'}, // redirect to products when the app starts with no path
    {path: 'create-product', component: CreateProductComponent},
    {path: 'update-product/:id', component: UpdateProductComponent},
    {path: 'view-product/:id', component: ProductDetailsComponent},

    // Customer routes
    {path: 'customers', component: CustomerListComponent},
    {path: 'create-customer', component: CreateCustomerComponent},
    {path: 'update-customer/:id', component: UpdateCustomerComponent},
    {path: 'view-customer/:id', component: CustomerDetailsComponent},

    // Shopping Cart routes
    {path: 'view-cart/:id', component: ShoppingCartComponent},
];
