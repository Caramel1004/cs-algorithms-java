import java.util.Comparator;

public class BinarySearchTree<E> {
    private class Node<E> {
        E element;
        Node<E> leftChildNode;
        Node<E> rightChildNode;

        Node(E element) {
            this.element = element;
            this.leftChildNode = null;
            this.rightChildNode = null;
        }
    }

    private Node<E> root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * 평균: O(log N)
     * 최악: O(N) => 편향된 탐색
     * 이진 탐색 트리에 새로운 요소를 추가합니다.
     *
     * @param element 새로 추가할 요소
     */
    public void add(E element, Comparator<? super E> comp) {
        Node<E> newNode = new Node<>(element); // 트리에 추가할 노드
        Node<E> currentParentNode = this.root; // 현재 부모 노드
        if (this.root == null) { // 루트가 비어있다면 루트에 추가
            this.root = newNode;
            return;
        }
        while (true) {
            int compValue = comp.compare(element, currentParentNode.element);
            if(compValue == 0) {
                return;
            }
            if (compValue < 0) {
                if (currentParentNode.leftChildNode == null) {
                    currentParentNode.leftChildNode = newNode;
                    return;
                }
                currentParentNode = currentParentNode.leftChildNode;
            } else {
                if (currentParentNode.rightChildNode == null) {
                    currentParentNode.rightChildNode = newNode;
                    return;
                }
                currentParentNode = currentParentNode.rightChildNode;
            }
        }
    }

    public E remove (E element) {
        return null;
    }

    public boolean contains (E element) {
        return false;
    }

    public int size() {
        return 0;
    }
}
