package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author dyx
 * @date 2021/1/8 15:48
 */
class GeneralBeanTest {

    @Test
    public void whenUsingJsonProperty_thenCorrect()
            throws IOException {
        MyBean2 bean = new MyBean2(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);
        System.out.println(result);

        MyBean2 resultBean = new ObjectMapper()
                .readerFor(MyBean2.class)
                .readValue(result);
        System.out.println(resultBean);
    }

    @Test
    public void whenSerializingUsingJsonFormat_thenCorrect()
            throws JsonProcessingException {
        Date date = new Date();
        EventWithFormat event = new EventWithFormat("party", date);

        String result = new ObjectMapper().writeValueAsString(event);

        System.out.println(result);
    }

}
