/*
 * Created on 13 janv. 2005
 */
package fr.umlv.ir2.compil.symbolstable.symbols;

import java.util.ArrayList;

import fr.umlv.ir2.compil.expressiontree.Exp;
import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.Typable;
import fr.umlv.ir2.compil.program.TypeManager;
import fr.umlv.ir2.compil.virtualmachine.Compilable;
import fr.umlv.ir2.compil.virtualmachine.Label;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;


/**
 * Represent a function declaration
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class Function implements Compilable,Typable
{
    /**
     * Store all parameters
     */
    private ArrayList<Param> params;
    private int locals;
    
    /**
     * Use for typing, if function is recursive
     */
    private boolean visited;
    
    /**
     * Code associate with
     */
    private Exp code;
    
    /**
     * Type of funciton
     */
    private Type type= Type.UNDEF; 
    
    /**
     * Name of the function
     */
    private String name;
    
    /**
     * Unique Id
     */
    private Label label;
    
    /**
     * Toke use for reference
     */
    Token refToken;
    
    /**
     * @param name fonction name
     */
    public Function(String name, Type type, Token refToken)
    {
        this.name = name;
        this.refToken = refToken;
        if(type!=null)
        {
            this.type = type;
        }
        params = new ArrayList<Param>();
    }
    
    /**
     * @param name fonction name
     */
    public Function(String name)
    {
        this.name = name;
        params = new ArrayList<Param>();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        setLabel(vm.function(name, params.size(), locals)); 
        code.compile(vm, getType());
        //Check and apply if conversion possible from the class type and the parameter type
        this.applyConversion(vm, getType(), code.getType());
        vm.ret();
    }    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.Compilable#applyConversion(fr.umlv.ir2.compil.program.Typable.Type, fr.umlv.ir2.compil.program.Typable.Type)
     */
    public void applyConversion(VirtualMachine vm, Type from, Type to)
    {
        TypeManager.applyConversion(vm, from, to, refToken);
    }
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.program.Typable#getType()
     */
    public Type getType()
    {
    	if(visited==true)
    	{
    	    return Type.UNDEF; //TypeManager.getDefaultType();
    	}
    	visited = true;
    	
        if(this.type==Type.UNDEF)
        {
            Type type = TypeManager.getComptibleType(code.getType(), this.type);
            this.type = type;
         }
         else
         {
            if(this.type!=code.getType())
            {
                System.out.println("[ERROR] Can't convert from '" + code.getType() +"' to '" + this.type + "'");
                System.exit(1);
            }
        }
        visited = false;
        return type;
    }
    
    
    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }
    
    
    /**
     * @param locals The locals to set.
     */
    public void setLocals(int locals)
    {
        this.locals = locals;
    }
    /**
     * @return Returns the locals.
     */
    public int getLocals()
    {
        return locals;
    }
    
    
    /**
     * @param code The code to set.
     */
    public void setCode(Exp code)
    {
        this.code = code;

    }
    /**
     * @return Returns the code.
     */
    public Exp getCode()
    {
        return code;
    }
    
    
    /**
     * @return Returns the params.
     */
    public ArrayList<Param> getParams()
    {
        return params;
    }
    
    public void addParam(Param param)
    {
        params.add(param);
    }
    
    
    /**
     * @return Returns the label.
     */
    public Label getLabel()
    {
        return label;
    }
    /**
     * Set the laebl
     * @param label
     */
    public void setLabel(Label label)
    {
        this.label = label;
    }
    
    
    public@Override String toString()
    {
        //UPDATE: Eclaircir l'affichage
        return "[Function:" + getType() + "]" + name + "[" +  params.size() + "] ="  + code;
    }
    
   /* private void calculateParamsType()
    {
    	if(code!=null)
    	{
    		for(Param param : params)
        	{
    			if(param.getType()==Type.UNDEF)
    			{
    				param.setType(code.getType());
    			}
        	}	
    	}
    	
    }*/
}
