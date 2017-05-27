// This is a java program to search an element in a Binary Search Tree
package com.shortthirdman.core.algorithm;

import java.util.Random;
import java.util.Scanner;
 
class BSTNode {
    BSTNode left, right;
    int data;
 
    public BSTNode() {
        left = null;
        right = null;
        data = 0;
    }
 
    public BSTNode(int n) {
        left = null;
        right = null;
        data = n;
    }
 
    public void setLeft(BSTNode n) {
        left = n;
    }
 
    public void setRight(BSTNode n) {
        right = n;
    }
 
    public BSTNode getLeft() {
        return left;
    }
 
    public BSTNode getRight() {
        return right;
    }
 
    public void setData(int d) {
        data = d;
    }
 
    public int getData() {
        return data;
    }
}
 
class BSTree {
    private BSTNode root;
 
    public BSTree() {
        root = null;
    }
 
    public boolean isEmpty() {
        return root == null;
    }
 
    public void insert(int data) {
        root = insert(root,data);
    }
 
    private BSTNode insert(BSTNode node, int data) {
        if (node == null) {
            node = new BSTNode(data);
		} else {
            if (data <= node.getData())
                node.left = insert(node.left, data);
            else
                node.right = insert(node.right, data);
        }
        return node;
    }
 
    public boolean search(int val) {
        return search(root, val);
    }
 
    private boolean search(BSTNode r, int val) {
        boolean found = false;
        while ((r != null) && !found) {
            int rval = r.getData();
            if (val < rval)
                r = r.getLeft();
            else if (val > rval)
                r = r.getRight();
            else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
 
    public void inorder() {
        inorder(root);
    }
 
    private void inorder(BSTNode r) {
        if (r != null) {
            inorder(r.getLeft());
            System.out.print(r.getData() + " ");
            inorder(r.getRight());
        }
    }
 
    public void preorder() {
        preorder(root);
    }
 
    private void preorder(BSTNode r) {
        if (r != null) {
            System.out.print(r.getData() + " ");
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }
 
    public void postorder() {
        postorder(root);
    }
 
    private void postorder(BSTNode r) {
        if (r != null) {
            postorder(r.getLeft());
            postorder(r.getRight());
            System.out.print(r.getData() + " ");
        }
    }
}
 
public class BinarySearchingTree {
    public static void main(String[] args) {
        BSTree bst = new BSTree();
        System.out.println("Binary Search Tree Deletion Test\n");
 
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int elements = 15;
        for (int i = 0; i < elements; i++) {
            bst.insert(Math.abs(rand.nextInt(100)));
		}
        System.out.println("Enter integer element to search");
        System.out.println("Search result : " + bst.search(input.nextInt()));
 
        System.out.print("\nPost order : ");
        bst.postorder();
        System.out.print("\nPre order  : ");
        bst.preorder();
        System.out.print("\nIn order   : ");
        bst.inorder();

        input.close();
    }
}