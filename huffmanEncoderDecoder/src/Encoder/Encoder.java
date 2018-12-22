/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encoder;

import java.util.HashMap;
import java.util.Map;
import Tree.HuffmanTree;

/**
 *
 * @author Amr
 */
public class Encoder {
    HuffmanTree tree;
    StringBuilder encodedString;
    Map<Character, Integer> freq;

    /**
     *
     * @param str
     */
    public Encoder(String str) {
        tree = new HuffmanTree();
        encodedString = new StringBuilder();
        freq = new HashMap<>();
        
        for (int i = 0; i < str.length(); i++) {
            if (!freq.containsKey(str.charAt(i))) {
                freq.put(str.charAt(i), 0);
            }
            freq.put(str.charAt(i), freq.get(str.charAt(i)) + 1);
        }

        System.out.println("Character Frequency Map = " + freq);
        tree.setRoot(tree.buildTree(freq));

        tree.setPrefixCodes(tree.getRoot(), new StringBuilder());
        System.out.println("Character Prefix Map = " + tree.getCharPrefixHashMap());

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            encodedString.append(tree.getCharPrefixHashMap().get(c));
        }

    }
    
    public HuffmanTree getTree() {
        return tree;
    }

    public StringBuilder getEncodedString() {
        return encodedString;
    }

    public Map<Character, Integer> getFreq() {
        return freq;
    }


}
