/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class test {

    public static void main(String[] args) {
//        System.out.println("anttsdjad");
//        System.out.println("a");
//        System.out.println("may con cho nay");
//        System.out.println("ha");

//        Random generator = new Random();
//        
//        for (int i = 0; i < 10; i++) {
//            int value = generator.nextInt(4) + i;
//            System.out.println(value);
//        }
        
        String characters = "ABCDEFGHIKLMMNORSTUIZXVC123456789";
        
        String randomString = "";
        
        int lenght = 4;
        
        Random ran = new Random();
        
//        int lenght = ran.nextInt(4);

        char[] text = new char[lenght];
        
        for (int i = 0; i < lenght; i++) {
            text[i] = characters.charAt(ran.nextInt(characters.length()));
        }
        
        for (int i = 0; i < text.length; i++) {
            randomString += text[i];
        }
        
        System.out.println(randomString);

        
        
        
    }
}
