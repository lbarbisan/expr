/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class TComment extends Token
{
    public TComment(String text)
    {
        setText(text);
    }

    public TComment(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TComment(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTComment(this);
    }
}
