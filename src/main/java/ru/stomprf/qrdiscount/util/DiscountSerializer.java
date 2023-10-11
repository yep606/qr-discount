package ru.stomprf.qrdiscount.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.stomprf.qrdiscount.model.Discount;

import java.io.IOException;

public class DiscountSerializer extends JsonSerializer<Discount> {
    @Override
    public void serialize(Discount discount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(discount.getValue());
    }
}
