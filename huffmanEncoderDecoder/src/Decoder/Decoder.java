/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Decoder;

import Tree.HuffmanNode;
import Tree.HuffmanTree;

/**
 *
 * @author Amr
 */
public class Decoder {

    public Decoder(String encoded, HuffmanTree encoderTree) {

        StringBuilder stringBuilder = new StringBuilder();

        HuffmanNode temp = encoderTree.getRoot();

        System.out.println("Encoded: " + encoded);

        for (int i = 0; i < encoded.length(); i++) {
            int j = Integer.parseInt(String.valueOf(encoded.charAt(i)));

            if (j == 0) {
                temp = temp.getLeft();
                if (temp.isLeaf()) {
                    stringBuilder.append(temp.getC());
                    temp = encoderTree.getRoot();
                }
            }
            if (j == 1) {
                temp = temp.getRight();
                if (temp.isLeaf()) {
                    stringBuilder.append(temp.getC());
                    temp = encoderTree.getRoot();
                }
            }
        }

        System.out.println("Decoded string is " + stringBuilder.toString());

    }

}
