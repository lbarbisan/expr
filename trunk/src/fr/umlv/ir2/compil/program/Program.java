/*
 * Created on 14 janv. 2005
 */
package fr.umlv.ir2.compil.program;

import java.util.ArrayList;

import fr.umlv.ir2.compil.expressiontree.Exp;
import fr.umlv.ir2.compil.expressiontree.ExpText;
import fr.umlv.ir2.compil.expressiontree.ExpFor;
import fr.umlv.ir2.compil.program.Typable.Type;
import fr.umlv.ir2.compil.symbolstable.SymbolsTable;
import fr.umlv.ir2.compil.symbolstable.symbols.Function;
import fr.umlv.ir2.compil.symbolstable.symbols.Global;
import fr.umlv.ir2.compil.virtualmachine.Compilable;
import fr.umlv.ir2.compil.virtualmachine.Label;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;

/**
 * Represent an Exp program.
 * @author Olivier Boitel
 * @author Laurent barbisan
 */
public class Program implements Compilable
{
    /**
     * Table to store all globals variables declaration
     */
    private ArrayList<Global> globals = new ArrayList<Global>();
    
    /**
     * Table to store all contants
     */
    private ArrayList<ExpText> constants = new ArrayList<ExpText>() ;
    
    /**
     * Table to store all Functions
     */
    private ArrayList<Function> functions = new ArrayList<Function>() ;
    
    /**
     * Execution list
     */
    private Main executionList = new Main();
    
    /**
     * Symbol table
     */
    private SymbolsTable symbolsTable = new SymbolsTable(); 
    
    // UPDATE: Si on ajoute une fonction qui existe déjà
    /**
     * Add a function to function list
     * @param function fnction to add
     */
    public void addFunction(Function function)
    {
        functions.add(function);
    }
    
    // UPDATE: Si on ajoute une variable globale qui existe déjà
    /**
     * Add a global variable to gloabls list
     * @param global Gloabl variable to add
     */
    public void addGlobal(Global global)
    {
        globals.add(global);
    }
    
    /**
     * Add a global variable to gloabls list
     * @param global Gloabl variable to add
     */
    public void addConstant(ExpText constant)
    {
        constants.add(constant);
    }
    
    /**
     * Add a code 
     * The code is order by the time of insertion.
     * @param compilable
     */
    public void addCompilable(Compilable compilable)
    {
        executionList.addCompilable(compilable);
    }
    
        
    public@Override String toString()
    {
        String string = new String("Liste d'execution:");
        for(Object object: executionList.getCompilables())
        {
        	if(object instanceof Exp)
        		string = string + "\n\t" +((Exp)object).toString();
        	else  if(object instanceof ExpFor)
        		string = string + "\n\t" + ((ExpFor)object).toString();
        	if(object instanceof Global)
        		string = string +  "\n\t" +((Global)object).toString();
        }
        
        string = string + "\n\nListe des constantes:";
        for(ExpText constant : constants)
        {
            string = string +  "\n\t" +constant.toString();
        }
        
        string = string + "\n\nListe des fonctions:";
        for(Function function : functions)
        {
            string = string + "\n\t" + function.toString();
        }
        string = string + "\n\nListe des variables globales:";
        for(Global global : globals)
        {
            string = string + "\n\t" + global.toString();
        }
        return  string;
    }

    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.program.code.compilable.Compilable#compile(fr.umlv.ir2.compil.virtualmachine.VirtualMachine)
     */
    public void compile(VirtualMachine vm, Type type)
    {
        vm.addConstantSection(); 
        
        /* Compil Constant */
       for(ExpText text : constants)
           text.Init(vm,type);

        vm.addDataSection();
        /* Compile Data */
        for(Global global :globals)
        {
            Label label = vm.global(global.getName(), global.getType());
            global.setLabel(label);
        }
        
        vm.addCodeSection();
        /* Compile code */
        for(Function function : functions)
            function.compile(vm, type);
        /* Compile Main */
        executionList.compile(vm, type);
        
        
        
    }
    /**
     * @return Returns the symbolTable.
     */
    public SymbolsTable getSymbolsTable()
    {
        return symbolsTable;
    }
    /**
     * @return Returns the executionList.
     */
    public Main getExecutionList()
    {
        return executionList;
    }

    /**
     * 
     */
    public void setNumMainVariables(int num)
    {
        executionList.setNumVariables(num);
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.program.code.compilable.Compilable#applyConversion(fr.umlv.ir2.compil.virtualmachine.VirtualMachine, fr.umlv.ir2.compil.program.Typable.Type, fr.umlv.ir2.compil.program.Typable.Type)
     */
    public void applyConversion(VirtualMachine vm, Type From, Type To)
    {
    }
}
