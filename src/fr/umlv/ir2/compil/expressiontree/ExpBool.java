/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for boolean value in expression tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpBool extends Exp
{
    Boolean value;
    
    /**
     * @param p
     */
    public ExpBool(String value, Token p)
    {
        super(p);
        this.value = new Boolean(value);
        this.type = Type.BOOL;
    }
    
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        
        if(value.booleanValue()) 
            vm.loadi(1);
        else
            vm.loadi(0);
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), type);
    }
    
    
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "[boolean]" + refToken.getText() + "(" + value.toString() + ")"  ;
    }
}
