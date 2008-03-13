/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import static fr.umlv.ir2.compil.expressiontree.ExpComp.Comparator.EQUAL;
import static fr.umlv.ir2.compil.expressiontree.ExpComp.Comparator.GREATER_THAN;
import static fr.umlv.ir2.compil.expressiontree.ExpComp.Comparator.GREATER_THAN_EQUAL;
import static fr.umlv.ir2.compil.expressiontree.ExpComp.Comparator.LOWER_THAN;
import static fr.umlv.ir2.compil.expressiontree.ExpComp.Comparator.LOWER_THAN_EQUAL;
import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.TypeManager;
import static fr.umlv.ir2.compil.program.Typable.Type.FLOAT;
import static fr.umlv.ir2.compil.program.Typable.Type.INT;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for comparaison operation in expression tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpComp extends Exp
{
    
    private Exp leftExp;
    private Exp rightExp;
    private Comparator operator;
    
    /**
     * Enum to represent binary operation
     * @author Olivier Boitel
     * @author Laurent Barbisan
     */
    public static enum Comparator
    {
        GREATER_THAN,
        LOWER_THAN,
        EQUAL,
        GREATER_THAN_EQUAL,
        LOWER_THAN_EQUAL
    }
    
    /**
     * Construct a new comparaison expression for expression tree
     * @param p
     */
    //UPDATE: Mise en place de toutes les Pre-conditions
    public ExpComp(Exp rightExp , ExpComp.Comparator operator,Exp leftExp, Token refToken)
    {
        super(refToken);
        this.rightExp = rightExp;
        this.leftExp = leftExp;
        this.operator = operator;
    }
    
    public Type getType()
    {
    	if(this.type==Type.UNDEF)
        {
         
                this.type = TypeManager.getComptibleType(leftExp.getType(), rightExp.getType());
                if(this.type==Type.UNDEF)
                    this.type = TypeManager.getDefaultType();
        }
        return  this.type;
    }
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Exp.Type type)
    {
        rightExp.compile(vm, this.type);
        leftExp.compile(vm, this.type);
        switch (type)
        {
        case FLOAT :
            switch(operator)
            {
            case EQUAL:
                vm.cmpE();
                break;
            case GREATER_THAN: 
                vm.cmpGT();
                break;
            case LOWER_THAN: 
                vm.cmpLT();
                break;
            case GREATER_THAN_EQUAL: 
                vm.cmpGTE();
                break;
            case LOWER_THAN_EQUAL: 
                vm.cmpLTE();
                break;
            }
            break;
            
        case INT :
            switch(operator)
            {
            case EQUAL:
                vm.cmpE();
                break;
            case GREATER_THAN: 
                vm.cmpGT();
                break;
            case LOWER_THAN: 
                vm.cmpLT();
                break;
            case GREATER_THAN_EQUAL: 
                vm.cmpGTE();
                break;
            case LOWER_THAN_EQUAL: 
                vm.cmpLTE();
                break;
            }
            break;
            
        }  
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), type);
    }
    

    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public@Override String toString()
    {
        return leftExp + " " + operator + " " + rightExp;
    }
}
