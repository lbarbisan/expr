/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;


import static fr.umlv.ir2.compil.expressiontree.ExpUnary.UnaryOperator.NOT;
import static fr.umlv.ir2.compil.expressiontree.ExpUnary.UnaryOperator.SUB;
import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.TypeManager;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for Bin symbol in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpUnary extends Exp
{

    public static enum UnaryOperator
    {
        ADD,
        SUB,
        NOT
    }
    
    Exp code;
    
    private UnaryOperator unaryType;

    /**
     * Create an Unary Expression
     * @param unaryType
     * @param code
     * @param refToken
     */
    public ExpUnary(UnaryOperator unaryType, Exp code, Token refToken)
    {
        super(refToken);
        this.code = code;
        this.unaryType = unaryType;
    }

    
    public@Override Type getType()
    {
        if(unaryType==UnaryOperator.NOT)
            return Type.BOOL;
        else
            return code.getType();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        code.compile(vm, type);
        switch(unaryType)
        {
        case NOT:
            vm.not();
            break;
        case SUB:
            if(type==Type.FLOAT)
            {
                vm.loadf(-1);
                vm.fMult();
            }
            else
            {
            vm.neg();
            }
            break;
        }
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), TypeManager.getComptibleType(getType(), type));

        
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "(" +  refToken.getText()+ code + ")";
    }
}
