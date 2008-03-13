/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class TMcLet extends Token
{
    public TMcLet()
    {
        super.setText("let");
    }

    public TMcLet(int line, int pos)
    {
        super.setText("let");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TMcLet(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMcLet(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TMcLet text.");
    }
}