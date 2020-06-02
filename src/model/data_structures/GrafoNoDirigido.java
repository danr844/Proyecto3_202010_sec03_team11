package model.data_structures;
import java.util.Comparator;
import java.util.Iterator;



public class GrafoNoDirigido<T extends Comparable<T>, V extends Comparable<V>> {

	private int edgesNumber;
	private int vertexNumber;
	private SeparateChainingHT<T, Vertex<T,V>> tableVertex;
	private RedBlackBST<T, Edge<T,V>> redBlackEdgeTree;
	private RedBlackBST<Double, Vertex<T,V>> vertexNewKey;

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
		vertexNewKey= new RedBlackBST<Double, Vertex<T,V>>();

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

	public Iterable<Double> keyInRange(Double lo, Double hi){
		return vertexNewKey.keys(lo, hi);
	}
	/**
	 * Adds the undirected edge v-w to this graph.
	 *
	 * @param  initialVertex one vertex in the edge
	 * @param  finalVertex the other vertex in the edge
	 * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(T idEdge, T initialVertex, T finalVertex, double cost, int costComp) {
		validateVertex(initialVertex);
		Vertex<T, V>vertex1 =tableVertex.get(initialVertex);
		Vertex<T, V>vertex2 =tableVertex.get(finalVertex);

		Edge<T, V> edgeToAdd = new Edge<T,V>(idEdge,vertex1, vertex2, cost, costComp);
		if(!redBlackEdgeTree.contains(edgeToAdd.getID())){
			redBlackEdgeTree.put(idEdge, edgeToAdd);
			edgesNumber++;
		}
		else
			redBlackEdgeTree.get(idEdge).changeDistanceCost(cost);
		vertex1.addEdge(edgeToAdd);
		Edge<T, V> edgeToAdd2 = new Edge<T,V>(idEdge,vertex2, vertex1, cost, costComp);
		vertex2.addEdge(edgeToAdd2);


	}
	public void addVertex(T idVertex, V infoVertex){
		tableVertex.put(idVertex, new Vertex<T,V>(idVertex, infoVertex));
		vertexNumber++;
	}
	public void addVertexNewKey(Double newKey,T idVertex, V infoVertex){
		vertexNewKey.put(newKey, new Vertex<T,V>(idVertex, infoVertex));
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
	public Vertex<T,V> getVertexNewKey(Double idVertex){
		return vertexNewKey.get(idVertex);
	}
	public Vertex<T,V> getVertex(T idVertex){
		return tableVertex.get(idVertex);
	}
	public double getEdgeCostByID(T edgeID){
		return redBlackEdgeTree.get(edgeID).getDistanceCost();
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
				return actual.getDistanceCost();
			}
		}

		return -1.0;
	}
	public void setEdgeCostID(T id, double cost){
		Edge<T,V>edge= redBlackEdgeTree.get(id);
		if(edge!=null)
			edge.changeDistanceCost(cost);
	}
	public void setEdgeCostVertex(T idIniVertex,T finalVertex, double cost){
		validateVertex(idIniVertex);
		validateVertex(finalVertex);
		getEdgeByVertex(idIniVertex, finalVertex).changeDistanceCost(cost);
	}

	public RedBlackBST<T, Edge<T,V>> getEdgesRBST(){
		return redBlackEdgeTree;
	}
	public SeparateChainingHT<T, Vertex<T,V>> getVertexHAsh(){
		return tableVertex;
	}
	public Comparator<Edge<T,V>> darComparador(){
		Comparator<Edge<T,V>> peso = new Comparator<Edge<T,V>>()
		{
			
			@Override
			public int compare(Edge<T, V> arg0, Edge<T, V> arg1) {
				// TODO Auto-generated method stub
				if(arg0.getDistanceCost()<arg1.getDistanceCost())
				return 1;
				else if(arg0.getDistanceCost()>arg1.getDistanceCost())
					return -1;
				else
					return 0;
			}
		};
		return peso;

	}
	public Queue<Edge<T,V>>KruskalMST()
	{
		GrafoNoDirigido<T, V >G = this;
		Queue<Edge<T,V>>mst = new Queue<Edge<T,V>>(); 
		MinPQ<Edge<T,V>> pq = new MinPQ<Edge<T,V>>(G.edgesNumber, darComparador()); 
		UF uf = new UF(G.vertexNumber); 
		while (!pq.isEmpty() && mst.size() < G.vertexNumber-1)
		{ 
			Edge<T,V> e = pq.delMin();// Get min weight edge on pq 
			int v = (int) e.getOrigin().getID(),w = (int) e.getFinalVertex().getID(); // and its vertices.
			if (uf.connected(v, w)) continue; // Ignore ineligible edges.
			uf.union(v, w); // Merge components. 
			mst.enqueue(e); // Add edge to mst. 
		}
		return mst;
	}



}
