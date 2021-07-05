package BinaryTreeProblems;


import java.util.Stack;

public class DiameterOfBinaryTree {
	
	
	public static class DiaPair {
		
		int height;
		int diameter;
	}

	

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
		System.out.println("Naive approch:");
		System.out.println("Diameter:"+getDiameter(root));
		System.out.println("Efficient approch:");
		DiaPair ans=diameter2(root);
		System.out.println("Diameter:"+ans.diameter);
		
	}
	private static DiaPair diameter2(Node root) {
		
		if(root==null)
		{
			DiaPair basePair=new DiaPair();
			basePair.height=-1;
			basePair.diameter=0;
			return basePair;
		}
		
		DiaPair leftPair=diameter2(root.left);
		DiaPair rightPair=diameter2(root.right);
		DiaPair myPair=new DiaPair();
		
		myPair.height=Math.max(leftPair.height,rightPair.height)+1;
		int candidate=leftPair.height+rightPair.height+2;
		myPair.diameter=Math.max(candidate,Math.max(leftPair.diameter,rightPair.diameter));
		
		
		return myPair;
		
		
		
	}
	private static int getDiameter(Node root) {
		
		if(root==null)
		{
			return 0;
		}
		//if max edge path exist in left of root (excludes root node)
		int leftDiameter=getDiameter(root.left);
		//if max edge path exist in right of root  (excludes root node)
		int rightDiameter=getDiameter(root.right);
		// else max edge path exist left,right including root  (includes root node,so add 2)
		int totalHeight=height(root.left)+height(root.right)+2;
		
		int dia=Math.max(totalHeight,Math.max(leftDiameter,rightDiameter));
		
		return dia;
	}
	private static int height(Node root) {
		
		if(root==null)
		{
			return -1;
		}
		
		int leftHeight=height(root.left);
		int rightHeight=height(root.right);
		
		if(leftHeight>=rightHeight)
		{
			return leftHeight+1;
		}
		else
		{
			return rightHeight+1;
		}
	}


}
