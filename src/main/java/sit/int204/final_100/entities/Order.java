// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "orderDate", nullable = false)
    private Instant orderDate;

    @Size(max = 10)
    @NotNull
    @Column(name = "orderNumber", nullable = false, length = 10)
    private String orderNumber;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orderId")
    private Set<OrderItem> orderItems = new LinkedHashSet<>();
}