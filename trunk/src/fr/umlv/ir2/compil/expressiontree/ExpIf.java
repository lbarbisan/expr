/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.TypeManager;
import fr.umlv.ir2.compil.virtualmachine.Label;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for Bin symbol in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpIf extends Exp
{

    Exp condition;
    Exp resultTrue;
    Exp resultFalse;
    
    /**
     * 
     * @param condition
     * @param resultTrue
     * @param ResultFalse
     * @param p
     */
    public ExpIf(Exp condition, Exp resultTrue, Exp resultFalse,  Token p)
    {
        super(p);
        this.condition = condition;
        this.resultTrue = resultTrue;
        this.resultFalse = resultFalse;
    }

    
    public@Override Type getType()
    {
    	if(this.type==Type.UNDEF)
        {
         
                this.type = TypeManager.getComptibleType(resultTrue.getType(), resultFalse.getType());
                if(this.type==Type.UNDEF)
                    this.type = TypeManager.getDefaultType();
        }
    	return this.type;
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Exp.Type type)
    {
        Label LTrue;
        Label LFalse;
        Label  LNext;
        
        LTrue = vm.generateLabel("LTrue");
        LFalse = vm.generateLabel("LFalse");
        LNext = vm.generateLabel("LNext");
        
        condition.compile(vm, condition.getType());
        vm.branch(LTrue, LFalse);
        vm.label(LTrue);
        resultTrue.compile(vm, type);
        vm.jump(LNext);
        vm.label(LFalse);
        resultFalse.compile(vm, type);
        vm.label(LNext);
        this.applyConversion(vm, getType(), type);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "if (" + condition + ")" + "then {" + resultTrue  + "else {" + resultFalse  + "}" ;
    }
    
}

