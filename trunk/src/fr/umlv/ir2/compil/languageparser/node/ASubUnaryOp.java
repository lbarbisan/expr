/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class ASubUnaryOp extends PUnaryOp
{
    private TMinus _minus_;

    public ASubUnaryOp()
    {
    }

    public ASubUnaryOp(
        TMinus _minus_)
    {
        setMinus(_minus_);

    }
    public Object clone()
    {
        return new ASubUnaryOp(
            (TMinus) cloneNode(_minus_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASubUnaryOp(this);
    }

    public TMinus getMinus()
    {
        return _minus_;
    }

    public void setMinus(TMinus node)
    {
        if(_minus_ != null)
        {
            _minus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _minus_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_minus_);
    }

    void removeChild(Node child)
    {
        if(_minus_ == child)
        {
            _minus_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_minus_ == oldChild)
        {
            setMinus((TMinus) newChild);
            return;
        }

    }
}
