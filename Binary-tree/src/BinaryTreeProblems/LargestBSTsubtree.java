package BinaryTreeProblems;


import java.util.Stack;

public class LargestBSTsubtree {
	
	public static class BSTPair{
		
		boolean isBST;
		int min;
		int max;
		Node root;
		int size;
	}

	static int max=Integer.MIN_VALUE;
	static int min=Integer.MAX_VALUE;
	
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
		
		BSTPair ans=largestBSTsubtree(root);
		
		System.out.println("Largest BST subtree node is :"+ans.root.data);
		System.out.println("Size of this subtree is :"+ans.size);
	

	}
	private static BSTPair largestBSTsubtree(Node root)
	{
		if(root==null)
		{
			BSTPair bp=new BSTPair();
			bp.min=Integer.MAX_VALUE;
			bp.max=Integer.MIN_VALUE;
			bp.isBST=true;
			return bp;
		}
		
		BSTPair lp=largestBSTsubtree(root.left);
		BSTPair rp=largestBSTsubtree(root.right);
		
		BSTPair myPair=new BSTPair();
		myPair.isBST=lp.isBST && rp.isBST && (root.data>lp.max && root.data<rp.min);
		myPair.min=Math.min(root.data,Math.min(lp.min,rp.min));
		myPair.max=Math.max(root.data,Math.max(lp.max,rp.max));
		
		if(myPair.isBST)
		{
			myPair.root=root;
			myPair.size=lp.size+rp.size+1;
		}
		else if(lp.size > rp.size)
		{
			myPair.root=lp.root;
			myPair.size=lp.size;
		}
		else
		{
			myPair.root=rp.root;
			myPair.size=rp.size;
		}
		
		return myPair;
		
	}


}
