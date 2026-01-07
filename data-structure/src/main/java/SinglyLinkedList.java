import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    class Node<E> {
        private E data; // 요소
        private Node<E> nextNode; // 다음 연결된 노드를 저장하는 변수

        Node(E data) {
            this.data = data;
            this.nextNode = null;
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
     *  O(n)
     *  인덱스의 노드 탐색
     */
    private Node<E> search(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode = this.head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode;
    }

    /**
     *  O(1)
     *  노드 머리쪽에 추가
     */
    public void addFirst(E data) {
        // 힙 영역에 올라간 기존 머리 노드를 로컬 변수에 저장
        Node<E> oldHead = this.head;
        // 새로운 머리 노드가 될 노드 생성
        Node<E> newHead = new Node<>(data);
        // 새로운 노드의 다음 노드를 기존 머리 노드와 연결
        newHead.nextNode = this.head;
        // 전역 변수인 head(머리 노드)에 새로 생성된 노드로 갱신
        this.head = newHead;
        // 머리 노드가 존재하지 않았다면, 꼬리 노드에 새로운 머리 노드 저장
        if(oldHead == null) {
            this.tail = newHead;
        }
        // 사이즈 1증가
        this.size++;
    }

    /**
     *  O(1)
     *  노드 꼬리쪽에 추가
     */
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
        // 기존 꼬리 노드의 다음 노드를 새로운 꼬리 노드와 연결
        oldTail.nextNode = newTail;
        // 전역 변수인 tail(꼬리 노드)에 새로 생성된 노드로 갱신
        this.tail = newTail;
        // 사이즈 1증가
        this.size++;
    }

    /**
     *  O(n)
     *  원하는 위치에 노드 추가
     *  머리부터 꼬리까지 연결 방향으로 차례대로 탐색
     */
    public void add(int index, E data) {
        if(index < 0 || index > this.size) { // 인덱스 범위가 사이즈보다 크거나 음수면 예외 상황 던짐
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) { // 머리 노드로 추가
            addFirst(data);
            return;
        }
        if(index == this.size) { // index위치가 사이즈와 같다면 꼬리 노드로 추가
            addLast(data);
            return;
        }
        // 추가할 노드 생성
        Node<E> newNode = new Node<>(data);
        // 추가하려는 인덱스 위치의 이전 노드를 탐색
        Node<E> previousNode = search(index - 1);
        // 이전 노드와 연결되어있던 다음 노드를 새로 생성된 노드로 저장
        newNode.nextNode = previousNode.nextNode;
        // 이전 노드의 다음 노드를 새로 추가하려는 노드와 연결
        previousNode.nextNode = newNode;
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
        // 연결 노드 단방향으로 순회하면서 요소 비교
        for(int index = 0; currentNode != null; index++) {
            if(comp.compare(currentNode.data, data) == 0) {
                return index;
            }
            currentNode = currentNode.nextNode; // 순회하면서 현재 노드를 지속 갱신
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
        // 머리 노드의 삭제될 요소
        E removedData = this.head.data;
        if(this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            // 머리 노드와 연결되어있던 다음 노드를 머리 노드로 갱신
            this.head = this.head.nextNode;
        }
        // 리스트 사이즈 1 감소
        this.size--;
        return removedData;
    }

    /**
     *  search메소드로 순회하기 때문에 O(n)
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
        Node<E> previousNode = search(this.size - 2);
        // 꼬리 노드는 다음 노드가 없으므로 null처리
        previousNode.nextNode = null;
        // 기존 꼬리 노드의 이전 노드를 꼬리 노드로 갱신
        this.tail = previousNode;
        // 리스트 사이즈 1 감소
        this.size--;
        return removedData;
    }

    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) {
            return removeFirst();
        }
        if(index == this.size - 1) {
            return removeLast();
        }
        // 삭제할 노드의 이전 노드를 탐색
        Node<E> previousNode = search(index - 1);
        // 삭제될 노드
        Node<E> removedNode = previousNode.nextNode;
        // 삭제할 노드와 연결되어있던 다음 노드를 이전 노드의 다음 노드로 연결
        previousNode.nextNode = removedNode.nextNode;
        // 없어도 GC는 정상 동작
        removedNode.nextNode = null;
        this.size--;
        return removedNode.data;
    }

    public E remove(E data) {
        return null;
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
            currentNode = currentNode.nextNode;
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