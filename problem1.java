public class BST {
    static class Node {
        int ele;
        Node lf, rt;
        public Node(int value) {
            ele = value;
            lf = rt = null;
        }
    }
    Node root;
    public BST() {
        root = null;
    }
    public void insert(int ele) {
        root = insert1(root, ele);
    }
    private Node insert1(Node root, int ele) {
        if (root == null) {
            root = new Node(ele);
            return root;
        }
        if (ele < root.ele)
            root.lf = insert1(root.lf, ele);
        else if (ele > root.ele)
            root.rt = insert1(root.rt, ele);
        return root;
    }
    public void inorder() {
        inorder1(root);
    }
    private void inorder1(Node root) {
        if (root != null) {
            inorder1(root.lf);
            System.out.print(root.ele + " ");
            inorder1(root.rt);
        }
    }
    public void delete(int ele) {
        root = delete1(root, ele);
    }
    private Node delete1(Node root, int ele) {
        if (root == null)
            return root;
        if (ele < root.ele)
            root.lf = delete1(root.lf, ele);
        else if (ele > root.ele)
            root.rt = delete1(root.rt, ele);
        else {
            if (root.lf == null)
                return root.rt;
            else if (root.rt == null)
                return root.lf;
            root.ele = minValue(root.rt);
            root.rt = delete1(root.rt, root.ele);
        }
        return root;
    }
    public boolean search(int ele) {
        return search1(root, ele);
    }
    private boolean search1(Node root, int ele) {
        if (root == null)
            return false;
        if (root.ele == ele)
            return true;
        if (ele < root.ele)
            return search1(root.lf, ele);
        return search1(root.rt, ele);
    }
    private int minValue(Node root) {
        int minv = root.ele;
        while (root.lf != null) {
            minv = root.lf.ele;
            root = root.lf;
        }
        return minv;
    }
    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(4);
        tree.insert(2);
        tree.insert(9);
        tree.insert(1);
        tree.insert(3);
        System.out.println("Tree follows Inorder traversal: ");
        tree.inorder();
        System.out.println("\nsearch for element 0 in the tree: " + tree.search(0));
        System.out.println("Search for element 3 in the tree: " + tree.search(3));
        tree.delete(2);
        System.out.println("\nTree after deleting element 2: ");
        tree.inorder();
    }
}



OUTPUT:
Tree follows Inorder traversal: 
1 2 3 4 9 
search for element 0 in the tree: false
Search for element 3 in the tree: true

Tree after deleting element 2: 
1 3 4 9 
