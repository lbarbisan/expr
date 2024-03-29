/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class AGlobalDeclarations extends PDeclarations
{
    private TMcVar _mcVar_;
    private PGlobalVar _globalVar_;
    private TEqual _equal_;
    private PExpression _expression_;
    private TSemicolon _semicolon_;

    public AGlobalDeclarations()
    {
    }

    public AGlobalDeclarations(
        TMcVar _mcVar_,
        PGlobalVar _globalVar_,
        TEqual _equal_,
        PExpression _expression_,
        TSemicolon _semicolon_)
    {
        setMcVar(_mcVar_);

        setGlobalVar(_globalVar_);

        setEqual(_equal_);

        setExpression(_expression_);

        setSemicolon(_semicolon_);

    }
    public Object clone()
    {
        return new AGlobalDeclarations(
            (TMcVar) cloneNode(_mcVar_),
            (PGlobalVar) cloneNode(_globalVar_),
            (TEqual) cloneNode(_equal_),
            (PExpression) cloneNode(_expression_),
            (TSemicolon) cloneNode(_semicolon_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGlobalDeclarations(this);
    }

    public TMcVar getMcVar()
    {
        return _mcVar_;
    }

    public void setMcVar(TMcVar node)
    {
        if(_mcVar_ != null)
        {
            _mcVar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _mcVar_ = node;
    }

    public PGlobalVar getGlobalVar()
    {
        return _globalVar_;
    }

    public void setGlobalVar(PGlobalVar node)
    {
        if(_globalVar_ != null)
        {
            _globalVar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _globalVar_ = node;
    }

    public TEqual getEqual()
    {
        return _equal_;
    }

    public void setEqual(TEqual node)
    {
        if(_equal_ != null)
        {
            _equal_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _equal_ = node;
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

    public TSemicolon getSemicolon()
    {
        return _semicolon_;
    }

    public void setSemicolon(TSemicolon node)
    {
        if(_semicolon_ != null)
        {
            _semicolon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _semicolon_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_mcVar_)
            + toString(_globalVar_)
            + toString(_equal_)
            + toString(_expression_)
            + toString(_semicolon_);
    }

    void removeChild(Node child)
    {
        if(_mcVar_ == child)
        {
            _mcVar_ = null;
            return;
        }

        if(_globalVar_ == child)
        {
            _globalVar_ = null;
            return;
        }

        if(_equal_ == child)
        {
            _equal_ = null;
            return;
        }

        if(_expression_ == child)
        {
            _expression_ = null;
            return;
        }

        if(_semicolon_ == child)
        {
            _semicolon_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_mcVar_ == oldChild)
        {
            setMcVar((TMcVar) newChild);
            return;
        }

        if(_globalVar_ == oldChild)
        {
            setGlobalVar((PGlobalVar) newChild);
            return;
        }

        if(_equal_ == oldChild)
        {
            setEqual((TEqual) newChild);
            return;
        }

        if(_expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        if(_semicolon_ == oldChild)
        {
            setSemicolon((TSemicolon) newChild);
            return;
        }

    }
}
