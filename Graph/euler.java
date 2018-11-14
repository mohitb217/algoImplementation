import java.util.*;
import java.io.*;
class euler{
	static Stack path = new Stack();

	static void DFSvisit(graph graph,int v,boolean visited[]){
		visited[v] = true;
		path.push(graph.adjArray[v]);

		Iterator<Integer> i = graph.adjArray[v].listIterator();

		while( i.hasNext() ){
			int n= i.next();
			if(visited[n]==false){
				DFSvisit(graph,n,visited);
			}
		}
	}

	static boolean isConnected(graph graph){
		boolean visited[] = new boolean[graph.verticeCount];
		int i;

		for(i=0;i<graph.verticeCount;i++){
			visited[i]=false;
		}

		for(i=0;i<graph.verticeCount;i++){
			if(graph.adjArray[i].size()!=0){
				break;
			}
		}

		if(i==graph.verticeCount){
			return true;
		}

		DFSvisit(graph,i,visited);

		for(i=0;i<graph.verticeCount;i++){
			if(visited[i]==false && graph.adjArray[i].size()>0){
				return false;
			}
		}
		return true;
	}


	static int isEulerian(graph graph){
		if(isConnected(graph) ==false){
			return 0;
		}

		int checker = 0;
		for(int i=0;i<graph.verticeCount;i++){
			if(graph.adjArray[i].size()%2!=0)
				checker++;
		}

		if(checker>2){
			return 0;
		}

		return (checker==2)?1:2;
	}

	static void test(graph graph){
		int result = isEulerian(graph);
		if(result==0)
			System.out.println("Not Eulerian");
		if(result==1)
			System.out.println("Euler Path exists");
		if(result==2)
			System.out.println("Euler cycle");
		if(result!=0){
			while(!path.isEmpty()){
				System.out.println(path.pop());
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		graph g1 = new graph(5); 
        g1.addEdge(1, 0); 
        g1.addEdge(0, 2); 
        g1.addEdge(2, 1); 
        g1.addEdge(0, 3); 
        g1.addEdge(3, 4); 
        test(g1);
  
        graph g2 = new graph(5); 
        g2.addEdge(1, 0); 
        g2.addEdge(0, 2); 
        g2.addEdge(2, 1); 
        g2.addEdge(0, 3); 
        g2.addEdge(3, 4); 
        g2.addEdge(4, 0);
        test(g2); 
  
        graph g3 = new graph(5); 
        g3.addEdge(1, 0); 
        g3.addEdge(0, 2); 
        g3.addEdge(2, 1); 
        g3.addEdge(0, 3); 
        g3.addEdge(3, 4); 
        g3.addEdge(1, 3); 
        test(g3);
  
        graph g4 = new graph(3); 
        g4.addEdge(0, 1); 
        g4.addEdge(1, 2); 
        g4.addEdge(2, 0);
        test(g4);

        graph g5 = new graph(3);
        test(g5);

	}
}


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
}