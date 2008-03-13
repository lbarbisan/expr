/*
 * Created on 15 déc. 2004
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.ErrorsManager;
import fr.umlv.ir2.compil.program.Typable;
import fr.umlv.ir2.compil.program.TypeManager;
import fr.umlv.ir2.compil.virtualmachine.Compilable;
import fr.umlv.ir2.compil.virtualmachine.CompileException;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;
import fr.umlv.ir2.compil.virtualmachine.CompileException.ErrorType;

/**
 * This class is an abstract class for node representation in
 * Expression Tree
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public abstract class Exp implements Compilable, Typable
{
    
    /**
     * Real type of the expression
     */
    protected Typable.Type type=Type.UNDEF;
    protected Typable.Type inducedType=Type.UNDEF;
    
    /**
     * Reference token
     */
    protected Token refToken;
    
    /**
     * Default Contructor.
     * The default constructor assign int type to expression.
     * @param refToken Token refrences for line and column
     */
    protected Exp(Token refToken){
        this.refToken = refToken;
    }
    
    /**
     * Error throws in analysis
     * @param message
     * @return
     */
    public void error(ErrorType type, String message)
    {
        ErrorsManager.addException(new CompileException(ErrorType.ERROR, refToken.getLine() , refToken.getPos(),  message));
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.Compilable#applyConversion(fr.umlv.ir2.compil.program.Typable.Type, fr.umlv.ir2.compil.program.Typable.Type)
     */
    public void applyConversion(VirtualMachine vm, Type from, Type to)
    {
        TypeManager.applyConversion(vm, from, to, refToken);
    }
    
    /**
     * @return Returns the type.
     */
    public Type getType()
    {
    	return this.type;
    }
    
    public@Override abstract String toString();
}
