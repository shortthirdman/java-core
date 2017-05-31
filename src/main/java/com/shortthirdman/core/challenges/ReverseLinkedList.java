package com.shortthirdman.core.challenges;

public class ReverseLinkedList {

	public class Node {
		public int value;
		public Node next;
		public Node(int v) {
			value = v;
			next = null;
		}
	}
	Node head;
	
	public ReverseLinkedList() {
		head = new Node(1);
	}
	
	public void addNode(int i) {
		Node curr = head;
		while(curr.next != null) {
			curr = curr.next;
		}
		curr.next = new Node(i);
	}
	
	public void print() {
		Node curr = head;
		System.out.println(curr.value);
		while(curr.next != null) {
			curr = curr.next;
			System.out.println(curr.value);
		}
	}
	
	public void reverseList() {
		//head = reverse(head);
		
		//iterative method
		Node curr = head;
		while(curr.next != null){
			Node next = curr.next;
			curr.next = curr.next.next;
			next.next = head;
			head = next;
		}
	}
	
	//recursive method
	public Node reverse(Node n){
		if(n.next == null)
			return n;
		Node next = n.next;
		n.next = null;
		Node rev = reverse(next);
		next.next = n;
		return rev;
	}

	public static void main( String[] arg){
		ReverseLinkedList test = new ReverseLinkedList();
		test.addNode(2);
		test.addNode(3);
		test.addNode(4);
		test.print();
		test.reverseList();
		test.print();
	
	}

}