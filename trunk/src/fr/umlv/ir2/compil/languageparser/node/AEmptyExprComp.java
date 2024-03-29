/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class AEmptyExprComp extends PExprComp
{
    private PExprSum _exprSum_;

    public AEmptyExprComp()
    {
    }

    public AEmptyExprComp(
        PExprSum _exprSum_)
    {
        setExprSum(_exprSum_);

    }
    public Object clone()
    {
        return new AEmptyExprComp(
            (PExprSum) cloneNode(_exprSum_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEmptyExprComp(this);
    }

    public PExprSum getExprSum()
    {
        return _exprSum_;
    }

    public void setExprSum(PExprSum node)
    {
        if(_exprSum_ != null)
        {
            _exprSum_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _exprSum_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_exprSum_);
    }

    void removeChild(Node child)
    {
        if(_exprSum_ == child)
        {
            _exprSum_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_exprSum_ == oldChild)
        {
            setExprSum((PExprSum) newChild);
            return;
        }

    }
}
