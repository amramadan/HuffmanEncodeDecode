/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;


/**
 *
 * @author Amr
 * @code:  
 *  class represents huffmanNode and implements comparable to support priority queue
 *      @params:
 *          - character c 
 *          - integer frequency
 *          - left and right children (are null if leaf)
 *      @methods:
 *          - getters and setters for all attributes
 *          - constructor huffmanNode: to build node
 *          - isLeaf: to check if node is leaf
 *          - comapreTo: to compare frequencies to help create priority queue 
 */
public class HuffmanNode implements Comparable<HuffmanNode>{
    
    private char c;
    private int frequency;
    private HuffmanNode left, right;

    public HuffmanNode(char c, int frequency, HuffmanNode left, HuffmanNode right) {
        this.c = c;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    
    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

        public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    } 

    public boolean isLeaf(){
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode that) {
        return this.frequency - that.frequency;
    }

  
    
        
}
