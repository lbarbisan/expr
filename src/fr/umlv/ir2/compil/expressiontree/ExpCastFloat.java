/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for Cast in float symbol in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpCastFloat extends Exp
{

    Exp code;

    
    /**
     * @param p
     */
    public ExpCastFloat(Exp code, Token refToken)
    {
        super(refToken);
        this.code = code;
        this.type = Type.INT;
    }

    
    public void compile(VirtualMachine vm, Exp.Type type)
    {
        //TODO: A vérfier: le typeage
        code.compile(vm, Type.FLOAT);
        vm.f2I();
        
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "(" +  refToken.getText()+ code + ")";
    }
}
