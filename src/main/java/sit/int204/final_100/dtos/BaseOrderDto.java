// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.dtos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Data
public class BaseOrderDto {
    Integer id;
    String orderNumber;
    Instant orderDate;
    CustomerDto customer;
    Double totalAmount;
    Set<OrderItemDto> orderItems;

    @Data
    public static class CustomerDto {
        Integer id;
        @JsonIgnore
        String firstName;
        @JsonIgnore
        String lastName;

        public String getName() {
            return this.firstName + " " + this.lastName;
        }
    }

    @Data
    public static class OrderItemDto {
        Integer id;
        Double unitPrice;
        Integer quantity;
        String productName;
    }
}