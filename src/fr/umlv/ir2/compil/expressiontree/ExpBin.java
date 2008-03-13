/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import static fr.umlv.ir2.compil.expressiontree.ExpBin.Operator.ADD;
import static fr.umlv.ir2.compil.expressiontree.ExpBin.Operator.AND;
import static fr.umlv.ir2.compil.expressiontree.ExpBin.Operator.CONCAT;
import static fr.umlv.ir2.compil.expressiontree.ExpBin.Operator.DIV;
import static fr.umlv.ir2.compil.expressiontree.ExpBin.Operator.MULT;
import static fr.umlv.ir2.compil.expressiontree.ExpBin.Operator.OR;
import static fr.umlv.ir2.compil.expressiontree.ExpBin.Operator.SUB;
import static fr.umlv.ir2.compil.expressiontree.ExpBin.Operator.XOR;
import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.ErrorsManager;
import fr.umlv.ir2.compil.program.TypeManager;
import static fr.umlv.ir2.compil.program.Typable.Type.BOOL;
import static fr.umlv.ir2.compil.program.Typable.Type.FLOAT;
import static fr.umlv.ir2.compil.program.Typable.Type.INT;
import fr.umlv.ir2.compil.virtualmachine.CompileException;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;
import fr.umlv.ir2.compil.virtualmachine.CompileException.ErrorType;

/**
 * Node for binary operation in expression tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpBin extends Exp
{
    
    private Exp leftExp;
    private Exp rightExp;
    private Operator operator;
    
    /**
     * Enum to represent binary operation
     * @author Laurent Barbisan
     */
    public static enum Operator
    {
        ADD,
        CONCAT,
        SUB,
        DIV,
        MULT,
        AND,
        OR,
        XOR,
        MOD
    }
    
    /**
     * Construct a new binary expression for expression tree
     * @param p
     */
    //UPDATE: Mise en place de toutes les Pre-conditions
    public ExpBin(Exp rightExp ,ExpBin.Operator operator,Exp leftExp, Token refToken)
    {
        super(refToken);
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        this.operator = operator;
    }
    
    
    public Type getType()
    {
    	if(this.type==Type.UNDEF)
        {
            if(operator==Operator.AND || operator==Operator.OR ||operator==Operator.XOR  )
            {
                this.type = Type.BOOL;
            }
            else
            {
                if(operator!=Operator.CONCAT)
                {
                    this.type = TypeManager.getComptibleType(leftExp.getType(), rightExp.getType());
                if(this.type==Type.BOOL)
                {
                    this.type=Type.FLOAT;
                }
                if(this.type==Type.UNDEF)
                    this.type = TypeManager.getDefaultType();
                }
                else
                {
                    this.type=Type.TEXT;
                }
            }
        }
        return  this.type;
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Exp.Type type)
    {
        rightExp.compile(vm, getType());
        leftExp.compile(vm, getType());
        switch (getType())
        {
        case FLOAT :
            switch(operator)
            {
            case ADD: 
                vm.fAdd();
                break;
            case SUB: 
                vm.fSub();
                break;
            case MULT: 
                vm.fMult();
                break;
            case DIV: 
                vm.fDiv();
                break;
            case MOD:
                vm.mod();
                break;
            case CONCAT:
                vm.ppF();
            }
            break;
        case INT :
            switch(operator)
            {
            case ADD: 
                vm.iAdd();
                break;
            case SUB: 
                vm.iSub();
                break;
            case MULT: 
                vm.iMult();
                break;
            case DIV: 
                vm.iDiv();
                break;
            case MOD:
                vm.mod();
                break;
            case CONCAT:
                vm.ppI();
            }
            break;
        case BOOL:
            switch(operator)
            {
            case AND: 
                vm.and();
                break;
            case OR: 
                vm.or();
                break;
            case XOR: 
                vm.xor();
                break;
            }
        }  
        //Check and apply if conversion possible from the class type and the parameter type
        this.applyConversion(vm, getType(), type);
    }
    
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public@Override String toString()
    {
        return leftExp + " " + operator + " " + rightExp;
    }
}
