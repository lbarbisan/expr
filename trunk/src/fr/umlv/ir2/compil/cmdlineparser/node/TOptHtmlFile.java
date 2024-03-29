/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.node;

import fr.umlv.ir2.compil.cmdlineparser.analysis.*;

public final class TOptHtmlFile extends Token
{
    public TOptHtmlFile(String text)
    {
        setText(text);
    }

    public TOptHtmlFile(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TOptHtmlFile(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTOptHtmlFile(this);
    }
}
