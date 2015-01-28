/**
 * Chandani Patel
 * 11/05/2014
 * You have 100 small bottles arranged in a circle with a number on each bottle. 
 * You drink bottle #1, skip 1, drink bottle #3, skip 2, drink bottle #6, skip 3, drink bottle #10, 
 * and continue to skip one more bottle after each drink, which bottle will the last one you drink?
 * 
 */


public class Bottles {

	public static void main(String[] args) {
		//Initialize circular list
		CircularLinkedList list = new CircularLinkedList();

		//Add values from 1 to 100 to the list
		for(int a=1; a<=100;a++){
			list.add(a);
		}
		
		//Initialize variables
		int bottleNumber;
		int count =  1;
		int bottlesLeft = 100;
		int index = 1;
		int skip = 0;

		//Print out list
		list.print();
		System.out.println();
		System.out.println();
		
		while (count<=99){

			//The index will skip one more iteration from the previous index
			//Use bottlesLeft mod to only include the numbers of bottles left
			//To make sure it will continue in a circle
			index =(index+skip)%bottlesLeft;

			//Get the bottle number from the list
			bottleNumber = list.get(index);

			//Delete number at index 
			list.delete(index);

			/*Print for Testing*/
			//System.out.println("Drink # " + count + " is Bottle # " + bottleNumber +" skipped by " + skip + " bottles");
			//list.printRange(bottlesLeft-1);
			//System.out.println();
			
			//Advance iterations and the decrease the number of bottles left
			count++;
			bottlesLeft--;
			skip++;


		}
		//Print Final bottle
		System.out.println("Final Drink is Bottle # "+list.get(index));
		//list.print();

	}
}


class CircularLinkedList {

	static Node head;
	static Node current;
	static Node temp;


	/**
	 * Add value at the end of the list
	 */
	public void add(int value){
		//Create a new Node with the value
		Node node = new Node(value);	

		//If it is the first value
		if(head==null){
			//Let head be equal to the new node
			head = node;
			//Let the next node equal to the head so its circular
			head.nextNode = head;
		}else{
			current = head;
			//Go down the list
			while(current.nextNode!=head){
				current = current.nextNode;
			}
			//Let the next node equal to the new node
			current.nextNode = node;
			//Let the next node equal to the head so its circular
			node.nextNode = head;

		}


	}

	/**
	 * Get the value at index
	 */
	public int get(int index){
		current = head;
		//Go through the list until index is found and get the data
		for(int i =1;i<index;i++){
			current = current.nextNode;
		}
		return current.data;
	}

	/**
	 * Delete the number at index
	 */
	public void delete(int index){
		int i = 1;
		current = head;

		//If the index is the head then shift to the right by using a temporary node
		if(index==1){
			temp = head.nextNode;
			while(temp.nextNode!=head){
				temp = temp.nextNode;
			}
			temp.nextNode=temp.nextNode.nextNode;
			head=current.nextNode;
		}else{
			//Else find the node then shift to the right 
			while(i != index-1){
				current = current.nextNode;
				i++;
			}
			current.nextNode=current.nextNode.nextNode;
		}
		Node.size--;
	}

	/**
	 * Print the list
	 */
	public void print(){		
		current = head;
		do{
			System.out.print(" " + current.data + " " );
			current = current.nextNode;
		}while(current!=head);
	}

	/**
	 * Print the list only in the range of 0 to num
	 */
	public void printRange(int num){
		current = head;
		for(int i=0;i<num;i++){
			System.out.print(" " + current.data + " " );
			current = current.nextNode;
		}
	}
}

/**
 * 
 *
 */
class Node {

	static int size = 0;
	int data;
	Node nextNode;

	Node(int data){
		this.data = data;
		size++;

	}

}
