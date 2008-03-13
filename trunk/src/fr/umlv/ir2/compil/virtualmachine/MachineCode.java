/*
 * Created on 20 janv. 2005
 */
package fr.umlv.ir2.compil.virtualmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;


import fr.umlv.ir2.compil.program.Typable.Type;

/**
 * Compile code for x86 Machine.
 * @author Laurent Barbisan
 * @author Boitel Olivier
 */
public class MachineCode implements VirtualMachine
{
    HashMap<String, Integer> labels = new HashMap<String, Integer>();
    
    PrintWriter writer;
    
    
    boolean console=false;
    
    /**
     * Constructor.
     * Show compilation on stdout.
     */
    public MachineCode()
    {
        console = true;
    	writer = new PrintWriter(System.out);
    }
    
    /**
     * Constructor.
     * Write compilation code in 'fileName'
     * @param fileName file name for compialtion file
     */
    public MachineCode(File file)
    {
        try
        {
            writer = new PrintWriter(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("[ERROR]can't create output file for assembler : " + file.getAbsolutePath());
            System.exit(1);
        }        
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadP(int)
     */
    public void loadP(int numParam)
    {
        //Fonctionnelle
        //Push on the stack the numParam parameter
        writer.println("\tpushl " + (4*numParam+8) + "(%ebp)");
        writer.flush();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadi(int)
     */
    public void loadi(int value)
    {
        //Fonctionnelle
        writer.println("\tpushl $"  + value );
        writer.flush();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadG(fr.umlv.ir2.compil.virtualmachine.Label)
     */
    public void loadG(Label globalLabel)
    {
        //Fonctionnelle
        writer.println("\tpushl " + globalLabel);
        writer.flush();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#storeG(fr.umlv.ir2.compil.virtualmachine.Label)
     */
    public void storeG(Label globalLabel)
    {
        //Fonctionnelle
        writer.println("\tpopl " + globalLabel);
        writer.flush();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadT(int)
     */
    public void loadT(int variable)
    {
        writer.println("\tpushl " + (-variable*4) + "(%ebp)");
        writer.flush(); 
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#storeT(int)
     */
    public void storeT(int variable)
    {
        writer.println("\tpopl " + (-variable*4) + "(%ebp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#loadf(float)
     */
    public void loadf(float value)
    {
        //on charge sur la pile la valeur hexadecimale du float 
        int convertion = Float.floatToIntBits(value);
        writer.println("\tpushl $0x" + Integer.toHexString(convertion));
        writer.flush();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#iAdd()
     */
    public void iAdd()
    {
        //Fonctionnelle
        writer.println("\tpopl %eax");
        writer.println("\taddl  (%esp), %eax");
        writer.println("\tmovl  %eax, (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#fAdd()
     */
    public void fAdd()
    {
        //methode gcc
        writer.println("\tflds (%esp)");
        writer.println("\taddl $4, %esp");
        writer.println("\tfadds (%esp)");
        writer.println("\tfstps (%esp)");
        writer.flush();
        
        /*//methode intel
        writer.println("\tpopl %eax");
        writer.println("\tfld %eax");
        writer.println("\tmovl (%esp), %eax");
        writer.println("\tfld %eax");
        writer.println("\tfaddp");
        writer.println("\tfstp (%esp)");
        writer.flush();*/    
    }
    
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#iMult()
     */
    public void iMult()
    {
        //Fonctionnelle
        writer.println("\tpopl %eax");
        writer.println("\timull  (%esp), %eax");
        writer.println("\tmovl  %eax, (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#fMult()
     */
    public void fMult()
    {
        /*//methode gcc
        writer.println("\tpopl %eax");
        writer.println("\tflds %eax");
        writer.println("\tfmuls (%esp)");
        writer.println("\tfstps (%esp)");
        writer.flush();*/
        
        //methode corrigée
        writer.println("\tflds (%esp)");
        writer.println("\taddl $4, %esp");
        writer.println("\tfmuls (%esp)");
        writer.println("\tfstps (%esp)");
        writer.flush();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#iDiv()
     */
    public void iDiv()
    {
        //Fonctionnelle
        //Load diviseur
        writer.println("\tmovl (%esp), %eax");
        writer.println("\tcltd");
        //fait la divison avec le haut de pile
        //UPDATE : division par zéro ?
        writer.println("\tidivl 4(%esp)");
        //Redescend le haut de pile
        writer.println("\taddl $4, %esp ");
        writer.println("\tmovl %eax, (%esp)");
        writer.flush();       
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#mod()
     */
    public void mod()
    {
//      Fonctionnelle
        //Load diviseur
        writer.println("\tmovl (%esp), %eax");
        writer.println("\tcltd");
        //fait la divison avec le haut de pile
        //UPDATE : division par zéro ?
        writer.println("\tidivl 4(%esp)");
        //Redescend le haut de pile
        writer.println("\taddl $4, %esp ");
        writer.println("\tmovl %edx, (%esp)");
        writer.flush();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#fDiv()
     */
    public void fDiv()
    {
        /*//methode gcc a tester 
        writer.println("\tpopl %eax");
        writer.println("\tflds (%esp)");
        writer.println("\tfdivs (%eax)");
        writer.println("\tfstps (%esp)");
        writer.flush();*/
        
        //methode corrigée
        
        writer.println("\tflds (%esp)");
        writer.println("\tfdivs 4(%esp)");
        writer.println("\taddl $4, %esp");
        writer.println("\tfstps (%esp)");
        writer.flush();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#iSub()
     */
    public void iSub()
    {
        //Fonctionnelle
        writer.println("\tpopl %eax");
        writer.println("\tsubl  (%esp), %eax");
        writer.println("\tmovl  %eax, (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#fSub()
     */
    public void fSub()
    {
        /*//methode gcc;
        writer.println("\tpopl %eax");
        writer.println("\tflds (%esp)");
        writer.println("\tfsubs (%eax)");
        writer.println("\tfstps (%esp)");
        writer.flush();*/
        
        //methode corrigée
        writer.println("\tflds (%esp)");
        writer.println("\tfsubs 4(%esp)");
        writer.println("\taddl $4, %esp");
        writer.println("\tfstps (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#f2I()
     */
    public void f2I()
    {
        writer.println("\tflds (%esp)");
        writer.println("\tfistpl (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#i2F()
     */
    public void i2F()
    {
        writer.println("\tfildl (%esp)");
        writer.println("\tfstps (%esp)");
        writer.flush();
    }
    
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ret()
     */
    public void ret()
    {
        //Fonctionnelle
        writer.println("\tpopl %eax");
        writer.println("\tleave");
        writer.println("\tret");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#exit()
     */
    public void exit()
    {
        writer.flush();
    }
    

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ppI()
     */
    public void ppI()
    {
        //Fonctinnelle
        writer.println("\tpushl $int_show");
        writer.println(callPrintf());
        writer.println("\taddl $4, %esp");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ppF()
     */
    public void ppF()
    {
        // Fonctionnelle
        writer.println("\tflds (%esp)");
        writer.println("\tsubl $4, %esp");
        writer.println("\tfstpl (%esp)");
        writer.println("\tpushl $float_show");
        writer.println(callPrintf());
        writer.println("\taddl $8, %esp");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ppB()
     */
    public void ppB()
    {
        Label LFalse;
        Label LNext;
        LFalse = generateLabel("False");
        LNext = generateLabel("Next");
        
        writer.println("\tpopl %eax");
        writer.println("\tcmpl $0 , %eax");
        writer.println("\tje " + LFalse);
        writer.println("\tpushl $string_true");
        jump(LNext);
        
        label(LFalse);
        writer.println("\tpushl $string_false");
        label(LNext);
        writer.println("\tpushl $string_show");
        writer.println(callPrintf());
        writer.println("\taddl $4, %esp");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ppNwl()
     */
    public void ppNwl()
    {
        //Fonctionnelle
        writer.println("\tpushl $string_return");
        writer.println(callPrintf());
        writer.println("\taddl $4, %esp");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#ppT(fr.umlv.ir2.compil.virtualmachine.Label)
     */
    public void ppT(Label constantLabel)
    {
        writer.println("\tpushl $" + constantLabel);
        writer.println("\tpushl $string_show");
        writer.println(callPrintf());
        writer.println("\taddl $4, %esp");
        writer.flush();
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#main(int)
     */
    public void main(int locals)
    {
        if(System.getProperty("os.name").startsWith("Windows"))
        {
            mainWindows(locals);
        }
        else
        {
            mainLinux(locals);
        }
        writer.println("\tfinit");
        writer.flush();
    }
    
    private void mainLinux(int locals)
    {
        writer.println("\t.globl main");
        //writer.println("\t.type main, @function");
        writer.println("main:");
        writer.println("\tpushl %ebp");
        writer.println("\tmovl  %esp, %ebp");
        // 8 plus le nombre de variable locales *4
        writer.println("\tsubl  $" + (8+locals*4) +", %esp");
        //writer.println("\tandl  $-16, %esp");
        //writer.println("\tmovl  $0, %eax");
        writer.flush();
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#main(int)
     */
    private void mainWindows(int locals)
    {
        
        writer.println("\t.globl _main");
        //writer.println("\t.def	_main;	.scl	2;	.type	32;	.endef");
        writer.println("_main:");
        writer.println("\tpushl	%ebp");
        writer.println("\tmovl	%esp, %ebp");
        // 8 plus le nombre de variable locales *4
        writer.println("\tsubl	$" + (8+locals*4) +", %esp");
        //writer.println("\tandl	$-16, %esp");
        //writer.println("\tmovl	$0, %eax");
       // writer.println("\taddl	$15, %eax");
       // writer.println("\taddl	$15, %eax");
       // writer.println("\tshrl	$4, %eax");
       // writer.println("\tsall	$4, %eax");
       // writer.println("\tmovl	%eax, -4(%ebp)");
       // writer.println("\tmovl	-4(%ebp), %eax");
       // writer.println("\tcall	__alloca");
       // writer.println("\tcall	___main");
        writer.flush();
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#addConstantSection()
     */
    public void addConstantSection()
    {
        //Fonctionnelle
        writer.println(".section .rodata");
        //UPDATE : Utiliser la génération des label pour poser ces labels
        writer.println("int_show: .string \"%d\"");
        writer.println("float_show: .string \"%f\"");
        writer.println("string_show: .string \"%s\"");
        writer.println("string_return: .string \"\\n\"");
        writer.println("string_true: .string \"true\"");
        writer.println("string_false: .string \"false\"");
        writer.flush();
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#addDataSection()
     */
    public void addDataSection()
    {
        //	writer.println(".section .bss");
        //	writer.flush();
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#addCodeSection()
     */
    public void addCodeSection()
    {
        //Fonctionnelle
        writer.println(".section .text");
        writer.flush();
    }
    
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#label(java.lang.String)
     */
    public void label(Label labelName)
    {
        //Fonctionnelle
        writer.println(labelName.toString()  + " :");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#addComment(java.lang.String)
     */
    public void addComment(String comment)
    {
        //Fonctionnelle
        writer.println("#" + comment);
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#jump(fr.umlv.ir2.compil.virtualmachine.Label)
     */
    public void jump(Label label)
    {
        writer.println("\tjmp " + label);
        writer.flush();
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#branchIZ(fr.umlv.ir2.compil.virtualmachine.Label, fr.umlv.ir2.compil.virtualmachine.Label)
     */
    public void branch(Label labelTrue, Label labelFalse)
    {
        writer.println("\tpopl %ecx");
        writer.println("\tcmpl $1 , %ecx");
        writer.println("\tje " + labelTrue);
        jump(labelFalse);
        writer.flush();
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#call(fr.umlv.ir2.compil.virtualmachine.Label)
     */
    public void call(Label functionName, int paramNum)
    {
        writer.println("\tcall " + functionName);
        writer.println("\taddl $" + paramNum*4 + ", (%esp)");
        writer.println("\tpushl %eax");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#global(java.lang.String, fr.umlv.ir2.compil.expressiontree.Exp.Type)
     */
    public Label global(String name, Type type)
    {
        int size;
        switch (type)
        {
        case FLOAT:
            size = 32;
            break;
        case INT:
        default:
            size = 16;
        break;
        }
        Label label = generateLabel(name);
        writer.println(".comm " +  label + " , " + size);
        writer.flush();
        return label;
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#function(java.lang.String, int, int)
     */
    public Label function(String name, int params, int locals)
    {
        Label label = generateLabel(name);
        writer.println(label + ":");
        writer.println("\tpushl %ebp");
        writer.println("\tmovl %esp, %ebp");
        //Espace alloué pour le nombre de variable locals
        writer.println("\tsubl $" + locals*4 + ", %esp");
        writer.flush();
        return label ;
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#text(java.lang.String, java.lang.String)
     */
    public Label text(String name, String value)
    {
        Label label = generateLabel(name);
        writer.println(label + ": .string " + value );
        writer.flush();
        return label ;
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
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#neg()
     */
    public void neg()
    {
        writer.println("\tmovl (%esp), %eax");
        writer.println("\tneg %eax");
        writer.println("\tmovl %eax, (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#inc()
     */
    public void inc()
    {        
        writer.println("\taddl $1, (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#dec()
     */
    public void dec()
    {
        //writer.println("\tdec");
        writer.println("\tsubl $1, (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#and()
     */
    public void and()
    {
        writer.println("\tpopl %eax");
        writer.println("\tandl %eax, (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#or()
     */
    public void or()
    {
        writer.println("\tpopl %eax");
        writer.println("\tor %eax, (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#xor()
     */
    public void xor()
    {
    	writer.println("\tpopl %eax");
        writer.println("\txor %eax, (%esp)");
        writer.flush();
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#not()
     */
    public void not()
    {
        writer.println("\txor $1,(%esp)");
        writer.flush();
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpGT()
     */
    public void cmpGT()
    {
        writer.println("\tpopl %eax");
        writer.println("\tcmpl (%esp), %eax");
        writer.println("\tsetg %cl");
        writer.println("\tmovzbl %cl, %ecx");
        writer.println("\tpushl %ecx");
        writer.flush();
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpGTE()
     */
    public void cmpGTE()
    {
        writer.println("\tpopl %eax");
        writer.println("\tcmpl (%esp), %eax");
        writer.println("\tsetge %cl");
        writer.println("\tmovzbl %cl, %ecx");
        writer.println("\tpushl %ecx");
        writer.flush();
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpLT()
     */
    public void cmpLT()
    {
        writer.println("\tpopl %eax");
        writer.println("\tcmpl (%esp), %eax");
        writer.println("\tsetl %cl");
        writer.println("\tmovzbl %cl, %ecx");
        writer.println("\tpushl %ecx");
        writer.flush();
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpLTE()
     */
    public void cmpLTE()
    {
        writer.println("\tpopl %eax");
        writer.println("\tcmpl (%esp), %eax");
        writer.println("\tsetle %cl");
        writer.println("\tmovzbl %cl, %ecx");
        writer.println("\tpushl %ecx");
        writer.flush();
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.virtualmachine.VirtualMachine#cmpE()
     */
    public void cmpE()
    {
        writer.println("\tpopl %eax");
        writer.println("\tcmpl (%esp), %eax");
        writer.println("\tsete %cl");
        writer.println("\tmovzbl %cl, %ecx");
        writer.println("\tpushl %ecx");
        writer.flush();
    }
    
    /**
     * convert the top of the stack into a boolean format.
     *
     */
    public void i2b()
    {
        writer.println("\tcmpl $0, (%esp)");
        writer.println("\tsetne %cl");
        writer.println("\tmovzbl %cl, %eax");
        writer.println("\tmovl %eax, (%esp)");
        writer.flush();
    }
    
    
    private String callPrintf()
    {
        if(System.getProperty("os.name").startsWith("Windows"))
        {
            return "\tcall _printf";
        }
        else
        {
            return "\tcall printf";
        }
    }

    public@Override void finalize()
    {
       if(console==false)
       {
    	writer.close();
       }
    }


    
}
