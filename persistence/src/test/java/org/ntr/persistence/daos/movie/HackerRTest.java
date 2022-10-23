package org.ntr.persistence.daos.movie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class HackerRTest {

    // Top node
    private SinglyLinkedListNode n1;
    private SinglyLinkedListNode n2;
    private SinglyLinkedListNode n3;
    private SinglyLinkedListNode n4;

    @BeforeEach
    public void setup() {
        n1 = new SinglyLinkedListNode(1);
        n2 = new SinglyLinkedListNode(2);
        n3 = new SinglyLinkedListNode(3);
        n4 = new SinglyLinkedListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
    }

    class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode() {

        }

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }

    }

    @Test
    public void addNodeAtPosTest() {

        SinglyLinkedListNode result = insertNodeAtPosition(n1, 4, 2);
    }

    @Test
    public void deleteNodeAtPosTest() {
    }

    public SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
        SinglyLinkedListNode temp = llist;
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        while (position > 1) {
            temp = temp.next;
            position--;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        return llist;
    }

    public SinglyLinkedListNode deleteNode(SinglyLinkedListNode llist, int position) {
        if (position == 0) {
            llist = llist.next;
            return llist;
        }

        SinglyLinkedListNode temp = llist;

        while (position > 1) {
            temp = temp.next;
            position--;
        }

        temp.next = temp.next.next;
        return llist;
    }

    @Test
    public void reverseTest() {
        SinglyLinkedListNode n1 = new SinglyLinkedListNode(1);
        SinglyLinkedListNode n2 = new SinglyLinkedListNode(2);
        SinglyLinkedListNode n3 = new SinglyLinkedListNode(3);
        SinglyLinkedListNode n4 = new SinglyLinkedListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        SinglyLinkedListNode result = reverse(n1);
    }

    public SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
        SinglyLinkedListNode current = llist;
        SinglyLinkedListNode reversed = null;
        while (current != null) {
            SinglyLinkedListNode newNode = current.next;
            current.next = reversed;
            reversed = current;
            current = newNode;
        }

        return reversed;
    }

    @Test
    public void threeXTest() {
        // random number from 1 to 10
        int initialNumber = 598271;
        int x = initialNumber;
        int count = 0;
        while (x != 1) {
            ++count;
            if (x % 2 == 0) {
                System.out.printf("Iteration %s: %s / 2", count, x);
                x /= 2;
            } else {
                System.out.printf("Iteration %s: %s * 3 + 1", count, x);
                x = x * 3 + 1;
            }
            System.out.printf(" = %s\n", x);
        }

        System.out.printf("Initial number: %s, Iterations: %s", initialNumber, count);

    }

    @Test
    public void addNodeAtTailTest() {
        SinglyLinkedListNode temp = n1;
        int data = 5;

        while (temp.next != null) temp = temp.next;
        temp.next = new SinglyLinkedListNode(data);

        int answer = 1;
        // test n1
        while (n1.next != null) {
            Assertions.assertNotNull(n1);
            Assertions.assertEquals(answer, n1.data);
            n1 = n1.next;
            answer++;
        }
    }

    @Test
    public void addNodeAtStartTest() {
        SinglyLinkedListNode newStart = new SinglyLinkedListNode(5);
        newStart.next = n1;
    }

//    @Test
//    public void mergeListsTest() {
//        SinglyLinkedListNode h1 = new SinglyLinkedListNode(1);
//        SinglyLinkedListNode h2 = new SinglyLinkedListNode(3);
//        SinglyLinkedListNode h3 = new SinglyLinkedListNode(7);
//        h1.next = h2;
//        h2.next = h3;
//
//        SinglyLinkedListNode l1 = new SinglyLinkedListNode(1);
//        l1.next = new SinglyLinkedListNode(2);
//
////        SinglyLinkedListNode mergedLists = mergeLists(h1, l1);
//    }

    @Test
    public void rotateArrayTest() {
        List<Integer> rotatedStep2 = rotateLeft(2, Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expectedStep2 = Arrays.asList(3, 4, 5, 1, 2);
        Assertions.assertNotNull(rotatedStep2);
        Assertions.assertEquals(rotatedStep2, expectedStep2);

        List<Integer> rotatedStep3 = rotateLeft(3, Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expectedStep3 = Arrays.asList(4, 5, 1, 2, 3);
        Assertions.assertNotNull(rotatedStep3);
        Assertions.assertEquals(rotatedStep3, expectedStep3);

        List<Integer> rotatedStep4 = rotateLeft(4, Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expectedStep4 = Arrays.asList(5, 1, 2, 3, 4);
        Assertions.assertNotNull(rotatedStep4);
        Assertions.assertEquals(rotatedStep4, expectedStep4);
    }

    @Test
    public void hasCycleTest() {
        boolean actual = hasCycle(n1);

        Assertions.assertFalse(actual);
    }
// not finished lol
    public SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null && head2 == null) return null;
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        SinglyLinkedListNode merged = null;
        SinglyLinkedListNode c1 = head1;
        SinglyLinkedListNode c2 = head2;

        while (c1 != null && c2 != null) {
            if (c1.data == c2.data) {
                merged = new SinglyLinkedListNode(c1.data);
                addNode(merged, c2);
            }
            if (c1.data > c2.data) {
                String s = "da";
            }
        }
        return null;
    }

    private void addNode(SinglyLinkedListNode list, SinglyLinkedListNode nodeToAdd) {
        if (list == null) {
            list = nodeToAdd;
            return;
        }
        SinglyLinkedListNode temp = list;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = nodeToAdd;
    }

    public List<Integer> rotateLeft(int d, List<Integer> arr) {
        List<Integer> rotatedArray = new ArrayList<>(arr);
        for (int i = arr.size() - 1; i >= 0; i--) {
            int current = arr.get(i);
            if (i - d < 0) {
                rotatedArray.set(arr.size() - (d - i), current);
            } else {
                rotatedArray.set(i - d, current);
            }
        }
        return rotatedArray;
    }

    public boolean hasCycle(SinglyLinkedListNode head) {
        if (head == null) return false;

        Map<SinglyLinkedListNode, Integer> nodeCounterMap = new HashMap<>();
        SinglyLinkedListNode tmp = head;
        while (tmp != null) {
            if (nodeCounterMap.containsKey(tmp)) return true;
            nodeCounterMap.put(tmp, 1);
            tmp = tmp.next;
        }
        return false;
    }

    @Test
    public void findMergePointTest() {
        SinglyLinkedListNode h1 = new SinglyLinkedListNode(10);
        SinglyLinkedListNode h2 = new SinglyLinkedListNode(20);

        h1.next = h2;
        h2.next = n1.next.next; // n3

        int actual = findMergeNode(n1, h1);

        Assertions.assertEquals(3, actual);
    }

    public int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode h1 = head1;
        SinglyLinkedListNode h2 = head2;

        while (h2 != null) {
            while (h1 != null) {
                if (h1 == h2) return h1.data;
                h1 = h1.next;
            }
            h1 = head1;
            h2 = h2.next;
        }
        return -1;
    }

    @Test
    public void removeDuplicatesTest() {
        SinglyLinkedListNode dup = new SinglyLinkedListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = dup;
        dup.next = n4;
        SinglyLinkedListNode shit = removeDuplicates(n1);
    }

    public SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode llist) {
        SinglyLinkedListNode current = llist;
        SinglyLinkedListNode prev = null;
        while (current != null) {
            if (prev != null) {
                if (current.data == prev.data) {
                    prev.next = current.next;
                    current = prev;
                }
            }
            prev = current;
            current = current.next;
        }
        return llist;
    }

}
