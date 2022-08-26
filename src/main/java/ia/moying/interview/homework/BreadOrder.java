package ia.moying.interview.homework;

import java.util.Objects;

public record BreadOrder(BreadKind breadKind, Customer customer) {

    public BreadOrder {
        Objects.requireNonNull(breadKind);
        Objects.requireNonNull(customer);
    }
}
