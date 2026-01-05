import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    public static class Node<E> {
        private E data; // 요소
        private Node<E> next; // 다음 연결된 노드를 저장하는 변수

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     *  O(1)
     *  노드 머리쪽에 추가
     */
    public void addFirst(E data) {
        // 노드 생성
        Node<E> node = new Node<>(data, null);
        // 힙 영역에 올라간 기존 머리 노드를 로컬 변수에 저장
        Node<E> firstNode = this.head;
        // 생성된 노드를 head와 연결
        node.next = this.head;
        // 기존 head 변수에 생성된 node 저장
        this.head = node;
        // 첫 노드라면 머리 == 꼬리 이므로 tail 변수에 생성된 노드 저장
        if(firstNode == null) {
            this.tail = node;
        }
        // 사이즈 1증가
        this.size++;
    }

    /**
     *  O(1)
     *  노드 꼬리쪽에 추가
     */
    public void addLast(E data) {
        // 노드 생성
        Node<E> node = new Node<>(data, null);
        // 힙 영역에 올라간 기존 꼬리 노드를 로컬 변수에 저장
        Node<E> lastNode = this.tail;
        // 전역 변수 tail에 생성된 노드 저장
        this.tail = node;
        if(lastNode == null) { // 기존에 꼬리 노드가 없었다면 머리 노드에 꼬리 노드 저장
            this.head = this.tail;
        } else { // 힙 영역에 올라간 기존 꼬리 노드를 새로운 꼬리 노드에 연결 시킴
            lastNode.next = node;
        }
        // 사이즈 1증가
        this.size++;
    }

    /**
     *  O(n)
     *  원하는 위치에 노드 추가
     *  머리부터 꼬리까지 연결 방향으로 차례대로 탐색
     */
    public void add(int index, E data) {
        if(index < 0 || index > size) { // 인덱스 범위가 사이즈보다 크거나 음수면 예외 상황 던짐
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) { // 머리 노드로 추가
            addFirst(data);
            return;
        }
        if(index == size) { // index위치가 사이즈와 같다면 꼬리 노드로 추가
            addLast(data);
            return;
        }
        // 노드 생성
        Node<E> node = new Node<>(data, null);
        // 추가하려는 인덱스 위치의 이전 인덱스를 탐색
        Node<E> prevNode = search(index - 1);
        // 이전 노드와 연결되어있던 다음 노드를 새로 생성된 노드에 저장
        node.next = prevNode.next;
        // 이전 노드는 새로운 노드와 연결되도록 새로운 노드로 포인터 방향 변경
        prevNode.next = node;
        this.size++;
    }

    /**
     *  O(n)
     *  인덱스의 노드 탐색
     */
    private Node<E> search(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode = this.head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /**
     *  O(n)
     *  인덱스의 요소 탐색
     */
    public int indexOf(E data, Comparator<E> comp) {
        // comp NPE 처리
        Objects.requireNonNull(comp, "Comparator must not be null");
        Node<E> currentNode = this.head; // 현재 탐색 중인 노드
        // 연결 노드 단방향으로 순회하면서 요소 비교
        for(int index = 0; currentNode != null; index++) {
            if(comp.compare(currentNode.data, data) == 0) {
                return index;
            }
            currentNode = currentNode.next; // 순회하면서 현재 노드를 지속 갱신
        }
        return -1;
    }

    /**
     *  O(n)
     *  연결 리스트 해당 인덱스의 요소 반환
     */
    public E get(int index) {
        return search(index).data;
    }

    /**
     *  O(n)
     *  연결 리스트 해당 인덱스의 요소 수정
     */
    public void set(int index, E data) {
        Node<E> searchedNode = search(index);
        searchedNode.data = data;
    }

    /**
     *  O(1)
     *  머리 노드 삭제
     */
    public E removeFirst() {
        if(this.head == null) { // 머리 노드 없으면 예외 상황
            throw new NoSuchElementException();
        }
        if(this.size == 1) {
            this.tail = null;
        }
        // 머리 노드의 삭제될 요소
        E removedData = this.head.data;
        // 머리 노드와 연결되어있던 다음 노드를 머리 노드로 갱신
        this.head = this.head.next;
        // 리스트 사이즈 1 감소
        this.size--;
        return removedData;
    }

    /**
     *  O(1)
     *  꼬리 노드 삭제
     */
    public E removeLast() {
        if(this.head == null) {
            throw new NoSuchElementException();
        }
        if(this.size == 1) { // 사이즈가 1이면 머리 노드와 꼬리 노드가 동일하므로 removeFirst 메소드 호출
            return removeFirst();
        }
        // 꼬리 노드의 삭제될 요소
        E removedData = this.tail.data;
        // 꼬리 노드의 이전 노드를 탐색
        Node<E> prevNode = search(this.size - 2);
        // 꼬리 노드는 다음 노드가 없으므로 null처리
        prevNode.next = null;
        // 기존 꼬리 노드의 이전 노드를 꼬리 노드로 갱신
        this.tail = prevNode;
        // 리스트 사이즈 1 감소
        this.size--;
        return removedData;
    }

    /**
     *  O(n)
     *  연결 리스트 출력
     */
    public void dump() {
        StringBuilder sb = new StringBuilder();
        Node<E> currentNode = this.head;
        while(currentNode != null) {
            sb.append(currentNode.data).append(" ");
            currentNode = currentNode.next;
        }
        System.out.println(sb);
    }

    /**
     *  O(1)
     *  연결 리스트 사이즈 반환
     */
    public int size () {
        return this.size;
    }
}