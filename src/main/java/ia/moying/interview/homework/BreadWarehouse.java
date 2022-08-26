package ia.moying.interview.homework;

import ia.moying.interview.homework.domain.Bread;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BreadWarehouse {

    private final List<BreadItem> breadItems = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger();

    /**
     * 下单预扣面包，获取成功后会锁定对应面包库存
     *
     * @param order order
     * @return 出库的面包，不会为null
     * @throws BreadServiceException 库存不足时
     */
    public BreadItem withhold(BreadOrder order, List<BreadItemInspector> inspectors) {
        BreadKind kind = order.breadKind();
        BreadItem breadItem = breadItems.stream()
                .filter(bs -> bs.getBread().kind() == kind)
                .filter(bs -> !bs.isLock())
                .findAny().orElseThrow(() -> new BreadServiceException("面包:" + kind + "库存不足"));

        for (BreadItemInspector inspector : inspectors) {
            try {
                inspector.check(breadItem);
            } catch (Exception e) {
                inspector.reset(breadItem);
                throw e;
            }
        }
        breadItem.lock(order);
        return breadItem;
    }

    public void in(Bread bread) {
        if (bread == null) {
            return;
        }
        breadItems.add(new BreadItem(idGenerator.incrementAndGet(), bread));
    }

    public void in(Collection<Bread> breads) {
        Objects.requireNonNull(breads);
        if (breads.isEmpty()) {
            return;
        }
        breads.stream().map(bread -> new BreadItem(idGenerator.incrementAndGet(), bread))
                .forEach(breadItems::add);
    }

    public void clear() {
        breadItems.clear();
    }

    public String statItems() {
        Map<BreadKind, Long> statMap = breadItems.stream()
                .collect(Collectors.groupingBy(x -> x.bread.kind(), Collectors.counting()));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<BreadKind, Long> entry : statMap.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public int getBreadQuantity(BreadKind kind) {
        Predicate<BreadItem> predicate = bi -> !bi.isLock();
        if (kind != null) {
            predicate = predicate.and(bi -> bi.getBread().kind() == kind);
        }
        return (int) breadItems.stream().filter(predicate).count();
    }

    public void unlock(int id) {
        breadItems.stream().filter(bi -> bi.getId() == id)
                .forEach(BreadItem::unlock);
    }

    public Bread out(Bill bill) {
        BreadItem breadItem = breadItems.stream()
                .filter(bi -> bi.getId() == bill.itemId())
                .findAny().orElseThrow(() -> new BreadServiceException("面包库存不存在"));
        breadItems.remove(breadItem);
        return breadItem.getBread();
    }

    public void remove(BreadItem breadItem) {
        breadItems.remove(breadItem);
    }

    public static class BreadItem {

        private final int id;
        private final Bread bread;
        private BreadOrder order;

        private BreadItem(int id, Bread bread) {
            this.id = id;
            this.bread = bread;
        }

        void lock(BreadOrder order) {
            this.order = order;
        }

        public boolean isLock() {
            return order != null;
        }

        void unlock() {
            order = null;
        }

        public int getId() {
            return id;
        }

        public Bread getBread() {
            return bread;
        }
    }

}
