public class RBT {
    private Node root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private class Node {
        int dt;
        Node lf, rt;
        boolean clr;
        Node(int dt) {
            this.dt = dt;
            this.clr = RED;
        }
    }
    private Node rotateLeft(Node E) {
        Node A = E.rt;
        E.rt = A.lf;
        A.lf = E;
        A.clr = E.clr;
        E.clr = RED;
        return A;
    }
    private Node rotateRight(Node E) {
        Node A = E.lf;
        E.lf = A.rt;
        A.rt = E;
        A.clr = E.clr;
        E.clr = RED;
        return A;
    }
    private void flipColors(Node E) {
        E.clr = RED;
        E.lf.clr = BLACK;
        E.rt.clr = BLACK;
    }
    public void insert(int dt) {
        root = insert(root, dt);
        root.clr = BLACK; // Root should always be black
    }
    private Node insert(Node E, int dt) {
        if (E == null)
            return new Node(dt);
        if (dt < E.dt)
            E.lf = insert(E.lf, dt);
        else if (dt > E.dt)
            E.rt = insert(E.rt, dt);
        if (isRed(E.rt) && !isRed(E.lf))
            E = rotateLeft(E);
        if (isRed(E.lf) && isRed(E.lf.lf))
            E = rotateRight(E);
        if (isRed(E.lf) && isRed(E.rt))
            flipColors(E);
        return E;
    }
    private boolean isRed(Node A) {
        if (A == null) return false;
        return A.clr == RED;
    }
    public boolean search(int dt) {
        return search(root, dt);
    }
    private boolean search(Node B, int dt) {
        if (B == null) return false;
        if (dt == B.dt) return true;
        if (dt < B.dt) return search(B.lf, dt);
        else return search(B.rt, dt);
    }
    public void delete(int dt) {
        root = delete(root, dt);
    }
    private Node delete(Node E, int dt) {
        if (E == null) return null;

        if (dt < E.dt)
            E.lf = delete(E.lf, dt);
        else if (dt > E.dt)
            E.rt = delete(E.rt, dt);
        else {
            if (E.lf == null) return E.rt;
            if (E.rt == null) return E.lf;
            Node temp = E;
            E = min(temp.rt);
            E.rt = deleteMin(temp.rt);
            E.lf = temp.lf;
        }
        return E;
    }
   private Node min(Node node) {
        if (node.lf == null) return node;
        else return min(node.lf);
    }
    private Node deleteMin(Node node) {
        if (node.lf == null) return node.rt;
        node.lf = deleteMin(node.lf);
        return node;
    }
    public static void main(String[] args) {
        RBT rbt = new RBT();
        rbt.insert(20);
        rbt.insert(10);
        rbt.insert(30);
        rbt.insert(5);
        rbt.insert(15);
        System.out.println("Tree follows inorder traversal:");
        rbt.inorderTraversal(rbt.root);
        System.out.println("\nRoot node color: " + (rbt.root.clr == BLACK ? "BLACK" : "RED"));
        System.out.println("Searching for 15: " + rbt.search(15));
        System.out.println("Searching for 1: " + rbt.search(1));
        rbt.delete(15);
        System.out.println("After deleting key 15 the red-black tree:");
        rbt.inorderTraversal(rbt.root);
    }
    private void inorderTraversal(Node node) {
        if (node == null)
            return;
        inorderTraversal(node.lf);
        System.out.print(node.dt + " ");
        inorderTraversal(node.rt);
    }

    private static boolean isBalanced(Node node, int blackHeight) {
        if (node == null)
            return blackHeight == -1;
        if (node.clr == BLACK)
            blackHeight--;
        boolean leftBalanced = isBalanced(node.lf, blackHeight);
        boolean rightBalanced = isBalanced(node.rt, blackHeight);
        return leftBalanced && rightBalanced;
    }
}


OUTPUT:
Tree follows inorder traversal:
5 10 15 20 30 
Root node color: BLACK
Searching for 15: true
Searching for 1: false
After deleting key 15 the red-black tree:
5 10 20 30 
