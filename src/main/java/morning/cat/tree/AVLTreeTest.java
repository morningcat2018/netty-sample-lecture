package morning.cat.tree;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/11/1 上午3:59
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree bbt = new AVLTree();
        bbt.put(3);
        bbt.put(2);
        bbt.put(1);
        bbt.put(4);
        bbt.put(5);
        bbt.put(6);
        bbt.put(7);
        bbt.put(10);
        bbt.put(9);
        bbt.middOrderErgodic();

        System.out.println("-----各节点平衡状况-----");
        bbt.sequenceErgodic();

        bbt.delete(5);
        bbt.delete(2);
        bbt.middOrderErgodic();

        System.out.println("-----各节点平衡状况-----");
        bbt.sequenceErgodic();
    }
}
