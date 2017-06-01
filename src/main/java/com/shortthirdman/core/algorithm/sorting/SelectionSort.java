package com.shortthirdman.core.algorithm.sorting;

import java.util.Scanner;
 
public class SelectionSort {
    /* Selection Sort function */
    public static void sort(int arr[]) {
        int N = arr.length;
        int i, j, pos, temp;
        for (i = 0; i < N-1; i++) {
            pos = i;
            for (j = i+1; j < N; j++) {
                if (arr[j] < arr[pos]) {
                    pos = j;
                }
            }
            /* Swap arr[i] and arr[pos] */
            temp = arr[i];
            arr[i] = arr[pos];
            arr[pos]= temp;            
        }        
    }
    /* Main method */
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );
 
        System.out.println("Selection Sort Test\n");
        int n, i;
        /* Accept number of elements */
        System.out.println("Enter number of integer elements");
        n = scan.nextInt();
        /* Create integer array on n elements */
        int arr[] = new int[ n ];
        /* Accept elements */
        System.out.println("\nEnter "+ n +" integer elements");
        for (i = 0; i < n; i++)
            arr[i] = scan.nextInt();
        /* Call method sort */
        sort(arr);
        /* Print sorted Array */
        System.out.println("\nElements after sorting ");        
        for (i = 0; i < n; i++)
            System.out.print(arr[i]+" ");            
        System.out.println();            
    }
}