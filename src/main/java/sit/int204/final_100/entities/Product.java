// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "productName", nullable = false, length = 50)
    private String productName;

    @ColumnDefault("0.00")
    @Column(name = "unitPrice")
    private Double unitPrice;

    @Size(max = 30)
    @Column(name = "package", length = 30)
    private String packageField;

    @NotNull
    @Column(name = "discontinued")
    private Boolean discontinued = false;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

}