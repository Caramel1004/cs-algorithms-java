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
        Node<E> currentParentNode = currentNode;
        if (currentNode == null) {
            return null;
        }
        // 후계자 탐색 (오른쪽 노드의 서브트리 안에서 가장 작은 노드 탐색)
        boolean isLeftNode = true;
        while (true) {
            int compValue = comp.compare(element, currentNode.element);
            if (compValue == 0) { // 일치하는 노드 탐색 성공인경우 트리 재구성이 필요함
                E removedElement = currentNode.element;
                rebuild(currentParentNode, currentNode, isLeftNode); // 여기서 currentNode는 삭제할 노드
                return removedElement;
            }
            // 엣지 타고 내려가기
            if (compValue < 0) {
                if (currentNode.leftChildNode == null) {
                    return null;
                }
                currentParentNode = currentNode;
                currentNode = currentNode.leftChildNode;
                isLeftNode = true;
            } else {
                if (currentNode.rightChildNode == null) {
                    return null;
                }
                currentParentNode = currentNode;
                currentNode = currentNode.rightChildNode;
                isLeftNode = false;
            }
        }
    }

    private void rebuild(Node<E> parentNode, Node<E> childNode, boolean isLeftNode) {
        Node<E> currentParentNode = childNode;
        // 자식 노드에 자손이 있는 경우
        if (childNode.rightChildNode != null) {
            Node<E> currentNode = childNode.rightChildNode; // 오른쪽 노드
            if (currentNode.leftChildNode != null) {
                while (true) { // 마지막에는 리프까지 도달하므로 왼쪽 자식 노드가 존재하지 않을때까지 반복문을 끝내지 않습니다.
                    if (currentNode.leftChildNode == null) {
                        break;
                    }
                    currentParentNode = currentNode;
                    currentNode = currentNode.leftChildNode;
                }
                currentParentNode.leftChildNode = currentNode.rightChildNode;
            } else {
                parentNode.rightChildNode = currentNode.rightChildNode;
            }
            childNode.element = currentNode.element;
            return;
        }
        if (childNode.leftChildNode != null) {
            Node<E> currentNode = childNode.leftChildNode;
            if (currentNode.rightChildNode != null) {
                while (true) {
                    if (currentNode.rightChildNode == null) {
                        break;
                    }
                    currentParentNode = currentNode;
                    currentNode = currentNode.rightChildNode;
                }
                currentParentNode.rightChildNode = currentNode.leftChildNode;
            } else {
                parentNode.leftChildNode = currentNode.leftChildNode;
            }
            childNode.element = currentNode.element;
            return;
        }
        // 자식 노드가 자손이 존재하지 않는 경우
        if(parentNode == childNode) {
            this.root = null;
            return;
        }
        if (isLeftNode) {
            parentNode.leftChildNode = null;
        } else {
            parentNode.rightChildNode = null;
        }
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
