/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.expressiontree;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.TypeManager;
import fr.umlv.ir2.compil.virtualmachine.Label;
import fr.umlv.ir2.compil.virtualmachine.Labelable;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Node for Bin symbol in Expression Tree.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ExpText extends Exp implements Labelable
{

    private String value;
    private String name;
    private Label label;
    /**
     * @param p
     */
    public ExpText(String name , Token refToken)
    {
        super(refToken);
        value = refToken.getText();
        type = Type.TEXT;
    }

    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.expressiontree.Exp#toString()
     */
    public String toString()
    {
        return "[" + type + "]" + refToken.getText();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Exp.Type type)
    {
        vm.ppT(getLabel());
        //Check and apply if conversion possible from the class type and the parameter type
        applyConversion(vm, getType(), TypeManager.getComptibleType(getType(), type));

    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.symbols.Symbol#compile(fr.umlv.ir2.compil.VirtualMachine)
     */
    public void Init(VirtualMachine vm, Exp.Type type)
    {
        setLabel(vm.text(name, value));
        

    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.Labelable#getLabel()
     */
    public Label getLabel()
    {
        return label;
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.Labelable#setLabel(fr.umlv.ir2.compil.virtualmachine.Label)
     */
    public void setLabel(Label label)
    {
        this.label = label;
    }
}
