/*
 * Created on 13 déc. 2004
 */
package fr.umlv.ir2.compil.symbolstable;

/**
 * Interface use to implement a symbol table.
 * This interface represent an implementatin using Hast table with linked list.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public interface Env
{

    /**
     * add a symbol to stack
     * 
     * @param symbolName name of symbol added to stack
     * @param symbol symbol added
     */
    public void push(String symbolName, Object symbol);
    
    /**
     * pop the symbol out of the stack
     * 
     * @param symbolName name of the symbol to pop 
     */
    public void pop(String symbolName);
    
    /**
     * return the upper symbol in the stack
     * 
     * @param symbolName name of the symbol to get
     * @return last symbol in the stack
     */
    public Object get(String symbolName);
}
