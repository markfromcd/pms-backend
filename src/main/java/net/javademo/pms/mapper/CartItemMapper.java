package net.javademo.pms.mapper;

import net.javademo.pms.entity.CartItem;
import net.javademo.pms.model.CartItemModel;
import net.javademo.pms.model.ProductModel;

public class CartItemMapper {
    public static CartItemModel mapToCartItemModel(CartItem item) {
        CartItemModel itemModel = new CartItemModel();
        itemModel.setId(item.getId());

        // Map product
        if (item.getProduct() != null) {
            itemModel.setProduct(ProductMapper.mapToProductModel(item.getProduct()));
        }
        itemModel.setQuantity(item.getQuantity());
        itemModel.setSubtotal(item.getSubtotal());
        itemModel.setDiscountedSubtotal(item.getDiscountedSubtotal());
        return itemModel;
    }

    public static CartItem mapToCartItem(CartItemModel itemModel) {
        CartItem item = new CartItem();
        item.setId(itemModel.getId());

        // Map product if any
        if (itemModel.getProduct() != null ) {
            item.setProduct(ProductMapper.mapToProduct(itemModel.getProduct()));
        }
        item.setQuantity(itemModel.getQuantity());

        return item;
    }
}
