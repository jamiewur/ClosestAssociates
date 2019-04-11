import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// A singly linked list implementation
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
            head = newNode;
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

    public MyPair find(String vertLabel) {
        if (length == 0) {
            return null;
        }
        Node currentNode = head;
        for (int i = 0; i < length; i++) {
            MyPair currentEdge = currentNode.getValue();
            String currentNodeVertLabel = currentEdge.getKey();
            if (Objects.equals(currentNodeVertLabel, vertLabel)) {
                return currentEdge;
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public boolean remove(String vertLabel) {
        if (length == 0) {
            return false;
        }
        Node currentNode = head;
        if (Objects.equals(currentNode.getValue().getKey(), vertLabel)) {
            head = currentNode.getNext();
            length--;
            return true;
        }
        Node previousNode = currentNode;
        currentNode = currentNode.getNext();
        while (currentNode != null) {
            if (Objects.equals(currentNode.getValue().getKey(), vertLabel)) {
                previousNode.setNext(currentNode.getNext());
                currentNode = null;
                length--;
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        return false;
    }

    // Return all values (MyPair) in the list
    public List<MyPair> getAllValues() {
        if (length == 0) {
            return new ArrayList<>();
        }
        List<MyPair> list = new ArrayList<>();
        Node currentNode = head;
        while (currentNode != null) {
            list.add(currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        return list;
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

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
