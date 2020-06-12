public class BSTMap<K extends Comparable<? super K>, V> implements Map<K, V> {

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }


    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node == null) {
            return null;
        }
        root = remove(root, key);
        return node.value;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) > 0) {
            node = remove(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            node = remove(node.left, key);
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            Node predecessor = maxmum(node.left);
            predecessor.left = removeMax(node.left);
            predecessor.right = node.right;
            node.left = node.right = null;
            return predecessor;
        }
        return node;
    }

    private Node removeMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        return removeMax(node.right);
    }

    private Node maxmum(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return maxmum(node.right);
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node != null) {
            node.value = newValue;
        } else {
            throw new IllegalArgumentException("key " + key + " not exist!");
        }
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
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.key + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
    public static void main(String[] args) {
        Map<Integer, String> map = new BSTMap<>();
        map.add(0, "haha");
        map.add(1, "hahaha");
        map.add(2, "hahahaha");
        System.out.println(map);
        System.out.println(map.remove(1));
        System.out.println(map);
        System.out.println(map.getSize());
        System.out.println(map.get(2));
    }
}
