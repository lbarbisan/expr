/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class AEmptyExprSum extends PExprSum
{
    private PExprProduct _exprProduct_;

    public AEmptyExprSum()
    {
    }

    public AEmptyExprSum(
        PExprProduct _exprProduct_)
    {
        setExprProduct(_exprProduct_);

    }
    public Object clone()
    {
        return new AEmptyExprSum(
            (PExprProduct) cloneNode(_exprProduct_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEmptyExprSum(this);
    }

    public PExprProduct getExprProduct()
    {
        return _exprProduct_;
    }

    public void setExprProduct(PExprProduct node)
    {
        if(_exprProduct_ != null)
        {
            _exprProduct_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _exprProduct_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_exprProduct_);
    }

    void removeChild(Node child)
    {
        if(_exprProduct_ == child)
        {
            _exprProduct_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_exprProduct_ == oldChild)
        {
            setExprProduct((PExprProduct) newChild);
            return;
        }

    }
}