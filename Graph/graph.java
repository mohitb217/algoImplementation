import java.util.LinkedList;

class graph{

	int verticeCount=0;
	static LinkedList<Integer> adjArray[];
	graph(int v){
		verticeCount = v;
		adjArray = new LinkedList[verticeCount];
		for(int i=0;i<verticeCount;i++){
			adjArray[i] = new LinkedList<>();
		}
	}

	static void addEdge(int src,int dest){
		adjArray[src].add(dest);
		adjArray[dest].add(src);
	}

	void print(){
		for(int i=0;i<verticeCount;i++){
			System.out.println("Adjancy list of vertex " + i);
			System.out.print("head");
			for(Integer x: adjArray[i] ){
				System.out.print("->" + x );
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		graph graph = new graph(5); 
        addEdge( 0, 1); 
        addEdge( 0, 4); 
        addEdge( 1, 2); 
        addEdge( 1, 3); 
        addEdge( 1, 4); 
        addEdge( 2, 3); 
        addEdge( 3, 4);
        graph.print();
	}
}