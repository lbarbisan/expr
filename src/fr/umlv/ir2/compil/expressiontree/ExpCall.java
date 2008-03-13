/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import java.util.LinkedList;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.TypeManager;
import fr.umlv.ir2.compil.program.Typable.Type;
import fr.umlv.ir2.compil.symbolstable.symbols.Function;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for call of function in expression tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpCall extends Exp
{

    LinkedList<Exp> params;
    private Function function;
    
    /**
     * Constructor
     * @param params list of function parameters
     * @param p reference token for line and column
     */
    public ExpCall(LinkedList<Exp> params, Function function, Token p)
    {
        super(p);
        this.params = params;
        this.function  = function;
    }

    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        for (int i = (params.size()-1); i >= 0; i--)
        {
            params.get(i).compile(vm, params.get(i).getType());
            applyConversion(vm, params.get(i).getType(), function.getParams().get(i).getType(), params.get(i).refToken);
        }
        vm.call(function.getLabel(), params.size());
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), type);
    }
    
    public Type getType()
    {
        return function.getType();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.Compilable#applyConversion(fr.umlv.ir2.compil.program.Typable.Type, fr.umlv.ir2.compil.program.Typable.Type)
     */
    public void applyConversion(VirtualMachine vm, Type from, Type to, Token refToken)
    {
        TypeManager.applyConversion(vm, from, to, refToken);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        String string = new String("[Function Call:" +  this.type + "]" + refToken.getText() + " ");
        for(Exp param: params)
            string = string + param; 
        string = string + ")";
        return string ; 
    }
}
