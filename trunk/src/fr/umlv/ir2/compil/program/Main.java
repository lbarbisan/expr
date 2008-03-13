/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.program;

import java.util.ArrayList;

import fr.umlv.ir2.compil.program.Typable.Type;
import fr.umlv.ir2.compil.virtualmachine.Compilable;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Class use to store list of execution
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class Main implements Compilable
{
    private ArrayList<Compilable> compilables;
    private int numVariables= 0;
    
    public Main()
    {
        compilables = new ArrayList<Compilable>();
    }
    
    public void addCompilable(Compilable compilable)
    {
        compilables.add(compilable);
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.program.code.compilable.Compilable#compile(fr.umlv.ir2.compil.virtualmachine.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        //Nombre de variable local
        vm.main(numVariables);
        for(Compilable compilable: compilables)
            compilable.compile(vm, type);
        vm.exit();
        vm.ret();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.program.code.compilable.Compilable#applyConversion(fr.umlv.ir2.compil.virtualmachine.VirtualMachine, fr.umlv.ir2.compil.program.Typable.Type, fr.umlv.ir2.compil.program.Typable.Type)
     */
    public void applyConversion(VirtualMachine vm, Type From, Type To)
    {
    }
    /**
     * @return Returns the compilables.
     */
    public ArrayList<Compilable> getCompilables()
    {
        return compilables;
    }
    /**
     * @return Returns the numVariables.
     */
    public int getNumVariables()
    {
        return numVariables;
    }
    /**
     * @param numVariables The numVariables to set.
     */
    public void setNumVariables(int numVariables)
    {
        this.numVariables = numVariables;
    }
}
