# 斗地主游戏 version 1.0 说明文档
> **重难点归纳：**
> 1. Map以及HashMap的定义方法、遍历方式、及其类内部方法的使用：
> 	·`·map.put(0, "大王")`  将数据存入HashMap；
> 	· Map不可以foreach遍历，Set则可以；
> 	· Map的遍历需要借助map.get(key)方法，使用for循环遍历。
> 2. ArrayList的定义方法，遍历方式，及其内部方法的使用：
> 	· `arrayList.size() `获取列表大小；
> 	·` arrayList.add()` 添加列表元素；
> 	· foreach循环遍历方法。
> 3. Collections类内方法：
> 	.` Collections.sort(arrayList)` 调用排序算法
> 注： 更多详情参见本项目更高版本的说明文档，或者API开发文档
>
> **功能实现**：
>- [x]  牌面组合
>- [x]  洗牌
>- [x]  发牌
>- [x]  看牌

## 源码如下：

```python
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

```