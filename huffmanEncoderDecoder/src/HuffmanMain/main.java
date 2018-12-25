/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanMain;

import Encoder.Encoder;
import Decoder.Decoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Amr
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Encoder encode;
        Decoder decode;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter file name :");
         String fileName = input.next();
         
         
       
        
        encode = new Encoder(fileName);
//        /decode = new Decoder(text);
    }

}
