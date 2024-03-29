/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class AFunctionDeclarations extends PDeclarations
{
    private TMcFunction _mcFunction_;
    private PFunctionName _functionName_;
    private TLPar _lPar_;
    private final LinkedList _params_ = new TypedLinkedList(new Params_Cast());
    private PParamEnd _paramEnd_;
    private TRPar _rPar_;
    private TType _type_;
    private TEqual _equal_;
    private PExpression _expression_;
    private TSemicolon _semicolon_;

    public AFunctionDeclarations()
    {
    }

    public AFunctionDeclarations(
        TMcFunction _mcFunction_,
        PFunctionName _functionName_,
        TLPar _lPar_,
        List _params_,
        PParamEnd _paramEnd_,
        TRPar _rPar_,
        TType _type_,
        TEqual _equal_,
        PExpression _expression_,
        TSemicolon _semicolon_)
    {
        setMcFunction(_mcFunction_);

        setFunctionName(_functionName_);

        setLPar(_lPar_);

        {
            this._params_.clear();
            this._params_.addAll(_params_);
        }

        setParamEnd(_paramEnd_);

        setRPar(_rPar_);

        setType(_type_);

        setEqual(_equal_);

        setExpression(_expression_);

        setSemicolon(_semicolon_);

    }

    public AFunctionDeclarations(
        TMcFunction _mcFunction_,
        PFunctionName _functionName_,
        TLPar _lPar_,
        XPParams _params_,
        PParamEnd _paramEnd_,
        TRPar _rPar_,
        TType _type_,
        TEqual _equal_,
        PExpression _expression_,
        TSemicolon _semicolon_)
    {
        setMcFunction(_mcFunction_);

        setFunctionName(_functionName_);

        setLPar(_lPar_);

        if(_params_ != null)
        {
            while(_params_ instanceof X1PParams)
            {
                this._params_.addFirst(((X1PParams) _params_).getPParams());
                _params_ = ((X1PParams) _params_).getXPParams();
            }
            this._params_.addFirst(((X2PParams) _params_).getPParams());
        }

        setParamEnd(_paramEnd_);

        setRPar(_rPar_);

        setType(_type_);

        setEqual(_equal_);

        setExpression(_expression_);

        setSemicolon(_semicolon_);

    }
    public Object clone()
    {
        return new AFunctionDeclarations(
            (TMcFunction) cloneNode(_mcFunction_),
            (PFunctionName) cloneNode(_functionName_),
            (TLPar) cloneNode(_lPar_),
            cloneList(_params_),
            (PParamEnd) cloneNode(_paramEnd_),
            (TRPar) cloneNode(_rPar_),
            (TType) cloneNode(_type_),
            (TEqual) cloneNode(_equal_),
            (PExpression) cloneNode(_expression_),
            (TSemicolon) cloneNode(_semicolon_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFunctionDeclarations(this);
    }

    public TMcFunction getMcFunction()
    {
        return _mcFunction_;
    }

    public void setMcFunction(TMcFunction node)
    {
        if(_mcFunction_ != null)
        {
            _mcFunction_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _mcFunction_ = node;
    }

    public PFunctionName getFunctionName()
    {
        return _functionName_;
    }

    public void setFunctionName(PFunctionName node)
    {
        if(_functionName_ != null)
        {
            _functionName_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _functionName_ = node;
    }

    public TLPar getLPar()
    {
        return _lPar_;
    }

    public void setLPar(TLPar node)
    {
        if(_lPar_ != null)
        {
            _lPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _lPar_ = node;
    }

    public LinkedList getParams()
    {
        return _params_;
    }

    public void setParams(List list)
    {
        _params_.clear();
        _params_.addAll(list);
    }

    public PParamEnd getParamEnd()
    {
        return _paramEnd_;
    }

    public void setParamEnd(PParamEnd node)
    {
        if(_paramEnd_ != null)
        {
            _paramEnd_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _paramEnd_ = node;
    }

    public TRPar getRPar()
    {
        return _rPar_;
    }

    public void setRPar(TRPar node)
    {
        if(_rPar_ != null)
        {
            _rPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _rPar_ = node;
    }

    public TType getType()
    {
        return _type_;
    }

    public void setType(TType node)
    {
        if(_type_ != null)
        {
            _type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _type_ = node;
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
            + toString(_mcFunction_)
            + toString(_functionName_)
            + toString(_lPar_)
            + toString(_params_)
            + toString(_paramEnd_)
            + toString(_rPar_)
            + toString(_type_)
            + toString(_equal_)
            + toString(_expression_)
            + toString(_semicolon_);
    }

    void removeChild(Node child)
    {
        if(_mcFunction_ == child)
        {
            _mcFunction_ = null;
            return;
        }

        if(_functionName_ == child)
        {
            _functionName_ = null;
            return;
        }

        if(_lPar_ == child)
        {
            _lPar_ = null;
            return;
        }

        if(_params_.remove(child))
        {
            return;
        }

        if(_paramEnd_ == child)
        {
            _paramEnd_ = null;
            return;
        }

        if(_rPar_ == child)
        {
            _rPar_ = null;
            return;
        }

        if(_type_ == child)
        {
            _type_ = null;
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
        if(_mcFunction_ == oldChild)
        {
            setMcFunction((TMcFunction) newChild);
            return;
        }

        if(_functionName_ == oldChild)
        {
            setFunctionName((PFunctionName) newChild);
            return;
        }

        if(_lPar_ == oldChild)
        {
            setLPar((TLPar) newChild);
            return;
        }

        for(ListIterator i = _params_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set(newChild);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(_paramEnd_ == oldChild)
        {
            setParamEnd((PParamEnd) newChild);
            return;
        }

        if(_rPar_ == oldChild)
        {
            setRPar((TRPar) newChild);
            return;
        }

        if(_type_ == oldChild)
        {
            setType((TType) newChild);
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

    private class Params_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PParams node = (PParams) o;

            if((node.parent() != null) &&
                (node.parent() != AFunctionDeclarations.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != AFunctionDeclarations.this))
            {
                node.parent(AFunctionDeclarations.this);
            }

            return node;
        }
    }
}
