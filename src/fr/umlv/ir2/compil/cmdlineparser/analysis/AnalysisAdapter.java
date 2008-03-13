/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.analysis;

import java.util.*;
import fr.umlv.ir2.compil.cmdlineparser.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable in;
    private Hashtable out;

    public Object getIn(Node node)
    {
        if(in == null)
        {
            return null;
        }

        return in.get(node);
    }

    public void setIn(Node node, Object in)
    {
        if(this.in == null)
        {
            this.in = new Hashtable(1);
        }

        if(in != null)
        {
            this.in.put(node, in);
        }
        else
        {
            this.in.remove(node);
        }
    }

    public Object getOut(Node node)
    {
        if(out == null)
        {
            return null;
        }

        return out.get(node);
    }

    public void setOut(Node node, Object out)
    {
        if(this.out == null)
        {
            this.out = new Hashtable(1);
        }

        if(out != null)
        {
            this.out.put(node, out);
        }
        else
        {
            this.out.remove(node);
        }
    }
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    public void caseAGrammar(AGrammar node)
    {
        defaultCase(node);
    }

    public void caseAFiles(AFiles node)
    {
        defaultCase(node);
    }

    public void caseAOptionsStart(AOptionsStart node)
    {
        defaultCase(node);
    }

    public void caseAEmptyOptionsStart(AEmptyOptionsStart node)
    {
        defaultCase(node);
    }

    public void caseAVerboseOptionsEnd(AVerboseOptionsEnd node)
    {
        defaultCase(node);
    }

    public void caseAHelpOptionsEnd(AHelpOptionsEnd node)
    {
        defaultCase(node);
    }

    public void caseALexOptionsEnd(ALexOptionsEnd node)
    {
        defaultCase(node);
    }

    public void caseAIOptionsEnd(AIOptionsEnd node)
    {
        defaultCase(node);
    }

    public void caseAHtmlOptionsEnd(AHtmlOptionsEnd node)
    {
        defaultCase(node);
    }

    public void caseAHtmlFileOptionsEnd(AHtmlFileOptionsEnd node)
    {
        defaultCase(node);
    }

    public void caseAVmOptionsEnd(AVmOptionsEnd node)
    {
        defaultCase(node);
    }

    public void caseAX86OptionsEnd(AX86OptionsEnd node)
    {
        defaultCase(node);
    }

    public void caseAObjOptionsEnd(AObjOptionsEnd node)
    {
        defaultCase(node);
    }

    public void caseANextOption(ANextOption node)
    {
        defaultCase(node);
    }

    public void caseAEmptyNextOption(AEmptyNextOption node)
    {
        defaultCase(node);
    }

    public void caseAId(AId node)
    {
        defaultCase(node);
    }

    public void caseTQuote(TQuote node)
    {
        defaultCase(node);
    }

    public void caseTOptVerbose(TOptVerbose node)
    {
        defaultCase(node);
    }

    public void caseTOptHelp(TOptHelp node)
    {
        defaultCase(node);
    }

    public void caseTOptLex(TOptLex node)
    {
        defaultCase(node);
    }

    public void caseTOptI(TOptI node)
    {
        defaultCase(node);
    }

    public void caseTOptHtml(TOptHtml node)
    {
        defaultCase(node);
    }

    public void caseTOptHtmlFile(TOptHtmlFile node)
    {
        defaultCase(node);
    }

    public void caseTOptX86(TOptX86 node)
    {
        defaultCase(node);
    }

    public void caseTOptObj(TOptObj node)
    {
        defaultCase(node);
    }

    public void caseTOptVm(TOptVm node)
    {
        defaultCase(node);
    }

    public void caseTId(TId node)
    {
        defaultCase(node);
    }

    public void caseTBlank(TBlank node)
    {
        defaultCase(node);
    }

    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    public void defaultCase(Node node)
    {
    }
}