package model.data_structures;

public class LinkedList <Key>
{

	private int n;
	
	private Node1<Key> first;
	private Node1<Key> ultimo; 
	
	
	
	
	public LinkedList(){		
	}

	public int size()
	{
		return n;
	}
	
	public boolean isEmpty()
	{
		return size()==0;
	}
	
	 public boolean contains(Key key) {
	        return get(key) != null;
	    } 

	
	public Key get(Key k)
	{
		for(Node1<Key> x=first; x!=null; x=x.next)
		{
			if(k.equals(x.key))
			{
				return x.key;
			}
		}
		return null;
	}
	public Queue<Key> getAll (Key k){
		Queue<Key> queue = new Queue<Key>();
		for(Node1<Key> x=first; x!=null; x=x.next)
		{
			if(k.equals(x.key))
			{
				queue.enqueue(x.key);;
			}
		}
		return queue;
		
	}
	
	public void put(Key k)
	{
		Node1<Key>node= new Node1<Key>(k, null);
		if(first==null){
			first = node;
			ultimo = node;
		}
		else{
			ultimo.changeNext(node);
			ultimo = node;
		}
		n++;
	}
	
	  public Key delete(Key key) 
	    {
	        first = delete(first, key);
	        return first.key;
	    }

	    // delete key in linked list beginning at Node x
	  private Node1<Key> delete(Node1<Key> x, Key key) 
	  {
	        if (x == null) return null;
	        if (key.equals(x.key)) {
	            n--;
	            return x.next;
	        }
	        x.next = delete(x.next, key);
	        return x;
	    }
	    
	
		public Iterable<Key> keys()  {
	        Queue<Key> queue = new Queue<Key>();
	        for (Node1<Key> x = first; x != null; x = x.next)
	            queue.enqueue( x.key);
	        return queue;
	    }



}
