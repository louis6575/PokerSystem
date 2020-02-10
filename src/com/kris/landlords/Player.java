package com.kris.landlords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

/**
 * 定义玩家类
 * 玩家的操作：取牌
 *
 * @author: Kris
 * Date: 2020/2/7 14:31
 */
public class Player {
    private String id; // 玩家id
    private String name;  // 玩家昵称
    private int countCards;  // 手牌数
    private ArrayList<Integer> handCards = new ArrayList<>();  // 手牌

    public void send() {
        System.out.println("玩家出牌阶段");
    }

    public void pass() {
        System.out.println("要不起！");
    }

    public int count() {
        int sum = 0;
        System.out.println("手牌数：" + sum);
        return sum;
    }

    /**
     * @param poker 玩家手牌牌面
     */
    public void look(HashMap<Integer, String> poker) {
        // 编号排序
        Collections.sort(handCards);
        // 遍历ArrayList集合，获取元素，作为键，到集合Map中找牌面
        System.out.print(name + ": ");
        for (Integer key : handCards) {
            String value = poker.get(key);
            System.out.print(value + "\t");
        }
        System.out.println();
    }

    public Player() {

    }

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(getId(), player.getId()) &&
                Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getHandCards() {
        return handCards;
    }

    public void setHandCards(ArrayList<Integer> handCards) {
        this.handCards = handCards;
    }

    public void setHandCards(Integer pokerNumber) {
        handCards.add(pokerNumber);
        setCountCards(handCards.size());
    }

    public int getCountCards() {
        return countCards;
    }

    public void setCountCards(int countCards) {
        this.countCards = countCards;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", countCards=" + countCards +
                ", handCards=" + handCards +
                '}';
    }
}
