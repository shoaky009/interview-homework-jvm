package ia.moying.interview.homework;

import java.util.Objects;

public record Customer(String name) {

    public Customer {
        Objects.requireNonNull(name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name不能为空");
        }
    }

}
