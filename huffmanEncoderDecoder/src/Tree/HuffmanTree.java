/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author Amr
 * @code:
 *  class represents huffmanTree
 *      @params:
 *          - HuffmanNode root
 *      @methods:
 *          - constructor huffmanNode: to build node
 *          - buildTree: constructs Huffman tree
 *              - @params: Map
 *
 */
public class HuffmanTree {

    private static HuffmanNode root;
    private static Map<Character, String> charPrefixHashMap;

    public HuffmanTree() {
        root = new HuffmanNode('-', 0, null, null);
        charPrefixHashMap = new HashMap<>();
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void setRoot(HuffmanNode root) {
        HuffmanTree.root = root;
    }
    
    public Map<Character, String> getCharPrefixHashMap() {
        return charPrefixHashMap;
    }
    
    public HuffmanNode buildTree(Map<Character, Integer> freq) {

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = freq.keySet();

        for (Character c : keySet) {
            HuffmanNode huffmanNode = new HuffmanNode(c, freq.get(c), null, null);
            priorityQueue.offer(huffmanNode);
        }

        while (priorityQueue.size() > 1) {

            HuffmanNode x = priorityQueue.peek();
            priorityQueue.poll();

            HuffmanNode y = priorityQueue.peek();
            priorityQueue.poll();

            HuffmanNode sum = new HuffmanNode('-', x.getFrequency() + y.getFrequency(), x, y);
            root = sum;

            priorityQueue.offer(sum);
        }

        return priorityQueue.poll();
    }

    public void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {

        if (node != null) {
            if (node.isLeaf()) {
                charPrefixHashMap.put(node.getC(), prefix.toString());

            } else {
                prefix.append('0');
                setPrefixCodes(node.getLeft(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setPrefixCodes(node.getRight(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

    }

}
