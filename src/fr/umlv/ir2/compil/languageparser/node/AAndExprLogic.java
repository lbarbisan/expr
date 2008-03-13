/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class AAndExprLogic extends PExprLogic
{
    private PExprLogic _exprLogic_;
    private TAnd _and_;
    private PExprComp _exprComp_;

    public AAndExprLogic()
    {
    }

    public AAndExprLogic(
        PExprLogic _exprLogic_,
        TAnd _and_,
        PExprComp _exprComp_)
    {
        setExprLogic(_exprLogic_);

        setAnd(_and_);

        setExprComp(_exprComp_);

    }
    public Object clone()
    {
        return new AAndExprLogic(
            (PExprLogic) cloneNode(_exprLogic_),
            (TAnd) cloneNode(_and_),
            (PExprComp) cloneNode(_exprComp_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAndExprLogic(this);
    }

    public PExprLogic getExprLogic()
    {
        return _exprLogic_;
    }

    public void setExprLogic(PExprLogic node)
    {
        if(_exprLogic_ != null)
        {
            _exprLogic_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _exprLogic_ = node;
    }

    public TAnd getAnd()
    {
        return _and_;
    }

    public void setAnd(TAnd node)
    {
        if(_and_ != null)
        {
            _and_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _and_ = node;
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

    public String toString()
    {
        return ""
            + toString(_exprLogic_)
            + toString(_and_)
            + toString(_exprComp_);
    }

    void removeChild(Node child)
    {
        if(_exprLogic_ == child)
        {
            _exprLogic_ = null;
            return;
        }

        if(_and_ == child)
        {
            _and_ = null;
            return;
        }

        if(_exprComp_ == child)
        {
            _exprComp_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_exprLogic_ == oldChild)
        {
            setExprLogic((PExprLogic) newChild);
            return;
        }

        if(_and_ == oldChild)
        {
            setAnd((TAnd) newChild);
            return;
        }

        if(_exprComp_ == oldChild)
        {
            setExprComp((PExprComp) newChild);
            return;
        }

    }
}
