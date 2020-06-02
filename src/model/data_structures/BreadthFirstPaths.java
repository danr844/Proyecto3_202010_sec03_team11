package model.data_structures;

import java.util.ArrayList;

public class BreadthFirstPaths 
{ 
	private boolean[] marked; // is there an s-v path?
	private int[] edgeTo; // previous edge on shortest s-v path
	private int[] distTo; // number of edges shortest s-v path 


	public BreadthFirstPaths(GrafoNoDirigido<Integer, GeographicPoint> G, int s)
	{ 
		marked = new boolean[G.getVertexSize()];
		distTo = new int[G.getVertexSize()];
		edgeTo = new int[G.getVertexSize()]; 
		bfs(G, s);
	}


	private void bfs(GrafoNoDirigido<Integer, GeographicPoint> G, int s)
	{ 
		Queue<Integer> q = new Queue<Integer>();
		for (int v = 0; v < G.getVertexSize(); v++)
			distTo[v] = 999999; // initialize distances
		marked[s] = true; // mark the source vertex as visited
		distTo[s] = 0; // source vertex’s distance is 0
		q.enqueue(s); // put the source vertex on the queue. 
		while (!q.isEmpty())
		{ int v = q.dequeue(); // dequeue the next vertex. 
		for (int w : G.adj(v))
			if (!marked[w]) 
			{ // for every unmarked adjacent vertex 
				edgeTo[w] = v; // save last edge on a shortest path 
				marked[w] = true; // mark it because path is known 
				distTo[w] = distTo[v] + 1; // add a step to w’s distance
				q.enqueue(w); // add w to the queue. 
			} 
		} 

	}
	public Queue<Integer> getpath(int init, int finals){
		int i=finals;
		int path = edgeTo[i];
		Queue<Integer> queue = new Queue<Integer>();
		while(path!=0){
			queue.enqueue(path);
			i = path;
		}
		return queue;
	}

}