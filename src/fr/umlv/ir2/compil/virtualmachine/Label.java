/*
 * Created on 24 janv. 2005
 */
package fr.umlv.ir2.compil.virtualmachine;

/**
 * This class is use represent a label in VirtualMachine.
 * This class must be created with Factory VirtualMachine.generateLabel()
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class Label
{
    private String prefix;
    private long Number;
    
    /**
     * Create an new label with name prefixNumber
     * @param prefix prefix to use
     * @param Number number to use
     */
    public Label(String prefix , long Number)
    {
        this.prefix = prefix;
        this.Number = Number;
    }
    
    /**
     * Return String representation
     */
    public@Override String toString()
    {
        return prefix + Number;
    }
    
}
