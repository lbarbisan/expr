/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for float in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpFloat extends Exp
{

    private Float value;
    /**
     * @param refToken
     */
    public ExpFloat(Token refToken)
    {
        super(refToken);
        value = new Float(refToken.getText());
        type = Type.FLOAT;
    }

    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        vm.loadf(value.floatValue());
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), type);
    }
    
    
    /**
     * 
     */
    public@Override String toString()
    {
        return "[FLOAT]" +  refToken.getText() + " " ;
    }
}
