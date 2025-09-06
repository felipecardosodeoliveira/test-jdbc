package entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Double latitude;
    private Double longitude;
    private Instant moment;
    private OrderStatus status;
    public List<Product> products = new ArrayList<>();

    public Order(Long id, Double latitude, Double longitude, Instant moment, OrderStatus status) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.moment = moment;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", moment=" + moment +
                ", status=" + status +
                ", products=" + products +
                '}';
    }
}
