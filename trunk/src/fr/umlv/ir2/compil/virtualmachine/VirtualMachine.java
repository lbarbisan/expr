/*
 * Created on 16 janv. 2005
 */
package fr.umlv.ir2.compil.virtualmachine;

import fr.umlv.ir2.compil.expressiontree.Exp;

/**
 * Interface use to implement a virtual machine using stack.
 * @author Laurent Barbisan
 */
public interface VirtualMachine
{
    
    /*==========================================================================================================
     *                                          I N S T R U C T I O N S
     *==========================================================================================================*/
    
    /*=========STACKS========*/
    
    /**
     * Load value of global variable 'global' on stack.
     * @param global Global variable to load its value.
     */
    public void loadG(Label globalLabel);
    
    /**
     * Pop stack value into global variable 'global'
     * @param global Global variable which take the value of the top of stack.
     */
    public void storeG(Label globalLabel);
    
    /**
     * Load value of parameter 'param' on stack
     * @param param number of parameter
     */
    public void loadP(int numParam);
    
    /**
     * Load value of local variable 'local' on stack.
     * @param local Local variable to load its value.
     */
    public void loadT(int variable);
    
    /**
     * Pop stack value into local variable 'local'
     * @param local Local variable which take the value of the top of stack.
     */
    public void storeT(int variable);
    
    /**
     * Load integer value on stack.
     * @param value value to load on stack.
     */
    public void loadi(int value);
    
    /**
     * Load float value on stack.
     * @param value value to load on stack.
     */
    public void loadf(float value);
    
    
    /*=========OPERATIONS========*/
    
    
    /**
     * pop two integer value from stack and push its sum.
     */
    public void iAdd();
    
    /**
     * pop two float value from stack and push its sum.
     */
    public void fAdd();
    
    /**
     * pop two integer value from stack and push its 'produit'.
     */
    public void iMult();
    
    /**
     * pop two float value from stack and push its 'produit'.
     */
    public void fMult();
    
    /**
     * pop two integer value from stack and push its 'quotient'.
     */
    public void iDiv();
    
    /**
     * Pop two integer value from stack and push the modulo
     */
    public void mod();

    /**
     * pop two float value from stack and push its 'quotiant'.
     */
    public void fDiv();
    
    /**
     * pop two integer value from stack and push its 'soustraction'.
     */
    public void iSub();
    
    /**
     * pop two float value from stack and push its 'soustraction'.
     */
    public void fSub();
    
    /**
     * Negate, Substract Integer from 0
     */
    public void neg();
   
    /**
     * Compare (Greater than) the two value in the top of the stack.
     * Push the result on the top of the stack
     */
    public void cmpGT();
    
    /**
     * Compare (Greater than equal)the two value in the top of the stack.
     * Push the result on the top of the stack
     */
    public void cmpGTE();
    
    /**
     * Compare (Lower than)the two value in the top of the stack.
     * Push the result on the top of the stack
     */
    public void cmpLT();
    
    /**
     * Compare (Lower than equal)the two value in the top of the stack.
     * Push the result on the top of the stack
     */
    public void cmpLTE();
    
    /**
     * Compare (Equal)the two value in the top of the stack.
     * Push the result on the top of the stack
     */
    public void cmpE();
    
    /**
     * Increment
     */
    public void inc();
    
    /**
     * Decrement
     */
    public void dec();
    
    
    /*=========LOGICAL INSTRUCTIONS==========*/
    /**
     * AND Logical between two number of stack 
     */
    public void and();

    /**
     * OR Logical between two number of stack 
     */
    public void or();
    
    /**
     * XOR Logical between two number of stack 
     */
    public void xor();
    
    /**
     * NOT Logical between two number of stack 
     */
    public void not();
    
    
    /*=========CONVERSIONS========*/
    

    /**
     * convert the top of the stack from float to integer.
     */
    public void f2I();
    
    /**
     * convert the top of the stack from integer to float.
     */
    public void i2F();
    
    /**
     * convert the top of the stack from integer to boolean
     *
     */
    public void i2b();
    
    /*=========LABELS========*/
    
    
    /**
     * Create a new label with 'label' for name.
     * the labelName must be generated with getLabel method.
     * This method should verify if the labelName as been created with getLabel.
     * @param labelName : name of the label
     */
    public void label(Label label);
    
    /**
     * Jump to label with name 'labelName'
     * the labelName must be generated with getLabel method.
     * This method should verify if the labelName as been created with generatedLabel.
     * @param labelName name of label to jump
     */
    public void jump(Label label);
    
    /**
     * Branch If Zero.
     * Branch to labelNameTrue if top of stack aqual to zero else branch to labelNameFalse
     * the labelName must be generated with getLabel method.
     * This method should verify if the labelName as been created with generatedLabel. 
     * @param labelNameTrue
     * @param labelNameFalse
     */
    public void branch(Label labelTrue,Label labelFalse);
    
    
    /*=========FUNCTIONS========*/
    
    /**
     * call a function 
     * @param functionName name of function
     */
    public void call(Label functionName, int paramNum);
    

    /**
     * return to the caller.
     */
    public void ret();
    
    /**
     * Exit program
     */
    public void exit();
    
    
    /*=========AFFICHAGES=========*/
    
    
    /**
     * Print a constant
     * @param constant constant to print
     */
    public void ppT(Label constantLabel);
    
    /**
     * Print integer value
     * @param value integer to print value
     */
    public void ppI();
    
    /**
     * Print float value
     * @param value float to print value
     */
    public void ppF();
    
    /**
     * print boolean value 
     *
     */
    public void ppB();
    
    /**
     * Print a return carriage 
     */
    public void ppNwl();
    
    
    /*==========================================================================================================
     *                                          S E C T I O N S
     *==========================================================================================================*/
    
    /**
     * Declare a variable
     * @param name name of the global variable
     */
    public Label global(String name, Exp.Type type);
    
    /**
     * Declare a fonction
     * @param name name of function
     * @param params number of parameters
     * @param locals number of locals vairable
     */
    public Label function(String name, int params, int locals);
    
    /**
     * Declare a text constant
     * @param name name of the constant
     * @param value value of the constant
     */
    public Label text(String name, String value);
    
    /**
     * Define all calcul to do
     * @param locals number of locals variable used.
     */
    public void main(int locals);
    
    /**
     * Add a cosntant Section
     *
     */
    public void addConstantSection();
    
    /**
     * Add a data section
     *
     */
    public void addDataSection();
    
    /**
     * Add a code section
     */
    public void addCodeSection();
    
    /**
     * Add a comment
     */
    public void addComment(String comment);
    
    /**
     * create a new name for a label.
     * This method must return an unique label name.
     * The Implementation of this method, can or can't
     * use 'prefix' String for build the label name.
     * @param name name of label
     */
    public Label generateLabel(String prefix);
    
}   
