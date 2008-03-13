/*
 * Created on 16 janv. 2005
 */
package fr.umlv.ir2.compil.virtualmachine;

import java.util.HashMap;

import fr.umlv.ir2.compil.expressiontree.Exp;

/**
 * Implements VirtualMachine, to show a PseudoCode.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class PseudoCode implements VirtualMachine
{
    
    HashMap<String, Integer> labels = new HashMap<String, Integer>();
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadG(fr.umlv.ir2.compil.symbols.Global)
     */
    public void loadG(Label globalId)
    {
        System.out.println("  load_g " + globalId);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#storeG(fr.umlv.ir2.compil.symbols.Global)
     */
    public void storeG(Label globalId)
    {
        System.out.println("  store_g " + globalId);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadP(fr.umlv.ir2.compil.symbols.Param)
     */
    public void loadP(int numParam)
    {
        System.out.println("  load_p " + numParam);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadT(fr.umlv.ir2.compil.symbols.Local)
     */
    public void loadT(int variable)
    {
        System.out.println("  load_t " + variable); 
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#storeT(fr.umlv.ir2.compil.symbols.Local)
     */
    public void storeT(int variable)
    {
        System.out.println("  store_t " + variable);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadi(int)
     */
    public void loadi(int value)
    {
        System.out.println("  load_i " + value);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadf(float)
     */
    public void loadf(float value)
    {
        System.out.println("  load_f " + value);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#iAdd()
     */
    public void iAdd()
    {
        System.out.println("  iAdd");
    }
    
    
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#fAdd()
     */
    public void fAdd()
    {
        System.out.println(" fAdd");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#iMult()
     */
    public void iMult()
    {
        System.out.println("  iMult");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#fMult()
     */
    public void fMult()
    {
        System.out.println("  fMult");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#iDiv()
     */
    public void iDiv()
    {
        System.out.println("  iDiv");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#fDiv()
     */
    public void fDiv()
    {
        System.out.println("  fDiv");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#iSub()
     */
    public void iSub()
    {
        System.out.println("  iSub");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#fSub()
     */
    public void fSub()
    {
        System.out.println("  fSub");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#f2I()
     */
    public void f2I()
    {
        System.out.println("  f2I");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#i2F()
     */
    public void i2F()
    {
        System.out.println("  i2F");
    }
    
    public void i2b()
    {
    	System.out.println("  i2b");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#label(java.lang.String)
     */
    public void label(Label labelName)
    {
        System.out.println("label "+ labelName + " :");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#getLabel(java.lang.String)
     */
    public Label generateLabel(String prefix)
    {
        if(prefix==null) prefix= "L";
        Integer value = labels.get(prefix);
        if(value==null)
        {
            labels.put(prefix, new Integer(0));
        }
        else
        {
            labels.put(prefix, new Integer(labels.get(prefix).intValue()+1));
        }
        return new Label(prefix, labels.get(prefix).intValue());
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.VirtualMachine#jump(java.lang.String)
     */
    public void jump(Label label)
    {
        System.out.println("  jump " + label);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#branch(java.lang.String, java.lang.String)
     */
    public void branch(Label labelTrue, Label labelFalse)
    {
        System.out.println("  branch " +  labelTrue + " " +  labelFalse);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#call(java.lang.String)
     */
    public void call(Label functionName, int paramNum)
    {
        System.out.println("  call" + functionName );
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ret()
     */
    public void ret()
    {
        System.out.println("  ret");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#exit()
     */
    public void exit()
    {
        System.out.println("  exit");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ppT(fr.umlv.ir2.compil.symbols.Constant)
     */
    public void ppT(Label constantID)
    {
        System.out.println("  ppT $" + constantID);
    }
    
    public void ppB()
    {
    	System.out.println("  ppB");
    }
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ppI(int)
     */
    public void ppI()
    {
        System.out.println("  ppI");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ppF(float)
     */
    public void ppF()
    {
        System.out.println("  ppF");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ppNwl()
     */
    public void ppNwl()
    {
        System.out.println("  ppNwl");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#global(fr.umlv.ir2.compil.symbols.Global)
     */
    public Label global(String global, Exp.Type type)
    {
        Label label = generateLabel(global);
        System.out.println("  global " + label );
        return label;
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#function(java.lang.String, int, int)
     */
    public Label function(String name, int params, int locals)
    {
        Label label = generateLabel(name);
        System.out.println("function " + label + " "+ params + " " + locals);
        return label;
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#text(fr.umlv.ir2.compil.symbols.Constant)
     */
    public Label text(String constant, String value)
    {
        Label label = generateLabel(constant);
        System.out.println("  text " + label + "=" + value);
        return label;
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#main(int)
     */
    public void main(int locals)
    {
        System.out.println("main " + locals);
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#addConstantSection()
     */
    public void addConstantSection()
    {
        System.out.println("--Constant--");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#addDataSection()
     */
    public void addDataSection()
    {
        System.out.println("--Data--");
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#addCodeSection()
     */
    public void addCodeSection()
    {
        System.out.println("--Code--");
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#addComment(java.lang.String)
     */
    public void addComment(String comment)
    {
        System.out.println("//" + comment);
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#neg()
     */
    public void neg()
    {
        System.out.println("  neg");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#inc()
     */
    public void inc()
    {
        System.out.println("  inc");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#dec()
     */
    public void dec()
    {
        System.out.println("  dec");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#and()
     */
    public void and()
    {
        System.out.println("  and");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#or()
     */
    public void or()
    {
        System.out.println("  or");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#xor()
     */
    public void xor()
    {
        System.out.println("  xor");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#not()
     */
    public void not()
    {
        System.out.println("  not");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpGT()
     */
    public void cmpGT()
    {
        System.out.println("  cmpGT");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpGTE()
     */
    public void cmpGTE()
    {
        System.out.println("  cmpGTE");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpLT()
     */
    public void cmpLT()
    {
        System.out.println("  cmpLT");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpLTE()
     */
    public void cmpLTE()
    {
        System.out.println("  cmpLTE");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpE()
     */
    public void cmpE()
    {
        System.out.println("  cmpE");
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#mod()
     */
    public void mod()
    {
        System.out.println("  mod");

    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmp()
     */
    
    
}
