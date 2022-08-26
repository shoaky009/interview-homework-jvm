package ia.moying.interview.homework;

public interface BreadItemInspector {

    /**
     * 出库前检查item
     *
     * @param item 面包
     */
    void check(BreadWarehouse.BreadItem item);

    void reset(BreadWarehouse.BreadItem item);
}
