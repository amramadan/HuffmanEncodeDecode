/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanMain;

import Encoder.Encoder;
import Decoder.Decoder;

/**
 *
 * @author Amr
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Encoder encode;
        Decoder decode;
        
        String original = "Amr Ramadan";
        System.out.println("Original Text = " + original);
        encode = new Encoder(original);
        decode = new Decoder(encode.getEncodedString().toString(), encode.getTree());
    }

}
