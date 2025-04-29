import { Product } from './product';


export class CartItem {
    id?: number;
    product!: Product;
    quantity!: number;
    subtotal?: number;
    discountedSubtotal?: number;
}
