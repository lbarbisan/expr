/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.TypeManager;
import fr.umlv.ir2.compil.symbolstable.symbols.Local;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for boolean value in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpLet extends Exp
{
    
    Exp value;
    Exp code;
    Local local;
    
    /**
     * @param p
     */
    public ExpLet(Local local, Exp value, Exp code, Token refToken)
    {
        super(refToken);
        this.value = value;
        this.code = code;
        this.local = local;
    }
    
    public Type getType()
    {
        /* Type de la variable non définit */
    	if(local.getType()==Type.UNDEF)
        {
            local.setType(value.getType());
            return TypeManager.getComptibleType(value.getType(), code.getType());
        }
        else
            return local.getType();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Exp.Type type)
    {
        value.compile(vm, getType());
        //TODO: Compilation du let à verifier
        vm.storeT(local.getIndex());
        code.compile(vm, getType());
        applyConversion(vm, getType(), type);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "[Let]" + local + "=" + value  + " in " + code ;
    }
}
