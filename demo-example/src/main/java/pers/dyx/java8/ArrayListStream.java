package pers.dyx.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author: dyx
 * @date: 2018/11/8 11:47
 * @description:
 */
public class ArrayListStream {
    public static void main(String args[]) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("1", "12"));
        students.add(new Student("2", "22"));
        List<String> list = Arrays.asList("Java", "Thread", "Concurrency", "Scala", "Clohure");
        //并行stream，线程安全
//        list.parallelStream().map(String::toUpperCase).forEach(System.out::println);

        list.parallelStream().map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList()).forEach(System.out::println);

        String num = students.stream().map(Student::getAge).reduce("0", (a, b) -> a + b);

        System.out.println(num);
    }
}
