package at.technikumwien.statisticsservice;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    public static boolean isEMonth(Long id) {
        return Arrays.stream(EMonth.values())
                .map(EMonth::getId)
                .collect(Collectors.toList())
                .contains(id);
    }
}
