public class Node<E> {
    public Node<E> left;
    public Node<E> right;
    public int key;
    public E val;

    public Node(int key, E val, Node<E> left, Node<E> right) {
        this.left = left;
        this.right = right;
        this.key = key;
        this.val = val;
    }
}