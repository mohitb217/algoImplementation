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
			System.out.println("LEFT  LEFT CASE");

			return rightRotate(node);
		}
		if(balance<-1 && data>node.right.data){
			System.out.println("RIGHT RIGHT CASE");

			return leftRotate(node);
		}
		if(balance>1 && data>node.left.data){
			node.left = leftRotate(node.left);
			System.out.println("LEFT RIGHT CASE");
			return rightRotate(node);
		}
		if(balance<-1 && data<node.right.data){
			node.right = rightRotate(node.right);
			System.out.println("RIGHT LEFT CASE");

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

	boolean balanceCheck(){
		boolean result = balanceCheckloop(root,true);
		return result;
	}

	boolean balanceCheckloop(node curr,boolean check){
		if(curr!=null && check==true){
			balanceCheckloop(curr.left,check);
			if( curr.height!= (1) || curr.height!= (-1) || curr.height!= (0) ){
				check = false;
			}
			balanceCheckloop(curr.right,check);

		}
		else if(check==false){
			return false;
		}
		return true;
	}

	void delete(int data){
		deleteLoop(root,data);
	}

	node deleteLoop(node curr,int data){
		if(curr==null){
			return null;
		}
		if(curr.data>data){
			curr.left = deleteLoop(curr.left,data);
		}
		else if(curr.data<data){
			curr.right = deleteLoop(curr.right,data);
		}
		else{
			if(curr.left==null || curr.right == null){
				node temp = null;

				if(curr.left==null){
					temp = curr.right;
				}
				else{
					temp = curr.left;
				}

				if(temp ==null){
					temp = curr;
					curr = null;
				}
				else{
					curr = temp;
				}
			}
			else{
				node temp = minValueNode(curr.right);

				curr.data = temp.data;

				curr.right = deleteLoop(curr.right,temp.data);
			}
		}

		if(curr==null){
			return null;
		}

		curr.height = 1 + Math.max(height(curr.left),height(curr.right));

		int balance = getBalance(curr);

		if(balance>1 && data<curr.left.data){
			System.out.println("LEFT  LEFT CASE");

			return rightRotate(curr);
		}
		if(balance<-1 && data>curr.right.data){
			System.out.println("RIGHT RIGHT CASE");

			return leftRotate(curr);
		}
		if(balance>1 && data>curr.left.data){
			curr.left = leftRotate(curr.left);
			System.out.println("LEFT RIGHT CASE");
			return rightRotate(curr);
		}
		if(balance<-1 && data<curr.right.data){
			curr.right = rightRotate(curr.right);
			System.out.println("RIGHT LEFT CASE");

			return leftRotate(curr);
		}
		return curr;
	}



	node minValueNode(node node)  
    {  
        node current = node;  
        while (current.left != null)  
        	current = current.left;  
  
        return current;  
    } 

	public static void main(String[] args) {
		avl tree = new avl();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);
		tree.insert(15);
		tree.insert(12);

	

		tree.printInorder();
		System.out.println();
		tree.printPreorder();
		System.out.println();
		tree.printPostorder();
		System.out.println(tree.balanceCheck());
		tree.delete(20);
		System.out.println();
		tree.printInorder();

	}
}

class node{
	int data;
	node left = null;
	node right = null;
	int height = 1;
}