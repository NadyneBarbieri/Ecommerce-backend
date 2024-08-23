package model;

public enum OrderStatus {
    PENDING,         // Pedido pendente de processamento
    PROCESSING,      // Pedido está sendo processado
    SHIPPED,         // Pedido foi enviado
    DELIVERED,       // Pedido foi entregue ao cliente
    CANCELED,        // Pedido foi cancelado
    RETURNED,        // Pedido foi devolvido
    COMPLETED        // Pedido foi concluído com sucesso
}
