/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cyk;

/**
 *
 * @author Marlon
 */
public interface ContextFreeGrammarInterface {
    
    public boolean isTerminal(Character c);
    
    public boolean recognizeWord(CharSequence w);
    
    public boolean hasDerivation(Character a1, CharSequence a2);
    
    
    
    
    
    
    
    
}
