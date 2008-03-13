/*
 * Created on 15 janv. 2005
 */

package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.program.Typable;
import fr.umlv.ir2.compil.symbolstable.symbols.Local;
import fr.umlv.ir2.compil.virtualmachine.Compilable;
import fr.umlv.ir2.compil.virtualmachine.Label;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Represent a For Instruction.
 * @author Laurent Barbisan
 * @author Boitel Olivier
 * 
 */
public class ExpFor implements Compilable,Typable
{
    
    /**
     * Code associate with
     */
    private Exp start;
    private Exp end;
    private ExpPrint action;
    private Local local ;
    private Type type;
    /**
     * @param name fonction name
     */
    public ExpFor(Local local)
    {
        this.local = local;
        this.type = Type.INT;
    }
    
    public@Override String toString()
    {
        return "For " + start + " to " + end + " " + action; 
    }
	/**
	 * @return Returns the action.
	 */
	public Exp getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(ExpPrint action) {
		this.action = action;
	}
	/**
	 * @return Returns the end.
	 */
	public Exp getEnd() {
		return end;
	}
	/**
	 * @param end The end to set.
	 */
	public void setEnd(Exp end) {
		this.end = end;
	}
	/**
	 * @return Returns the start.
	 */
	public Exp getStart() {
		return start;
	}
    
	/**
	 * @param start The start to set.
	 */
	public void setStart(Exp start) {
		this.start = start;
	}

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.program.code.compilable.Compilable#compile(fr.umlv.ir2.compil.virtualmachine.VirtualMachine, fr.umlv.ir2.compil.expressiontree.Exp.Type)
     */
    public void compile(VirtualMachine vm, Type type)
    {
         Label labelStart = vm.generateLabel("ForStart");
         Label labelContinue = vm.generateLabel("ForContinue");
         Label labelEnd = vm.generateLabel("ForEnd");
         
//       Condition de boucle
         start.compile(vm, this.type);
         vm.storeT(local.getIndex());
         
         vm.label(labelStart);
         vm.loadT(local.getIndex());
//       Condition
         end.compile(vm, this.type);
         vm.cmpGTE();
         vm.branch(labelContinue, labelEnd);
         vm.label(labelContinue);
         //store top of stack in local variable
         vm.storeT(local.getIndex());
         vm.loadT(local.getIndex());

         //action
         action.compile(vm, type);
         
         //on incrémente la variable locale du for
         vm.loadT(local.getIndex());
         vm.inc();
         vm.storeT(local.getIndex());
         vm.jump(labelStart);
         vm.label(labelEnd);
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.program.code.compilable.Typable#getType()
     */
    public Type getType()
    {
        return this.type;
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.program.code.compilable.Compilable#applyConversion(fr.umlv.ir2.compil.virtualmachine.VirtualMachine, fr.umlv.ir2.compil.program.Typable.Type, fr.umlv.ir2.compil.program.Typable.Type)
     */
    public void applyConversion(VirtualMachine vm, Type From, Type To)
    {
    }
}
