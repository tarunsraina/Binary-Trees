package BinaryTreeProblems;

import java.util.Stack;

public class SumOfBinaryTree {
	
	static int sum=0;
	
	public static class Node{
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
	
	public static class Pair{
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
		int sum=sum(root);
		System.out.println("Size of a tree:"+sum);
	}

	private static int sum(Node root) {
		if(root==null)
		{
			return 0;
		}
		return sum(root.left)+sum(root.right)+root.data;

	}

}
