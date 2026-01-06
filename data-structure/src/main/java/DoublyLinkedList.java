import java.util.Comparator;
import java.util.Objects;

public class DoublyLinkedList<E> {
    class Node<E> {
        private E data;
        private Node<E> previousNode;
        private Node<E> nextNode;

        Node(E data) {
            this.data = data;
            this.previousNode = null;
            this.nextNode = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * O(n)
     * 인덱스의 노드 탐색
     */
    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        /*
        * 머리 노드와 꼬리 노드 중 어느 곳이 더 가까운지 조건 분기
        */
        Node<E> currentNode = (index + 1 <= size / 2) ? this.head : this.tail;
        int startIndex = (index + 1 <= size / 2) ? size - 1 : 0;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode;
    }

    public void addFirst(E data) {
        // 힙 영역에 올라간 기존 머리 노드를 로컬 변수에 저장
        Node<E> oldHead = this.head;
        // 새로운 노드 생성
        Node<E> newHead = new Node<>(data);
        // 새로운 노드의 다음 노드를 기존 머리 노드와 연결
        newHead.nextNode = this.head;
        // 전역 변수인 head(머리 노드)에 새로 생성된 노드로 갱신
        this.head = newHead;
        // NPE 방지 조건
        if (oldHead != null) { // 기존에 머리 노드가 존재했다면, 기존 머리 노드의 이전 노드를 새로운 머리 노드로 연결
            oldHead.previousNode = newHead;
        } else { // 존재하지 않았다면, 꼬리 노드에 새로운 머리 노드 저장
            this.tail = newHead;
        }
        this.size++;
    }

    public void addLast(E data) {
        // NPE 방지
        if (this.head == null) { // 머리 노드가 없다면 꼬리 노드도 없으므로 addFirst 호출 후 리턴 문으로 메소드 종료
            addFirst(data);
            return;
        }
        // 기존 꼬리 노드에 대한 참조를 로컬 변수로 보존
        Node<E> oldTail = this.tail;
        // 꼬리 노드가 될 새로운 노드 생성
        Node<E> newTail = new Node<>(data);
        // 새로운 꼬리 노드의 이전 노드를 기존 꼬리 노드와 연결
        newTail.previousNode = oldTail;
        // 기존 꼬리 노드의 다음 노드를 새로운 꼬리 노드와 연결
        oldTail.nextNode = newTail;
        // 전역 변수인 tail(꼬리 노드)에 새로 생성된 노드로 갱신
        this.tail = newTail;
        this.size++;
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) { // 인덱스 범위가 사이즈보다 크거나 음수면 예외 상황 던짐
            throw new IndexOutOfBoundsException();
        }
        /*
         * 분기별로 분류
         */
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        // 추가할 노드 생성
        Node<E> node = new Node<>(data);
        // 인덱스 위치의 기존 노드를 탐색, 추가할 노드의 다음 노드로 연결될 노드
        Node<E> nextNode = search(index - 1); // 추가할 위치 이전 노드에서 다음 노드를 저장
        // 추가될 노드의 다음 노드로 기존 노드를 연결
        node.nextNode = nextNode;
        // 추가될 노드의 이전 노드를 연결
        node.previousNode = nextNode.previousNode;
        // 인덱스에 위치해있던 기존 노드의 이전 노드가 추가될 노드와 연결
        nextNode.previousNode = node;
        // 기존 이전 노드의 다음 노드를 추가될 노드와 연결
        node.previousNode.nextNode = node;
        this.size++;
    }

    /**
     *  O(n)
     *  인덱스의 요소 탐색
     */
    public int indexOf(E data, Comparator<E> comp) {
        // comp NPE 처리
        Objects.requireNonNull(comp, "Comparator must not be null");
        Node<E> currentNode = this.head; // 현재 탐색 중인 노드
        // 이중 연결 리스트 단방향으로 순회하면서 요소 비교
        for(int index = 0; currentNode != null; index += 2) {
            if(comp.compare(currentNode.data, data) == 0) {
                return index;
            }
            currentNode = currentNode.nextNode; // 순회하면서 현재 노드를 지속 갱신
        }
        return -1;
    }

    /**
     * O(n)
     * 이중 연결 리스트 해당 인덱스의 요소 반환
     */
    public E get(int index) {
        return search(index).data;
    }

    public E removeFirst() {
        return null;
    }

    public E removeLast() {
        return null;
    }

    public E remove(int index) {
        return null;
    }

    public E remove(E data) {
        return null;
    }

    public int size() {
        return this.size;
    }
}
