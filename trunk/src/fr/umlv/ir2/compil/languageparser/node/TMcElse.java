/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class TMcElse extends Token
{
    public TMcElse()
    {
        super.setText("else");
    }

    public TMcElse(int line, int pos)
    {
        super.setText("else");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TMcElse(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMcElse(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TMcElse text.");
    }
}
