class AVLNode {
    int dt, height;
    AVLNode lf, rt;
    public AVLNode(int dt) {
        this.dt = dt;
        this.height = 1;
        this.lf = null;
        this.rt = null;
    }
}
public class AVLTree {
    AVLNode root;
    int height(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }
    int balanceFactor(AVLNode node) {
        if (node == null)
            return 0;
        return height(node.lf) - height(node.rt);
    }
    AVLNode insert(AVLNode node, int dt) {
        if (node == null)
            return new AVLNode(dt);
        if (dt < node.dt)
            node.lf = insert(node.lf, dt);
        else if (dt > node.dt)
            node.rt = insert(node.rt, dt);
        else
            return node;
        node.height = 1 + Math.max(height(node.lf), height(node.rt));
        int balance = balanceFactor(node);
        if (balance > 1 && dt < node.lf.dt)
            return rightRotate(node);
        if (balance < -1 && dt > node.rt.dt)
            return leftRotate(node);
        if (balance > 1 && dt > node.lf.dt) {
            node.lf = leftRotate(node.lf);
            return rightRotate(node);
        }
        if (balance < -1 && dt < node.rt.dt) {
            node.rt = rightRotate(node.rt);
            return leftRotate(node);
        }
        return node;
    }
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.rt;
        AVLNode T2 = y.lf;
        y.lf = x;
        x.rt = T2;
        x.height = Math.max(height(x.lf), height(x.rt)) + 1;
        y.height = Math.max(height(y.lf), height(y.rt)) + 1;
        return y;
    }
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.lf;
        AVLNode T2 = x.rt;
        x.rt = y;
        y.lf = T2;
        y.height = Math.max(height(y.lf), height(y.rt)) + 1;
        x.height = Math.max(height(x.lf), height(x.rt)) + 1;
        return x;
    }
    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.lf != null)
            current = current.lf;
        return current;
    }
    AVLNode deleteNode(AVLNode root, int dt) {
        if (root == null)
            return root;
        if (dt < root.dt)
            root.lf = deleteNode(root.lf, dt);
        else if (dt > root.dt)
            root.rt = deleteNode(root.rt, dt);
        else {
            if ((root.lf == null) || (root.rt == null)) {
                AVLNode temp = null;
                if (temp == root.lf)
                    temp = root.rt;
                else
                    temp = root.lf;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                AVLNode temp = minValueNode(root.rt);
                root.dt = temp.dt;
                root.rt = deleteNode(root.rt, temp.dt);
            }
        }
        if (root == null)
            return root;
        root.height = Math.max(height(root.lf), height(root.rt)) + 1;
        int balance = balanceFactor(root);
        if (balance > 1 && balanceFactor(root.lf) >= 0)
            return rightRotate(root);
        if (balance > 1 && balanceFactor(root.lf) < 0) {
            root.lf = leftRotate(root.lf);
            return rightRotate(root);
        }
        if (balance < -1 && balanceFactor(root.rt) <= 0)
            return leftRotate(root);
        if (balance < -1 && balanceFactor(root.rt) > 0) {
            root.rt = rightRotate(root.rt);
            return leftRotate(root);
        }
        return root;
    }
    boolean search(AVLNode root, int dt) {
        if (root == null)
            return false;
        if (root.dt == dt)
            return true;
        if (root.dt < dt)
            return search(root.rt, dt);
        return search(root.lf, dt);
    }
    void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.dt + " ");
            preOrder(node.lf);
            preOrder(node.rt);
        }
    }
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.root = avlTree.insert(avlTree.root, 5);
        avlTree.root = avlTree.insert(avlTree.root, 10);
        avlTree.root = avlTree.insert(avlTree.root, 15);
        avlTree.root = avlTree.insert(avlTree.root, 20);
        avlTree.root = avlTree.insert(avlTree.root, 25);
        System.out.println("AVL Tree traversal -> preorder:");
        avlTree.preOrder(avlTree.root);
        System.out.println();
        avlTree.root = avlTree.deleteNode(avlTree.root, 15);
        System.out.println("After deleting 15 AVL tree will be:");
        avlTree.preOrder(avlTree.root);
        System.out.println();
        int searchKey = 18;
        if (avlTree.search(avlTree.root, searchKey))
            System.out.println(searchKey + " found in the AVL tree.");
        else
            System.out.println(searchKey + " not found in the AVL tree");
    }
}


OUTPUT:
AVL Tree traversal -> preorder:
10 5 20 15 25 
After deleting 15 AVL tree will be:
10 5 20 25 
18 not found in the AVL tree
