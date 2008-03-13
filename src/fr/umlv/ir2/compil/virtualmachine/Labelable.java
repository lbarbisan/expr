/*
 * Created on 24 janv. 2005
 */
package fr.umlv.ir2.compil.virtualmachine;


/**
 * Interface use by VirtualMachine to assign UniqueID
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public interface Labelable
{
    /**
     * Return the label of the implentating class
     * @return Label Object represent a label
     */ 
	public Label getLabel();
	
	/**
	 * Set the label of the implementing class
	 * @param label Label set to implementing class
	 */
    public void setLabel(Label label);
}
