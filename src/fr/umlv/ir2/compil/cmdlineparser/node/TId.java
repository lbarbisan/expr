/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.node;

import fr.umlv.ir2.compil.cmdlineparser.analysis.*;

public final class TId extends Token
{
    public TId(String text)
    {
        setText(text);
    }

    public TId(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TId(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTId(this);
    }
}
