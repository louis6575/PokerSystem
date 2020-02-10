package com.kris.landlords.version_1_0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 斗地主牌面设计及发牌看牌功能
 *
 * @version 1.0 网课版本的斗地主
 * @author: Kris
 * Date: 2020/2/7 13:49
 */
public class DDZ {
    public static void main(String[] args) {
        // 1.组合牌
        // 创建Map集合，键是编号，值是牌
        HashMap<Integer, String> poker = new HashMap<>();
        // 创建List集合，存储编号
        ArrayList<Integer> pokerNumber = new ArrayList<>();
        // 定义出13个点数的组合
        String[] numbers = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        // 定义花色数组
        String[] colors = {"♠", "♥", "♣", "♦"};

        // 存储大王和小王
        poker.put(0, "大王");
        pokerNumber.add(0);
        poker.put(1, "小王");
        pokerNumber.add(1);

        // 定义整数变量，作为键
        int index = 2;
        // 遍历数组，[花色,点数]的组合存入Map集合
        for (String number : numbers) {
            for (String color : colors) {
                poker.put(index, color + number);
                pokerNumber.add(index);
                index++;
            }
        }

        // 2.洗牌
        Collections.shuffle(pokerNumber);

        // 3.发牌
        ArrayList<Integer> player1 = new ArrayList<>();
        ArrayList<Integer> player2 = new ArrayList<>();
        ArrayList<Integer> player3 = new ArrayList<>();
        ArrayList<Integer> bottom = new ArrayList<>();
        // 发牌采用%3索引
        for (int i = 0; i < pokerNumber.size(); i++) {
            if (i < 3) {
                // 先抽出底牌
                bottom.add(pokerNumber.get(i));
            } else if (i % 3 == 0) {
                player1.add(pokerNumber.get(i));
            } else if (i % 3 == 1) {
                player2.add(pokerNumber.get(i));
            } else {
                player3.add(pokerNumber.get(i));
            }

        }

        // 4.看牌
        // 对玩家手中的编号排序
        Collections.sort(player1);
        Collections.sort(player2);
        Collections.sort(player3);
        // 根据玩家中的编号，到Map中寻找牌面
        look("玩家1", player1, poker);
        look("玩家2", player2, poker);
        look("玩家3", player3, poker);
        look("底牌", bottom, poker);

    }

    private static void look(String playerName, ArrayList<Integer> player, HashMap<Integer, String> poker) {
        // 遍历ArrayList集合，获取元素，作为键，到集合Map中找牌面
        System.out.print(playerName + ": ");
        for (Integer key : player) {
            String value = poker.get(key);
            System.out.print(value + "\t");
        }
        System.out.println();
    }
}
