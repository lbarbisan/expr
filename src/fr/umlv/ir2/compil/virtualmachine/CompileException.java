/*
 * Created on 28 janv. 2005
 */
package fr.umlv.ir2.compil.virtualmachine;

/**
 * Generate an compile exception.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class CompileException extends Exception
{
    public enum ErrorType
    {
        ASSERTION,
        INFORMATION,
        WARNING,
        ERROR
    }
    
    private ErrorType errorType;
    
    /**
     * Create an CompileException
     * @param line line of error
     * @param column column of error
     * @param message message error
     */
    public CompileException(ErrorType errorType, int line, int column, String message)
    {
    	super("[" + errorType + "](" + line  +  "," + column +  ") " + message);
    	this.errorType = errorType;
    }
    
	/**
	 * @return Returns the errorType.
	 */
	public ErrorType getErrorType() {
		return errorType;
	}
}
