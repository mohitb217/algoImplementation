class redblack{
	node root;
	redblack(){
		root = null;
	}

	// INSERTION
	void leftRotate(node node){
		if(node.parent!=null){
			if(node==node.parent.left){
				node.parent.left = node.right;
			}
			else{
				node.parent.right = node.left;
			}
			node.right.parent = node.parent;
			node.parent = node.right;
			if(node.right.left!=null){
				node.right.left.parent = node;
			}
			node.right = node.right.left;
			node.parent.left = node;
		}
		else{
			node right = root.right;
			root.right = right.left;
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = null;
			root = right;
		}
	}

	void rightRotate(node node){
		if(node.parent!=null){
			if(node.parent.left==node){
				node.parent.left = node.left;
			}
			else{
				node.parent.right  = node.left;
			}
			node.left.parent = node.parent;
			node.parent = node.left;

			if(node.left.right!=null){
				node.left.right.parent = node;
			}

			node.left = node.left.right;
			node.parent.right = node;


		}
		else{
			node left = root.left;
			root.left = left.right;
			left.right.parent = root;
			left.right = root;
			root.parent = left;
			left.parent = null;
			root = left;
		}
	}

	void insert(int data){
		node temp = root;
		if(root ==null){
			root = new node(data);
		}
		else{
			node node = new node(data);

			while(true){
				if(node.data<temp.data){
					if(temp.left==null){
						temp.left = node;
						node.parent = temp;
						break;
					}
					else{
						temp=temp.left;
					}
				}
				else if(node.data>temp.data){
					if(temp.right==null){
						temp.right =node;
						node.parent=temp;
						break;
					}
					else{
						temp=temp.right;
					}
				}
			}

			fixtree(node);
		}
	}

	void fixtree(node node){
		while (node.parent.color=="red") {
			node uncle = null;

			if(node.parent==node.parent.parent.left){
				uncle = node.parent.parent.right;
				
				if(uncle!=null && uncle.color=="red"){
					node.parent.color="black";
					uncle.color = "black";
					node.parent.parent.color = "red";
					node = node.parent.parent;
					continue;
				}

				if(node==node.parent.right){
					node = node.parent;
					leftRotate(node);
				}
				node.parent.color = "black";
				node.parent.parent.color = "red";
				rightRotate(node.parent.parent);

			}
			else{
				uncle = node.parent.parent.left;
				if( uncle!=null && uncle.color=="red" ){
					node.parent.color = "black";
					uncle.color = "black";
					node.parent.parent.color = "red";
					node = node.parent.parent;
					continue;
				}
				if(node==node.parent.left){
					node = node.parent;
					rightRotate(node);
				}
				node.parent.color = "black";
				node.parent.parent.color = "red";
				leftRotate(node.parent.parent);
			}
		}

		root.color = "black";
	}

	void print(){
		printPreOrder(root);
		System.out.println();
		printPostOrder(root);
		System.out.println();
		printInOrder(root);
	}

	void printInOrder(node head){
		if(head==null){
			return;
		}
		printPreOrder(head.left);
		System.out.println("Data: " + head.data);
		printPreOrder(head.right);		
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

	node findNode(int data,node node){
		if(root ==null){
			return null;
		}

		if(data>node.data){
			if(node.left!=null){
				return findNode(data,node.left);
			}
		}
		else if(data<node.data){
			if(node.right!=null){
				return findNode(data,node.right);
			}
		}
		else{
			return node;
		}
		return null;
	}




	// DELETION

	void transplant(node target,node with){
		if (target.parent==null) {
			root = with;
		}
		else if(target == target.parent.left){
			target.parent.left = with;
		}
		else{
			target.parent.right = with;
		}
		with.parent = target.parent;
	}

	node treeMinimum(node node){
		while(node.left!=null){
			node = node.left;
		}
		return node;
	}

	boolean delete(int data){
		node z = new node(data);
		if(( z=findNode(z.data,root) )==null)
			return false;
		node x;
		node y=z;
		String yOriginalColor = y.color;

		if(z.left==null){
			x=z.right;
			transplant(z,z.right);
		}
		else if(z.right==null){
			x = z.left;
			transplant(z,z.left);
		}
		else{
			y = treeMinimum(z.right);
			yOriginalColor = y.color;
			x= y.right;
			if(y.parent==z){
				x.parent=y;
			}
			else{
				transplant(y,y.right);
				y.right = z.right;
				y.right.parent = y;
			}

			transplant(z,y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}

		if(yOriginalColor=="black"){
			deleteFixup(x);
		}

		return true;
	}

	void deleteFixup(node x){
		while(x!=root && x.color=="black"){

			if(x==x.parent.left){
				node uncle = x.parent.right;
				if(uncle.color =="red"){
					uncle.color ="black";
					x.parent.color="red";
					leftRotate(x.parent);
					uncle = x.parent.right;
				}
				if(uncle.left.color=="black" && uncle.right.color=="black"){
					uncle.color="red";
					x=x.parent;
					continue;
				}
				else if(uncle.right.color=="black"){
					uncle.left.color = "black";
					uncle.color = "red";
					rightRotate(uncle);
					uncle = x.parent.right;
				}

				if(uncle.right.color=="red"){
					uncle.color = x.parent.color;
					x.parent.color = "black";
					uncle.right.color= "black";
					rightRotate(x.parent);
					x = root;
				}
			}


			else{
				node uncle = x.parent.left;
				if(uncle.color=="red"){
					uncle.color = "black";
					x.parent.color= "red";
					rightRotate(x.parent);
					uncle = x.parent.left;
				}
				if(uncle.right.color=="black" && uncle.left.color=="black"){
					uncle.color = "red";
					x = x.parent;
					continue;
				}
				else if(uncle.left.color=="black"){
					uncle.right.color = "black";
					uncle.color = "red";
					leftRotate(uncle);
					uncle = x.parent.left;
				}
				if(uncle.left.color=="red"){
					uncle.color=x.parent.color;
					x.parent.color = "black";
					uncle.left.color = "black";
					rightRotate(x.parent);
					x = root;
				}
			}
		}
		x.color="black";
	}

	public static void main(String[] args) {
		redblack tree = new redblack();
		tree.insert(20);
		tree.insert(10);		
		tree.insert(30);
		tree.insert(60);
		
		tree.delete(30);

		tree.print();
	}


}

class node{
	int data;
	node left = null;
	node right = null;
	node parent = null;
	String color = "black";
	node(int data){
		this.data = data;
	} 
}