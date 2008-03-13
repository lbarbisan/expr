package fr.umlv.ir2.compil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

import fr.umlv.ir2.compil.cmdlineparser.CmdLineDepth;
import fr.umlv.ir2.compil.cmdlineparser.Config;
import fr.umlv.ir2.compil.expressiontree.Exp;
import fr.umlv.ir2.compil.html.HtmlDepthFirst;
import fr.umlv.ir2.compil.languageparser.ExpDepthFirst;
import fr.umlv.ir2.compil.languageparser.lexer.Lexer;
import fr.umlv.ir2.compil.languageparser.lexer.LexerException;
import fr.umlv.ir2.compil.languageparser.node.EOF;
import fr.umlv.ir2.compil.languageparser.node.Node;
import fr.umlv.ir2.compil.languageparser.node.Token;
import fr.umlv.ir2.compil.languageparser.parser.Parser;
import fr.umlv.ir2.compil.languageparser.parser.ParserException;
import fr.umlv.ir2.compil.program.ErrorsManager;
import fr.umlv.ir2.compil.program.Program;
import fr.umlv.ir2.compil.virtualmachine.CompileException;
import fr.umlv.ir2.compil.virtualmachine.MachineCode;
import fr.umlv.ir2.compil.virtualmachine.PseudoCode;
import fr.umlv.ir2.compil.virtualmachine.CompileException.ErrorType;

/*
 * Created on 15 sept. 2004
 *
 */

/**
 * Main class. 
 * Use to launch application.
 * @author Olivier Boitel
 * @author laurent barbisan
 */
//TODO : Creation du fichier HTML
//UPDATE : Passer For en ExpFor
//FIXME : Mise en place fiche de test
//FIXME : Eclairsissement du typage
public class Expc 
{
    private static File asmFile=null;
    private static File delFile=null;
    private static CompileException lastException=null;
    
    public static void main(String[] args) 
    {
        
        Lexer lexer;                        				/* file lexer */
        Token token;                       					/* token in analysis */
        Parser parser;                      				/* file parser */
        
        /* Parse the command line using sableCC */
        StringBuffer cmdLine = new StringBuffer();
        for(String arg : args)
        {
            cmdLine.append("\"").append(arg).append("\"");
        }
        CmdLineDepth.parseCommandLine(cmdLine.toString()); 
        
        /* Show Help if needed */
        if(Config.help || Config.fileList.size()==0)
        {
            Config.showHelp();
            System.exit(1);
        }
        
        /* Tente d'ouvrir le fichier */
        for(String file :Config.fileList)
        {
            try
            {
                System.out.println("[ACTION]Lexical Analysis...");
                lexer = new Lexer(new PushbackReader(new FileReader(file), 10000));
                if(Config.onlyShowLexem==true)
                {
                    while (!((token=lexer.next()) instanceof EOF))
                    {
                        System.out.print(token.getClass().getSimpleName() + ":");
                        System.out.println(token);
                    }
                }
                if(Config.onlyShowLexem==false)
                {
                    System.out.println("[ACTION]Syntax Analysis...");
                    lexer = new Lexer(new PushbackReader(new FileReader(file), 10000));
                    parser = new Parser(lexer);
                    
                    Program program = new Program();
                    Node root = parse(parser);
                    
                   stopProgram();
                   /* Generate HTML */
                    if(Config.createHtml==true)
                    {
                        System.out.println("[ACTION]Generate HTML...");
                        File htmlFile = new File(Config.outputFileHtml);
                        HtmlDepthFirst htmlDepthFirst =  new HtmlDepthFirst(htmlFile);
                        root.apply(htmlDepthFirst);
                        htmlDepthFirst.write();
                    }
                    root.apply(new ExpDepthFirst(program));
                    stopProgram();
                    if(Config.virutalMachinePseudoCode==true)
                    {
                        System.out.println("[ACTION]Generate Pseudo-code...");
                        PseudoCode pc =  new PseudoCode();
                        program.compile(pc, Exp.Type.UNDEF);
                        
                    }
                    
                    if(Config.x86Code==true && Config.writeInFileAssembler==true)
                    {
                        System.out.println("[ACTION]Generate assembler...");
                        asmFile = new File(Config.outputFileAssembler);
                        MachineCode pc =  new MachineCode(asmFile);
                        program.compile(pc, Exp.Type.UNDEF);
                        pc.finalize();
                    }
                    
                    if(Config.x86Code==true && Config.writeInFileAssembler==false)
                    {
                        System.out.println("[ACTION]Generate assembler...");
                        MachineCode pc =  new MachineCode();
                        program.compile(pc, Exp.Type.UNDEF);
                        pc.finalize();
                    }	
                    if(Config.showSymbols==true)
                    {
                        System.out.println("[ACTION]Show Symbols...");
                        System.out.println(program);
                    }
                    
                    stopProgram();
                    System.out.println("[ACTION]Show error(s)/warning(s)");
                    ErrorsManager.ShowException();
                    System.out.println("[INFORMATION]Program sucessfully finished.");
                }
            }
            catch (FileNotFoundException eFileNotFound)
            {
                System.out.println("[ERROR]Impossible de trouver le fichier \"" + file + "\".");
                System.out.println("Rajouter l'option --help pour plus d'information");
                System.exit(1);
            }
    		catch (LexerException eLexerException)
            {
                System.out.println("[ERROR]Erreur d'analyse lexical: " + eLexerException.getMessage());   
                System.out.println("Rajouter l'option --help pour plus d'information");
                System.exit(1);
            }
            catch (IOException eIOMistake)
            {
                System.err.println(eIOMistake.getMessage());
                System.out.println("[ERROR]Erreur generale d'Entree/Sortie");
                System.exit(1);
            }
         
        }
    }
    
    private static Node parse(Parser parser)
    {
        Node root = null;
		try 
		{
			root = parser.parse();
		}    
		catch (LexerException eLexer)
        {
			ErrorsManager.addException(new CompileException(ErrorType.ERROR,0, 0, 
					"[ERROR]Erreur d'analyse lexical: " + eLexer.getMessage())); 
            parse(parser);
        }
        catch (ParserException eParser)
        {
            
            CompileException exception = new CompileException(ErrorType.ERROR,eParser.getToken().getPos(), eParser.getToken().getLine(), "[ERROR]Erreur de syntaxe sur: ' " +  eParser.getToken() + "' "+ 
                    eParser.getMessage());
            
            
            if(lastException!=null)
            {
                if(lastException.getMessage().equals(exception.getMessage())==false) 
                {
                    ErrorsManager.addException(exception);
                    lastException = exception;
                    parse(parser);
                }
            }
            else
            {
                ErrorsManager.addException(exception);
                lastException = exception;
                parse(parser);
            }
            
        }
        catch (IOException eIOMistake)
        {
            System.err.println(eIOMistake.getMessage());
            System.out.println("[ERROR]Erreur generale d'Entree/Sortie");
            System.exit(1);
        }
        return root;
        
    }
 
    public static void stopProgram()
    {
        if(ErrorsManager.isMustStop()==true)
        {
            System.out.println("[ACTION]Show error(s)/warning(s)");
            ErrorsManager.ShowException();
        
            System.out.println("[ACTION]Deletes files...");
            File delFile;
            try 
            {
                if(asmFile!=null) 
                {
                    asmFile.delete();
                }
            }
            catch(NullPointerException exception){}
            try 
            {
                delFile = new File(Config.outputFileHtml);
                if(delFile!=null) 
                {
                    delFile.delete();
                }
            }
            catch(NullPointerException exception){}
            System.out.println("[INFORMATION]Program finished with errors.");
            System.exit(1);
        }
         
    }
}

