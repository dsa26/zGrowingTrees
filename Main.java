public class Main {
    public static void main(String[] args) {
        BST<Boolean> tree = new BST<>();
        tree.put(6, true);
        tree.put(4, true);
        tree.put(8, true);
        tree.put(3, true);
        tree.put(5, true);
        tree.put(7, true);
        tree.put(9, true);
        System.out.println(tree.search(6).key);
    }
}
