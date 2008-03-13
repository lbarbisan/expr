/*
 * Created on 24 janv. 2005
 */
package fr.umlv.ir2.compil.program;

import java.util.ArrayList;
import java.util.logging.ErrorManager;

import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.program.Typable.Type;
import static fr.umlv.ir2.compil.program.Typable.Type.BOOL;
import static fr.umlv.ir2.compil.program.Typable.Type.FLOAT;
import static fr.umlv.ir2.compil.program.Typable.Type.INT;
import static fr.umlv.ir2.compil.program.Typable.Type.TEXT;
import static fr.umlv.ir2.compil.program.Typable.Type.UNDEF;
import fr.umlv.ir2.compil.virtualmachine.CompileException;
import fr.umlv.ir2.compil.virtualmachine.VirtualMachine;
import fr.umlv.ir2.compil.virtualmachine.CompileException.ErrorType;

/**
 * Manage program error.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class TypeManager
{
	public static Type getComptibleType(Type type1, Type type2)
	{
        ArrayList<Type> list = new ArrayList<Type>();
        list.add(type1);
        list.add(type2);
        return getComptibleType(list);
	}
    
    private static Type getComptibleType(ArrayList<Type> list)
    {
        Type selectType = Type.UNDEF;
        for(Type type : list)
        {
            switch(type)
            {
                case BOOL:
                    selectType =  getCompatibleBool(selectType, null);
                    break;
                case FLOAT:
                    selectType =  getCompatibleFloat(selectType, null);
                    break;
                case UNDEF:
                    selectType =  getCompatibleUndef(selectType, null);
                    break;
                case INT:
                    selectType =  getCompatibleInt(selectType, null);
                    break;
                case TEXT:
                    selectType =  getCompatibleText(selectType, null);
                    break;
            }
        }
        return selectType;
    }
    
    private static Type getCompatibleBool(Type to,  Token refToken)
    {
        switch(to)
        {
        case BOOL:
            return Type.BOOL;
        case FLOAT:
            return Type.BOOL;
        case INT:
            return Type.BOOL;
        case TEXT:
            return Type.TEXT;
        case UNDEF:
            return Type.BOOL;
        default:
            throw new IllegalStateException("Unknow Type");
        }    
    }
    
    private static Type getCompatibleInt(Type to,  Token refToken)
    {
        switch(to)
        {
        case BOOL:
            return Type.BOOL;
        case FLOAT:
            return Type.FLOAT;
        case INT:
            return Type.INT;
        case TEXT:
            return Type.TEXT;
        case UNDEF:
            return Type.INT;
        default:
            throw new IllegalStateException("Unknow Type");
        }    
    }
    
    private static Type getCompatibleFloat(Type to,  Token refToken)
    {
        switch(to)
        {
        case BOOL:
            return Type.BOOL;
        case FLOAT:
            return Type.FLOAT;
        case INT:            
            return Type.FLOAT;
        case TEXT:
            return Type.TEXT;
        case UNDEF:         
            return Type.FLOAT;
        default:
            throw new IllegalStateException("Unknow Type");
        }    
    }
    
    private static Type getCompatibleUndef(Type to,  Token refToken)
    {
        return to;    
    }
    
    private static Type getCompatibleText(Type to, Token refToken)
    {
        return Type.TEXT;
    }
      
    private static Type convertion(Type from, Type to, Token refToken)
    {
        Type type;
        switch(from)
        {
        case BOOL:
            type = convertBoolTo(to, refToken); 
            break;
        case FLOAT:
            type = convertFloatTo(to, refToken);
            break;
        case INT:
            type = convertIntTo(to, refToken);
            break;
        case TEXT:
            type =  convertTextTo(to, refToken);
            break;
        case UNDEF:
            type  = convertUndefTo(to, refToken);
            break;
        default:
            throw new IllegalStateException("Unknow Type");
        }   
        return type;
    }
    
    private static Type convertBoolTo(Type to,  Token refToken)
    {
        switch(to)
        {
        case BOOL:
            return Type.BOOL;
        case FLOAT:
        	if(refToken!=null) ErrorsManager.addException(new CompileException(ErrorType.ERROR,
                    refToken.getLine(), refToken.getPos(),  
                    refToken + ": Can't convert from 'BOOL' to '" + to  + "'"));
            return Type.FLOAT;
        case INT:
        	if(refToken!=null) ErrorsManager.addException(new CompileException(ErrorType.ERROR,
                    refToken.getLine(), refToken.getPos(),  
                    refToken + ": Can't convert from 'BOOL' to '" + to  + "'"));
            return Type.INT;
        case TEXT:
            return Type.TEXT;
        case UNDEF:
            return Type.BOOL;
        default:
            throw new IllegalStateException("Unknow Type");
        }    
    }
    
    private static Type convertIntTo(Type to,  Token refToken)
    {
        switch(to)
        {
        case BOOL:
        	if(refToken!=null) ErrorsManager.addException(new CompileException(ErrorType.WARNING,
                    refToken.getLine(), refToken.getPos(),  
                    refToken + ": Implicit conversion from 'INT' to '" + to  + "'"));
            return Type.BOOL;
        case FLOAT:
        	if(refToken!=null) ErrorsManager.addException(new CompileException(ErrorType.WARNING,
                    refToken.getLine(), refToken.getPos(),  
                    refToken + ": Implicit conversion from 'INT' to '" + to  + "'"));
            return Type.FLOAT;
        case INT:
            return Type.INT;
        case TEXT:
            return Type.TEXT;
        case UNDEF:
            return Type.INT;
        default:
            throw new IllegalStateException("Unknow Type");
        }    
    }
    
    private static Type convertFloatTo(Type to,  Token refToken)
    {
        switch(to)
        {
        case BOOL:
        	if(refToken!=null) ErrorsManager.addException(new CompileException(ErrorType.WARNING,
                    refToken.getLine(), refToken.getPos(),          		
                    refToken + ": Implicit conversion from 'FLOAT' to '"  + to + "'"));
            return Type.BOOL;
        case FLOAT:
            return Type.FLOAT;
        case INT:
        	if(refToken!=null) ErrorsManager.addException(new CompileException(ErrorType.ERROR,
            		refToken.getLine(), refToken.getPos(),          		
            		refToken + ": Can't convert from 'FLOAT' to '"  + to + "'"));
            return Type.FLOAT;
        case TEXT:
            return Type.TEXT;
        case UNDEF:        	
            return Type.FLOAT;
        default:
            throw new IllegalStateException("Unknow Type");
        }    
    }
    
    private static Type convertUndefTo(Type to,  Token refToken)
    {
    	return to;    
    }
    
    private static Type convertTextTo(Type to, Token refToken)
    {
    	if(refToken!=null) ErrorsManager.addException(new CompileException(ErrorType.ERROR,
        		refToken.getLine(), refToken.getPos(),          		
        		refToken + ": Can't convert from 'TEXT' to '"  + to + "'"));

    	return to;
    }
    
    public static void applyConversion(VirtualMachine vm, Type from, Type to, Token refToken)
    {
        if(from==to)
            return;
        
        convertion(from , to, refToken);
        
        if(from==UNDEF || to==UNDEF)
                return;
        
       //UPDATE : Instruction de compilation à faire
       if(from==Type.INT && to==Type.BOOL)
       {
       		vm.i2b();
       }
       else if(from==Type.FLOAT && to==Type.INT)
       {
       		vm.f2I();
       }
       else if(from==Type.INT && to==Type.FLOAT)
       {
       		vm.i2F();
       }
       else if(from==Type.FLOAT && to==Type.BOOL)
       {
            vm.f2I();
            vm.i2b();
       }
       else
       {
           ErrorsManager.addException(new CompileException(
                  ErrorType.ASSERTION, refToken.getLine(), refToken.getPos(),
                  "Conversion from '" + from + "' to '" + to + "' not implemented"
           ));
       }
           
    }
    
    public static Type getDefaultType()
    {
    	return Type.INT;
    }
}
