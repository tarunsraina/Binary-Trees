package BinaryTreeProblems;


import java.util.Stack;

public class RemoveLeavesFromBinaryTree {
	
	
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
		System.out.println("Before removing leaves:");
		Display(root);
		removeLeaves(root);
		System.out.println("After removing leaves:");
		Display(root);
	}
	private static void Display(Node node) {
		
		if(node==null)
		{
			return;
		}
		
		String str="";
		str+=node.left==null?".":node.left.data+"";
		str+="<-"+node.data+"->";
		str+=node.right==null?".":node.right.data+"";
		
		System.out.println(str);
		
		Display(node.left);
		Display(node.right);
		
	}
	private static Node removeLeaves(Node root) {
		
		if(root==null)
		{
			return null;
		}
		
		if(root.left==null && root.right==null)
		{
			return null;
		}
		
		root.left=removeLeaves(root.left);
		root.right=removeLeaves(root.right);
		return root;
		
	}


}
