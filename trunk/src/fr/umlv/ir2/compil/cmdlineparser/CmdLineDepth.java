/*
 * Created on 31 déc. 2004
 */
package fr.umlv.ir2.compil.cmdlineparser;

import java.io.PushbackReader;
import java.io.StringReader;

import fr.umlv.ir2.compil.cmdlineparser.analysis.DepthFirstAdapter;
import fr.umlv.ir2.compil.cmdlineparser.lexer.Lexer;
import fr.umlv.ir2.compil.cmdlineparser.node.AFiles;
import fr.umlv.ir2.compil.cmdlineparser.node.AHelpOptionsEnd;
import fr.umlv.ir2.compil.cmdlineparser.node.AHtmlFileOptionsEnd;
import fr.umlv.ir2.compil.cmdlineparser.node.AHtmlOptionsEnd;
import fr.umlv.ir2.compil.cmdlineparser.node.AIOptionsEnd;
import fr.umlv.ir2.compil.cmdlineparser.node.AId;
import fr.umlv.ir2.compil.cmdlineparser.node.ALexOptionsEnd;
import fr.umlv.ir2.compil.cmdlineparser.node.AObjOptionsEnd;
import fr.umlv.ir2.compil.cmdlineparser.node.AVerboseOptionsEnd;
import fr.umlv.ir2.compil.cmdlineparser.node.AVmOptionsEnd;
import fr.umlv.ir2.compil.cmdlineparser.node.AX86OptionsEnd;
import fr.umlv.ir2.compil.cmdlineparser.node.Token;
import fr.umlv.ir2.compil.cmdlineparser.parser.Parser;


/**
 *  Visitor use to configure Config class with command line parameters.
 *  This visitor extends from DepthFirstAdapter wich is the default
 *  visitor of sableCC. SableCC is use to parse command line
 *  cf. cmdline.scc.
 * @author Olivier Boitel
 * @author Laurent Barbisan 
 */
public class CmdLineDepth extends DepthFirstAdapter
{

    
    
    /**
     * Options to show symbols.
     */
    public void outAIOptionsEnd(AIOptionsEnd node)
    {
        Config.showSymbols = true;
    }
    /**
     * Parse a command line.
     * @param string command line to parse
     */
    public static void  parseCommandLine(String string)
    {
        Lexer lexer;
        Parser parser;
        Token token;

        if(string==null)
            return;
        
        lexer = new Lexer(new PushbackReader(new StringReader(string), 10000));
        parser = new Parser(lexer);
        try
        {
            parser.parse().apply(new CmdLineDepth());
        }
        catch(Exception exception)
        {
            System.out.println("[Error]Expc: Commande incorrect, " + exception.getMessage());
        }

    }
   
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.cmdlineparser.analysis.DepthFirstAdapter#outAFiles(fr.umlv.ir2.compil.cmdlineparser.node.AFiles)
     */
    public void outAFiles(AFiles node)
    {
        if(node.getId()!=null)
        {
            Config.fileList.add(node.getId().getText());
        }
    }

    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.cmdlineparser.analysis.DepthFirstAdapter#outAHelpS(fr.umlv.ir2.compil.cmdlineparser.node.AHelpS)
     */
    public@Override void outAHelpOptionsEnd(AHelpOptionsEnd node)
    {
        Config.help=true;
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.cmdlineparser.analysis.DepthFirstAdapter#outAVerboseS(fr.umlv.ir2.compil.cmdlineparser.node.AVerboseS)
     */
    public@Override void outAVerboseOptionsEnd(AVerboseOptionsEnd node)
    {
        Config.verbose=true;
    }

    /**
     * 
     */
    public@Override  void outALexOptionsEnd(ALexOptionsEnd node)
    {
        Config.onlyShowLexem = true;
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.cmdlineparser.analysis.DepthFirstAdapter#outAHtmlFileS(fr.umlv.ir2.compil.cmdlineparser.node.AHtmlFileS)
     */
    public void outAHtmlFileOptionsEnd(AHtmlFileOptionsEnd node)
    {
        if(((AId)node.getId())!=null)
        {
            Config.outputFileHtml =  ((AId)node.getId()).getId().getText();    
        }
         
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.cmdlineparser.analysis.DepthFirstAdapter#outAHtmlS(fr.umlv.ir2.compil.cmdlineparser.node.AHtmlS)
     */
    public void outAHtmlOptionsEnd(AHtmlOptionsEnd node)
    {
        Config.createHtml = true;
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.cmdlineparser.analysis.DepthFirstAdapter#outAObjS(fr.umlv.ir2.compil.cmdlineparser.node.AObjS)
     */
    public void outAObjOptionsEnd(AObjOptionsEnd node)
    {
        
    	Config.writeInFileAssembler=true;
    	if(((AId)node.getId())!=null)
        {
    	    Config.outputFileAssembler = ((AId)node.getId()).getId().getText();
        }
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.ir2.compil.cmdlineparser.analysis.DepthFirstAdapter#outAX86Options(null)
     */
    public void outAX86OptionsEnd(AX86OptionsEnd node)
    {
        Config.x86Code =true;
    }
    
    /** (non-Javadoc)
     * @see fr.umlv.ir2.compil.cmdlineparser.analysis.DepthFirstAdapter#outAVmS(fr.umlv.ir2.compil.cmdlineparser.node.AVmS)
     */
    public void outAVmOptionsEnd(AVmOptionsEnd node)
    {
        Config.virutalMachinePseudoCode  = true;
    }
}