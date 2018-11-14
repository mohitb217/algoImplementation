class heap{
	
	int[] heapArray;
	int heapSize=0;
	int heapMaxSize;
	heap(int size){
		heapArray = new int[size];
		heapMaxSize = size;
	}

	void insert(int data){
		if(heapSize<heapMaxSize){
			heapArray[heapSize] = data;
			heapSize++;
			buildMaxHeap();
		}

		else{
			System.out.println("Heap Full");
		}
	}

	
	int leftChild(int i){
		return heapArray[2*i];
	}
	int rightChild(int i){
		return heapArray[ (2*i + 1) ];
	}

	int parent(int i){
		if(i%2==0){
			return heapArray[i/2];
		}
		else{
			return heapArray[i/2 + 1];
		}
	}
	int parentIndex(int i){
		if(i%2==0){
			return i/2;
		}
		else{
			return i/2 + 1;
		}
	}



	void maxHeapify(int i){
		int largest = i;

		if(2*i<heapSize && leftChild(i)>heapArray[largest]){
			largest = 2*i;
		}
		if((2*i)+1<heapSize && rightChild(i)>heapArray[largest]){
			largest = (2*i)+1;
		}

		if(largest!=i){
			int temp = heapArray[i];
			heapArray[i] = heapArray[largest];
			heapArray[largest] = temp; 
			maxHeapify(largest);
		}
	}	

	void buildMaxHeap(){

		for(int i= (int)Math.floor(heapSize/2-1);i>=0;i--){
			maxHeapify(i);
		}
	}
	

	void heapSort(){
		buildMaxHeap();
		int tempSize = heapSize;
		for(int i = tempSize;i>0;i--){
			int temp = heapArray[i];
			heapArray[i] = heapArray[0];
			heapArray[0] = temp;
			heapSize--;
			maxHeapify(0);
			
		}
		heapSize = tempSize;
	}

	int heapExtractMax(){
		int temp = heapArray[0];
		heapArray[0] = heapArray[heapSize];
		heapArray[heapSize] = temp;

		heapSize--;
		maxHeapify(0);

		return heapArray[heapSize+1];  
	}

	void heapIncreaseKey(int index,int data){
		if(index<heapSize){
			if(data<heapArray[index]){
				System.out.println("ERROR: INPUT LARGER VALUE");
			}
			else{
				heapArray[index] = data;
				while(index>0 && parent(index)<heapArray[index]){
					int temp = heapArray[index];
					heapArray[index] = heapArray[ parentIndex(index) ];
					heapArray[ parentIndex(index) ] = temp;

					index = parentIndex(index);
				}
			}
		}
		else{
			System.out.println("Index wrong");
		}
	}

	void printHeap(){
		for(int i=0;i<heapSize+1;i++){
			System.out.println(heapArray[i]);
		}
	}



	public static void main(String[] args) {
		heap heap_1 = new heap(10);
		heap_1.insert(10);
		heap_1.insert(20);
		heap_1.insert(30);
		heap_1.insert(40);
		heap_1.insert(15);
		heap_1.insert(65);

		heap_1.printHeap();
		System.out.println();
		
/*		System.out.println("heapExtractMax:  " + heap_1.heapExtractMax() );
		System.out.println();
		heap_1.printHeap();
		System.out.println();

		heap_1.heapIncreaseKey(4,35);
		heap_1.printHeap(); */

	}

}