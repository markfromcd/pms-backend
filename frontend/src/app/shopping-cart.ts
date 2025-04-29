import { CartItem } from "./cart-item";
export class ShoppingCart {
    id?: number;
    items: CartItem[] = [];
    totalPrice?: number;
    discountedTotalPrice?: number;
}
