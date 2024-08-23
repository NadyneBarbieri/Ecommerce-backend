package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.CartItemDTO;
import model.CartItem;
import model.Product;
import repository.CartItemRepository;
import repository.ProductRepository;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Converte CartItem para CartItemDTO
    public CartItemDTO toDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setProductId(cartItem.getProduct().getId());
        dto.setProductName(cartItem.getProduct().getName());
        dto.setQuantity(cartItem.getQuantity());
        dto.setPrice(cartItem.getPrice());
        return dto;
    }

    // Converte CartItemDTO para CartItem
    public CartItem toEntity(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setPrice(cartItemDTO.getPrice());
        return cartItem;
    }

    // Adiciona um item ao carrinho
    public CartItem addItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = toEntity(cartItemDTO);
        return cartItemRepository.save(cartItem);
    }

    // Atualiza um item do carrinho
    public CartItem updateItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = toEntity(cartItemDTO);
        return cartItemRepository.save(cartItem);
    }

    // Remove um item do carrinho pelo ID
    public void removeItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    // Encontra todos os itens do carrinho
    public List<CartItem> findAllItems() {
        return cartItemRepository.findAll();
    }

    // Encontra um item do carrinho pelo ID
    public Optional<CartItem> findItemById(Long id) {
        return cartItemRepository.findById(id);
    }
}
