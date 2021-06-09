package streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest implements PrintNameBeforeEach{

    OrderService ordersService = new OrderService() ;


    @BeforeEach
    public void init(){


        Product p1 = new Product("Tv","IT",2000);
        Product p2 = new Product("Laptop","IT",2400);
        Product p3 = new Product("Phone","IT",400);
        Product p4 = new Product("Lord of The Rings","Book",20);
        Product p5 = new Product("Harry Potter Collection","Book",120);

        Order o1 = new Order("pending", LocalDate.of(2021,6,7));
        o1.addProduct(p1);
        o1.addProduct(p2);
        o1.addProduct(p5);

        Order o2 = new Order("on delivery", LocalDate.of(2021,6,1));
        o2.addProduct(p3);
        o2.addProduct(p1);
        o2.addProduct(p2);

        Order o3 = new Order("pending", LocalDate.of(2021,6,6));
        o1.addProduct(p1);
        o1.addProduct(p2);
        o1.addProduct(p4);

        ordersService.saveOrder(o1);
        ordersService.saveOrder(o2);
        ordersService.saveOrder(o3);

    }

    @Test
    @DisplayName("countStatus() - Megszámolja hány rendelés van egy bizonyos státusszal")
    public void countStatus(){
        assertEquals(2L, ordersService.countOrdersByStatus("pending"));
        assertEquals(1L, ordersService.countOrdersByStatus("on delivery"));
    }

    @Test
    @DisplayName("getOrderByCategory() - Visszaadja azokat a rendeléseket, amelyek tartalmaznak a megadott categóriájú terméket")
    public void getOrderByCategory() {
        assertEquals("Book", ordersService.orderByCategory("Book").get(0).getProducts().get(2).getCategory());
    }

    @Test
    @DisplayName("getProductsByPrice() - A rendelt termékek közül visszaadja azokat, amelyek egy bizonyos egységár fölött vannak")
    public void getProductsByPrice() {
        assertEquals(1,ordersService.productsOverAmountPrice(2399).size());
        assertEquals("Laptop",ordersService.productsOverAmountPrice(2399).get(0).getName());
    }


}