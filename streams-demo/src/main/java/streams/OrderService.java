package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private String catParam;


    public void saveOrder(Order order){
        orders.add(order);
    }

    public long countOrdersByStatus(String status) {
        return orders
                .stream()
                .filter(o -> o.getStatus().equals(status))
                .count();
    }

    private boolean catCheck(Order order) {
        return order.getProducts().stream().anyMatch(c -> c.getCategory().equals(catParam));
    }

    public List<Order> orderByCategory(String category) {
        return orders
                .stream()
                .filter(o -> o.getProducts()
                        .stream()
                        .anyMatch(p -> p.getCategory().equals(category)))
                .collect(Collectors.toList());
    }

    public List<Product> productsOverAmountPrice(int price) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getPrice()>price)
                .distinct()
                .collect(Collectors.toList());
    }

}
