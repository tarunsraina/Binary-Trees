package BinaryTreeProblems;


import java.util.ArrayList;
import java.util.Stack;

public class rightViewOfBinaryTree {
	
	static ArrayList<Integer> right=new ArrayList<>();

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
		
		Integer tree[]= {1,2,4,null,null,5,null,null,3,null,6,null,null};
				
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
		right.add(root.data);
		leftView(root);
		System.out.println(right);
	}
	private static void leftView(Node root) {
	
		if(root==null)
		{
			return;
		}
		if(root.right!=null)
		{
		    right.add(root.right.data);
		}
		else if(root.right==null && root.left!=null)
		{
			right.add(root.left.data);
		}
		leftView(root.right);
		
	}
	
}
