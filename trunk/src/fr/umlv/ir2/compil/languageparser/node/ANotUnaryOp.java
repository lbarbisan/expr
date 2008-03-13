/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class ANotUnaryOp extends PUnaryOp
{
    private TMcNot _mcNot_;

    public ANotUnaryOp()
    {
    }

    public ANotUnaryOp(
        TMcNot _mcNot_)
    {
        setMcNot(_mcNot_);

    }
    public Object clone()
    {
        return new ANotUnaryOp(
            (TMcNot) cloneNode(_mcNot_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotUnaryOp(this);
    }

    public TMcNot getMcNot()
    {
        return _mcNot_;
    }

    public void setMcNot(TMcNot node)
    {
        if(_mcNot_ != null)
        {
            _mcNot_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _mcNot_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_mcNot_);
    }

    void removeChild(Node child)
    {
        if(_mcNot_ == child)
        {
            _mcNot_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_mcNot_ == oldChild)
        {
            setMcNot((TMcNot) newChild);
            return;
        }

    }
}