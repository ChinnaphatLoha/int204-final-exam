// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sit.int204.final_100.dtos.BaseOrderDto;
import sit.int204.final_100.dtos.OrderInput;
import sit.int204.final_100.entities.Order;
import sit.int204.final_100.exceptions.DuplicateValueException;
import sit.int204.final_100.exceptions.NotFoundException;
import sit.int204.final_100.repositories.OrderRepository;

import java.time.Instant;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public BaseOrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Order id '%s' does not exist", id))
        );
        return modelMapper.map(order, BaseOrderDto.class);
    }

    @Transactional
    public BaseOrderDto createOrder(OrderInput input) {
        String orderNumber = input.getOrderNumber();
        if (Boolean.TRUE.equals(orderRepository.existsByOrderNumber(orderNumber))) {
            throw new DuplicateValueException(
                    String.format("Duplicate entry '%s' for key 'orders.orderNumber'", orderNumber));
        }
        Order newOrder = modelMapper.map(input, Order.class);
        newOrder.setOrderDate(Instant.now());
        return modelMapper.map(orderRepository.save(newOrder), BaseOrderDto.class);
    }
}
