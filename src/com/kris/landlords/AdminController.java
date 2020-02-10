package com.kris.landlords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 控制台类
 * 用于定义扑克牌的玩法
 *
 * @author: Kris
 * Date: 2020/2/7 17:00
 */
public class AdminController {
    private static final int BOTTOM_NUM = 3;  // 定义底牌数
    private static final boolean KING = true;  // 定义是否含有大小王
    private static final boolean DOUBLE_POKER = false;  // 定义是否为两副扑克牌
    private HashMap<Integer, String> poker = new HashMap<>();  // 创建Map集合，键是编号，值是牌
    private ArrayList<Integer> pokerNumber = new ArrayList<>();  // 创建List集合，存储编号
    private String[] numbers;  // 定义牌面数字数组
    private String[] colors;  // 定义牌面花色数组

    /**
     * 本类功能集成，即 设定牌面 -> 洗牌 -> 发牌 -> 看牌
     */
    public void main() {
        Player player1 = new Player("test01", "王二");  // 添加玩家
        Player player2 = new Player("test02", "张三");  // 添加玩家
        Player player3 = new Player("test03", "李四");  // 添加玩家
        Player bottom = new Bottom();  // 抽出底牌

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(bottom);

        // 设定牌面
        setInfo();
        // 洗牌
        shuffle(pokerNumber);
        // 发牌
        dealCards(players, pokerNumber);
        // 看牌，根据玩家中的编号，到HashMap中寻找牌面
        player1.look(poker);
        player2.look(poker);
        player3.look(poker);
        bottom.look(poker);
        System.out.println(player1.toString());
    }

    /**
     * 定义牌面信息，牌面数字与牌面花色组合匹配
     */
    public void setInfo() {
        int index = 0;
        if (KING) {
            numbers = new String[]{"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
            // 存储大王和小王
            poker.put(0, "大王");
            pokerNumber.add(0);
            poker.put(1, "小王");
            pokerNumber.add(1);
            // 定义整数变量，作为键
            index = 2;
        } else {
            numbers = new String[]{"3", "2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4"};
        }
        if (!DOUBLE_POKER) {
            colors = new String[]{"♠", "♥", "♣", "♦"};
        } else {
            colors = new String[]{"♠", "♠", "♥", "♥", "♣", "♣", "♦", "♦"};
        }


        // 遍历数组，[花色,点数]的组合存入Map集合
        for (String number : numbers) {
            for (String color : colors) {
                poker.put(index, color + number);
                pokerNumber.add(index);
                index++;
            }
        }
    }

    /**
     * 根据牌面序号进行洗牌
     *
     * @param pokerNumber ArrayList<Integer>类型，表示所有牌的牌面对应的序号
     */
    private static void shuffle(ArrayList<Integer> pokerNumber) {
        Collections.shuffle(pokerNumber);
    }

    /**
     * 发牌
     *
     * @param players     Player对象
     * @param pokerNumber ArrayList<Integer>类型，表示所有牌的牌面对应的序号列表
     */
    private static void dealCards(ArrayList<Player> players, ArrayList<Integer> pokerNumber) {
        int playerNumber = players.size() - 1;  // 玩家数量
        int turn = 0;  // 轮到当前玩家取牌
        // 发牌采用%3索引
        for (int i = 0; i < pokerNumber.size(); i++) {
            turn = i % playerNumber;  // 轮到当前玩家取牌
            if (i < BOTTOM_NUM) {
                // 先抽出底牌
                players.get(players.size() - 1).setHandCards(pokerNumber.get(i));
            } else {
                players.get(turn).setHandCards(pokerNumber.get(i));
            }
        }
    }
}
