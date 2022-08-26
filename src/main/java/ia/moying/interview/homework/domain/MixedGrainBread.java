package ia.moying.interview.homework.domain;

import com.google.common.collect.Range;
import ia.moying.interview.homework.BreadKind;

import java.time.LocalDate;
import java.time.LocalTime;

public class MixedGrainBread extends Bread {

    private static final Range<LocalTime> FREE_RANGE = Range.closed(LocalTime.of(7, 0), LocalTime.of(9, 0));

    public MixedGrainBread() {
        super(10, LocalDate.now().plusDays(2L));
    }

    public MixedGrainBread(LocalDate expirationDate) {
        super(10, expirationDate);
    }

    @Override
    public BreadKind kind() {
        return BreadKind.MIXED_GRAIN;
    }

    @Override
    public int calculatePrice() {
        if (isFree()) {
            return 0;
        }
        return originalPrice;
    }

    private boolean isFree() {
        return todayExpire() && FREE_RANGE.contains(LocalTime.now());
    }
}
