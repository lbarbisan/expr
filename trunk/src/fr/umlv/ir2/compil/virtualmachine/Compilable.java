/*
 * Created on 17 janv. 2005
 */
package fr.umlv.ir2.compil.virtualmachine;

import fr.umlv.ir2.compil.program.Typable.Type;

/**
 * Interface use to implements compilable.
 * A compilable classe can be pass to VirutalMachine.
 * @author Olivier Boitel
 * @author laurent Barbisan
 */
public interface Compilable
{
    /**
     * method use to compil a class code
     * @param vm
     * @param type
     */
    public void compile(VirtualMachine vm, Type type);
    
    /**
     * do VirtualMachine.XXXtoXXX()
     * @param From
     * @param To
     */
    public void applyConversion(VirtualMachine vm, Type From, Type To);
    
}
