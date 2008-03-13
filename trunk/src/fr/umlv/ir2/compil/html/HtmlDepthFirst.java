/*
 * Created on 10 févr. 2005
 */
package fr.umlv.ir2.compil.html;

import java.io.File;

import fr.umlv.ir2.compil.languageparser.analysis.DepthFirstAdapter;
import fr.umlv.ir2.compil.languageparser.node.AForDeclarations;
import fr.umlv.ir2.compil.languageparser.node.AFunctionDeclarations;
import fr.umlv.ir2.compil.languageparser.node.AGlobalDeclarations;
import fr.umlv.ir2.compil.languageparser.node.AIfExprIfLet;
import fr.umlv.ir2.compil.languageparser.node.ALetExprIfLet;
import fr.umlv.ir2.compil.languageparser.node.APrintDeclarations;
import fr.umlv.ir2.compil.languageparser.node.Node;
import fr.umlv.ir2.compil.languageparser.node.TAnd;
import fr.umlv.ir2.compil.languageparser.node.TEqual;
import fr.umlv.ir2.compil.languageparser.node.TGteq;
import fr.umlv.ir2.compil.languageparser.node.TMcBool;
import fr.umlv.ir2.compil.languageparser.node.TMcElse;
import fr.umlv.ir2.compil.languageparser.node.TMcFloat;
import fr.umlv.ir2.compil.languageparser.node.TMcFor;
import fr.umlv.ir2.compil.languageparser.node.TMcFunction;
import fr.umlv.ir2.compil.languageparser.node.TMcIf;
import fr.umlv.ir2.compil.languageparser.node.TMcIn;
import fr.umlv.ir2.compil.languageparser.node.TMcInt;
import fr.umlv.ir2.compil.languageparser.node.TMcLet;
import fr.umlv.ir2.compil.languageparser.node.TMcNot;
import fr.umlv.ir2.compil.languageparser.node.TMcPrint;
import fr.umlv.ir2.compil.languageparser.node.TMcThen;
import fr.umlv.ir2.compil.languageparser.node.TMcTo;
import fr.umlv.ir2.compil.languageparser.node.TMcVar;


/**
 * @author Olivier Boitel
 * @author Laurent Babrisan
 * 
 */
public class HtmlDepthFirst extends DepthFirstAdapter
{
	private HTMLExport htmlExport;
    
    private int equalHashCode=0;
    
	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inAForDeclarations(fr.umlv.ir2.compil.parser.node.AForDeclarations)
	 */
	public void inAForDeclarations(AForDeclarations node) {
		
        htmlExport.writeln(0);
        htmlExport.writeln(0,false);
        super.inAForDeclarations(node);
        this.equalHashCode=node.getEqual().hashCode();
	}
	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inAFunctionDeclarations(fr.umlv.ir2.compil.parser.node.AFunctionDeclarations)
	 */
	public void inAFunctionDeclarations(AFunctionDeclarations node) {
		
        htmlExport.writeln(0);
        htmlExport.writeln(0,false);
        this.equalHashCode=node.getEqual().hashCode();
        
	}

	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inAGlobalDeclarations(fr.umlv.ir2.compil.parser.node.AGlobalDeclarations)
	 */
	public void inAGlobalDeclarations(AGlobalDeclarations node) {
		
        htmlExport.writeln(0);
        htmlExport.writeln(0,false);
        this.equalHashCode=node.getEqual().hashCode();
        super.inAGlobalDeclarations(node);
	}

	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inAIfExprIfLet(fr.umlv.ir2.compil.parser.node.AIfExprIfLet)
	 */
	public void inAIfExprIfLet(AIfExprIfLet node) {
		
		this.equalHashCode=node.hashCode();
        super.inAIfExprIfLet(node);
	}

	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inALetExprIfLet(fr.umlv.ir2.compil.parser.node.ALetExprIfLet)
	 */
	public void inALetExprIfLet(ALetExprIfLet node) {

        htmlExport.writeln(2);
        this.equalHashCode=node.getEqual().hashCode();
        super.inALetExprIfLet(node);
	}
	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#inAPrintDeclarations(fr.umlv.ir2.compil.parser.node.APrintDeclarations)
	 */
	public void inAPrintDeclarations(APrintDeclarations node) {
		
        htmlExport.writeln(0,false);
        htmlExport.writeln(0,false);
	}
	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#outAForDeclarations(fr.umlv.ir2.compil.parser.node.AForDeclarations)
	 */
	public void outAForDeclarations(AForDeclarations node) {
		super.outAForDeclarations(node);
        htmlExport.setRelativeIndent(-2);
        
	}
	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#outAFunctionDeclarations(fr.umlv.ir2.compil.parser.node.AFunctionDeclarations)
	 */
	public void outAFunctionDeclarations(AFunctionDeclarations node) {
		super.outAFunctionDeclarations(node);
        htmlExport.setRelativeIndent(-2);
	}

	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#outAGlobalDeclarations(fr.umlv.ir2.compil.parser.node.AGlobalDeclarations)
	 */
	public void outAGlobalDeclarations(AGlobalDeclarations node) {

		super.outAGlobalDeclarations(node);
        htmlExport.setRelativeIndent(-2);
	}
	
	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#outAIfExprIfLet(fr.umlv.ir2.compil.parser.node.AIfExprIfLet)
	 */
	public void outAIfExprIfLet(AIfExprIfLet node) {
		super.outAIfExprIfLet(node);
        htmlExport.setRelativeIndent(-2);
    }

	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.DepthFirstAdapter#outALetExprIfLet(fr.umlv.ir2.compil.parser.node.ALetExprIfLet)
	 */
	public void outALetExprIfLet(ALetExprIfLet node) {
		super.outALetExprIfLet(node);
        htmlExport.setRelativeIndent(-2);
	}
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcVar(fr.umlv.ir2.compil.parser.node.TMcVar)
	 */
	public void caseTMcVar(TMcVar node) {

		htmlExport.writeStyledElement("keyword", node.getText());
	}
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcFunction(fr.umlv.ir2.compil.parser.node.TMcFunction)
	 */
	public void caseTMcFunction(TMcFunction node) {
		

		htmlExport.writeStyledElement("keyword", node.getText());
		
	}
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcPrint(fr.umlv.ir2.compil.parser.node.TMcPrint)
	 */
	public void caseTMcPrint(TMcPrint node) {
		
        htmlExport.writeln(0);
        htmlExport.writeStyledElement("keyword", node.getText());
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcFor(fr.umlv.ir2.compil.parser.node.TMcFor)
	 */
	public void caseTMcFor(TMcFor node) {
		
		htmlExport.writeStyledElement("keyword", node.getText());
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcTo(fr.umlv.ir2.compil.parser.node.TMcTo)
	 */
	public void caseTMcTo(TMcTo node) {
	
        htmlExport.writeln(0);
		htmlExport.writeStyledElement("keyword", node.getText());
        htmlExport.writeln(0);
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcInt(fr.umlv.ir2.compil.parser.node.TMcInt)
	 */
	public void caseTMcInt(TMcInt node) {
		
        htmlExport.writeStyledElement("keyword", node.getText());
		
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcFloat(fr.umlv.ir2.compil.parser.node.TMcFloat)
	 */
	public void caseTMcFloat(TMcFloat node) {
		
		htmlExport.writeStyledElement("keyword", node.getText());
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcBool(fr.umlv.ir2.compil.parser.node.TMcBool)
	 */
	public void caseTMcBool(TMcBool node) {
		
		htmlExport.writeStyledElement("keyword", node.getText());
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcIf(fr.umlv.ir2.compil.parser.node.TMcIf)
	 */
	public void caseTMcIf(TMcIf node) {
		
		htmlExport.writeStyledElement("keyword", node.getText());
	}
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcThen(fr.umlv.ir2.compil.parser.node.TMcThen)
	 */
	public void caseTMcThen(TMcThen node) {
		
		htmlExport.writeStyledElement("keyword", node.getText());
        htmlExport.writeln(2);
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcElse(fr.umlv.ir2.compil.parser.node.TMcElse)
	 */
	public void caseTMcElse(TMcElse node) {
		
        htmlExport.writeln(-2);
        htmlExport.writeStyledElement("keyword", node.getText());
        htmlExport.writeln(2);
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcLet(fr.umlv.ir2.compil.parser.node.TMcLet)
	 */
	public void caseTMcLet(TMcLet node) {
		
        htmlExport.writeStyledElement("keyword", node.getText());
	}
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcIn(fr.umlv.ir2.compil.parser.node.TMcIn)
	 */
	public void caseTMcIn(TMcIn node) {
		
		htmlExport.writeln(-2);
        htmlExport.writeStyledElement("keyword", node.getText());
        htmlExport.writeln(2);
	}
    
	/* (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTMcNot(fr.umlv.ir2.compil.parser.node.TMcNot)
	 */
	public void caseTMcNot(TMcNot node) {

		htmlExport.writeStyledElement("keyword", node.getText());
	}

	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTEqual(fr.umlv.ir2.compil.parser.node.TEqual)
	 */
	public void caseTEqual(TEqual node) {
		
		super.caseTEqual(node);
        if(node.hashCode()==this.equalHashCode)
        {
            htmlExport.writeln(+2);
            this.equalHashCode=0;
        }

	}
	
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTGteq(fr.umlv.ir2.compil.parser.node.TGteq)
	 */
	public void caseTGteq(TGteq node) {

		super.caseTGteq(node);
        htmlExport.writeElement(" " + node.getText()+ " ");
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.Analysis#caseTAnd(fr.umlv.ir2.compil.parser.node.TAnd)
	 */
	public void caseTAnd(TAnd node) {

		super.caseTAnd(node);
        htmlExport.writeElement(" " + node.getText()+ " ");
	}
    
	/** (non-Javadoc)
	 * @see fr.umlv.ir2.compil.parser.analysis.AnalysisAdapter#defaultCase(fr.umlv.ir2.compil.parser.node.Node)
	 */
	public void defaultCase(Node node) {
		
		super.defaultCase(node);
        htmlExport.writeElement(" " + node.toString() + " ");
	}
	public HtmlDepthFirst(File file)
	{
		this.htmlExport = new HTMLExport(file);
	}
	
	public void write()
	{
		htmlExport.close();
	}
}
