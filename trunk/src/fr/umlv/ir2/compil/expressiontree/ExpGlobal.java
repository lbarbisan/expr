/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.symbolstable.symbols.Global;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for Global symbol in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpGlobal extends Exp
{

    Global global;
   /**
    * Constructor.
    * @param p refrence token
    * @param initCode initialisation expression for the gloabl variable
    */
    public ExpGlobal(Global global, Token refToken)
    {
        super(refToken);
        this.global = global;
    }

    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        vm.loadG(global.getLabel());
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), type);

    }
    
    /**
     * Return type of global
     */
    public@Override Type getType()
    {
        return global.getType();
    }
    /**
     * Show Gloabl
     */
    public@Override String toString()
    {
        return "[Global:" + getType() + "]" + refToken.getText();
    }

}
