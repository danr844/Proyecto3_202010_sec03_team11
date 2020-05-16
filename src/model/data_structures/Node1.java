

package model.data_structures;
public class Node1<Key>
{ // linked-list node
	public Key key;
	public Node1<Key> next;
	public Node1<Key>primero;
	public Node1<Key>ultimo;

	public Node1(Key key, Node1<Key> next)
	{
		this.key = key;
		this.next = next;
	}
	public void changeNext(Node1<Key>node){
		next = node;
	}
}




