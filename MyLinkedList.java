public class MyLinkedList {

    private Node head;
    private int length;

    public MyLinkedList() {
        head = null;
        length = 0;
    }

    public void add(MyPair newValue) {
        Node newNode = new Node(newValue);
        if (head == null) {
            head = newNode
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        length++;
    }

    public MyPair get(int index) throws IndexOutOfBoundsException {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getValue();
    }

    private class Node {

        private MyPair value;
        private Node next;

        public Node(MyPair value) {
            this.value = value;
            this.next = null;
        }

        public MyPair getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setValue(MyPair value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
