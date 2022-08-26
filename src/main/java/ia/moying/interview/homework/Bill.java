package ia.moying.interview.homework;

import com.google.common.collect.Range;

import java.util.Objects;

public record Bill(int itemId, Customer customer, int price) {

    public Bill {
        Objects.requireNonNull(customer);
        boolean less = Range.lessThan(0).contains(price);
        if (less) {
            throw new IllegalArgumentException();
        }
    }
}
