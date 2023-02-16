package model; 

public class CircularLinkedList {

	private Node head; 
	private Node tail; 

	public CircularLinkedList() { }

	public void addLast(Node node){
		if(this.head == null){
			this.tail = node; 
			this.head = node; 
			this.tail.setNext(this.head);
			this.head.setPrevious(this.tail);
		}
		else{
			this.tail.setNext(node);
			node.setPrevious(this.tail);
			this.tail = node; 
			this.tail.setNext(this.head);
			this.head.setPrevious(this.tail);
		}
	}

	public void addFirst(Node node){
		if(head == null){
			head = node;
			tail = node;
		}else{
			node.setNext(head);
			head = node;
		}
	}

	// Método de activación
	public void print(){
		print(this.head); 
	}

	private void print(Node current){

		// Casos base
		if(this.head == null && this.tail == null){
			System.out.println("La lista esta vacia");
			return; 
		}
		if(current == this.tail){
			System.out.println(current.getValue());
			return; 
		}
		System.out.println(current.getValue());
		print(current.getNext());

	}

	public String printList(){
		return printList(this.head, "[ "); 
	}

	private String printList(Node current, String msj){
		if(this.head == null){
			return "Empty list";		
		}
		if(current == this.tail){
			msj += tail.getValue() + " ]";
			return msj; 
		}

		msj += current.getValue() + " "; 
		return printList(current.getNext(), msj); 
	}

	public Node search(int goal){
		return search(goal, this.head); 
	}

	private Node search(int goal, Node current){
		// Caso base 
		if(current == null){
			return null; 
		}

		// caso borde 
		if(goal == head.getValue() && current.equals(this.head)){
			return this.head; 
		}

		if(goal == tail.getValue() && current.equals(this.tail)){
			return this.tail; 
		}
		if(goal == current.getValue()){
			return current; 
		}
		if(current == this.tail && goal != this.tail.getValue()){
			return null; 
		}

		return search(goal, current.getNext()); 
	}

	// triger de la función
	public void delete(int goal){
		delete(goal, head);
	}

	private void delete(int goal, Node current){
		//Casos base
		if(current == null){
			return;
		}
		//Caso borde: eliminar la cabeza
		if(head.getValue() == goal){
			head = current.getNext();
			return;
		}
		// Segundo caso borde elimina la cola
		if(tail.getValue() == goal && tail == current){
			current.getPrevious().setNext(null); //  previous.setNext(null);
			tail = current.getPrevious(); // previous;
			return;
		}
		// Caso intermedio 
		if(current.getValue() == goal){
			current.getPrevious().setNext(current.getNext()); // previous.setNext(current.getNext());
			return;
		}
		//Llamado recursivo
		delete(goal, current.getNext());
		//      ^       ^           ^
		//      |       |           | 
		// objetivo  previous    current
	}


}
