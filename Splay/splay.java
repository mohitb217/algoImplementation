class splay{

	node root;
	
	splay(){
		root=null;
	}

	node leftRotate(node y){
		node x = y.right;
		y.right = x.left;
		x.left = y;

		return x; 
	}

	node rightRotate(node y){
		node x = y.left;
		y.left = x.right;
		x.right = y;
		return x;
	}

	node splayTree(node curr,int data){
		
		if(curr==null || curr.data==data){
			return curr;
		}

		if(curr.data>data){
			if(curr.left==null){
				return curr;
			}

			if(curr.left.data>data){
				curr.left.left = splayTree(curr.left.left,data);
				curr = rightRotate(curr);
			}
			else if(curr.left.data<data){
				curr.left.right = splayTree(curr.left.right,data);
				if(curr.left.right!=null){
					curr.left = leftRotate(curr.left);
				}
			}

			if(curr.left==null){
				return curr;
			}
			else{
				return rightRotate(curr);
			}
		}

		else{
			if(curr.right==null){
				return curr;
			}

			if(curr.right.data>data){
				curr.right.left=splayTree(curr.right.left,data);
				if(curr.right.left!=null){
					curr.right = rightRotate(curr.right);
				}
			}
			else if(curr.right.data<data){
				curr.right.right = splayTree(curr.right.right,data);
				curr = leftRotate(curr);
			}

			if(curr.right==null){
				return curr;
			}
			else{
				leftRotate(curr);
			}
		}

		return curr;

	}

	node splayTreenow(int data){
		root = splayTree(root,data);
		return root;
	}

	void insert(int data){
		root = insertNow(root,data);
	}

	node insertNow(node curr,int data){
		if(curr==null){
			node newNode = new node();
			newNode.data = data;
			curr=newNode;
			return curr;			
		} 

		curr = splayTreenow(data);

		if(curr.data==data){
			return curr;
		}

		node newNode = new node();
		newNode.data = data;

		if(curr.data<data){
			newNode.left = curr;
			newNode.right = curr.right;
			curr.right = null;
		}
		else{
			newNode.right = curr;
			newNode.left = curr.left;
			curr.left = null;
		}

		return newNode;
	}

	void search(int data){
		root = searchTree(data);
		if(root.data==data){
			System.out.println("Found " + data);
		}
		else{
			System.out.println("Not found");
		}
	}

	node searchTree(int data){
		return splayTreenow(data);
	}


	void delete(int data){
		if(root==null){
			return ;
		}

		root = splayTreenow(data);

		if(data!=root.data){
			System.out.println("Data not found");
		}

		node temp;
		if(root.left==null){
			temp = root;
			root = root.right;
		}
		else{
			temp = root;
			splayTree(root.left,data);
			root.right = temp.right;
		}

	}

	void printInorder(){
		printInorderLoop(root);
	}

	void printInorderLoop(node curr){
		
		if(curr!=null){
			printInorderLoop(curr.left);
			System.out.println(curr.data);
			printInorderLoop(curr.right);
		}
	}

	void printPreorderLoop(node curr){
		
		if(curr!=null){
			System.out.println(curr.data);
			printPreorderLoop(curr.left);
			printPreorderLoop(curr.right);
		}
	}	
	void printPreorder(){
		printPreorderLoop(root);
	}

	void printPostorderLoop(node curr){
		
		if(curr!=null){
			printPostorderLoop(curr.left);
			printPostorderLoop(curr.right);
			System.out.println(curr.data);
		}
	}	
	void printPostorder(){
		printPostorderLoop(root);
	}


	public static void main(String[] args) {
		splay tree = new splay();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		
		tree.search(10);
		tree.printPreorder();
		System.out.println();

		tree.delete(30);

		tree.printPreorder();
		System.out.println();

	}

}

class node{
	node left = null;
	node right = null;
	int data;
}