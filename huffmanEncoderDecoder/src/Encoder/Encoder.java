/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encoder;

import java.util.HashMap;
import java.util.Map;
import Tree.HuffmanTree;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

/**
 *
 * @author Amr
 */
public class Encoder {

    HuffmanTree tree;
    StringBuilder encodedString;
    Map<Character, Integer> freq;
    FileOutputStream fs;
    FileWriter fw;
    BufferedReader br;
    File file;
    BitSet bits;
    BitSet bits2;
    byte[] data;
    byte[] data2;

    /**
     *
     * @param str
     */
    public Encoder(String filename) throws IOException {
        tree = new HuffmanTree();
        encodedString = new StringBuilder();
        freq = new HashMap<>();

        file = new File(System.getProperty("user.dir") + "\\" + filename + ".txt");
        br = new BufferedReader(new FileReader(file));

        String str = readFromFile(br);
        

        fw = new FileWriter(System.getProperty("user.dir") + "\\compressed.txt", true);

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
        
        
//        looping on frequency
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {

            char c = entry.getKey();
            fw.append(c);
            fw.append(System.getProperty("line.separator"));
            String freq = Integer.toString(entry.getValue());
            fw.append(freq);
            fw.append(System.getProperty("line.separator"));

        }
        fw.append(System.getProperty("line.separator"));
        fw.close();

        fs = new FileOutputStream(System.getProperty("user.dir") + "\\compressed.txt", true);

        String output = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            encodedString.append(tree.getCharPrefixHashMap().get(c));

            bits = fromString(tree.getCharPrefixHashMap().get(c));
           
            data = bits.toByteArray();

            //System.out.println(data);
            fs.write(data);
            bits2 = fromByteArray(data);
            
        }
        fs.close();
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

    private static BitSet fromString(String binary) {
        BitSet bitset = new BitSet(binary.length());
        int len = binary.length();
        for (int i = len - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                bitset.set(len - i - 1);
            }
        }
        return bitset;
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
    
    public static String readFromFile(BufferedReader br) throws IOException
    {
        String st;
        String str = "";
        while ((st = br.readLine()) != null) {
            str += st;

        }
        br.close();
        return str;
    }

}
