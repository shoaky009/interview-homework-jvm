package ia.moying.interview.homework.domain;

import ia.moying.interview.homework.BreadExpiredException;
import ia.moying.interview.homework.BreadKind;

import java.time.LocalDate;

public class TunaSandwich extends Bread {

    public TunaSandwich() {
        super(12, LocalDate.now().plusDays(1L));
    }

    public TunaSandwich(LocalDate expirationDate) {
        super(12, expirationDate, true);
    }

    @Override
    public BreadKind kind() {
        return BreadKind.TUNA_SANDWICH;
    }

    @Override
    public int calculatePrice() {
        if (expired() || todayExpire()) {
            throw new BreadExpiredException("金枪鱼三明治已过期");
        }
        return originalPrice;
    }
}
