package model.data_structures;


public class LinkedNode<Key, V>
{public Key key;
public V val;
public LinkedNode<Key, V> next;

public LinkedNode(Key key, V val, LinkedNode<Key, V> next)
{
	this.key = key;
	this.val = val;
	this.next = next;
}
}




