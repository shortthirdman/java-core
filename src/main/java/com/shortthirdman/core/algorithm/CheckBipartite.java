package com.shortthirdman.core.algorithm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckBipartite {
    private int numberOfNodes;
    private static final int NO_OF_COLOR = 2;
 
    private boolean isSafe(int vertex,int[][] adjacencyMatrix, int [] colored, int color) {
        for (int destination = 1; destination <= numberOfNodes; destination++) {
            if (adjacencyMatrix[vertex][destination] == 1 && colored[destination] == color) {
                return false;
            }
        }
        return true;
    }
 
    private boolean checkBipartiteUtil(int adjacencyMatrix[][], int[] colored, int vertex) {
        if (vertex > numberOfNodes) {
            return true;
        }
        for (int colorNum = 1; colorNum <= NO_OF_COLOR; colorNum++) {
            if (isSafe(vertex, adjacencyMatrix, colored, colorNum)) {
                colored[vertex] = colorNum;
                if (checkBipartiteUtil(adjacencyMatrix, colored, vertex + 1)) {
                    return true;
                } else {
                    return false;
                }
            }	
        }
        return false;
    }
 
    public boolean checkBipartite(int adjacencyMatrix[][]) {
        boolean bipartite = true;
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] colored = new int[numberOfNodes + 1];
 
        for (int vertex = 1; vertex <= numberOfNodes; vertex++) {
            colored[vertex] = 0;
        }
 
        if (!checkBipartiteUtil(adjacencyMatrix, colored, 1)) {
            bipartite = false;
        }
        return bipartite;
    }
 
    public static void main(String... arg) {
        int number_of_nodes;
        Scanner scanner = null;
 
        try {
            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            number_of_nodes = scanner.nextInt();
 
            int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
            System.out.println("Enter the adjacency matrix");
            for (int i = 1; i <= number_of_nodes; i++) {
                for (int j = 1; j <= number_of_nodes; j++) {
                    adjacency_matrix[i][j] = scanner.nextInt();
                }
            }
 
            for (int i = 1; i <= number_of_nodes; i++) {
                for (int j = 1; j <= number_of_nodes; j++) {	
                    if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0) {
                        adjacency_matrix[j][i] = 1;
                    }
                }
            }
 
            CheckBipartite bipartite = new CheckBipartite();
            if (bipartite.checkBipartite(adjacency_matrix)) {
                System.out.println("the given graph is bipartite");
            } else {
                System.out.println("the given graph is not bipartite");
            }
        } catch (InputMismatchException inputMismatch) {
            System.out.println("Wrong Input format");
        }
        scanner.close();
    }	
}