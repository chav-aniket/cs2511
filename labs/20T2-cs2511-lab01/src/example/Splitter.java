package example;

import java.util.Scanner;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner sc = new Scanner(System.in);

        String []in = sc.nextLine().split(" ");
        for (int i = 0; i < in.length; i++) {
            System.out.println(in[i]);
        }
        sc.close();
    }
}
