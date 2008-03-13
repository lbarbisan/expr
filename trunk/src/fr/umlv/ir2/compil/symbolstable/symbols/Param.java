/*
 * Created on 13 janv. 2005
 */
package fr.umlv.ir2.compil.symbolstable.symbols;

import fr.umlv.ir2.compil.program.Typable;

/**
 * Represent a parameter declaration
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class Param implements Typable
{
    private String name;
    private Type type = Type.UNDEF;
    private int index;
    
    
    public Param(String name, int index,  Type type)
    {
        this.name = name;
        this.index = index;
        
        if(type!=null)
        {
            this.type = type;
        }
         
    }
    
    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }
    
    
    /**
     * @return Returns the index.
     */
    public int getIndex()
    {
        return index;
    }
    
    
    /**
     * @return Returns the type.
     */
    public Type getType()
    {
        return type;
    }
    
    /**
     * @return Returns the type.
     */
  /*  public void  setType(Type type)
    {
        this.type = type;
    }*/
    
    
   
    /**
     * Show Parameter
     */
    public@Override String toString()
    {
        return "[Param:" + type + "]" + name + " ";  
    }

    
}
