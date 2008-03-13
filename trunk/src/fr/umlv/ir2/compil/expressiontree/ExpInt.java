/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for Bin symbol in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpInt extends Exp
{

    private Integer value;
    /**
     * @param p
     */
    public ExpInt(Token refToken)
    {
        super(refToken);
        value = new Integer(refToken.getText());
        type = Type.INT;
    }

    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "[" + type + "]" + refToken.getText();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Exp.Type type)
    {
        vm.loadi(value.intValue());
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), type);

    }
}
