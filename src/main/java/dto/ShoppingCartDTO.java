package dto;

import java.util.List;

import model.CartItem;

public class ShoppingCartDTO {

	private Long id;

	private List<CartItem> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

}
