// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sit.int204.final_100.exceptions.InvalidTotalAmountException;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderInput {
    @NotNull(message = "must not be null")
    @Size(min = 6, max = 10, message = "size must be between 6 and 10")
    @Pattern(regexp = "^\\d+$", message = "order number must be digits only")
    String orderNumber;
    @NotNull(message = "must not be null")
    CustomerDto customer;
    @NotNull(message = "must not be null")
    Double totalAmount;
    @NotNull(message = "must not be null")
    @Size(min = 1, max = 5, message = "size must be between 1 and 5")
    Set<OrderItemDto> orderItems;

    public OrderInput(String orderNumber, CustomerDto customer, Double totalAmount, Set<OrderItemDto> orderItems) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.orderItems = new HashSet<>(orderItems);
        this.totalAmount = totalAmount;
    }

    public void setTotalAmount(Double totalAmountInput) {
        Double total = 0.0;
        for (OrderItemDto item : this.orderItems) {
            total += item.unitPrice * item.quantity.doubleValue();
        }
        if (!total.equals(totalAmountInput)) {
            throw new InvalidTotalAmountException("Invalid total amount");
        }
        this.totalAmount = total;
    }

    @Data
    public static class CustomerDto {
        Integer id;
    }

    @Data
    public static class OrderItemDto {
        Integer quantity;
        Double unitPrice;
        ProductDto product;
    }

    @Data
    public static class ProductDto {
        Integer id;
    }
}
