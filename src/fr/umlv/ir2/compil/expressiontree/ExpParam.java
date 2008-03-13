/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.symbolstable.symbols.Param;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for Param symbol in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpParam extends Exp
{
    
    private Param param;

    /**
     * @param refToken Reference token
     * @param numParam number of parameter in function
     */
    public ExpParam(Param param, Token refToken)
    {
        super(refToken);
        this.param = param;
    }

    
    public@Override Type getType()
    {
        return param.getType();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        vm.loadP(param.getIndex());
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), type);

    }
    

    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "[Param:" + getType() + "]" + refToken.getText();
    }

}
