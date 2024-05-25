// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.final_100.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    boolean existsByOrderNumber(String orderNumber);
}