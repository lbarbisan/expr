/*
 * Created on 13 janv. 2005
 */
package fr.umlv.ir2.compil.symbolstable.symbols;


import fr.umlv.ir2.compil.expressiontree.Exp;
import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.TypeManager;
import fr.umlv.ir2.compil.program.Typable.Type;
import fr.umlv.ir2.compil.virtualmachine.Compilable;
import fr.umlv.ir2.compil.virtualmachine.Label;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;


/**
 * Represent a global declaration
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class Global implements Compilable
{
    
    private Token refToken;
	private String name;
    private Label label;
    private Type type = Type.UNDEF;
    
    /**
     * Code use to Initialise variable
     */
    private Exp initCode;
    
    public Global(String name, Type type, Token refToken)
    {
    	this.refToken = refToken;
    	this.name=name;
        if(type!=null)
        {
            this.type=type;
        }
    }
    
    
    /**
     * @return Returns the label.
     */
    public Label getLabel()
    {
        return label;
    }
    /**
     * @param label The label to set.
     */
    public void setLabel(Label label)
    {
        this.label = label;
    }
    
    /**
     * @return Returns the type.
     */
    public Exp.Type getType()
    {
        	if(this.type==Type.UNDEF) 
        	{
        		Type type = TypeManager.getComptibleType(initCode.getType(), this.type);
        		if(type==Type.UNDEF)
        			this.type=TypeManager.getDefaultType();
        		else
        			this.type = type;
        	}
        		
        return type;
    }
    /**
     * @return Returns the initCode.
     */
    public Exp getInitCode()
    {
        return initCode;
    }
    /**
     * @param initCode The initCode to set.
     */
    public void setInitCode(Exp initCode)
    {
        this.initCode = initCode;
    }
    
    
    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.Compilable#compile(fr.umlv.ir2.compil.virtualmachine.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        initCode.compile(vm, getType());
        vm.storeG(label);
    }
    
    
    public@Override String toString()
    {
        return "[Global:" + type + "]" + name + "(init:" + initCode + ")"; 
    }


    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.program.code.compilable.Compilable#applyConversion(fr.umlv.ir2.compil.virtualmachine.VirtualMachine, fr.umlv.ir2.compil.program.Typable.Type, fr.umlv.ir2.compil.program.Typable.Type)
     */
    public void applyConversion(VirtualMachine vm, Type from, Type to)
    {
        TypeManager.applyConversion(vm, from, to, refToken);
    }
}
