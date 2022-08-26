package ia.moying.interview.homework;

/**
 * 标记是否可过期的Item.
 *
 * @author mhx
 */
public interface Expirable {

    /**
     * @return true:已过期,false:未过期.
     */
    boolean expired();

    /**
     * @return true:今日内过期,false:不是今日内过期或已过期
     */
    boolean todayExpire();
}
