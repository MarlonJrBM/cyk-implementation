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
import java.util.Set;


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
    public boolean hasDerivation(CharSequence a1, CharSequence a2)
    {
        return (rules.hasRule(a1, a2));
    }
    
   
    @Override
    public boolean recognizeWord(CharSequence w)

    {
        ArrayList<String>[][] table;
        int length = w.length();
        table = new ArrayList[length][];
        for (int i = 0; i < length; ++i)
        {
         table[i] = new ArrayList[length];
         for (int j = 0; j < length; ++j)
            table[i][j] = new ArrayList < String > ();
        }       
        
        for (int i = 0; i < length; ++i)
      {
         Set<CharSequence> keys = rules.keySet();
         for (CharSequence key : keys)
         {
            if (this.hasDerivation(key, w.subSequence(i, i+1)))
               table[i][i].add((String)key);
         }  
      }
        
      for (int l = 2; l <= length; ++l)
      {
         for (int i = 0; i <= length - l; ++i)
         {
            int j = i + l - 1;
            for (int k = i; k <= j - 1; ++k)
            {
               Set<CharSequence> keys = rules.keySet();
               for (CharSequence key : keys)
               {
                  List<CharSequence> stringValues = rules.get(key);
                  for (CharSequence s : stringValues)
                  {
                      if (s.length()>1) //it is not a terminal
                      {
                          if (table[i][k].contains(s.subSequence(0, 1))
                                  && table[k+1][j].contains(s.subSequence(1, 2)))
                          {
                              table[i][j].add((String)key);
                          }

                      }
                  }

               }
            }
         }
      } 
      
      if (table[0][length - 1].contains("S")) // we started from 0
         return true;
      return false;
        
      
    }
    
    
    
    
}


class GrammarMap extends TreeMap<CharSequence,List<CharSequence>>
{
    
    
    public void addRule(CharSequence variable, CharSequence derivation)
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
    
    public boolean hasRule(CharSequence a1, CharSequence a2)
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