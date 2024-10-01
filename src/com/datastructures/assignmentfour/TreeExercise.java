
package com.datastructures.assignmentfour;

//import apple.laf.JRSUIUtils;

import java.util.List;
import java.util.Scanner;

/**
    Daniel Shafer
    Lowest Common Ancestor
 */
public class TreeExercise 
{
    public static void main(String[] args)
    {
        String[] myStringChars = new String[26];
        TreeNode[] myNodes = new TreeNode[26];

        for(int i = 0; i < 26; i++)
        {
            myStringChars[i] = new String(Character.toChars(i+65));
            //System.out.println(myStringChars[i]);
        }
        
        // create the Tree as a linked structure from the array myStringChars
        // the Strings are stored using the representation for trees as arrays in the book
        // (e.g. for an element i, the left child is stored in position 2*i + 1, right child is 
        // on position 2*(i + 1). Also specify the level of a TreeNode

        for(int i = 0; i < 26; i++) {
            TreeNode root = new TreeNode();
            root.setContents(myStringChars[i]);
            myNodes[i] = root;
        }
        for(int i = 0; i < 26; i++){
            if(i == 0){
                myNodes[i].setParent(null);
                myNodes[i].setLevel(1);
            }else{
                int parent = (i - 1) / 2;
                myNodes[i].setParent(myNodes[parent]);
            }
            int leftChild = (2 * i) + 1;
            if(leftChild < 26) {
                myNodes[i].setLeftChild(myNodes[leftChild]);
            }
            int rightChild = 2 * (i + 1);
            if (rightChild < 26){
                myNodes[i].setRightChild(myNodes[rightChild]);
            }
            if(i < 4){
                myNodes[i].setLevel(2);
            }else if(i < 8){
                myNodes[i].setLevel(3);
            }else if(i < 16){
                myNodes[i].setLevel(4);
            }
        }

        // create a traversal by levels and print as you traverse to check that the creation of the tree has happened correctly
        TreeNode root = myNodes[0];
        List<String> treeTraversal = root.levelOrder(myNodes[0]);
        System.out.println("Full Tree: " + treeTraversal);

        // create the code that asks the user for two letters in uppercase and searches for the nodes in the tree that contain
        // such letters
        System.out.println("Please enter the first letter (Uppercase Letters Only):");
        Scanner userInput1 = new Scanner(System.in);
        String firstLetter = userInput1.nextLine();
        TreeNode firstNode = root.findNodeOnTree(firstLetter);
        System.out.println("\nPlease enter the second letter (Uppercase Letters Only):");
        Scanner userInput2 = new Scanner(System.in);
        String secondLetter = userInput2.nextLine();
        TreeNode secondNode = root.findNodeOnTree(secondLetter);
        
        // create the code that calls the static method below and finds the lowest common ancestor of two TreeNodes
        TreeNode commonAncestor = findLowestCommonAncestor(root, firstNode, secondNode);
        
        if(commonAncestor != null)
        {
            System.out.println(commonAncestor.getContents());
        }
    }   
    
    public static TreeNode findLowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2)
    {
        // Given two nodes on the same tree, this method should return the lowest common ancestor.
        // if no common ancestor is found, this method returns null .
        if(root == null){
            return null;
        }
        if(root.equals(node1) || root.equals(node2)){
            return root;
        }
        TreeNode leftNode = findLowestCommonAncestor(root.getLeftChild(), node1, node2);
        TreeNode rightNode = findLowestCommonAncestor(root.getRightChild(), node1, node2);
        if(leftNode != null && rightNode != null){
            return root;
        }else if(leftNode == null && rightNode == null){
            return null;
        }else if(leftNode != null){
            return leftNode;
        }else if(rightNode != null){
            return rightNode;
        }
        return null;
    }
}
