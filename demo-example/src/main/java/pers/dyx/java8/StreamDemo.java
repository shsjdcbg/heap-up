package pers.dyx.java8;

import pers.dyx.java.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Java 8 Stream
 *
 * @author dyx
 * @version 1.0
 * @date 2020/7/16 16:58
 */
public class StreamDemo {

    public static void main(String args[]) {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("欧阳雪", 18, "中国", 'F'));
        personList.add(new Person("Tom", 24, "美国", 'M'));
        personList.add(new Person("Harley", 22, "英国", 'F'));
        personList.add(new Person("向天笑", 20, "中国", 'M'));
        personList.add(new Person("李康", 22, "中国", 'M'));
        personList.add(new Person("小梅", 20, "中国", 'F'));
        personList.add(new Person("何雪", 21, "中国", 'F'));
        personList.add(new Person("李康", 22, "中国", 'M'));

        System.out.println("找出年龄大于18岁的人并输出");
        personList.stream().filter(person -> person.getAge() > 18).forEach(System.out::println);

        System.out.println("统计所有中国人的数量");
        long chinaPersonNum = personList.stream().filter(person -> "中国".equals(person.getCountry())).count();
        System.out.println("中国人有：" + chinaPersonNum + "个");

        System.out.println("取出两个女性");
        personList.stream().filter(person -> 'F' == person.getSex()).limit(2).forEach(System.out::println);

        System.out.println("从第2个女性开始，取出所有的女性");
        personList.stream().filter(person -> 'F' == person.getSex()).skip(1).forEach(System.out::println);

        System.out.println("找出所有国家，去重");
        personList.stream().map(Person::getCountry).distinct().forEach(System.out::println);

        System.out.println("找出所有年龄，并排序");
        personList.stream().map(Person::getAge).sorted().forEach(System.out::println);

        System.out.println("按照年龄排序");
        personList.stream().sorted(Comparator.comparing(Person::getAge)).forEach(System.out::println);

        System.out.println("按照年龄倒序排序");
        personList.stream().sorted(Comparator.comparing(Person::getAge).reversed()).forEach(System.out::println);

        System.out.println("判断是否都是成年人");
        boolean adult = personList.stream().filter(person -> person.getAge() != null)
                .allMatch(person -> person.getAge() >= 18);
        System.out.println("是否都是成年人：" + adult);

        System.out.println("判断是否都是中国人");
        boolean chinaese = personList.stream().allMatch(p -> "中国".equals(p.getCountry()));
        System.out.println("是否都是中国人：" + chinaese);

        System.out.println("判断是否有英国人");
        boolean english = personList.stream().anyMatch(p -> "英国".equals(p.getCountry()));
        System.out.println("是否有英国人：" + english);

        System.out.println("找出年龄最大的人");
        Optional<Person> maxAge = personList.stream().max(Comparator.comparing(Person::getAge));
        maxAge.ifPresent(person -> System.out.println("年龄最大的人信息：" + person));

        System.out.println("找出年龄最小的人");
        Optional<Person> minAge = personList.stream().min(Comparator.comparing(Person::getAge));
        minAge.ifPresent(person -> System.out.println("年龄最小的人信息：" + person));

        System.out.println("求所有人的年龄之和");
        Optional<Integer> reduce = personList.stream().map(Person::getAge).reduce(Integer::sum);
        System.out.println("年龄总和：" + reduce);

        System.out.println("将国家收集起来转换成list");
        List<String> collect = personList.stream().map(Person::getCountry).distinct().collect(Collectors.toList());
        System.out.println(collect);

        System.out.println("计算平均年龄");
        Double avgAge = personList.stream().collect(Collectors.averagingInt(Person::getAge));
        System.out.println("平均年龄为：" + avgAge);

    }

}
