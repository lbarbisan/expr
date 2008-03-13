/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class TMcThen extends Token
{
    public TMcThen()
    {
        super.setText("then");
    }

    public TMcThen(int line, int pos)
    {
        super.setText("then");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TMcThen(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMcThen(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TMcThen text.");
    }
}