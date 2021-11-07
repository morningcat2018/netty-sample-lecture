package morning.cat.tree;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/11/1 上午3:59
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree bbt = new AVLTree();
        bbt.put(17);
        bbt.put(20);
        bbt.put(10);
        bbt.put(19);
        bbt.put(30);
        bbt.put(23);
        bbt.put(36);
        bbt.put(2);
        bbt.middOrderErgodic();

//        System.out.println("-----各节点平衡状况-----");
//        bbt.sequenceErgodic();
//
//        bbt.delete(5);
//        bbt.delete(2);
//        bbt.middOrderErgodic();
//
//        System.out.println("-----各节点平衡状况-----");
//        bbt.sequenceErgodic();
    }
}
