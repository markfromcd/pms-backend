import { ShoppingCart } from './shopping-cart';

export class Customer {
    id?: number;
    name!: string;
    email!: string;
    address!: string;
    phone?: string;
    cart?: ShoppingCart;
}
