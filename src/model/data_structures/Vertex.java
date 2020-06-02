package model.data_structures;

import java.util.Iterator;


public class Vertex<T extends Comparable<T>, V extends Comparable<V>> implements Comparable<Vertex<T, V>>{
	private T idVertex;
	private V infoVertex;
	private boolean isMarked;
	private int color;
	private Edge<T,V> edge; 
	private double Distance;
	private LinkedList<Comparendo>ComparendosList;
	
	private LinkedList<Edge<T, V>> adjacencyList;

	
	public Vertex(T id, V pInfoVertex){
		idVertex = id;
		infoVertex = pInfoVertex;
		adjacencyList = new LinkedList<Edge<T,V>>();
		ComparendosList = new LinkedList<>();
			
	}
	
	public T getID(){
		return idVertex;
	}
	
	public void addComparendo(Comparendo comparendo){
		ComparendosList.put(comparendo);
	}
	public int getComparendosNumber(){
		return ComparendosList.size();
	}
	public LinkedList<Comparendo>getComparendosList(){
		return ComparendosList;
	}
	public V getInfo(){
		return infoVertex;
	}
	public boolean isMarked(){
		return isMarked;
	}
	
	public void check(int pColor, Edge<T,V>pFinalEdge){
		color= pColor;
		edge = pFinalEdge;
		isMarked=true;
	}
	
	public void uncheck(){
		edge= null;
		isMarked= false;
	}
	
	public Edge<T,V> getFinalEdge(){
		return edge;
	}
	
	public int getColor(){
		return color;
	}
	public void changeFinalEdge(Edge<T,V>pEdge){
		edge=pEdge;
	}
	public void changeInfo(V pInfoVertex){
		infoVertex = pInfoVertex;
	}
	public void changeColor(int pColor){
		color = pColor;
	}
	public void changeDistance(double pDistance){
		Distance= pDistance;
	}
	public void addEdge(Edge<T,V>edge){
		if(adjacencyList.contains(edge)){
			adjacencyList.get(edge).changeDistanceCost(edge.getDistanceCost());
		}
		else{
			adjacencyList.put(edge);
		}
	}
	
	public Iterable<Edge<T,V>>getAdjacencyList(){
		return adjacencyList.keys();
	}
	public int getAdjacencyListSize(){
		return adjacencyList.size();
	}

	public Edge<T,V> deleteEdge(T edgeID){
		Iterator<Edge<T,V>> iterator= getAdjacencyList().iterator();
		while(iterator.hasNext()){
			Edge<T,V>edge= iterator.next();
			if(edge.getFinalVertex().idVertex.compareTo(edgeID)==0)
				return adjacencyList.delete(edge);
		}
		return null;
	}
	
	
	
	
	
	@Override
	public int compareTo(Vertex<T, V> o) {
		// TODO Auto-generated method stub
		return o.getID().compareTo(idVertex);
	}
}
