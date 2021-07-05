package BinaryTreeProblems;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class PathToAllLeavesFromRootInBinaryTree {
	
	static ArrayList<Node> leaves=new ArrayList<>();
	static ArrayList<Integer> path=new ArrayList<>();
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
		
		pathToAllLEavesFromRoot(root);
		
	}
	private static void pathToAllLEavesFromRoot(Node root) {
		
		getAllLeaves(root);
		
		for(int i=0;i<leaves.size();i++)
		{
			nodeToRootPath(root,leaves.get(i).data);
			Collections.reverse(path);
			System.out.println(path);
			path=new ArrayList<>();
		}
	
	}
	private static void nodeToRootPath(Node root, int nodeData) {
		
		findNode(root,nodeData);
		
	}
	private static boolean findNode(Node node, int nodeData) {
		
		if(node==null)
		{
			return false;
		}
		if(node.data==nodeData)
		{
			path.add(node.data);
			return true;
		}
		
		boolean foundInLeft=findNode(node.left,nodeData);
		if(foundInLeft)
		{
			path.add(node.data);
			return true;
		}
		
		boolean foundInRight=findNode(node.right,nodeData);
		if(foundInRight)
		{
			path.add(node.data);
			return true;
		}
		
		return false;
		
	}
	private static void getAllLeaves(Node root) {
		
		if(root==null)
		{
			return;
		}
		
		if(root.left==null && root.right==null)
		{
			leaves.add(root);
		}
		
		getAllLeaves(root.left);
		getAllLeaves(root.right);
		
	}

}
