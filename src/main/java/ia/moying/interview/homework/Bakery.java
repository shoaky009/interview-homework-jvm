package ia.moying.interview.homework;

import ia.moying.interview.homework.domain.Bread;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Bakery {

    private final BreadWarehouse warehouse;

    public Bakery(BreadWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Bill order(BreadOrder order) {
        Objects.requireNonNull(order);
        BreadWarehouse.BreadItem breadItem = warehouse.withhold(order, createBreadItemInspectors());

        Bread bread = breadItem.getBread();
        Bill bill;
        try {
            bill = new Bill(breadItem.getId(), order.customer(), bread.calculatePrice());
        } catch (Exception e) {
            warehouse.unlock(breadItem.getId());
            throw e;
        }
        return bill;
    }

    private List<BreadItemInspector> createBreadItemInspectors() {
        BreadItemInspector inspector = new DefaultBreadItemInspector(warehouse);
        return Collections.singletonList(inspector);
    }

    static class DefaultBreadItemInspector implements BreadItemInspector {

        private final BreadWarehouse warehouse;

        DefaultBreadItemInspector(BreadWarehouse warehouse) {
            this.warehouse = warehouse;
        }

        @Override
        public void check(BreadWarehouse.BreadItem item) {
            Bread bread = item.getBread();
            if (bread.expired()) {
                throw new BreadExpiredException("面包已过期");
            }
            if (bread.kind() == BreadKind.TUNA_SANDWICH && bread.todayExpire()) {
                throw new BreadExpiredException("金枪鱼三明治已过期");
            }
        }

        @Override
        public void reset(BreadWarehouse.BreadItem item) {
            warehouse.remove(item);
        }
    }

    public Bread pay(Bill bill) {
        //当作付款成功，只做释放库存操作
        return warehouse.out(bill);
    }

}
