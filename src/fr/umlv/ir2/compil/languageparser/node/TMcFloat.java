/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class TMcFloat extends Token
{
    public TMcFloat()
    {
        super.setText("float");
    }

    public TMcFloat(int line, int pos)
    {
        super.setText("float");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TMcFloat(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMcFloat(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TMcFloat text.");
    }
}
