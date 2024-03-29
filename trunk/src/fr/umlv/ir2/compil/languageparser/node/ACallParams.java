/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class ACallParams extends PCallParams
{
    private PExpression _expression_;
    private TComma _comma_;

    public ACallParams()
    {
    }

    public ACallParams(
        PExpression _expression_,
        TComma _comma_)
    {
        setExpression(_expression_);

        setComma(_comma_);

    }
    public Object clone()
    {
        return new ACallParams(
            (PExpression) cloneNode(_expression_),
            (TComma) cloneNode(_comma_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACallParams(this);
    }

    public PExpression getExpression()
    {
        return _expression_;
    }

    public void setExpression(PExpression node)
    {
        if(_expression_ != null)
        {
            _expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _expression_ = node;
    }

    public TComma getComma()
    {
        return _comma_;
    }

    public void setComma(TComma node)
    {
        if(_comma_ != null)
        {
            _comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _comma_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_expression_)
            + toString(_comma_);
    }

    void removeChild(Node child)
    {
        if(_expression_ == child)
        {
            _expression_ = null;
            return;
        }

        if(_comma_ == child)
        {
            _comma_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        if(_comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

    }
}
