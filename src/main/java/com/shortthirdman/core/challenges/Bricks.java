package com.shortthirdman.core.challenges;

/* A Pile of Bricks */

public class Bricks {

	public Bricks(){
	}
	
	public void findTwoShortestSides(){
	
	}
	
	public static void main(String[] args){
		//File file = new File(args[0]);
		File file = new File("PackageTest.txt");
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			while((line = in.readLine()) != null) {
				if(line.length() == 0)
					continue;
				String[] lineArray = line.split(":");
				double weightLimit = Integer.parseInt(lineArray[0].trim());				
				String[] stringItems = lineArray[1].trim().split(" ");
				ArrayList<Item> inputs = new ArrayList<Item>();
				for(String stringItem : stringItems) {
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
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
