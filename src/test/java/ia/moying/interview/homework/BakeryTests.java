package ia.moying.interview.homework;

import ia.moying.interview.homework.domain.Bread;
import ia.moying.interview.homework.domain.MixedGrainBread;
import ia.moying.interview.homework.domain.TunaSandwich;
import ia.moying.interview.homework.domain.WholeWheatBread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BakeryTests {

    private final BreadWarehouse warehouse = new BreadWarehouse();
    private final Bakery bakery = new Bakery(warehouse);

    @BeforeEach
    void addBreads() {
        warehouse.clear();
        warehouse.in(new WholeWheatBread());
        warehouse.in(new WholeWheatBread());
        warehouse.in(new MixedGrainBread());
        warehouse.in(new TunaSandwich(LocalDate.now()));
        System.out.println("初始化仓库========");
        System.out.println(warehouse.statItems());
    }

    @Test
    void checkStockAfterSuccess() {
        Customer customer = new Customer("customer1");
        BreadOrder breadOrder = new BreadOrder(BreadKind.WHOLE_WHEAT, customer);

        Bill bill = bakery.order(breadOrder);
        Bread bread = bakery.pay(bill);
        assertInstanceOf(WholeWheatBread.class, bread);

        int quantity = warehouse.getBreadQuantity(BreadKind.WHOLE_WHEAT);
        assertEquals(1, quantity);
    }

    @Test
    void checkStockAfterFailed() {
        Customer customer = new Customer("customer1");
        BreadOrder breadOrder = new BreadOrder(BreadKind.TUNA_SANDWICH, customer);

        assertThrows(BreadExpiredException.class, () -> bakery.order(breadOrder));

        int quantity = warehouse.getBreadQuantity(BreadKind.TUNA_SANDWICH);
        assertEquals(0, quantity);
    }

}
