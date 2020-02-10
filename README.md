# 斗地主游戏 version 2.0 说明文档

> **升级说明：**
> 
> 1. 采用面向对象编程，理解子类与父类的关系，成员变量及类内方法的继承，父类方法的重写。
> 2. 控制台流程控制。
> 
>
> 
>
> **重难点归纳：**
>
> 1. Map以及HashMap的定义方法、遍历方式、及其类内部方法的使用：
>    ·`·map.put(0, "大王")`  将数据存入HashMap；
>    · Map不可以foreach遍历，Set则可以；
>    · Map的遍历需要借助map.get(key)方法，使用for循环遍历。
> 2. ArrayList的定义方法，遍历方式，及其内部方法的使用：
>    · `arrayList.size() `获取列表大小；
>    ·` arrayList.add()` 添加列表元素；
>    · foreach循环遍历方法。
> 3. Collections类内方法：
>    .` Collections.sort(arrayList)` 调用排序算法
>    注： 更多详情参见本项目更高版本的说明文档，或者API开发文档
>
> **功能实现**：
>
> - [x] 牌面组合
> - [x] 洗牌
> - [x] 发牌
> - [x] 看牌

## 源码如下：

**[src/com/kris/landlords/MainApp.java](./src/com/kris/landlords/MainApp.java)**
```python
package com.kris.landlords;

/**
 * 主程序
 *
 * @author: Kris
 * Date: 2020/2/7 14:24
 */
public class MainApp {
    public static void main(String[] args) {
        System.out.println("斗地主开始！");
        new AdminController().main();
    }
}
```

**[src/com/kris/landlords/AdminController.java](./src/com/kris/landlords/AdminController.java)**

```python
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
     * 本类功能集成，即 设定牌面 -》 洗牌 -》 发牌 -》 看牌
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

```

**[src/com/kris/landlords/Player.java](./src/com/kris/landlords/Player.java)**

```python
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

```

**[src/com/kris/landlords/Bottom.java](./src/com/kris/landlords/Bottom.java)**

```python
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

```
