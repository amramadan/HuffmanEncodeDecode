/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Decoder;

import Tree.HuffmanNode;
import Tree.HuffmanTree;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Amr
 */
public class Decoder {

    static ArrayList<Character> chars;
    static ArrayList<Integer> prefix;
    Map<Character, Integer> freq;
    BufferedReader reader;
    File file;
    HuffmanTree tree;
    FileInputStream in;
    
    public Decoder(String fileName) throws FileNotFoundException, IOException {

        chars = new ArrayList<>();
        prefix = new ArrayList<>();
        freq = new HashMap<>();
        file = new File(System.getProperty("user.dir") + "\\"+fileName+".txt");
        reader = new BufferedReader(new FileReader(file));
        tree = new HuffmanTree();
        
        int count;
        
        readTree(reader);
        
        
        buildFreq(chars,prefix ,freq);
        tree.setRoot(tree.buildTree(freq));
        tree.setPrefixCodes(tree.getRoot(), new StringBuilder());
        
        String toBeDecoded;
         toBeDecoded = readFile(reader);
        
        
        StringBuilder stringBuilder = new StringBuilder();

        HuffmanNode temp = tree.getRoot();
        

        //System.out.println("Encoded: " + encoded);
        decodeFile(toBeDecoded, temp, stringBuilder, tree);
        
        
        System.out.println("Decoded string is " + stringBuilder.toString());
    }

    public static BitSet fromByteArray(byte[] bytes) {
        BitSet bits = new BitSet();
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[bytes.length - i / 8 - 1] & (1 << (i % 8))) > 0) {
                bits.set(i);
            }
        }
        return bits;
    }

    public static void readTree(BufferedReader reader) throws IOException {
        int lineCount = 0;
        String line;
        while (true) {
            line = reader.readLine();
            if (line.length() > 0) {

                
                    chars.add(line.charAt(0));
                    line = reader.readLine();
                    prefix.add(Integer.parseInt(line));
                    lineCount+=2;
                    
                

            } else {
                break;
            }

        }
        
    }
    
 
    public static String readFile(BufferedReader reader) throws IOException
    {
            String line;
            String output="";
            String s1;
            String encodedString="";
            while ((line = reader.readLine()) != null) {
                output +=line;
              }
            byte []arr = output.getBytes();
            
            
            for(int i=0 ;i<arr.length;i++)
            {
                 s1 = String.format("%8s", Integer.toBinaryString(arr[i] & 0xFF)).replace(' ', '0');
                 encodedString += s1;
                 System.out.println(s1);
            }
            return encodedString;
    }

    
    public static void buildFreq(ArrayList<Character> chars, ArrayList<Integer> prefix , Map<Character, Integer> freq)
    {
        
        for(int i=0;i<chars.size();i++)
        {
            freq.put(chars.get(i), prefix.get(i));
        }
    }
    
    public static void decodeFile(String encoded , HuffmanNode temp , StringBuilder stringBuilder , HuffmanTree encoderTree ) {
        for (int i = 0; i < encoded.length(); i++) {
            int j = Integer.parseInt(String.valueOf(encoded.charAt(i)));

            if (j == 1) {
                temp = temp.getLeft();
                if (temp.isLeaf()) {
                    stringBuilder.append(temp.getC());
                    temp = encoderTree.getRoot();
                }
            }
            if (j == 0) {
                temp = temp.getRight();
                if (temp.isLeaf()) {
                    stringBuilder.append(temp.getC());
                    temp = encoderTree.getRoot();
                }
            }
        }
    }

}





	
