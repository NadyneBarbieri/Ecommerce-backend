package service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.CartItemDTO;
import dto.OrderDTO;
import model.Order;
import model.OrderStatus;
import model.User;
import repository.OrderRepository;
import repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    // Converte Order para OrderDTO
    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setItems(order.getItems().stream().map(item -> {
            // Converta CartItem para CartItemDTO
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPrice(item.getPrice());
            return itemDTO;
        }).collect(Collectors.toList()));
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus().name());
        dto.setTotalAmount(order.getTotalAmount());
        return dto;
    }

    // Converte OrderDTO para Order
    public Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        order.setUser(user);
        order.setOrderDate(orderDTO.getOrderDate() != null ? orderDTO.getOrderDate() : LocalDateTime.now());
        order.setStatus(OrderStatus.valueOf(orderDTO.getStatus()));
        order.setTotalAmount(orderDTO.getTotalAmount());
        return order;
    }

    // Cria um novo pedido
    public Order createOrder(OrderDTO orderDTO) {
        Order order = toEntity(orderDTO);
        return orderRepository.save(order);
    }

    // Atualiza um pedido existente
    public Order updateOrder(OrderDTO orderDTO) {
        Order order = toEntity(orderDTO);
        return orderRepository.save(order);
    }

    // Remove um pedido pelo ID
    public void removeOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Encontra todos os pedidos
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    // Encontra um pedido pelo ID
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
