package BinaryTreeProblems;


import java.util.ArrayList;
import java.util.Stack;


public class printKNodesFarInBinaryTree {
	
	static ArrayList<Node> path=new ArrayList<>();
	public static class Node {
		
		int data;
		Node left;
		Node right;
		
		Node(int data,Node left,Node right)
		{
			this.data=data;
			this.left=left;
			this.right=right;
		}
	}
	public static class Pair {
		Node node;
		int state;
		
		Pair(Node node,int state)
		{
			this.node=node;
			this.state=state;
		}
	}
	public static void main(String[] args) {
		
		Integer tree[]= {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
				
		Node root=new Node(tree[0],null,null);
		Pair rootpair=new Pair(root,1);
		Stack<Pair> st=new Stack<>();
		st.push(rootpair);
		int index=0;
		while(st.size()>0)
		{
			Pair top=st.peek();
			
			if(top.state==1)
			{
				index++;
				if(tree[index]!=null)
				{
					top.node.left=new Node(tree[index],null,null);
					Pair leftPair=new Pair(top.node.left,1);
					st.push(leftPair);
				}
				else
				{
					top.node.left=null;
				}
				top.state++;
			}
			else if(top.state==2)
			{
				index++;
				if(tree[index]!=null)
				{
					top.node.right=new Node(tree[index],null,null);
					Pair rightPair=new Pair(top.node.right,1);
					st.push(rightPair);
				}
				else
				{
					top.node.right=null;
				}
				top.state++;
			}
			else
			{
				st.pop();
			}
		}
		int k=2;
		int data=50;
		printKNodesfar(root,data,k);
		System.out.println(path);
		
	}
	private static void printKNodesfar(Node root,int data,int k) {
		nodeToRootPath(root,data);
		for(int i=0;i<path.size();i++)
		{
			printKlevelsDown(path.get(i),k-i,i==0?null:path.get(i-1));
		}
		
	}
	private static void printKlevelsDown(Node root, int k,Node block) {
		
		if(root==null || k<0 || root==block)
		{
			return;
		}
		if(k==0)
		{
			System.out.print(root.data+" ");
		}
		printKlevelsDown(root.left,k-1,block);
		printKlevelsDown(root.right,k-1,block);
		
	}
	
private static boolean nodeToRootPath(Node node, int nodeData) {
		
		
		if(node==null)
		{
			return false;
		}
		
		if(node.data==nodeData)
		{
			path.add(node);
			return true;
		}
		
		boolean foundInLeft=nodeToRootPath(node.left,nodeData);
		if(foundInLeft)
		{
			path.add(node);
			return true;
		}
		
		boolean foundInRight=nodeToRootPath(node.right,nodeData);
		if(foundInRight)
		{
			path.add(node);
			return true;
		}
		return false;
	}
}
