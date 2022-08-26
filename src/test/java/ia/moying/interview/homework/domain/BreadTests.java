package ia.moying.interview.homework.domain;

import ia.moying.interview.homework.BreadExpiredException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BreadTests {

    @Test
    void shouldNotExpiredGivenNewWholeWheatBread() {
        Bread bread = new WholeWheatBread();
        assertFalse(bread.expired());
        assertFalse(bread.todayExpire());
    }

    @Test
    void shouldExpiredGiven2DaysAgoWholeWheatBread() {
        LocalDate expiredDate = LocalDate.now().minusDays(2L);
        Bread bread = new WholeWheatBread(expiredDate);
        assertTrue(bread.expired());
        assertFalse(bread.todayExpire());
    }

    @Test
    void shouldTodayExpireGivenTodayExpireWholeWheatBread() {
        LocalDate expiredDate = LocalDate.now();
        Bread bread = new WholeWheatBread(expiredDate);
        assertFalse(bread.expired());
        assertTrue(bread.todayExpire());
    }

    @Test
    void checkWholeWheatBreadPrice() {
        Bread bread = new WholeWheatBread();
        assertEquals(12, bread.calculatePrice());

        LocalDate expiredDate = LocalDate.now();
        bread = new WholeWheatBread(expiredDate);
        assertEquals(6, bread.calculatePrice());
    }

    @Test
    void checkMixedGrainBreadPrice() {
        LocalDate expiredDate = LocalDate.now();
        Bread bread = new MixedGrainBread(expiredDate);
        LocalTime notInFree = LocalTime.of(9, 1);
        LocalTime inFree = LocalTime.of(7, 0);

        try (MockedStatic<LocalTime> mockTime = Mockito.mockStatic(LocalTime.class)) {
            mockTime.when(LocalTime::now).thenReturn(notInFree);
            int price = bread.calculatePrice();
            assertEquals(10, price);

            mockTime.when(LocalTime::now).thenReturn(inFree);
            price = bread.calculatePrice();
            assertEquals(0, price);
        }
    }

    @Test
    void checkTunaSandwichPrice() {
        Bread bread = new TunaSandwich();
        assertEquals(12, bread.calculatePrice());

        bread = new TunaSandwich(LocalDate.now());
        assertThrows(BreadExpiredException.class, bread::calculatePrice);
    }

}