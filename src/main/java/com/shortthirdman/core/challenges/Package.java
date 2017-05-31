/* Package Problem

Challenge Description:

You want to send your friend a package with different things. 
Each thing you put inside of a package has such parameters as index number, weight and cost. 
The package has a weight limitation. 
Your goal is to determine which things to put into the package so that the total weight is less than or equal to the package limit and the total cost is as large as possible. 
This is a variation of Knapsack problem

Input sample:

Your program should accept as its first argument a path to a filename. The input file contains several lines. Each line is one test case. 

Each line contains the weight that a package can take (before the colon) and the list of things you need to pick from. 
Each thing is enclosed in parentheses where 1st number is a thing's index number, the 2nd is it's weight and the 3rd is it's cost. E.g.

81 : (1,53.38,$45) (2,88.62,$98) (3,78.48,$3) (4,72.30,$76) (5,30.18,$9) (6,46.34,$48)

8 : (1,15.3,$34)

75 : (1,85.31,$29) (2,14.55,$74) (3,3.98,$16) (4,26.24,$55) (5,63.69,$52) (6,76.25,$75) (7,60.02,$74) (8,93.18,$35) (9,89.95,$78)

56 : (1,90.72,$13) (2,33.80,$40) (3,43.15,$10) (4,37.97,$16) (5,46.81,$36) (6,48.77,$79) (7,81.80,$45) (8,19.36,$79) (9,6.76,$64)

Output sample:

For each set of things produce a list of things (their index numbers separated by comma) that you put into the package. E.g.

4
-
2,7
8,9

Constraints: 
Max weight any package can take is <= 100. 
There might be up to 15 things you need to choose from. 
Max weight and max cost of any thing is <= 100.
*/
package com.shortthirdman.core.challenges;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Package {

	double limit;
	ArrayList<Item> items;
	ArrayList<ArrayList<Item>> combinations;

	public Package(double l, ArrayList<Item> i){
		limit = l;
		items = i;
		combinations = new ArrayList<ArrayList<Item>>();
	}
	
	//remove items with weight over the limit
	public void filterItems(){
		Iterator<Item> iter = items.iterator();
		while(iter.hasNext()){
			Item i = iter.next();
			if(i.weight > limit)
				iter.remove();
		}
		//System.out.println("filtered items: " + items.toString());
	}
	
	public ArrayList<ArrayList<Item>> createCombinations(){
		//ArrayList<ArrayList<Item>> combinations = new ArrayList<ArrayList<Item>>();
		//loop through every item
		for(int x = 0; x < items.size(); x++){
			Item currentItem = items.get(x);
			//System.out.println("current item at " + x + " = " + currentItem.id);
			int combinationSize = combinations.size();
			//System.out.println("combination size: " + combinationSize);
			//System.out.println("current combination: " + combinations.toString());
			for(int y = 0; y < combinationSize; y++){
				ArrayList<Item> combination = combinations.get(y);
				//System.out.println("combination at " + y + " = " + combination.toString());
				ArrayList<Item> newCombination = new ArrayList<Item>(combination);
				newCombination.add(currentItem);
				//System.out.println("new combination: " + newCombination.toString());
				combinations.add(newCombination);
			}
			ArrayList<Item> current = new ArrayList<Item>();
			current.add(currentItem);
			combinations.add(current);
			//System.out.println(combinations.toString());
		}
		
		return combinations;
	}
	
	public double getWeight(ArrayList<Item> items){
		double total = 0;
		for(Item i : items){
			total += i.weight;
		}
		return total;
	}
	
	public double getPrice(ArrayList<Item> items){
		double total = 0;
		for(Item i : items){
			total += i.price;
		}
		return total;
	}
	
	public ArrayList<Item> getBestPackage(){
		ArrayList<Item> bestCombination = new ArrayList<Item>();
		double bestCost = 0;
		double bestWeight = 100; //max weight is 100
		for(ArrayList<Item> combination : combinations){
			double combinationWeight = getWeight(combination);
			if(combinationWeight > limit){
				continue;
			}else{
				double combinationPrice = getPrice(combination);
				if(combinationPrice > bestCost){
					bestCost = combinationPrice;
					bestCombination = combination;
					bestWeight = combinationWeight;
				}else if(combinationPrice == bestCost){	//use lightest weight
					if(combinationWeight < bestWeight){
						bestCost = combinationPrice;
						bestCombination = combination;
						bestWeight = combinationWeight;
					}
				}
			}
		}
		return bestCombination;
	}
	
	public void findPackage(){
		filterItems();
		combinations = createCombinations();
		if(combinations.size() == 0){
			System.out.println("-");
		}else{
			ArrayList<Item> bestCombination = getBestPackage();
			Collections.sort(bestCombination);
			printOutput(bestCombination);
			//System.out.println(bestCombination.toString());
		}
	}
	
	public void printOutput(ArrayList<Item> items){
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (Item i : items) {
			if(isFirst){
				sb.append(i.id);
				isFirst = false;
			}else{
				sb.append("," + i.id);
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args){
		//File file = new File(args[0]);
		File file = new File("PackageTest.txt");
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			while((line = in.readLine()) != null){
				if(line.length() == 0)
					continue;
				String[] lineArray = line.split(":");
				double weightLimit = Integer.parseInt(lineArray[0].trim());				
				String[] stringItems = lineArray[1].trim().split(" ");
				ArrayList<Item> inputs = new ArrayList<Item>();
				for(String stringItem : stringItems){
					String[] itemDetails = stringItem.split(",");
					int id = Integer.parseInt(itemDetails[0].substring(1));
					double weight = Double.parseDouble(itemDetails[1]);
					double price = Double.parseDouble(itemDetails[2].substring(1, itemDetails[2].length()-1));
					Item item = new Item(id, weight, price);
					inputs.add(item);
				}
				//Collections.sort(inputs);
				Package p = new Package(weightLimit, inputs);
				p.findPackage();
			}
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public static class Item implements Comparable<Item>{
		int id;
		double weight, price;
		public Item(int id, double weight, double price){
			this.id = id;
			this.weight = weight;
			this.price = price;
		}
		
		public String toString(){
			return "(" + id + "," + weight + "," + price + ")";
		}
		
		public int compareTo(Item i){
			return this.id - i.id;
		}
	}
}

