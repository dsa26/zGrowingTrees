public class BST<E> {
    private Node<E> head;

    public BST() {
        this.head = null;
    }

    public boolean contains(int key) {
        return contains(key, head);
    }

    public boolean contains(int key, Node<E> node) {
        if (node == null)
            return false;
        if (node.key == key)
            return true;
        return contains(key, node.left) || contains(key, node.right);
    }

    public Node<E> findMin() {
        return findMin(head);
    }

    public Node<E> findMin(Node<E> node) {
        Node<E> temp = node;
        while (temp.left != null)
            temp = temp.left;
        return temp;
    }

    public Node<E> findMax() {
        return findMax(head);
    }

    public Node<E> findMax(Node<E> node) {
        Node<E> temp = node;
        while (temp.right != null)
            temp = temp.right;
        return temp;
    }

    public void delete(int key) {
        this.head = delete(key, head);
    }

    public Node<E> delete(int key, Node<E> node) {
        if (node.key == key) {
            Node<E> least = findMin(node.right);
            node.key = least.key;
            node.val = least.val;
            if (least.left != null || least.right != null) {
                return delete(least.key, least);
            } else {
                return least;
            }
        } else if (node.key < key) {
            return delete(key, node.right);
        } else {
            return delete(key, node.left);
        }
    }

    public int height() {
        return height(head);
    }

    public int height(Node<E> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return height(node.left) + height(node.right);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int max() {
        return max(head);
    }

    public int max(Node<E> node) {
        if (node.right == null)
            return node.key;
        return max(node.right);
    }

    public int min() {
        return max(head);
    }

    public int min(Node<E> node) {
        if (node.left == null)
            return node.key;
        return max(node.left);
    }

    public void put(int key, E val) {
        put(key, val, head);
    }

    public void put(int key, E val, Node<E> node) {
        if (node == null) {
            head = new Node<E>(key, val, null, null);
            // Question: Will it be more efficient to move the head to the "middle" of the
            // tree?
        }
        Node<E> next;
        if (key < node.key) {
            next = node.left;
        } else {
            next = node.right;
        }
        if (next == null)
            next = new Node<E>(key, val, null, null);
        else
            put(key, val, next);
    }

    public int size() {
        return size(head);
    }

    public int size(Node<E> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return 1 + size(node.left) + size(node.right);
    }
}
