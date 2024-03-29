/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.analysis;

import fr.umlv.ir2.compil.cmdlineparser.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object in);
    Object getOut(Node node);
    void setOut(Node node, Object out);

    void caseStart(Start node);
    void caseAGrammar(AGrammar node);
    void caseAFiles(AFiles node);
    void caseAOptionsStart(AOptionsStart node);
    void caseAEmptyOptionsStart(AEmptyOptionsStart node);
    void caseAVerboseOptionsEnd(AVerboseOptionsEnd node);
    void caseAHelpOptionsEnd(AHelpOptionsEnd node);
    void caseALexOptionsEnd(ALexOptionsEnd node);
    void caseAIOptionsEnd(AIOptionsEnd node);
    void caseAHtmlOptionsEnd(AHtmlOptionsEnd node);
    void caseAHtmlFileOptionsEnd(AHtmlFileOptionsEnd node);
    void caseAVmOptionsEnd(AVmOptionsEnd node);
    void caseAX86OptionsEnd(AX86OptionsEnd node);
    void caseAObjOptionsEnd(AObjOptionsEnd node);
    void caseANextOption(ANextOption node);
    void caseAEmptyNextOption(AEmptyNextOption node);
    void caseAId(AId node);

    void caseTQuote(TQuote node);
    void caseTOptVerbose(TOptVerbose node);
    void caseTOptHelp(TOptHelp node);
    void caseTOptLex(TOptLex node);
    void caseTOptI(TOptI node);
    void caseTOptHtml(TOptHtml node);
    void caseTOptHtmlFile(TOptHtmlFile node);
    void caseTOptX86(TOptX86 node);
    void caseTOptObj(TOptObj node);
    void caseTOptVm(TOptVm node);
    void caseTId(TId node);
    void caseTBlank(TBlank node);
    void caseEOF(EOF node);
}
