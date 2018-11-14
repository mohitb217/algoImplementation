class avl{

	node root;

	avl(){
		root = null;
	}

	int height(node x){
		if(x==null){
			return 0;
		}
		return x.height;
	}

	
	void insert(int data){
		root = insertNode(root,data);
	}

	node insertNode(node node,int data){
		if(node==null){
			node newNode = new node();
			newNode.data = data;
			return newNode;
		}
		else{

			if(node.data>data){
				node.left = insertNode(node.left,data);
			}
			else if(node.data<data){
				node.right = insertNode(node.right,data);
			}
			else{
				return node;
			}
		}

		node.height = 1 + Math.max(height(node.left),height(node.right));

		int balance = getBalance(node);

		if(balance>1 && data<node.left.data){
			return rightRotate(node);
		}
		if(balance<-1 && data>node.right.data){
			return leftRotate(node);
		}
		if(balance>1 && data>node.left.data){
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		if(balance<-1 && data<node.right.data){
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}

	node leftRotate(node y){
		node x = y.right;
		y.right = x.left;
		x.left = y;

		y.height = Math.max( height(y.left),height(y.right) ) + 1;
		x.height = Math.max( height(x.left),height(x.right) ) + 1;
		return x; 
	}

	node rightRotate(node y){
		node x = y.left;
		y.left = x.right;
		x.right = y;
		y.height = Math.max( height(y.left),height(y.right) ) + 1;
		x.height = Math.max( height(x.left),height(x.right) ) + 1;
		return x;
	}

	int getBalance(node parent){
		if(parent==null){
			return 0;
		}
		return height(parent.left) - height(parent.right);
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
		avl tree = new avl();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);

		tree.printInorder();
		System.out.println();
		tree.printPreorder();
		System.out.println();
		tree.printPostorder();

	}
}

class node{
	int data;
	node left = null;
	node right = null;
	int height = 1;
}