export class Product {
    id?: number;
    name!: string;
    price!: number;
    stock!: number;
    category?: string;
    description?: string;
    discountedPrice?: number;
}
