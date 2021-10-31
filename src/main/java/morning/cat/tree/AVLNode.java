package morning.cat.tree;

import lombok.Data;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/11/1 上午3:46
 */
@Data
public class AVLNode {
    public AVLNode parent;
    public AVLNode leftChild, rightChild;
    public int data;

    public AVLNode(AVLNode parent, AVLNode leftChild, AVLNode rightChild, int data) {
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.data = data;
    }

    public AVLNode(int data) {
        this(null, null, null, data);
    }

    public AVLNode(AVLNode parent, int data) {
        this.parent = parent;
        this.data = data;
    }
}
