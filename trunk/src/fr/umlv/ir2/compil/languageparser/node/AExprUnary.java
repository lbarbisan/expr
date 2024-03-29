/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class AExprUnary extends PExprUnary
{
    private PUnaryOp _unaryOp_;
    private PExprSimple _exprSimple_;

    public AExprUnary()
    {
    }

    public AExprUnary(
        PUnaryOp _unaryOp_,
        PExprSimple _exprSimple_)
    {
        setUnaryOp(_unaryOp_);

        setExprSimple(_exprSimple_);

    }
    public Object clone()
    {
        return new AExprUnary(
            (PUnaryOp) cloneNode(_unaryOp_),
            (PExprSimple) cloneNode(_exprSimple_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAExprUnary(this);
    }

    public PUnaryOp getUnaryOp()
    {
        return _unaryOp_;
    }

    public void setUnaryOp(PUnaryOp node)
    {
        if(_unaryOp_ != null)
        {
            _unaryOp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _unaryOp_ = node;
    }

    public PExprSimple getExprSimple()
    {
        return _exprSimple_;
    }

    public void setExprSimple(PExprSimple node)
    {
        if(_exprSimple_ != null)
        {
            _exprSimple_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _exprSimple_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_unaryOp_)
            + toString(_exprSimple_);
    }

    void removeChild(Node child)
    {
        if(_unaryOp_ == child)
        {
            _unaryOp_ = null;
            return;
        }

        if(_exprSimple_ == child)
        {
            _exprSimple_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_unaryOp_ == oldChild)
        {
            setUnaryOp((PUnaryOp) newChild);
            return;
        }

        if(_exprSimple_ == oldChild)
        {
            setExprSimple((PExprSimple) newChild);
            return;
        }

    }
}
