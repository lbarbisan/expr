/*
 * Created on 24 janv. 2005
 */
package fr.umlv.ir2.compil.program;

/**
 * Use to define type of a class.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public interface Typable
{
    public static enum Type
    {
    INT,
    FLOAT,
    BOOL,
    TEXT,
    UNDEF
    }

    public Type getType();
    
}
