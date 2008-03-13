/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import java.util.LinkedList;

import fr.umlv.ir2.compil.languageparser.node.Token;
import static fr.umlv.ir2.compil.program.Typable.Type.BOOL;
import static fr.umlv.ir2.compil.program.Typable.Type.FLOAT;
import static fr.umlv.ir2.compil.program.Typable.Type.INT;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for Bin symbol in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpPrint extends Exp
{

    private LinkedList<Exp> toPrint = new LinkedList<Exp>();
    
    /**
     * @param p
     */
    public ExpPrint(Token refToken)
    {
        super(refToken);
    }

    public@Override Type getType()
    {
        return Type.TEXT;
    }
    
    public void addExpToPrint(Exp exp)
    {
        
        toPrint.addFirst(exp);
    }
    
    public void compile(VirtualMachine vm, Type type)
    {
        for(Exp exp : toPrint)
        {
            
            exp.compile(vm, exp.getType());
            switch(exp.getType())
            {
            case BOOL:
            	vm.ppB();
                break;
            case INT:
                vm.ppI();
                break;
            case FLOAT:
                vm.ppF();
                break;            
            }
        }
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "[Constant:" + getType() + "]" + refToken.getText();
    }

}
