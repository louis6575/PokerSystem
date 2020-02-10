package com.kris.landlords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 底牌，继承Player
 *
 * @author: Kris
 * Date: 2020/2/7 14:47
 */
public class Bottom extends Player {
    private ArrayList<Integer> bottomCards = new ArrayList<>();

    @Override
    public void look(HashMap<Integer, String> poker) {
//        super.look(poker);
        // 编号排序
        Collections.sort(bottomCards);
        // 遍历ArrayList集合，获取元素，作为键，到集合Map中找牌面
        System.out.print("底牌: ");
        for (Integer key : bottomCards) {
            String value = poker.get(key);
            System.out.print(value + "\t");
        }
        System.out.println();
    }

    public Bottom() {
        super();
    }

    public ArrayList<Integer> getBottomCards() {
        return bottomCards;
    }

    public void setHandCards(Integer pokerNumber) {
        bottomCards.add(pokerNumber);
    }
}
