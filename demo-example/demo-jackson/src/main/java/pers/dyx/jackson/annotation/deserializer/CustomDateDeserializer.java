package pers.dyx.jackson.annotation.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dyx
 * @date 2021/1/8 16:36
 */
public class CustomDateDeserializer
        extends StdDeserializer<Date> {

    private static final SimpleDateFormat FORMATTER
            = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        String date = jsonparser.getText();
        try {
            return FORMATTER.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
