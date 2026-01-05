public class DoublyLinkedList<E> {
    class Node<E> {
        private E data;
        private Node<E> nextNode;
        private Node<E> previousNode;

        Node(E data, Node<E> nextNode, Node<E> previousNode) {
            this.data = data;
            this.nextNode = nextNode;
            this.previousNode = previousNode;
        }
    }

    private Node<E> head;
    private Node<E> currentNode;

    DoublyLinkedList() {
        this.head = null;
        this.currentNode = null;
    }

    void addFirst(E data) {}

    void addLast(E data) {}
}
