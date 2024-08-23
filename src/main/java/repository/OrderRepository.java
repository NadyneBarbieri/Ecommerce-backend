package repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Order;
import model.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByStatus(OrderStatus status);

	List<Order> findByUserId(Long userId);

	List<Order> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<Order> findByStatusAndUserId(OrderStatus status, Long userId);

	List<Order> findByStatusIn(List<OrderStatus> statuses);
}
