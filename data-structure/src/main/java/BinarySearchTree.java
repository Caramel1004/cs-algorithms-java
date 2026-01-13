import java.util.Comparator;
import java.util.NoSuchElementException;

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
     * @param comp    두 요소를 비교할 비교자
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
            if (compValue == 0) {
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

    /**
     * 평균: O(log N)
     * 최악: O(N) => 편향된 탐색
     * 이진 탐색 트리에서 요소를 삭세합니다.
     *
     * @param element 새로 추가할 요소
     * @param comp    두 요소를 비교할 비교자
     * @return 삭제된 요소
     */
    public E remove(E element, Comparator<? super E> comp) {
        Node<E> currentNode = this.root;
        if (currentNode == null) {
            return null;
        }
        if(currentNode.leftChildNode == null && currentNode.rightChildNode == null ){

            this.root = null;
            return element;
        }
        // 후계자 탐색 (오른쪽 노드의 서브트리 안에서 가장 작은 노드 탐색)
        while (true) {
            int compValue = comp.compare(element, currentNode.element);
            if (compValue == 0) { // 일치하는 노드 탐색 성공인경우 트리 재구성이 필요함
                rebuild(currentNode, comp);
                return currentNode.element;
            }
            if (compValue < 0) {
                if (currentNode.leftChildNode == null) {
                    return null;
                }
                currentNode = currentNode.leftChildNode;
            } else {
                if (currentNode.rightChildNode == null) {
                    return null;
                }
                currentNode = currentNode.rightChildNode;
            }
        }
    }

    private void rebuild(Node<E> parentNode, Comparator<? super E> comp) {
        if(parentNode.rightChildNode != null) {
            Node<E> predecessor = findPredecessor(parentNode.rightChildNode, comp);
        }
        if(parentNode.leftChildNode != null) {
            Node<E> successor = findSuccessor(parentNode.leftChildNode, comp);
        }
    }

    private Node<E> findPredecessor(Node<E> parentNode, Comparator<? super E> comp) {
        return null;
    }

    private Node<E> findSuccessor(Node<E> parentNode, Comparator<? super E> comp) {
        return null;
    }


    /**
     * 평균: O(log N)
     * 최악: O(N) => 편향된 탐색
     * 이진 탐색 트리에서 요소를 탐색합니다.
     *
     * @param element 탐색할 요소
     * @param comp    두 요소를 비교할 비교자
     * @return 요소의 존재 유무
     */
    public boolean contains(E element, Comparator<? super E> comp) {
        Node<E> currentParentNode = this.root;
        if (this.root == null) {
            return false;
        }
        while (true) {
            int compValue = comp.compare(element, currentParentNode.element);
            if (compValue == 0) {
                return true;
            } else if (compValue < 0) {
                if (currentParentNode.leftChildNode == null) {
                    return false;
                }
                currentParentNode = currentParentNode.leftChildNode;
            } else {
                if (currentParentNode.rightChildNode == null) {
                    return false;
                }
                currentParentNode = currentParentNode.rightChildNode;
            }
        }
    }


}
