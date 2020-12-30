package at.technikumwien.statisticsservice.model;

import javax.persistence.EmbeddedId;

public enum EMonth {
    JANUARY(1L),
    FEBRUARY(2L),
    MARCH(3L),
    APRIL(4L),
    MAY(5L),
    JUNE(6L),
    JULY(7L),
    AUGUST(8L),
    SEPTEMBER(9L),
    OCTOBER(10L),
    NOVEMBER(11L),
    DECEMBER(12L);

    private Long id;

    EMonth(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static String getMonthNameToId(Long id) {
        for (EMonth elem : EMonth.values()) {
            if (elem.getId().equals(id)) return elem.name();
        }
        return null;
    }
}
