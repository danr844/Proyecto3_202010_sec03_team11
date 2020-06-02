package model.data_structures;
import java.util.Iterator;

import jdk.nashorn.internal.runtime.arrays.NumericElements;


public class GrafoNoDirigido<T extends Comparable<T>, V extends Comparable<V>> {

	private int edgesNumber;
	private int vertexNumber;
	private SeparateChainingHT<T, Vertex<T,V>> tableVertex;
	private RedBlackBST<T, Edge<T,V>> redBlackEdgeTree;

	/**
	 * Initializes an empty graph with {@code V} vertices and 0 edges.
	 * param V the number of vertices
	 *
	 * @param  V number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	public GrafoNoDirigido(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		vertexNumber=0;
		edgesNumber=0;
		tableVertex = new SeparateChainingHT<T, Vertex<T,V>>(V);
		redBlackEdgeTree = new RedBlackBST<T, Edge<T,V>>();

	}


	/**
	 * Returns the number of vertices in this graph.
	 *
	 * @return the number of vertices in this graph
	 */
	public int getVertexSize() {
		return vertexNumber;
	}

	/**
	 * Returns the number of edges in this graph.
	 *
	 * @return the number of edges in this graph
	 */
	public int getEdgeSize() {
		return edgesNumber;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(T v) {
		if (!tableVertex.contains(v))
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertexNumber-1));
	}

	public boolean containsVertex(T v) {
		if (!tableVertex.contains(v))
			return false;
		return true;
	}

	/**
	 * Adds the undirected edge v-w to this graph.
	 *
	 * @param  initialVertex one vertex in the edge
	 * @param  finalVertex the other vertex in the edge
	 * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(T idEdge, T initialVertex, T finalVertex, double cost) {
		validateVertex(initialVertex);
		Vertex<T, V>vertex1 =tableVertex.get(initialVertex);
		Vertex<T, V>vertex2 =tableVertex.get(finalVertex);

		Edge<T, V> edgeToAdd = new Edge<T,V>(idEdge,vertex1, vertex2, cost);
		if(!redBlackEdgeTree.contains(edgeToAdd.getID())){
			redBlackEdgeTree.put(idEdge, edgeToAdd);
			edgesNumber++;
		}
		else
			redBlackEdgeTree.get(idEdge).changeCost(cost);
		vertex1.addEdge(edgeToAdd);
		Edge<T, V> edgeToAdd2 = new Edge<T,V>(idEdge,vertex2, vertex1, cost);
		vertex2.addEdge(edgeToAdd2);


	}
	public void addVertex(T idVertex, V infoVertex){
		tableVertex.put(idVertex, new Vertex<T,V>(idVertex, infoVertex));
		vertexNumber++;
	}


	/**
	 * Returns the vertices adjacent to vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the vertices adjacent to vertex {@code v}, as an iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<T> adj(T v) {
		validateVertex(v);
		Iterable<Edge<T,V>> iterable = tableVertex.get(v).getAdjacencyList();
		Iterator<Edge<T,V>>iter = iterable.iterator();
		Queue<T> retorno = new Queue<T>();
		while(iter.hasNext()){
			Edge<T,V>edge = iter.next();
			retorno.enqueue(edge.getFinalVertex().getID());
		}
		return retorno;
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int degree(T v) {
		validateVertex(v);
		return tableVertex.get(v).getAdjacencyListSize();
	}
	public boolean uncheck(){
		if(tableVertex.darNumeroElementos()!=0){
			Iterator<T>iter= tableVertex.keys().iterator();
			while(iter.hasNext()){
				T idActualvertex = iter.next();
				tableVertex.get(idActualvertex).uncheck();
			}
			return true;
		}
		return false;
	}

	public Vertex<T,V> getVertex(T idVertex){
		return tableVertex.get(idVertex);
	}
	public double getEdgeCostByID(T edgeID){
		return redBlackEdgeTree.get(edgeID).getCost();
	}
	public Edge<T,V> getEdgeByID(T ID){
		return redBlackEdgeTree.get(ID);
	}
	public Edge<T,V> getEdgeByVertex(T idVertexIni, T idVertexFin){
		validateVertex(idVertexFin);
		validateVertex(idVertexIni);
		Vertex<T,V> VIni=tableVertex.get(idVertexIni);
		Vertex<T,V> VFin=tableVertex.get(idVertexFin);
		Iterator<Edge<T,V>>iter = VIni.getAdjacencyList().iterator();

		while(iter.hasNext())
		{
			Edge<T,V>actual=iter.next();
			if(actual.getFinalVertex().compareTo(VFin)==0)
			{
				return actual;
			}
		}
		return null;
	}

	public double getCostArc(T idVertexIni, T idVertexFin) 
	{
		validateVertex(idVertexIni);
		validateVertex(idVertexFin);
		Vertex<T,V> VIni=tableVertex.get(idVertexIni);
		Vertex<T,V> VFin=tableVertex.get(idVertexFin);
		Iterator<Edge<T,V>>iter = VIni.getAdjacencyList().iterator();

		while(iter.hasNext())
		{
			Edge<T,V>actual=iter.next();
			if(actual.getFinalVertex().compareTo(VFin)==0)
			{
				return actual.getCost();
			}
		}

		return -1.0;
	}
	public void setEdgeCostID(T id, double cost){
		Edge<T,V>edge= redBlackEdgeTree.get(id);
		if(edge!=null)
			edge.changeCost(cost);
	}
	public void setEdgeCostVertex(T idIniVertex,T finalVertex, double cost){
		validateVertex(idIniVertex);
		validateVertex(finalVertex);
		getEdgeByVertex(idIniVertex, finalVertex).changeCost(cost);
	}




}
