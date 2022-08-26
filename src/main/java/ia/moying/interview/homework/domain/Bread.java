package ia.moying.interview.homework.domain;

import com.google.common.collect.Range;
import ia.moying.interview.homework.BreadKind;
import ia.moying.interview.homework.Expirable;

import java.time.LocalDate;
import java.util.StringJoiner;

public abstract class Bread implements Expirable {

    protected final int originalPrice;
    protected final LocalDate expirationDate;
    protected final boolean hasMeat;

    Bread(int originalPrice, LocalDate expirationDate) {
        this.originalPrice = originalPrice;
        this.expirationDate = expirationDate;
        this.hasMeat = false;
    }

    Bread(int originalPrice, LocalDate expirationDate, boolean hasMeat) {
        this.originalPrice = originalPrice;
        this.expirationDate = expirationDate;
        this.hasMeat = hasMeat;
    }

    @Override
    public boolean expired() {
        return Range.greaterThan(getExpirationDate()).contains(LocalDate.now());
    }

    @Override
    public boolean todayExpire() {
        return LocalDate.now().isEqual(getExpirationDate());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Bread.class.getSimpleName() + "[", "]")
                .add("price=" + originalPrice)
                .add("productionTime=" + expirationDate)
                .toString();
    }

    public abstract BreadKind kind();

    /**
     * 计算金额，未来可能需要额外入参暂时不考虑
     *
     * @return 实际支付金额
     * @throws RuntimeException 当无法出库时
     */
    public abstract int calculatePrice();

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
