package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.CartItem;
import model.Product;
import model.ShoppingCart;
import repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    @Autowired
    private ProductService productService;

    public ShoppingCart addItem(Long cartId, Long productId, Integer quantity) {
        ShoppingCart cart = repository.findById(cartId).orElse(new ShoppingCart());
        Product product = productService.findById(productId);

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        
        cart.getItems().add(item);
        return repository.save(cart);
    }

    public ShoppingCart removeItem(Long cartId, Long productId) {
        ShoppingCart cart = repository.findById(cartId).orElseThrow();
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return repository.save(cart);
    }

    public ShoppingCart getCart(Long cartId) {
        return repository.findById(cartId).orElseThrow();
    }
}
