// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 40)
    @NotNull
    @Column(name = "firstName", nullable = false, length = 40)
    private String firstName;

    @Size(max = 40)
    @NotNull
    @Column(name = "lastName", nullable = false, length = 40)
    private String lastName;

    @Size(max = 40)
    @Column(name = "city", length = 40)
    private String city;

    @Size(max = 40)
    @Column(name = "country", length = 40)
    private String country;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new LinkedHashSet<>();

}