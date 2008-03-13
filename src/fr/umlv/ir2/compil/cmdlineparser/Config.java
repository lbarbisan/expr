 /*
 * Created on 31 déc. 2004
 */
package fr.umlv.ir2.compil.cmdlineparser;

import java.util.ArrayList;


/**
 * Configuration class use to store command line options. 
 * All method and field are static.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class Config
{
     /**
     * Define if Verbose mode is enable.
     */
    public static boolean verbose=false;
    
    /**
     * Define if a help message must be showned.
     */
    public static boolean help=false;
    
    /**
     * Contains the list of files to parse.
     */
    public static ArrayList<String> fileList = new ArrayList<String>();
    
    /**
     * Return the application version.
     * @return String application version
     */
    public static String getVersion()
    {
        //TODO: Ne jamais oublier la mise à jour de ce champ
        return "Preview 2";
    }
    
    /**
     * Specify if only lexem must be showned.
     */
    public static boolean onlyShowLexem = false;
    
    /**
     * 
     */
    public static boolean showSymbols = false;
    
    /**
     * 
     */
    public static boolean createHtml = false;
    
    /**
     * 
     */
    public static String outputFileHtml = "ExpHtml.html";
    
    /**
     * 
     */
    public static boolean virutalMachinePseudoCode = false;
    
    /**
     * 
     */
    public static boolean x86Code = false;
    
    /**
     * 
     */
    public static String outputFileAssembler = "a.s";
    
    /**
     * Write assembler in file or screen
     */
    public static boolean writeInFileAssembler = false;
    
    /**
     * Show help message. 
     */
    public static void showHelp()
    {
        System.out.println("Usage: expc [options] sources");
        System.out.println("Default: parse source to check grammar for Exp langage");
        System.out.println("Option:");
        System.out.println("\t-lex\t\tonly print the lexemes of source");
        System.out.println("\t-html\t\tprint the HTML documentation on standard output or file (see -d)");
        System.out.println("\t-d <file>\tset output file (see -html, default file is \"ExpHtml.html\")");
        System.out.println("\t-i\t\tprint global symbols with arity and type information");
        System.out.println("\t-vm\t\tCompile in pseudo-code");
        System.out.println("\t-x86\t\tCompile in X86 assembler");
        System.out.println("\t-o <file>\toutput file for assembler (default is \"a.s\")");
        System.out.println("\t-help\t\tprint this message and exit");
        System.out.println("Version: " + Config.getVersion());
        
    }
}
