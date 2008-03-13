/*
 * Created on 1 déc. 2004
 */
package fr.umlv.ir2.compil.languageparser;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

import fr.umlv.ir2.compil.cmdlineparser.Config;
import fr.umlv.ir2.compil.expressiontree.Exp;
import fr.umlv.ir2.compil.expressiontree.ExpBin;
import fr.umlv.ir2.compil.expressiontree.ExpBool;
import fr.umlv.ir2.compil.expressiontree.ExpCall;
import fr.umlv.ir2.compil.expressiontree.ExpCastFloat;
import fr.umlv.ir2.compil.expressiontree.ExpComp;
import fr.umlv.ir2.compil.expressiontree.ExpFloat;
import fr.umlv.ir2.compil.expressiontree.ExpFor;
import fr.umlv.ir2.compil.expressiontree.ExpGlobal;
import fr.umlv.ir2.compil.expressiontree.ExpIf;
import fr.umlv.ir2.compil.expressiontree.ExpInt;
import fr.umlv.ir2.compil.expressiontree.ExpLet;
import fr.umlv.ir2.compil.expressiontree.ExpLocal;
import fr.umlv.ir2.compil.expressiontree.ExpParam;
import fr.umlv.ir2.compil.expressiontree.ExpPrint;
import fr.umlv.ir2.compil.expressiontree.ExpText;
import fr.umlv.ir2.compil.expressiontree.ExpUnary;
import fr.umlv.ir2.compil.languageparser.analysis.DepthFirstAdapter;
import fr.umlv.ir2.compil.languageparser.node.AAndExprLogic;
import fr.umlv.ir2.compil.languageparser.node.ABoolConstants;
import fr.umlv.ir2.compil.languageparser.node.ABracketExprSimple;
import fr.umlv.ir2.compil.languageparser.node.ACallExprSimple;
import fr.umlv.ir2.compil.languageparser.node.ACallParams;
import fr.umlv.ir2.compil.languageparser.node.ACallParamsEnd;
import fr.umlv.ir2.compil.languageparser.node.ADivExprProduct;
import fr.umlv.ir2.compil.languageparser.node.AEqualExprComp;
import fr.umlv.ir2.compil.languageparser.node.AExprUnary;
import fr.umlv.ir2.compil.languageparser.node.AFloatConstants;
import fr.umlv.ir2.compil.languageparser.node.AForDeclarations;
import fr.umlv.ir2.compil.languageparser.node.AFunctionDeclarations;
import fr.umlv.ir2.compil.languageparser.node.AFunctionName;
import fr.umlv.ir2.compil.languageparser.node.AGlobalDeclarations;
import fr.umlv.ir2.compil.languageparser.node.AGlobalVar;
import fr.umlv.ir2.compil.languageparser.node.AGtExprComp;
import fr.umlv.ir2.compil.languageparser.node.AGteqExprComp;
import fr.umlv.ir2.compil.languageparser.node.AIdExprSimple;
import fr.umlv.ir2.compil.languageparser.node.AIfExprIfLet;
import fr.umlv.ir2.compil.languageparser.node.AIntConstants;
import fr.umlv.ir2.compil.languageparser.node.ALetExprIfLet;
import fr.umlv.ir2.compil.languageparser.node.ALocalVar;
import fr.umlv.ir2.compil.languageparser.node.ALtExprComp;
import fr.umlv.ir2.compil.languageparser.node.ALteqExprComp;
import fr.umlv.ir2.compil.languageparser.node.AMinusExprSum;
import fr.umlv.ir2.compil.languageparser.node.AModExprProduct;
import fr.umlv.ir2.compil.languageparser.node.AMultExprProduct;
import fr.umlv.ir2.compil.languageparser.node.ANotUnaryOp;
import fr.umlv.ir2.compil.languageparser.node.AOrExprLogic;
import fr.umlv.ir2.compil.languageparser.node.AParExprSimple;
import fr.umlv.ir2.compil.languageparser.node.AParamEnd;
import fr.umlv.ir2.compil.languageparser.node.AParams;
import fr.umlv.ir2.compil.languageparser.node.APlusExprSum;
import fr.umlv.ir2.compil.languageparser.node.APlusUnaryOp;
import fr.umlv.ir2.compil.languageparser.node.APrintDeclarations;
import fr.umlv.ir2.compil.languageparser.node.ASubUnaryOp;
import fr.umlv.ir2.compil.languageparser.node.ATextExprSimple;
import fr.umlv.ir2.compil.languageparser.node.AXorExprLogic;
import fr.umlv.ir2.compil.languageparser.node.Node;
import fr.umlv.ir2.compil.languageparser.node.TMcPrint;
import fr.umlv.ir2.compil.languageparser.node.TType;
import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.ErrorsManager;
import fr.umlv.ir2.compil.program.Program;
import fr.umlv.ir2.compil.program.Typable.Type;
import fr.umlv.ir2.compil.symbolstable.symbols.Function;
import fr.umlv.ir2.compil.symbolstable.symbols.Global;
import fr.umlv.ir2.compil.symbolstable.symbols.Local;
import fr.umlv.ir2.compil.symbolstable.symbols.Param;
import fr.umlv.ir2.compil.virtualmachine.CompileException;
import fr.umlv.ir2.compil.virtualmachine.CompileException.ErrorType;

/**
 * Visitor use by sableCC.
 * The visitor build the expression tree using the abstract tree of sableCC.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
//TODO Grammaire : rajouter le Modulo
//TODO Visiteur : nom implémenté Modulo
//TODO Tester : Si deux paramètres de fonction ont le même nom
//UPDATE Simplifier les paramètres (parcourir la listes dans la mathode du visiteur)
//UPDATE Enlever les attribut de la classe, les récupérer en dynamique dans la table des symboles
//UPDATE Mettre en place assertion si exps => EmptyStackFrame

public class ExpDepthFirst extends DepthFirstAdapter 
{

	/**
	 * Store the program
	 */
	private Program program; 
	/**
	 * Store current function
	 */
	private Function function; 
	/**
	 * Store current 'for' symbol
	 */
	private ExpFor forSymbol;
	/**
	 * Current Local Variable 
	 */
	private Local local;
	/**
	 * Number of local variable declared
	 */
	private int numVariable=1;
	/**
	 * Number of params
	 */
	private Stack<Integer> numCallParams = new Stack<Integer>();
    private int numDeclareParams = 0;
	/**
	 * Number of Main local variable
	 */
	private int numMainVariable=1;
	/**
	 * Store current Expression
	 */
	private Stack<Exp> exps;
	/**
	 * Use for print expression
	 */
	private int concat=0;
    private boolean underPrint=false;
    private int numberOfConcat=0;

	
	/**
	 * Create an ExpDepthFirst class with specified Symbol Table
	 * @param environnement Table Symbol
	 */
	public ExpDepthFirst(Program program)
	{
		this.program = program;
		this.exps = new Stack<Exp>(){
			public@Override Exp push(Exp item)
			{
				if(Config.verbose==true) System.out.println("[ExpPile]Push " + item.getClass().getSimpleName() + ":" +  item );
				return super.push(item);
			}
			public@Override Exp pop()
			{
				try	
                {
                    Exp exp = super.pop();
                    if(Config.verbose==true) System.out.println("[ExpPile]Pop " +  exp.getClass().getSimpleName() + ":" +  exp  );
                    return exp;
                }
                catch(EmptyStackException exception)
                {
                    return null;
                }
					

			}  
		};  
	}
	
	/*=======================================================================================================================
	 *                                           D E C L A R A T I O N  F U N C T I O N S
	 *=======================================================================================================================*/
	
	/**
	 * [out]Explore last parameter declaration
	 * Create new parameter.
	 * Add it to symbol table.
	 * Add it to current function.
	 */
	public@Override void outAParamEnd(AParamEnd node)
	{
		Type type;
		// For verbose
		super.outAParamEnd(node);
		
		// Create an new parameter
		type = getType(node.getType());
		if(type==Type.UNDEF)
		{
			type=Type.INT;
			ErrorsManager.addException(new CompileException(ErrorType.WARNING,
					node.getId().getLine(), node.getId().getPos(),  "Parameter of function '" + node.getId() + "' cast to INT"));
		}
		Param param= new Param(node.getId().getText(), numDeclareParams, type);
		numDeclareParams++;
		// [Visiblity] Add To symbol table
		program.getSymbolsTable().push(node.getId().getText(), param);
		
		// Add a param to function
		function.addParam(param);
		
	}
	
	/**
	 * [out]Explore parameter declaration
	 * Create new parameter.
	 * Add it to symbol table.
	 * Add it to current function.
	 */
	public@Override void outAParams(AParams node)
	{
		Type type;
		// For verbose
		super.outAParams(node);
		
		// Create an new parameter
		type = getType(node.getType());
		if(type==Type.UNDEF)
		{
			type=Type.INT;
			ErrorsManager.addException(new CompileException(ErrorType.WARNING,
					node.getId().getLine(), node.getId().getPos(),  "Parameter of function '" + node.getId() + "' cast to INT"));
		}
		Param param= new Param(node.getId().getText(),numDeclareParams,  type);
        numDeclareParams++;
		
		//[Visibility]Add To symbol table
		program.getSymbolsTable().push(node.getId().getText(), param);
		
		/* Ajoute le paramètre */
		function.addParam(param);
        
		
	}
	
	/**
	 * [in]Exploration of a function
	 * Create a new function.
	 * Add it in symbol table.
	 */
	public@Override void inAFunctionDeclarations(AFunctionDeclarations node)
	{        
		// For verbose
		super.inAFunctionDeclarations(node);
		
		Token functionName = ((AFunctionName)node.getFunctionName()).getId();
		
		numVariable=1;
        numDeclareParams=0;
		
		//[Visibility]Is already exist ?
		if(program.getSymbolsTable().get(functionName.getText())==null)
		{
			function = new Function(functionName.getText(), getType(node.getType()), ((AFunctionName)node.getFunctionName()).getId());

			//[Visibility]push into symbol table
			program.getSymbolsTable().push(functionName.getText(), function);
		}
		else
		{
			ErrorsManager.addException(new CompileException(ErrorType.ERROR,functionName.getLine(), functionName.getPos(),
					"Function already define: " + functionName.getText()));
		}
	}
	
	/**
	 * [out]Exploration of a function.
	 * Remove function from symbol table.
	 * Remove parameters from symbol table.
	 */
	public@Override void outAFunctionDeclarations(AFunctionDeclarations node)
	{
		// For verbose
		super.outAFunctionDeclarations(node);
		
		//Set the code for this function
		function.setCode(exps.pop());
        
        function.setLocals(numVariable);
		
		// Add to Program
		program.addFunction(function);
		
		//[Visibility]Pop parameters
		for(Param param: function.getParams())
		{
			try
			{
				program.getSymbolsTable().pop(param.getName());	
			}
			catch(NoSuchElementException exception)
			{
				ErrorsManager.addException(new CompileException(ErrorType.ASSERTION, 
						node.getMcFunction().getLine(), node.getMcFunction().getLine(), "Declare parameter '"+ param.getName() + "' not added to Symbol Table"));
			}
		}   
        numDeclareParams=0;
	}
	
	/*=======================================================================================================================
	 *                                            F O R  L O O P
	 *=======================================================================================================================*/
	
	/**
	 * [in]'for' loop
	 * Create an new 'For' symbol (For + Print)
	 * Add this symbol to the program
	 */
	public@Override void inAForDeclarations(AForDeclarations node)
	{
		//For verbose
		super.inAForDeclarations(node);
		
		//Define local variable
		Local local = new Local(node.getId().getText(), Type.INT, numMainVariable);
		
		//[Visibility]Add local variable of for
		// TODO : Typage des variables du for
		program.getSymbolsTable().push(node.getId().getText(), local);
		numMainVariable++;
        program.setNumMainVariables(numMainVariable);
		
		forSymbol = new ExpFor(local);
		
		// add loop to the program
		program.getExecutionList().addCompilable(forSymbol);
	}
	
	/**
	 * [out]'for' loop.
	 * Remove three expression from stack
	 * Set the exp associate to the 'for' symbol
	 */
	public@Override void outAForDeclarations(AForDeclarations node)
	{
		//For verbose
		super.outAForDeclarations(node);
		//Suppression de la pile des expressions necessaire
		ExpPrint action = new ExpPrint(node.getMcPrint()); 
		
        while(numberOfConcat>0)
        {
            action.addExpToPrint(exps.pop());
            numberOfConcat--;
        }
		
        Exp end = exps.pop();
		Exp start = exps.pop();
		
		//Set all code associate with for
		forSymbol.setEnd(end);
		forSymbol.setStart(start);
		forSymbol.setAction(action);
		
		//[Visibility]pop local variable of for
		program.getSymbolsTable().pop(node.getId().getText());
        
        concat=0;
        underPrint = false;
	}
	
	/*=======================================================================================================================
	 *                                            P R I N T
	 *=======================================================================================================================*/
	
	/**
	 *[in]Print an expression
	 *Add it to execution program list 
	 */
	public@Override void inAPrintDeclarations(APrintDeclarations node)
	{ 
		//For verbose
		super.inAPrintDeclarations(node);
		underPrint=true;
	}
	/**
	 * [out]Print an expression.
	 * pop code from exp stack. 
	 */
	public@Override void outAPrintDeclarations(APrintDeclarations node)
	{
		//For verbose
		super.outAPrintDeclarations(node);
		
		//Suppression de la pile des expressions
		ExpPrint print = new ExpPrint(node.getMcPrint());
		
		program.getExecutionList().addCompilable(print);
		while(numberOfConcat>0)
        {
            print.addExpToPrint(exps.pop());
            numberOfConcat--;
		}
        concat=0;
        underPrint = false;
	}
	
	/*=======================================================================================================================
	 *                                            V A R A I B L E S  D E C L A R A T I O N S
	 *=======================================================================================================================*/
	
	/**
	 * Déclaration de variable globale
	 */
	public@Override void outAGlobalDeclarations(AGlobalDeclarations node)
	{
		//For verbose
		super.outAGlobalDeclarations(node);
		
		Token token   = ((AGlobalVar)node.getGlobalVar()).getId();
		Global global;
		
		
		//Is already Exist ?
		if((global=(Global)program.getSymbolsTable().get(token.getText()))==null)
		{
			global  = new Global(token.getText(), getType(((AGlobalVar)node.getGlobalVar()).getType()), ((AGlobalVar)node.getGlobalVar()).getId());
			
			//[Visibility] Add to symbol table
			program.getSymbolsTable().push(token.getText(), global);
			
			// Add to Program
			program.addGlobal(global);
		}
		else
		{
			global  = new Global(token.getText(), getType(((AGlobalVar)node.getGlobalVar()).getType()),  ((AGlobalVar)node.getGlobalVar()).getId());
		}
		
		global.setInitCode(exps.pop());
		
		/* Add to initValue */
		program.getExecutionList().addCompilable(global);
		
	}
	
	
	/********************************** Empilage d'expression complexe (dépilage + empilage) *********************************/
	
	/**
	 * Expression If Expr then Expr Else Expr
	 */
	public@Override void outAIfExprIfLet(AIfExprIfLet node)
	{
		//For verbose
		super.outAIfExprIfLet(node);
          
		
		/* Dépile */
		Exp conditionFalse =  exps.pop();
		Exp conditionTrue = exps.pop();
		Exp condtion = exps.pop();
		
		ExpIf expif = new ExpIf(condtion, conditionTrue, conditionFalse, node.getMcIf());
		
		/* Empile */
		exps.push(expif);
        if(underPrint==true)
        {
            concat--;
        }
	}
	
	
	public@Override void outACallExprSimple(ACallExprSimple node)
	{
		//For verbose
		super.outACallExprSimple(node);
   
		LinkedList<Exp> params = new LinkedList<Exp>();
	
        Function function = (Function)program.getSymbolsTable().get(node.getId().getText());

        if(function==null)
        {
            ErrorsManager.addException(new CompileException(ErrorType.ERROR,node.getId().getLine(), node.getId().getPos(),
                    "function undefine or is declared after using it :" + node.getId()),true);
        }
        
        int numParams = this.numCallParams.pop();
        
        //Check number of params are the same
        if(numParams!= function.getParams().size())
        {
            ErrorsManager.addException(new CompileException(ErrorType.ERROR,node.getId().getLine(), node.getId().getPos(),
                    "Number of declared parameters in function " + node.getId() + " mismatch with call parameters, declared:" + function.getParams().size()
                     + ", called : " + numParams),false);
        }
        
		// Dépile
		for (int i = 0; i < function.getParams().size(); i++)
		{
			params.addFirst(exps.pop());
		}
		
		//Retrouve le nom de la fonction
		
		
		// Empile
		ExpCall expcall = new ExpCall(params, function, node.getId());
        exps.push(expcall);
        
        
        if(underPrint==true)
        {
            concat--;
        }
	}
	
	
	/**
	 * Expression Let X =...
	 */
	public@Override void outALetExprIfLet(ALetExprIfLet node)
	{
		//For verbose
		super.outALetExprIfLet(node);
        //concat = true;
		
		//UPDATE : Mettre le nom de la variable
		/* Dépilage */
		Exp code = exps.pop();
		Exp value = exps.pop();
		ExpLet let = new ExpLet(local, value, code, node.getMcLet());
		/* Empilage */
		exps.push(let);
		
		Token token = ((ALocalVar)node.getLocalVar()).getId();
		
		//[Visibility]Remove from symbol table
		program.getSymbolsTable().pop(token.getText());
        if(underPrint==true)
            {
                concat--;
            }
	}
	
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inALetF(fr.umlv.ir2.compil.parser.node.ALetF)
	 */
	public@Override void inALetExprIfLet(ALetExprIfLet node)
	{
		//For verbose
		super.inALetExprIfLet(node);
        if(underPrint==true)
        {
            concat++;
        }
		
		Token token = ((ALocalVar)node.getLocalVar()).getId();
		
		local = new Local(token.getText(),getType(((ALocalVar)node.getLocalVar()).getType()),  numVariable);
		
		numVariable++;
		
		//[Visibility]Add to symbol table
		program.getSymbolsTable().push(token.getText(),local);
	}
	
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inABracketExprSimple(fr.umlv.ir2.compil.parser.node.ABracketExprSimple)
     */
    public void inABracketExprSimple(ABracketExprSimple node)
    {
        super.inABracketExprSimple(node);
        if(underPrint=true)
        {
            concat++;
        }
        
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#outABracketExprSimple(fr.umlv.ir2.compil.parser.node.ABracketExprSimple)
     */
    public void outABracketExprSimple(ABracketExprSimple node)
    {
        super.outABracketExprSimple(node);
        ExpCastFloat floatExp = new ExpCastFloat(exps.pop(), node.getLBracket());
        exps.push(floatExp);
        if(underPrint==true)
        {
            concat--;
        }
    }
	
	/**
	 * Expression exp / Exp
	 */
    public@Override void outADivExprProduct(ADivExprProduct node)
    {
    
		//For verbose
		super.outADivExprProduct(node);
		
		ExpBin divExp = new ExpBin(exps.pop(), ExpBin.Operator.DIV, exps.pop(), node.getDiv());
		exps.push(divExp);
	}
	
	/**
	 * Expression exp - exp
	 */
	public@Override void outAMinusExprSum(AMinusExprSum node)
	{
		//For verbose
		super.outAMinusExprSum(node);
		
		ExpBin minusExp = new ExpBin(exps.pop(), ExpBin.Operator.SUB, exps.pop(), node.getMinus());
		exps.push(minusExp);
	}
    
	/**
	 * Expression exp * exp
	 */
	public@Override void outAMultExprProduct(AMultExprProduct node)
	{
		//For verbose
		super.outAMultExprProduct(node);
		
		ExpBin multExp = new ExpBin(exps.pop(), ExpBin.Operator.MULT, exps.pop(), node.getMult());
		exps.push(multExp);
	}
    
    /**
     * Expression exp % exp
     */
    public@Override void outAModExprProduct(AModExprProduct node)
    {
        //For verbose
        super.outAModExprProduct(node);
        
        ExpBin multExp = new ExpBin(exps.pop(), ExpBin.Operator.MOD, exps.pop(), node.getMod());
        exps.push(multExp);
    }
	
	/**
	 * Expression exp + exp
	 */
	public@Override void outAPlusExprSum(APlusExprSum node)
	{
		//For verbose
		super.outAPlusExprSum(node);
        if(concat!=0 || underPrint==false)
		{
            ExpBin plusExp = new ExpBin(exps.pop(), ExpBin.Operator.ADD, exps.pop(), node.getPlus());
			exps.push(plusExp);
		}
        else
        {
           numberOfConcat++;
        }
	}
	
	/**
	 * Expression Exp && Exp
	 */
    public@Override void outAAndExprLogic(AAndExprLogic node)
    {
        super.outAAndExprLogic(node);
    
		ExpBin plusExp = new ExpBin(exps.pop(), ExpBin.Operator.AND, exps.pop(), node.getAnd());
		exps.push(plusExp);
	}
	
	/**
	 * Expression Exp || Exp
	 */
	public void outAOrExprLogic(AOrExprLogic node)
	{
		//For verbose
		super.outAOrExprLogic(node);
		
		ExpBin plusExp = new ExpBin(exps.pop(), ExpBin.Operator.OR, exps.pop(), node.getOr());
		exps.push(plusExp);
	}
    
    /**
     * Expression Exp ^^ Exp
     */
    public void outAXorExprLogic(AXorExprLogic node)
    {
        //For verbose
        super.outAXorExprLogic(node);
        
        ExpBin plusExp = new ExpBin(exps.pop(), ExpBin.Operator.XOR, exps.pop(), node.getXor());
        exps.push(plusExp);
    }
    
	/**
	 * Expression Unaire
	 */
	public void outAExprUnary(AExprUnary node)
	{
		//For verbose
		super.outAExprUnary(node);
		
		Token token = null;
		ExpUnary.UnaryOperator operator = ExpUnary.UnaryOperator.ADD;
		ExpUnary unaryExp;
		
		if(node.getUnaryOp()!=null)
		{
			if(node.getUnaryOp().getClass()==APlusUnaryOp.class)
				
			{
				token = ((APlusUnaryOp)node.getUnaryOp()).getPlus();
				operator = ExpUnary.UnaryOperator.ADD;
			}
			else if(node.getUnaryOp().getClass()==ASubUnaryOp.class)
			{
				token = ((ASubUnaryOp)node.getUnaryOp()).getMinus();
				operator = ExpUnary.UnaryOperator.SUB;
			}
			else if(node.getUnaryOp().getClass()==ANotUnaryOp.class)
			{
				token = ((ANotUnaryOp)node.getUnaryOp()).getMcNot();
				operator = ExpUnary.UnaryOperator.NOT;
			}
			
			if(token!=null)
			{
				unaryExp = new ExpUnary(operator , exps.pop(), token);
				exps.push(unaryExp);
			}
			else
			{
				throw new AssertionError("[Error]token for unary expression can not be null!!!");
			}
		}
		
	}
	
	/************************* Comparaison ****************************/
	
	/**
	 * Operation Binaire      
	 *
	 */
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.languageparser.analysis.DepthFirstAdapter#outAEqualExprComp(fr.umlv.ir2.compil.languageparser.node.AEqualExprComp)
     */
    public@Override void outAEqualExprComp(AEqualExprComp node)
    {
        super.outAEqualExprComp(node);
        ExpComp expComp = new ExpComp(exps.pop(), ExpComp.Comparator.EQUAL ,exps.pop(), node.getEqual());
        exps.push(expComp);
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.languageparser.analysis.DepthFirstAdapter#outAGteqExprComp(fr.umlv.ir2.compil.languageparser.node.AGteqExprComp)
     */
    public@Override void outAGteqExprComp(AGteqExprComp node)
    {
        super.outAGteqExprComp(node);
        ExpComp expComp = new ExpComp(exps.pop(), ExpComp.Comparator.GREATER_THAN_EQUAL ,exps.pop(), node.getGteq());
        exps.push(expComp);
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.languageparser.analysis.DepthFirstAdapter#outAGtExprComp(fr.umlv.ir2.compil.languageparser.node.AGtExprComp)
     */
    public@Override void outAGtExprComp(AGtExprComp node)
    {
        super.outAGtExprComp(node);
        ExpComp expComp = new ExpComp(exps.pop(), ExpComp.Comparator.GREATER_THAN ,exps.pop(), node.getGt());
        exps.push(expComp);
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.languageparser.analysis.DepthFirstAdapter#outALteqExprComp(fr.umlv.ir2.compil.languageparser.node.ALteqExprComp)
     */
    public@Override void outALteqExprComp(ALteqExprComp node)
    {
        super.outALteqExprComp(node);
        ExpComp expComp = new ExpComp(exps.pop(), ExpComp.Comparator.LOWER_THAN_EQUAL ,exps.pop(), node.getLteq());
        exps.push(expComp);
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.languageparser.analysis.DepthFirstAdapter#outALtExprComp(fr.umlv.ir2.compil.languageparser.node.ALtExprComp)
     */
    public@Override void outALtExprComp(ALtExprComp node)
    {
        super.outALtExprComp(node);
        ExpComp expComp = new ExpComp(exps.pop(), ExpComp.Comparator.LOWER_THAN ,exps.pop(), node.getLt());
        exps.push(expComp);
    }
	
	/************************** Empilage d'expression 'terminales' **************************/
	
	/**
	 * Expression Texte
	 * Add a cosntant to programm
	 */
	public@Override void outATextExprSimple(ATextExprSimple node)
	{
		//For verbose
		super.outATextExprSimple(node);
		
		ExpText text = new ExpText(node.getTexte().getText(), node.getTexte());
		exps.push(text);
        
		program.addConstant(text);
		
	}
	
	
	/**
	 * Expression id
	 */
	public@Override void outAIdExprSimple(AIdExprSimple node)
	{
		//For verbose
		super.outAIdExprSimple(node);
		
		Object variable = program.getSymbolsTable().get(node.getId().getText());
		
		if(variable!=null)
		{
			if(variable.getClass()==Global.class)
				exps.push(new ExpGlobal((Global)variable, node.getId()));
			else if(variable.getClass()==Local.class)
				exps.push(new ExpLocal((Local)variable, node.getId()));
			else if(variable.getClass()==Param.class)
				exps.push(new ExpParam((Param) variable, node.getId()));
			/* Object unknow!!!! ???? */
			else
			{
				ErrorsManager.addException(new CompileException(ErrorType.ERROR,node.getId().getLine(), node.getId().getPos(),
						"Function or variable undefine/not visible:" + node.getId()),true);
			}
		}
		/* Symbol not found in symbol table */
		else
		{
			ErrorsManager.addException(new CompileException( ErrorType.ERROR,node.getId().getLine(), node.getId().getPos(),
					"Function or variable undefine/not visible:" + node.getId()), true);
		}  
	}
	
	/**
	 *  Expression boolean
	 */
	public@Override void outABoolConstants(ABoolConstants node)
	{
		//For verbose
		super.outABoolConstants(node);
		
		exps.push(new ExpBool(node.getBool().getText(), node.getBool()));
	}
	
	/**
	 * Expression Int
	 */
	public@Override void outAIntConstants(AIntConstants node)
	{
		//For verbose
		super.outAIntConstants(node);
		
		exps.push(new ExpInt(node.getInt()));
	}
	
	/**
	 * Expression Float
	 */
	public@Override void outAFloatConstants(AFloatConstants node)
	{
		//For verbose
		super.outAFloatConstants(node);
		
		exps.push(new ExpFloat(node.getFloat()));
	}
	
	/**
	 * Return the type of the id, depends of TType
	 * @param type type to convert in Type Enum
	 * @return Type enum
	 */
	private static Type getType(TType type)
	{
		if(type==null)
		{
			return Type.UNDEF;
		}
		if(type.getText().compareTo(":bool")==0)
		{
			return Type.BOOL;
		}
		else if(type.getText().compareTo(":int")==0)
		{
			return Type.INT;
		}
		else if(type.getText().compareTo(":float")==0)
		{
			return Type.FLOAT;
		}
		return Type.UNDEF;
	}
	
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#defaultIn(fr.umlv.ir2.compil.parser.node.Node)
	 */
	public void defaultIn(Node node) {
		if (Config.verbose==true) System.out.println("[In]  " + node.getClass().getSimpleName() + " : "+ node);
	}
	
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#defaultOut(fr.umlv.ir2.compil.parser.node.Node)
	 */
	public void defaultOut(Node node) {
		if (Config.verbose==true) System.out.println("[out] " + node.getClass().getSimpleName() + " : "+ node);
	}
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcPrint(fr.umlv.ir2.compil.parser.node.TMcPrint)
     */
    public void caseTMcPrint(TMcPrint node)
    {
        underPrint = true;
        numberOfConcat++;
        
        super.caseTMcPrint(node);
               
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inAParExprSimple(fr.umlv.ir2.compil.parser.node.AParExprSimple)
     */
    public void inAParExprSimple(AParExprSimple node)
    {
        super.inAParExprSimple(node);
        if(underPrint==true)
        {
            concat++;
        }

    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#outAParExprSimple(fr.umlv.ir2.compil.parser.node.AParExprSimple)
     */
    public void outAParExprSimple(AParExprSimple node)
    {
        super.outAParExprSimple(node);
        if(underPrint==true)
        {
            concat--;
        }
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inAIfExprIfLet(fr.umlv.ir2.compil.parser.node.AIfExprIfLet)
     */
    public void inAIfExprIfLet(AIfExprIfLet node)
    {
        super.inAIfExprIfLet(node);
        if(underPrint==true)
        {
            concat++;
        }
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inACallExprSimple(fr.umlv.ir2.compil.parser.node.ACallExprSimple)
     */
    public void inACallExprSimple(ACallExprSimple node)
    {
        super.inACallExprSimple(node);
        numCallParams.push(0);
        if(underPrint==true)
        {
            concat++;
        }
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inACallParams(fr.umlv.ir2.compil.parser.node.ACallParams)
     */
    public void inACallParams(ACallParams node)
    {
        super.inACallParams(node);
        int num= numCallParams.pop()+1;
        numCallParams.push(num);
        
    }
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inACallParamsEnd(fr.umlv.ir2.compil.parser.node.ACallParamsEnd)
     */
    public void inACallParamsEnd(ACallParamsEnd node)
    {
        super.inACallParamsEnd(node);
        int num= numCallParams.pop()+1;
        numCallParams.push(num);
    }
}
