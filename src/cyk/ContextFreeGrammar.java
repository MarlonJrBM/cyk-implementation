/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cyk;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author Marlon
 */
public class ContextFreeGrammar implements ContextFreeGrammarInterface {
    
    private final GrammarMap rules;
    
    public ContextFreeGrammar(GrammarMap rules)
    {
        this.rules = rules;
    }
    
    @Override
    public boolean isTerminal(Character c)
    {
        return (Character.isLowerCase(c));
    }
    
    @Override
    public boolean hasDerivation(Character a1, CharSequence a2)
    {
        return (rules.hasRule(a1, a2));
    }
    
   
    @Override
    public boolean recognizeWord(CharSequence w)
    {
        return false;
    }
    
    
    
    
}


class GrammarMap extends TreeMap<Character,List<CharSequence>>
{
    
    
    public void addRule(Character variable, CharSequence derivation)
    {
        ArrayList<CharSequence> list = new ArrayList();
        if (this.containsKey(variable))
        {
            list.addAll(this.get(variable));
            list.add(derivation);
            this.replace(variable, list);
        }
        else
        {
            list.add(derivation);
            this.put(variable, list);
        }
    }
    
    public boolean hasRule(Character a1, CharSequence a2)
    {
        
        ArrayList<CharSequence> list = (ArrayList) this.get(a1);
        
        if (list!=null)
        {
            if (list.contains(a2))
                return true;
        }
        return false;
        
    }
    
}