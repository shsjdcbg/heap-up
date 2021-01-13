package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author dyx
 * @date 2021/1/8 15:48
 */
class DeserializingBeanTest {

    @Test
    public void whenDeserializingUsingJsonAnySetter_thenCorrect()
            throws IOException {
        String json
                = "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";

        ExtendableBean bean = new ObjectMapper()
                .readerFor(ExtendableBean.class)
                .readValue(json);

        System.out.println(bean);
    }

    @Test
    public void whenDeserializingUsingJsonCreator_thenCorrect()
            throws IOException {

        String json = "{\"id\":1,\"theName\":\"My bean\"}";

        BeanWithCreator bean = new ObjectMapper()
                .readerFor(BeanWithCreator.class)
                .readValue(json);
        System.out.println(bean);
    }

    @Test
    public void whenDeserializingUsingJsonInject_thenCorrect()
            throws IOException {

        String json = "{\"name\":\"My bean\"}";

        InjectableValues inject = new InjectableValues.Std()
                .addValue(int.class, 1);
        BeanWithInject bean = new ObjectMapper().reader(inject)
                .forType(BeanWithInject.class)
                .readValue(json);

        System.out.println(bean);
    }


    @Test
    public void whenDeserializingUsingJsonSetter_thenCorrect()
            throws IOException {

        String json = "{\"id\":1,\"n\":\"My bean\"}";

        MyBean bean = new ObjectMapper()
                .readerFor(MyBean.class)
                .readValue(json);
        System.out.println(bean);
    }

    @Test
    public void whenDeserializingUsingJsonDeserialize_thenCorrect()
            throws IOException {

        String json
                = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";

        SimpleDateFormat df
                = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        EventWithSerializer event = new ObjectMapper()
                .readerFor(EventWithSerializer.class)
                .readValue(json);

        System.out.println(event);
    }

    @Test
    public void whenDeserializingUsingJsonAlias_thenCorrect() throws IOException {
        String json = "{\"fName\": \"John\", \"lastName\": \"Green\"}";
        AliasBean aliasBean = new ObjectMapper().readerFor(AliasBean.class).readValue(json);
        Assertions.assertEquals("John", aliasBean.getFirstName());
    }

@Test
public void whenDeserializingPolymorphic_thenCorrect()
        throws IOException {
    String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";

    Zoo zoo = new ObjectMapper()
            .readerFor(Zoo.class)
            .readValue(json);

    System.out.println(zoo);
}

}
