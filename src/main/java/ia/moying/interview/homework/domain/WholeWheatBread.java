package ia.moying.interview.homework.domain;

import ia.moying.interview.homework.BreadKind;

import java.time.LocalDate;

public class WholeWheatBread extends Bread {

    public WholeWheatBread() {
        super(12, LocalDate.now().plusDays(2L));
    }

    public WholeWheatBread(LocalDate expirationDate) {
        super(12, expirationDate);
    }

    @Override
    public BreadKind kind() {
        return BreadKind.WHOLE_WHEAT;
    }

    @Override
    public int calculatePrice() {
        if (todayExpire()) {
            return originalPrice / 2;
        }
        return originalPrice;
    }
}
