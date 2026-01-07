import java.util.Comparator;
import java.util.NoSuchElementException;
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
     * 인덱스의 노드를 탐색하는 메소드입니다.
     * <p>
     * 이중 연결 리스트는 꼬리 노드에서 이전 노드를 따라
     * 역방향으로 탐색할 수 있는 특징이 있습니다.
     * <p>
     * 이러한 특징을 활용하여, 탐색하려는 인덱스가 리스트의 앞쪽에
     * 가까운지 뒤쪽에 가까운지를 판단한 후,
     * 머리 노드 또는 꼬리 노드 중 더 가까운 위치에서 순회를 시작합니다.
     * <p>
     * 이로 인해 단일 연결 리스트에 비해 평균적인 순회 횟수는
     * 줄어들 수 있으나, 최악의 경우에는 여전히 모든 노드를
     * 탐색해야 하므로 시간 복잡도는 O(n)입니다.
     *
     * @param index 탐색할 요소의 인덱스
     * @return 지정된 인덱스에 위치한 요소
     */
    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode;
        // 머리 노드와 꼬리 노드 중 어느 곳이 더 가까운지 조건 분기
        if (index <= (this.size - 1) / 2) { // 머리 노드부터 시작
            currentNode = this.head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.nextNode;
            }
        } else { // 꼬리 노드부터 시작
            currentNode = this.tail;
            for (int i = this.size - 1; i > index; i--) {
                currentNode = currentNode.previousNode;
            }
        }
        return currentNode;
    }

    /**
     * O(1)
     * 연결 리스트의 머리 노드 위치에 새로운 요소를 추가합니다.
     * <p>
     * 포인터 재연결만 수행하므로 리스트의 크기와 무관하게 항상 일정한 시간 안에 동작합니다.
     *
     * @param data 새로 추가할 요소
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
        // NPE 방지 조건
        if (oldHead != null) { // 기존에 머리 노드가 존재했다면, 기존 머리 노드의 이전 노드를 새로운 머리 노드로 연결
            oldHead.previousNode = newHead;
        } else { // 존재하지 않았다면, 꼬리 노드에 새로운 머리 노드 저장
            this.tail = newHead;
        }
        this.size++;
    }

    /**
     * O(1)
     * 연결 리스트의 꼬리 노드 위치에 새로운 요소를 추가합니다.
     * <p>
     * 포인터 재연결만 수행하므로 리스트의 크기와 무관하게 항상 일정한 시간 안에 동작합니다.
     *
     * @param data 새로 추가할 요소
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
     * O(n)
     * 인덱스의 요소 탐색
     * <p>
     * 단일 연결 리스트와 이중 연결 리스트의 indexOf 메소드는
     * 모두 머리 노드부터 순회하며 첫 번째로 일치하는 요소를 찾기 때문에
     * 구현상 큰 차이가 없다고 생각합니다.
     * <p>
     * 꼬리 노드를 먼저 비교하면 최선의 경우 O(1)로 종료할 수 있을 것이라
     * 생각할 수 있으나, 해당 값이 꼬리 노드에 위치할 확률이 높다고 보장할 수 없고
     * 평균 실행 시간 또한 유의미하게 개선되지 않는다고 생각합니다.
     * <p>
     * 또한 indexOf 메소드의 의미(앞에서부터 첫 등장 위치 탐색)를
     * 명확하게 유지하기 위해 머리 노드부터 단방향 순회하도록 구현하였습니다.
     *
     * @param data 탐색할 요소
     * @param comp 두 요소를 비교할 비교자
     * @return 요소의 인덱스
     */
    public int indexOf(E data, Comparator<E> comp) {

        // comp NPE 처리
        Objects.requireNonNull(comp, "Comparator must not be null");
        Node<E> currentNode = this.head; // 현재 탐색 중인 노드
        // 이중 연결 리스트 단방향으로 순회하면서 요소 비교
        for (int index = 0; currentNode != null; index++) {
            if (comp.compare(currentNode.data, data) == 0) {
                return index;
            }
            currentNode = currentNode.nextNode; // 순회하면서 현재 노드를 지속 갱신
        }
        return -1;
    }

    /**
     * O(n)
     * 이중 연결 리스트에서 지정된 인덱스의 요소를 반환합니다.
     *
     * @param index 탐색할 요소의 인덱스
     * @return 지정된 인덱스에 위치한 요소
     * @throws IndexOutOfBoundsException 인덱스가 범위를 벗어나거나 음수인 경우 발생합니다.
     */
    public E get(int index) {
        return search(index).data;
    }

    /**
     * O(n)
     * 연결 리스트에서 지정된 인덱스의 요소를 새로운 값으로 교체합니다.
     *
     * @param index 수정할 요소의 인덱스
     * @param data 새로 설정할 데이터
     * @return 교체되기 이전에 저장되어 있던 데이터
     * @throws IndexOutOfBoundsException 인덱스가 범위를 벗어나거나 음수인 경우 발생합니다.
     */
    public E set(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> searchedNode = search(index);
        searchedNode.data = data;
        return searchedNode.data;
    }

    public E removeFirst() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        Node<E> targetNode = this.head; // 삭제될 노드 (머리 노드)
        E removedData = targetNode.data; // 삭제될 요소
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node<E> nextNode = targetNode.nextNode;
            // 삭제될 노드의 다음 노드가 새로운 머리 노드가 되고 기존 머리 노드와 연결 해제
            nextNode.previousNode = null;
            // 삭제될 노드의 다음 노드 연결 해제 (GC 친화, 선택 사항)
            targetNode.nextNode = null;
            // 전역 변수 head 기존 머리 노드의 다음 노드를 머리 노드로 저장
            this.head = nextNode;
        }
        this.size--;
        return removedData;
    }


    public E removeLast() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            return removeFirst();
        }
        Node<E> targetNode = this.tail;
        E removedData = targetNode.data;
        Node<E> previousNode = targetNode.previousNode;
        // 삭제될 노드의 이전 노드가 새로운 꼬리 노드가 되고 기존 꼬리 노드와 연결 해제
        previousNode.nextNode = null;
        // 삭제될 노드의 이전 노드 연결 해제 (GC 친화, 선택 사항)
        targetNode.previousNode = null;
        // 전역 변수 tail 기존 꼬리 노드의 이전 노드를 꼬리 노드로 저장
        this.tail = previousNode;
        this.size--;
        return removedData;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) {
            return removeFirst();
        }
        if(index == this.size - 1) {
            return removeLast();
        }
        // 이전 노드 ↔️ 삭제할 노드 ↔️ 다음 노드
        Node<E> targetNode = search(index);
        Node<E> previousNode = targetNode.previousNode;
        Node<E> nextNode = targetNode.nextNode;
        // 삭제되는 노드의 이전 노드와 다음 노드를 서로 연결
        previousNode.nextNode = nextNode;
        nextNode.previousNode = previousNode;
        // 삭제될 노드의 포인터들 연결 해제 (GC 친화)
        targetNode.previousNode = null;
        targetNode.nextNode = null;
        // 사이즈 1 감소
        this.size--;
        return targetNode.data;
    }

    public E remove(E data) {

        return null;
    }

    public int size() {
        return this.size;
    }
}
