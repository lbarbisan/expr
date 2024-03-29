/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class TMcFor extends Token
{
    public TMcFor()
    {
        super.setText("for");
    }

    public TMcFor(int line, int pos)
    {
        super.setText("for");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TMcFor(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMcFor(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TMcFor text.");
    }
}
