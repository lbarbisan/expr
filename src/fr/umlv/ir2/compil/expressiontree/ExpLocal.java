/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.symbolstable.symbols.Local;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for Local symbol in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpLocal extends Exp
{
    
    Local local;
    /**
     * @param p
     */
    public ExpLocal(Local local, Token refToken)
    {
        super(refToken);
        this.local = local;
    }
    
    
    public@Override Type getType()
    {
        return local.getType();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Exp.Type type)
    {
        vm.loadT(local.getIndex());
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), type);

    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "[local]" + refToken.getText();
    }
    
}
