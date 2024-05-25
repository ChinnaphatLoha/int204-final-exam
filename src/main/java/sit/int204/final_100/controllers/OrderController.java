// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.final_100.dtos.BaseOrderDto;
import sit.int204.final_100.dtos.OrderInput;
import sit.int204.final_100.services.OrderService;

@RestController
@RequestMapping("/65130500100/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseOrderDto> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<BaseOrderDto> createOrder(@Valid @RequestBody OrderInput input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(input));
    }
}
