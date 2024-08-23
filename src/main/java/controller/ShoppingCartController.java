package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.ShoppingCart;
import service.ShoppingCartService;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin("*")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @PostMapping("/{cartId}/adicionar/{productId}")
    public ResponseEntity<ShoppingCart> addItem(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam Integer quantity) {
        ShoppingCart cart = cartService.addItem(cartId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartId}/remover/{productId}")
    public ResponseEntity<ShoppingCart> removeItem(@PathVariable Long cartId, @PathVariable Long productId) {
        ShoppingCart cart = cartService.removeItem(cartId, productId);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable Long cartId) {
        ShoppingCart cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }
}
