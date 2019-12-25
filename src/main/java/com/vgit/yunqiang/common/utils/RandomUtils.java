package com.vgit.yunqiang.common.utils;

import java.util.*;

public class RandomUtils {

    public static Map<String, Integer> total = new HashMap<>();

    public static void random() {
        Set<String> set = new TreeSet<String>();

        while (true) {
            int sui = (int) (Math.random() * 33) + 1;
            set.add(sui < 10 ? "0" + sui : "" + sui);
            if (set.size() == 6) {
                break;
            }
        }

        Set<String> set2 = new TreeSet<String>();
        int sui2 = (int) (Math.random() * 16) + 1;
        set2.add(sui2 < 10 ? "0" + sui2 : "" + sui2);

        System.out.println("r：" + set + " b：" + set2);
    }

    public static Set<String> random32() {
        Set<String> set = new TreeSet<String>();
        while (true) {
            int sui = (int) (Math.random() * 33) + 1;
            set.add(sui < 10 ? "0" + sui : "" + sui);
            if (set.size() == 6) {
                break;
            }
        }
        return set;
    }

    public static void total(String str) {
        Integer count = total.get(str);
        if (count == null) {
            total.put(str, 1);
        } else {
            Integer ct = total.get(str);
            total.put(str, ++ct);
        }

        List<String> mapKeys = new ArrayList<>(total.keySet());
        Collections.sort(mapKeys);
    }

    public static void main(String[] args) {
        int count = 0;
        do {
            Set<String> r32 = random32();

            Iterator<String> it = r32.iterator();
            while (it.hasNext()) {
                String str = it.next();
                total(str);
            }

            count++;
        } while (count <= 100);

        System.out.println(total.toString());
    }

}
