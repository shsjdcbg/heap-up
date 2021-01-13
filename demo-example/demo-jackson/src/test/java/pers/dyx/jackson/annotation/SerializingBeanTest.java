package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

/**
 * @author dyx
 * @date 2021/1/8 15:48
 */
class SerializingBeanTest {
    @Test
    public void whenSerializingUsingJsonAnyGetter_thenCorrect() throws JsonProcessingException {

        ExtendableBean bean = new ExtendableBean("My bean");
        bean.add("attr1", "val1");
        bean.add("attr2", "val2");

        String result = new ObjectMapper().writeValueAsString(bean);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonGetter_thenCorrect()
            throws JsonProcessingException {

        MyBean bean = new MyBean(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonPropertyOrder_thenCorrect()
            throws JsonProcessingException {

        MyBean1 bean = new MyBean1(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonRawValue_thenCorrect()
            throws JsonProcessingException {

        RawBean bean = new RawBean("My bean", "{\"attr\":false}");

        String result = new ObjectMapper().writeValueAsString(bean);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonValue_thenCorrect()
            throws IOException {

        String enumAsString = new ObjectMapper()
                .writeValueAsString(TypeEnumWithValue.TYPE1);

        System.out.println(enumAsString);
    }

    @Test
    public void whenSerializingUsingJsonRootName_thenCorrect()
            throws JsonProcessingException {

        UserWithRoot user = new UserWithRoot(1, "John");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = mapper.writeValueAsString(user);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonSerialize_thenCorrect()
            throws JsonProcessingException {
        EventWithSerializer event = new EventWithSerializer("party", new Date());

        String result = new ObjectMapper().writeValueAsString(event);
        System.out.println(result);
    }


    @Test
    public void whenSerializingUsingJsonIgnoreProperties_thenCorrect()
            throws JsonProcessingException {

        BeanWithIgnore bean = new BeanWithIgnore(1, "My bean");

        String result = new ObjectMapper()
                .writeValueAsString(bean);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonIgnoreType_thenCorrect()
            throws JsonProcessingException {

        User.Name name = new User.Name("John", "Doe");
        User user = new User(1, name);

        String result = new ObjectMapper()
                .writeValueAsString(user);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonInclude_thenCorrect()
            throws JsonProcessingException {

        MyBean3 bean = new MyBean3(1, null);

        String result = new ObjectMapper()
                .writeValueAsString(bean);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonAutoDetect_thenCorrect()
            throws JsonProcessingException {

        PrivateBean bean = new PrivateBean(1, "My bean");

        String result = new ObjectMapper()
                .writeValueAsString(bean);

        System.out.println(result);
    }

    @Test
    public void whenSerializingPolymorphic_thenCorrect()
            throws JsonProcessingException {
        Zoo.Dog dog = new Zoo.Dog("lacy");
        Zoo zoo = new Zoo(dog);

        String result = new ObjectMapper()
                .writeValueAsString(zoo);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonUnwrapped_thenCorrect()
            throws JsonProcessingException {
        UnwrappedUser.Name name = new UnwrappedUser.Name("John", "Doe");
        UnwrappedUser user = new UnwrappedUser(1, name);

        String result = new ObjectMapper().writeValueAsString(user);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonView_thenCorrect()
            throws JsonProcessingException {
        Item item = new Item(2, "book", "John");

        String result = new ObjectMapper()
                .writerWithView(Views.Public.class)
                .writeValueAsString(item);

        System.out.println(result);

        String result1 = new ObjectMapper()
                .writerWithView(Views.Internal.class)
                .writeValueAsString(item);

        System.out.println(result1);
    }

    @Test
    public void whenSerializingUsingJacksonReferenceAnnotation_thenCorrect()
            throws JsonProcessingException {
        UserWithRef user = new UserWithRef(1, "John");
        ItemWithRef item = new ItemWithRef(2, "book", user);
        user.addItem(item);

        String result = new ObjectMapper().writeValueAsString(item);

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonIdentityInfo_thenCorrect()
            throws JsonProcessingException {
        UserWithIdentity user = new UserWithIdentity(1, "John");
        ItemWithIdentity item = new ItemWithIdentity(2, "book", user);
        user.addItem(item);

        String result = new ObjectMapper().writeValueAsString(item);

        System.out.println(result);
    }

@Test
public void whenSerializingUsingJsonFilter_thenCorrect()
        throws JsonProcessingException {
    BeanWithFilter bean = new BeanWithFilter(1, "My bean");

    FilterProvider filters
            = new SimpleFilterProvider().addFilter(
            "myFilter",
            SimpleBeanPropertyFilter.filterOutAllExcept("name"));

    String result = new ObjectMapper()
            .writer(filters)
            .writeValueAsString(bean);

    System.out.println(result);
}
}
