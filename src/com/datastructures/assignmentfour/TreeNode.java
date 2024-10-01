package com.datastructures.assignmentfour;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 Daniel Shafer
 Lowest Common Ancestor
 */
public class TreeNode<T extends Comparable>{
    private T contents;
    private TreeNode<T> parent;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;
    private int level;
    
    public TreeNode()
    {
    
    }
    public TreeNode(T data, TreeNode parent)
    {
        contents = data;
        this.parent = parent;
    }
    public void setLeftChild(TreeNode node)
    {
        this.leftChild = node;
    }        
    
    public void setRightChild(TreeNode node)
    {
        this.rightChild = node;
    }        
    
    public boolean isContentEquals(T data)
    {
        return 0 == getContents().compareTo(data);
    }

    /**
     * @return the contents
     */
    public T getContents() {
        return contents;
    }

    /**
     * @param contents the contents to set
     */
    public void setContents(T contents) {
        this.contents = contents;
    }

    /**
     * @return the parent
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * @return the leftChild
     */
    public TreeNode getLeftChild() {
        return leftChild;
    }

    /**
     * @return the rightChild
     */
    public TreeNode getRightChild() {
        return rightChild;
    }
     /**
     * Given an object T contentToSearch, this method returns
     * the node that stores the contentToShare or null if not found on the current tree
     * @return the node
     */
    public TreeNode findNodeOnTree(T contentToSearch)
    {
        List<TreeNode> nodes = new LinkedList();
        nodes.clear();
        nodes.add(this);
        
        while(!nodes.isEmpty())
        {
            TreeNode current = nodes.remove(0);
            if(current.isContentEquals(contentToSearch))
            {
                return current;
            }
            if(current.leftChild != null)
            {
                nodes.add(current.leftChild);
            }
            if(current.rightChild != null)
            {
                nodes.add(current.rightChild);
            }    
        }
        return null;
    }        

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    public List<T> levelOrder(TreeNode root){
        List<T> result = new ArrayList();
        if(root == null){
            return result;
        }
        Queue<TreeNode> levelQueue = new LinkedList();
        levelQueue.add(root);
        int currentLevel = 1;
        while(!levelQueue.isEmpty()){
            int size = levelQueue.size();
            //List<T> currentLevel = new ArrayList();
            for(int i = 0; i < size; i++){
                TreeNode currentNode = levelQueue.remove();
                result.add((T) currentNode.contents);
                if(currentNode.leftChild != null){
                    levelQueue.add(currentNode.leftChild);
                }
                if(currentNode.rightChild != null){
                    levelQueue.add(currentNode.rightChild);
                }
            }
            if(currentLevel < 5) {
                System.out.println("Level " + currentLevel + ": " + result);
            }
            currentLevel += 1;
        }
        return result;
    }
}
