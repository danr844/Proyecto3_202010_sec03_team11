package model.data_structures;

public class UF { 
	private int[] id; // id[i] = parent of i 
	private byte[] rank; // rank[i] = rank of subtree
	private int count; // number of components /** *
	public UF(int n) 
	{ 
		count = n;
		id= new int[n]; 
		rank = new byte[n];
		for (int i = 0; i < n; i++)
		{ 
			id[i] = i; rank[i] = 0; 
			}
		} 
	public boolean connected(int p, int q) 
	{
return find(p) == find(q);
} 
	

public int find(int p) 
{ 
	while (p != id[p])
	{ 
		id[p] = id[id[p]]; // path compression
		p = id[p];
		} 
	return p; 
	} 
public void union(int p,int q)
{ 
	int rootP=find(p); 
	int rootQ =find(q);
	if (rootP== rootQ) return;  
	if (rank[rootP] < rank[rootQ])
		id[rootP] = rootQ;
	else if (rank[rootP] > rank[rootQ]) 
		id[rootQ] = rootP; 
	else 
	{
		id[rootQ] = rootP;
		rank[rootP]++;
		} 
	count--;
	} 
	}
