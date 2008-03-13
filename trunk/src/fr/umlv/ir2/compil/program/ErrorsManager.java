/*
 * Created on 6 févr. 2005
 */
package fr.umlv.ir2.compil.program;

import java.util.Stack;

import fr.umlv.ir2.compil.virtualmachine.CompileException;
import fr.umlv.ir2.compil.virtualmachine.CompileException.ErrorType;

/**
 * Manage Compilation Errors.
 * 
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class ErrorsManager {
	
	private static Stack<CompileException> errors  = new Stack<CompileException>();
	private static boolean mustStop=false;
	
	/**
	 * Add a compilation exception to compile list
	 * @param exception
	 */
	public static void addException(CompileException exception, boolean haltIfErrorAndAssertions)
	{
		addException(exception);
		if(haltIfErrorAndAssertions==true)
		{
			if(exception.getErrorType()==ErrorType.ASSERTION ||
					exception.getErrorType()==ErrorType.ERROR)
			{
				ShowException();
				System.exit(1);
			}
		}
	}
	
	
	/**
	 * Add a compilation exception to compile list
	 * @param exception
	 */
	public static void addException(CompileException exception)
	{
		if(exception.getErrorType()==ErrorType.ASSERTION ||
				exception.getErrorType()==ErrorType.ERROR)
		{
			mustStop=true;
		}
		errors.push(exception);
	}
	
	/**
	 * Show All Exceptions
	 */
	public static void ShowException()
	{
		System.out.println("\n" + errors.size() + " error(s)/warning(s)");
		for(CompileException exception : errors)
		{
			System.out.println(exception.getMessage());
		}
	}
	
	/**
	 * Return if an error or an assertion as occurs. 
	 * @return true: an error or assertion as occurs
	 */
	public static boolean isMustStop()
	{
		return mustStop;
	}
	
	/**
	 * Clean Errors lists
	 *
	 */
	public static void clean()
	{
		mustStop=false;
		errors = new Stack<CompileException>();
	}
}
