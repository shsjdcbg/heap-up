package pers.dyx.thread.FightExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author: dyx
 * @date: 2018/11/6 09:32
 * @description: 模拟查询航班信息，利用线程的join方法并行查询各航班信息完成后汇总
 */
public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList(
            "CSA", "CEA", "HNA"
    );

    public static void main(String args[]) {
        List<String> results = search("SH", "BJ");
        System.out.println("###result");
        results.forEach(System.out::println);
    }

    private static List<String> search(String origin, String dest) {
        final List<String> results = new ArrayList<>();
        List<FightQueryTask> tasks = fightCompany.stream()
                .map(f -> createSearchTask(f, origin, dest))
                .collect(toList());

        tasks.forEach(Thread::start);
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        tasks.stream().map(FightQuery::get).forEach(results::addAll);
        return results;
    }

    private static FightQueryTask createSearchTask(String fight, String original, String dest) {
        return new FightQueryTask(fight, original, dest);
    }
}
