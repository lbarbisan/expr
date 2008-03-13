/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class AGteqExprComp extends PExprComp
{
    private PExprComp _exprComp_;
    private TGteq _gteq_;
    private PExprSum _exprSum_;

    public AGteqExprComp()
    {
    }

    public AGteqExprComp(
        PExprComp _exprComp_,
        TGteq _gteq_,
        PExprSum _exprSum_)
    {
        setExprComp(_exprComp_);

        setGteq(_gteq_);

        setExprSum(_exprSum_);

    }
    public Object clone()
    {
        return new AGteqExprComp(
            (PExprComp) cloneNode(_exprComp_),
            (TGteq) cloneNode(_gteq_),
            (PExprSum) cloneNode(_exprSum_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGteqExprComp(this);
    }

    public PExprComp getExprComp()
    {
        return _exprComp_;
    }

    public void setExprComp(PExprComp node)
    {
        if(_exprComp_ != null)
        {
            _exprComp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _exprComp_ = node;
    }

    public TGteq getGteq()
    {
        return _gteq_;
    }

    public void setGteq(TGteq node)
    {
        if(_gteq_ != null)
        {
            _gteq_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _gteq_ = node;
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
            + toString(_exprComp_)
            + toString(_gteq_)
            + toString(_exprSum_);
    }

    void removeChild(Node child)
    {
        if(_exprComp_ == child)
        {
            _exprComp_ = null;
            return;
        }

        if(_gteq_ == child)
        {
            _gteq_ = null;
            return;
        }

        if(_exprSum_ == child)
        {
            _exprSum_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_exprComp_ == oldChild)
        {
            setExprComp((PExprComp) newChild);
            return;
        }

        if(_gteq_ == oldChild)
        {
            setGteq((TGteq) newChild);
            return;
        }

        if(_exprSum_ == oldChild)
        {
            setExprSum((PExprSum) newChild);
            return;
        }

    }
}
