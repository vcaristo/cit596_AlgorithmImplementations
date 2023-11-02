import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Utility class that implements a dynamic-programming solution to the word segementation problem. 
 * 
 * Instance method check(String word) takes as input a string of letters and  
 * returns TRUE if the input string is valid sequence of english words, otherwise false.
 * 
 * If check() returns TRUE, instance variable 'stringOfWords' stores the first valid sequence of 
 * english words. Otherwise, 'stringOfWords' is an empty array. 
 * 
 * Uses the standard Unix dictionary of words at /usr/share/dict/american-english.
 */

class wordSegmenter{
    
    // dictionary
    static Set<String> allWords = new HashSet<>();
    
    //instance vars
   
    // DP array
    private int[][] d;   

    // for valid strings, the sequence of words
    // Otherwise, empty 
    public ArrayList<String> stringOfWords = new ArrayList<>();

    //constructor
    public wordSegmenter() throws IOException{

        // initialize dictionary
        BufferedReader dict = new BufferedReader(new FileReader("/usr/share/dict/american-english"));

        String l;

        while((l = dict.readLine()) != null){
            // filter single letters
            if ((l.length() != 1) || l.equals("a") || l.equals("i")){
                allWords.add(l);
            }
        }
    }

    // public methods 

    /*
     * Allows user to check a word
     */
    public boolean check(String word){
        // reset string of words
        stringOfWords = new ArrayList<>();

        // initialize DP array
        int size = word.length();

        d = new int[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                d[i][j]=-1;
            }
        }
                   
        return seqCheck(word, 0, word.length()-1);
    }

    // private methods

    /* helper function that implements the recursive DP algorithm*/
    private boolean seqCheck(String s, int i, int j){

        // check complete string
        if (d[i][j] == -1){
            if (allWords.contains(s.substring(i, j+1))){
                stringOfWords.add(0,s.substring(i, j+1));   // add to seq of words
                d[i][j] = 1;
                return true;
            }
            d[i][j] = 0;
        }

        // recurse on substrings
        for (int k = i; k < j; k++){
            // check DP array 
            if (d[i][k] == -1){
                if (allWords.contains(s.substring(i, k+1))){
                    d[i][k] = 1;
                }
               else{
                    d[i][k] = 0;
               } 
            }
          
            if (d[i][k] == 1 && seqCheck(s, k+1, j)){

                stringOfWords.add(0,s.substring(i, k+1));   // add to seq of words

                return true;
            }
        }

        // getting here, s_i....s_j is not a valid sequence of words
        return false;

    }   
    public static void main (String args[]) throws IOException{
        
        wordSegmenter segmenter = new wordSegmenter();
      
        String word;

        // execute checker until user force quits
        while (1 == 1){

            // get user input
            Scanner input = new Scanner(System.in);
            System.out.print("Word: ");
            word = input.nextLine();
                      
            String result = (segmenter.check(word) == true ? "Yes":"No");
            System.out.println("Valid sequence? " + result); 
            if (result.equals("Yes")){
                System.out.print(Arrays.asList(segmenter.stringOfWords) + "\n");
            }
            System.out.println();
        }

    }
}