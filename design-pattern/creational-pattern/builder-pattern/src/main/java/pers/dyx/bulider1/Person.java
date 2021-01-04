package pers.dyx.bulider1;

/**
 * Builder 模式
 * 当一个类中有很多属性，而且大部分属性为空时，如果要为这个类提供一个完整属性列表的构造函数的话
 * 会使用这个类被调用时需要提供所有的参数，这会非常的麻烦。因为大部分属性都是为空的。
 * 或者可以提供多个构造函数，分别适配不同的情形，但这样的话会导致构造函数非常多，
 * 而且调用这些构建函数时很容易混淆不同的参数（特别是这些参数有相同的类型）。
 * <p>
 * 而Builder模式可以解决以上问题，它通过一个Builder类来构造一个对象，而不是以这个类的构造函数，
 * 同时以各种各样的setter方法对需要更改默认属性的进行设置。
 *
 * @author dyx
 * @date 2018/8/23 09:30
 */
public class Person {
    private final String name;
    private final int age;
    private final int gender;
    private final String phoneNum;
    private final String email;
    private final String city;

    private Person(Builder builder) {
        name = builder.name;
        age = builder.age;
        gender = builder.gender;
        phoneNum = builder.phoneNum;
        email = builder.email;
        city = builder.city;
    }

    public String getMessage() {
        return name + age + gender + phoneNum + email + city;
    }

    public static class Builder {
        private String name;
        private int age = -1;
        private int gender = 1;
        private String phoneNum = "unkown";
        private String email = "unkown";
        private String city = "unkown";

        public Builder(String name) {
            this.name = name;
        }

        public Person build() {
            return new Person(this);
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(int gender) {
            this.gender = gender;
            return this;
        }

        public Builder phoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }
    }

    /**
     * 通过会有其它类中进行调用，这是为了方便，在本类中进行demo调用。
     */
    public static void main(String[] args) {
        Person person = new Person.Builder("ljh").age(5).gender(1).phoneNum("13579246810").city("gz").build();
        System.out.println(person.getMessage());
    }
}
