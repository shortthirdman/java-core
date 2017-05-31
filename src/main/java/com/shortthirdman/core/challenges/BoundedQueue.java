package com.shortthirdman.core.challenges;

/**
 *
 * @author Swetank Mohanty (shortthirdman)
 */

import java.util.LinkedList;

public class BoundedQueue {

	LinkedList queue;
	int size;

    public BoundedQueue(int i) {
    	size = i;
    	queue = new LinkedList();
    }

    void enqueue(int i){
    	if(queue.size() >= size){
    		System.out.println("ERROR: Queue is already full, " + i + " is not added.");
    	} else {
    		queue.add(i);
    	}
    }

    int dequeue() {
    	if(queue.size() == 0){
    		System.out.println("ERROR: Queue is empty");
    		return -1;
    	}
    	return (int)queue.removeFirst();
    }

    public static void main(String[] args) {
    	System.out.println("Creating a bounded queue of size 3");
    	BoundedQueue test = new BoundedQueue(3);

    	System.out.println("Testing Dequeue on Empty Queue");
    	System.out.println(test.dequeue());

    	System.out.println("Testing Enqueue & Dequeue");
    	test.enqueue(1);
    	test.printQueue();

    	System.out.println("Dequeued " + test.dequeue());
    	test.printQueue();

    	test.enqueue(2);
    	test.printQueue();

    	test.enqueue(3);
    	test.printQueue();

    	test.dequeue();
    	test.printQueue();

    	test.enqueue(4);
    	test.printQueue();

    	test.enqueue(5);
    	test.printQueue();

    	System.out.println("Testing Invalid Enqueue");
    	test.enqueue(6);
    	test.printQueue();

    	test.dequeue();
    	test.printQueue();

    	test.enqueue(7);
    	test.printQueue();
    }

    void printQueue() {
    	System.out.print("[");
    	for(int i = 0; i < queue.size(); i++){
    		System.out.print(queue.get(i) + " ");
    	}
    	System.out.println("]");
    }

}