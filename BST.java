import java.util.Queue;
import java.util.LinkedList;

public class BST<E> {
    public Node<E> head;

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
        if (node == null)
            return null;
        Node<E> temp = node;
        while (temp.left != null)
            temp = temp.left;
        return temp;
    }

    public Node<E> findMax() {
        return findMax(head);
    }

    public Node<E> findMax(Node<E> node) {
        if (node == null)
            return null;
        Node<E> temp = node;
        while (temp.right != null)
            temp = temp.right;
        return temp;
    }

    public void preorder() {
        preorder(head);
    }

    public void preorder(Node<E> node) {
        if (node == null)
            return;
        System.out.println(node.key);
        if (node.left != null) {
            preorder(node.left);
        }
        if (node.right != null) {
            preorder(node.right);
        }
    }

    public void postorder() {
        postorder(head);
    }

    public void postorder(Node<E> node) {
        if (node == null)
            return;
        if (node.left != null) {
            postorder(node.left);
        }
        if (node.right != null) {
            postorder(node.right);
        }
        System.out.println(node.key);
    }

    public void inorder() {
        inorder(head);
    }

    public void inorder(Node<E> node) {
        if (node == null)
            return;
        if (node.left != null) {
            inorder(node.left);
        }
        System.out.println(node.key);
        if (node.right != null) {
            inorder(node.right);
        }
    }

    public void levelorder() {
        Queue<Node<E>> queue = new LinkedList<>();
        if (head == null)
            return;
        System.out.println(head.key);
        queue.add(head.left);
        queue.add(head.right);

        while (queue.size() > 0) {
            Node<E> next = queue.remove();
            System.out.println(next.key);
            if (next.left != null) {
                queue.add(next.left);
            }
            if (next.right != null) {
                queue.add(next.right);
            }
        }

        return;
    }

    public void delete(int key) {
        delete(key, head);
    }

    public void delete(int key, Node<E> node) {
        if (node == null)
            return;
        if (size() == 1) {
            this.head = null;
            return;
        }
        if (node.key == key) {
            if (node.right != null) {
                if (node.right.left == null) {
                    node.key = node.right.key;
                    node.val = node.right.val;
                    if (node.right.right == null) {
                        node.right = null;
                        return;
                    } else {
                        delete(node.key, node.right);
                    }
                }
                Node<E> leastParent = getMinDelete(node.right);
                node.key = leastParent.left.key;
                node.val = leastParent.left.val;
                if (leastParent.left.right == null) { // Left must already be null
                    leastParent.left = null;
                    return;
                } else {
                    delete(node.key, leastParent.left);
                }
            } else if (node.left != null) {
                if (node.left.right == null) {
                    node.key = node.left.key;
                    node.val = node.left.val;
                    if (node.left.left == null) {
                        node.left = null;
                        return;
                    } else {
                        delete(node.key, node.left);
                    }
                }
                Node<E> mostParent = getMaxDelete(node.left);
                node.key = mostParent.right.key;
                node.val = mostParent.right.val;
                if (mostParent.right.left == null) {
                    mostParent.right = null;
                    return;
                } else {
                    delete(node.key, mostParent.right);
                }
            }
        } else if (node.key < key) { // Will cause error if the key does not exist and so the node.right is null
            if (node.right.key == key && node.right.left == null && node.right.right == null) {
                node.right = null; // Cannot figure out how to avoid this - If the required deletion is initially a
                                   // leaf, how do I maintain a pointer to the parent?
                return;
            }
            delete(key, node.right);
        } else {
            if (node.left.key == key && node.left.left == null && node.left.right == null) {
                node.left = null;
                return;
            }
            delete(key, node.left);
        }
    }

    private Node<E> getMinDelete(Node<E> node) {
        if (node.left.left == null) {
            return node;
        }

        return getMinDelete(node.left);
    }

    private Node<E> getMaxDelete(Node<E> node) {
        if (node.right.right == null) {
            return node;
        }

        return getMinDelete(node.right);
    }

    public int height() {
        return height(head);
    }

    public int height(Node<E> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return Math.max(height(node.left), height(node.right)) + 1;
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
        return min(head);
    }

    // Will return error if node is null
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
            return;
            // Question: Will it be more efficient to move the head to the "middle" of the
            // tree?
        }
        Node<E> next = new Node<E>(key, val, null, null);
        // Any way to optimize and make it take up less lines?
        if (key < node.key) {
            if (node.left == null)
                node.left = next;
            else
                put(key, val, node.left);
        } else {
            if (node.right == null)
                node.right = next;
            else
                put(key, val, node.right);
        }
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

    public Node<E> search(int key) {
        Node<E> current = head;
        while (current != null) {
            if (current.key == key) {
                return current;
            } else if (current.key < key) {
                current = current.right;
            } else if (current.key > key) {
                current = current.left;
            } else {
                return null;
            }
        }

        return null;
    }
}
