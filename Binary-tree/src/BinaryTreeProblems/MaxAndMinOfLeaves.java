package BinaryTreeProblems;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MaxAndMinOfLeaves {
	
	/*
	 * 
	 * 
	 * approach 1:one approch will be to store leaves in arraylist and sort it and return the max and min
	 * 
	 * 
	 * approac 2:We can just compare leaf nodes instead of sort technique,this will improve time complexity
	 * 				since sort is not applied and we just compare using if statements,this will improve
	 * 				time complexity.
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	static int max=Integer.MIN_VALUE;
	static int min=Integer.MAX_VALUE;
	
	static ArrayList<Integer> leaves=new ArrayList<>();
	
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
		
		findMaxAndMinOfLeaves(root);
		System.out.println("Approch 2:");
		System.out.println("Max leaf:"+max);
		System.out.println("Min leaf:"+min);
		
		
		findMaxAndMinOfLeaves2(root);
		System.out.println("Approch 1:");
		Collections.sort(leaves);
		System.out.println("Max leaf:"+leaves.get(leaves.size()-1));
		System.out.println("Min leaf:"+leaves.get(0));
		
		
	}
	private static void findMaxAndMinOfLeaves2(Node root) {
		
		if(root==null)
		{
			return;
		}
		
		if(root.left==null && root.right==null)
		{
			leaves.add(root.data);
		}
		
		findMaxAndMinOfLeaves2(root.left);
		findMaxAndMinOfLeaves2(root.right);
		
	}
	private static void findMaxAndMinOfLeaves(Node root) {
		
		if(root==null)
		{
			return;
		}
		
		if(root.left==null && root.right==null)
		{
			if(root.data>max)
			{
				max=root.data;
			}
			
			if(root.data<min)
			{
				min=root.data;
			}
	
		}
		
		findMaxAndMinOfLeaves(root.left);
		findMaxAndMinOfLeaves(root.right);
	}


}
