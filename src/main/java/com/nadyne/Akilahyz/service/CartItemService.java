package com.nadyne.Akilahyz.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadyne.Akilahyz.dto.CartItemDTO;
import com.nadyne.Akilahyz.dto.ProductDTO;
import com.nadyne.Akilahyz.model.CartItemModel;
import com.nadyne.Akilahyz.model.CartModel;
import com.nadyne.Akilahyz.model.ProductModel;
import com.nadyne.Akilahyz.repository.CartItemRepository;
import com.nadyne.Akilahyz.repository.CartRepository;
import com.nadyne.Akilahyz.repository.ProductRepository;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	private CartItemDTO convertToDto(CartItemModel cartItemModel) {
	    CartItemDTO cartItemDTO = new CartItemDTO();
	    cartItemDTO.setId(cartItemModel.getId());
	    
	    // Converter ProductModel para ProductDTO
	    ProductDTO productDTO = convertToProductDTO(cartItemModel.getProduct());
	    cartItemDTO.setProduct(productDTO);
	    
	    cartItemDTO.setQuantity(cartItemModel.getQuantity());
	    cartItemDTO.setTotalPrice(cartItemModel.getTotalPrice()); // Ajuste conforme necessário
	    
	    return cartItemDTO;
	}


	// Método para converter ProductModel para ProductDTO
	private ProductDTO convertToProductDTO(ProductModel productModel) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productModel.getId());
		productDTO.setName(productModel.getName());
		productDTO.setPrice(productModel.getPrice());
		// Adicione mais campos conforme necessário
		return productDTO;
	}


	public CartItemDTO addCartItem(Long productId, CartItemDTO cartItemDTO) {
	    // Busca o produto pelo productId
	    ProductModel product = productRepository.findById(productId)
	            .orElseThrow(() -> new OpenApiResourceNotFoundException("Produto não encontrado"));

	    // Cria um novo CartItemModel
	    CartItemModel cartItemModel = new CartItemModel();
	    cartItemModel.setProduct(product); // Usa ProductModel
	    cartItemModel.setQuantity(cartItemDTO.getQuantity());

	    // Calcula o totalPrice: quantidade * preço do produto, usando BigDecimal
	    BigDecimal productPrice = BigDecimal.valueOf(product.getPrice()); // Converte o preço para BigDecimal
	    BigDecimal quantity = BigDecimal.valueOf(cartItemDTO.getQuantity()); // Converte a quantidade para BigDecimal
	    BigDecimal totalPrice = productPrice.multiply(quantity); // Calcula o total usando BigDecimal

	    // Define o totalPrice no CartItemModel
	    cartItemModel.setTotalPrice(totalPrice);

	    // Salva o item do carrinho no banco de dados
	    cartItemModel = cartItemRepository.save(cartItemModel);

	    // Retorna o CartItemDTO convertido
	    return convertToDto(cartItemModel);
	}

	// Listar todos os itens de um carrinho
	public List<CartItemDTO> listCartItems(Long cartId) {
		// Busca o carrinho pelo ID
		CartModel cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

		// Busca os itens do carrinho
		List<CartItemModel> cartItems = cartItemRepository.findAllByCart(cart);

		// Converte os itens para DTO e retorna
		return cartItems.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	// busca produtos por id
	public List<CartItemModel> findItemsByProduct(Long product) {
		return cartItemRepository.findAllByProduct_Id(product);
	}

	public void removeCartItem(Long id) {
		// Verifica se o item existe
		CartItemModel cartItem = cartItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Item do carrinho não encontrado"));

		// Remove o item
		cartItemRepository.delete(cartItem);
	}

	public CartItemDTO updateCartItem(Long id, CartItemDTO cartItemDTO) {
	    // Busca o item no banco de dados
	    CartItemModel cartItemModel = cartItemRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Item do carrinho não encontrado"));

	    // Atualiza a quantidade
	    cartItemModel.setQuantity(cartItemDTO.getQuantity());

	    // Verifica se o produto não é nulo e se o preço é válido
	    if (cartItemModel.getProduct() != null && cartItemModel.getProduct().getPrice() != null) {
	        // Converte o preço para BigDecimal
	        BigDecimal price = BigDecimal.valueOf(cartItemModel.getProduct().getPrice());
	        // Calcula o preço total
	        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(cartItemModel.getQuantity()));
	        cartItemModel.setTotalPrice(totalPrice);
	    } else {
	        // Lida com o caso em que o preço é nulo
	        cartItemModel.setTotalPrice(BigDecimal.ZERO); // ou outra lógica que faça sentido
	    }

	    // Salva as alterações no banco de dados
	    cartItemModel = cartItemRepository.save(cartItemModel);

	    // Retorna o DTO atualizado
	    return convertToDto(cartItemModel);
	}


	public List<CartItemDTO> listAllCartItems() {
	    List<CartItemModel> allCartItems = cartItemRepository.findAll();
	    return allCartItems.stream().map(item -> convertToDto(item)).collect(Collectors.toList());
	}







}
