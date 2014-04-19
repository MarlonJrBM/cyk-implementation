/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cyk;


import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Marlon
 */
public class CYK {
    
    static String processWordInput(Scanner input)
    {
        String word = "";
        if (input.hasNext())
           {
               word = input.next();
           }
           System.out.println("Word to be checked: " + word);
           return word;
    }
    
    static GrammarMap processRulesInput(Scanner input)
    {
        Integer numRules = 0;
        String c = null;
        String derivation = "";
        GrammarMap map = new GrammarMap();
        
        try
        {
           
           if (input.hasNext())
           {
               numRules = input.nextInt();
           }
           System.out.println("Number of rules: " + numRules.toString());
           for (Integer ii=0; ii<numRules; ii++)
           {
               if (input.hasNext())
               {
                   c = input.next();
               }
               
               input.next(); // skips the ->  
               derivation = input.next();
               if (!Character.isLowerCase(derivation.charAt(0)))
                   //if it is a variable, we need to read the other one
                   //otherwise we need to read two variables
               {
                   derivation = derivation.concat(input.next());
               }
               
               map.addRule(c, derivation);
               System.out.println("Mapped " + c + " to " + derivation);
           }
           
        } catch(Exception e)
        {
            System.out.println("An exception while processing IO occurred. Please verify that the input and"
                    + " output files are being passed as arguments.\n Ex: ./program input.txt output.txt");
            System.exit(1);
        }
        finally {
            if (input != null) {
                input.close();
            }
        }
           
                
        return map;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String word = "";
        GrammarMap grammarRules;
        PrintWriter outputStream = null;
        Scanner inputStream = null;
        
        try
        {
            inputStream = new Scanner(new BufferedReader(new FileReader(args[0])));
            word = processWordInput(inputStream);
            grammarRules = processRulesInput(inputStream);
            ContextFreeGrammar grammar = new ContextFreeGrammar(grammarRules);
            outputStream = new PrintWriter(new FileWriter(args[1]));
            if (grammar.recognizeWord(word))
            {
                outputStream.println("SIM");
            }
            else
            {
                outputStream.println("NAO");
            }
            
            
        }catch (IOException e)
        {
            System.out.println("An exception while processing IO occurred. Please verify that the input and"
                    + " output files are being passed as arguments.\n Ex: ./program input.txt output.txt");
            System.exit(2);
            
        } finally
        {
            if (outputStream != null)
            {
                outputStream.close();
            }
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
        
        
        
        
        
    }
    
    
    
    
}
