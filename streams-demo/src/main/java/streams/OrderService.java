package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    public void saveOrder(Order order){
        orders.add(order);
    }

    public long countOrdersByStatus(String status) {
        return orders
                .stream()
                .filter(o -> o.getStatus().equals(status))
                .count();
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

    private boolean isBetween(Order o, LocalDate fromDate, LocalDate toDate) {
        return o.getOrderDate().isAfter(fromDate) && o.getOrderDate().isBefore(toDate)
                || o.getOrderDate().isEqual(fromDate) || o.getOrderDate().isEqual(toDate);
    }

    public double getTurnoverBetweenDates(LocalDate d1, LocalDate d2) {
        LocalDate fromDate = d1.isBefore(d2) ? d1 : d2;
        LocalDate toDate = d2.isAfter(d1) ? d2 : d1;

        return orders.stream()
                .filter(o -> isBetween(o,fromDate,toDate))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Optional<Product> getOrderedProductByName(String name) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }

}
//További gyakorló feladatok:
// Írj egy metódust ami paraméterként vár két dátumot, és adjuk vissza a két dátum közötti árbevételt,
// vagyis a két dátum közötti rendelések termékeinek az összértékét!
//Keressünk meg egy terméket a neve alapján, amit paraméterként lehet megadni.
//Adjuk vissza azt a rendelést, ami a legdrágább terméket tartalmazza. Ha több ilyen van bármelyiket!
