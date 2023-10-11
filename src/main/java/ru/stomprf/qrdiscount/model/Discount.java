package ru.stomprf.qrdiscount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.stomprf.qrdiscount.util.DiscountSerializer;

@JsonSerialize(using = DiscountSerializer.class)
public enum Discount {

    FIVE(5),
    SEVEN(7),
    TEN(10),
    FIFTEEN(15),
    TWENTY(20),
    TWENTY_FIVE(25);

    private final int value;

    Discount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
