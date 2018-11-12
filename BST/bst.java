class bst{
	node head;
	bst(){
		head = null;
	}

	void insert(int data){
		if(head == null){
			head = new node();
			head.data = data;
		}
		else{
			node temp = new node();
			node newNode = new node();
			newNode.data = data;

			temp  = head;
			node parent = null;

			while(temp!=null){
				parent = temp;
				if(temp.data>newNode.data){
					temp = temp.left;
				}
				else{
					temp = temp.right;
				}

			}

			if(parent.data>newNode.data){
				parent.left = newNode;
			}
			else if (parent.data<newNode.data) {
				parent.right = newNode;
			}
		}
	}

	void print(){
		printInOrder(head);
	} 

	void printInOrder(node head){
		if(head==null){
			return;
		}
			printInOrder(head.left);
			System.out.println("Data: " + head.data);
			printInOrder(head.right);		
	}
	void printPreOrder(node head){
		if(head==null){
			return;
		}	
			System.out.println("Data: " + head.data);
			printPreOrder(head.left);
			printPreOrder(head.right);		
	}

	void printPostOrder(node head){
		if(head==null){
			return;
		}	
			printPostOrder(head.left);
			printPostOrder(head.right);
			System.out.println("Data: " + head.data);
					
	}

	void delete(int data){

		if (head==null) {
			System.out.println("Empty tree");
		}

		else{
			node temp = head;
			node parent = head;
			while(true){
				if(temp.data>data){
					parent = temp;
					temp = temp.left;
					if (temp==null) {
						System.out.println("Element not found");
						break;
					}

				}
				else if (temp.data<data) {
					parent = temp;
					temp = temp.right;
					if (temp==null) {
						System.out.println("Element not found");
						break;
					}
				}
				else{
					if(temp.left==null || temp.right == null){
						if(temp.left==null){
							if(parent.data>data){
								parent.left= temp.right;
							}
							else{
								parent.right= temp.right;
							}
						}
						else{
							if(parent.data>data){
								parent.left= temp.left;
							}
							else{
								parent.right= temp.left;
							}
						}
					}
					else{
						/*if(temp.right.left==null){
							if(parent.data<data){
								parent.right=temp.right;
							}
							else{
								parent.left=temp.right;
							}
						}
						else{*/
							node traversalNode = temp.right;
							node parentalNode = temp.right;
							while(traversalNode.left!=null){
								parentalNode = traversalNode;
								traversalNode = traversalNode.left;
							}
							temp.data = traversalNode.data;
							parentalNode.left=null;
							if(temp.right.left==null){
								temp.right=temp.right.right;
							}

						//}

					}

					break;

				}
			}
		}

	}

	public static void main(String[] args) {
		bst tree_1 = new bst();
		tree_1.insert(60);
		tree_1.insert(30);
		tree_1.insert(20);
		tree_1.insert(50);
		tree_1.insert(70);
		tree_1.insert(80);
		tree_1.insert(40);

		tree_1.print();
		System.out.println("");
		tree_1.delete(30);
		tree_1.print();
	}
}

class node{
	node left = null;
	node right = null;
	int data;
}