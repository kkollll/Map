import javafx.util.Pair;

public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedListMap() {
        head = null;
        size = 0;
    }

    private Node getNode(K key) {
        return getNode(head, key);
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        }
        return getNode(node.next, key);
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            head = new Node(key, value, head == null? null : head);
            size++;
        } else {
            node.value = value;
        }
    }


    @Override
    public V remove(K key) {
        Pair<Node, V> pair = contains(head, key);
        head = pair.getKey();
        return pair.getValue();
    }

    private Pair<Node, V> contains(Node node, K key) {
        if (node == null) {
            return new Pair<>(null, null);
        }
        if (key.equals(node.key)) {
            Node tempNode = node.next;
            node.next = null;
            size--;
            return new Pair<>(tempNode, node.value);
        }
        Pair<Node, V> pair = contains(node.next, key);
        node.next = pair.getKey();
        return new Pair<>(node,pair.getValue());
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException("key is not exist!");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node node = head;
        while (node != null) {
            res.append(node.key + " : " + node.value + System.lineSeparator());
            node = node.next;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedListMap<>();
        map.add(0, "haha");
        map.add(0, "hahaha");
        map.add(2, "hahahaha");
        System.out.println(map);
        System.out.println(map.remove(1));
        System.out.println(map);
        System.out.println(map.getSize());
    }
}
