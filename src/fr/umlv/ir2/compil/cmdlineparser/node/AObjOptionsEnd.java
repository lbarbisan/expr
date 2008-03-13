/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.node;

import java.util.*;
import fr.umlv.ir2.compil.cmdlineparser.analysis.*;

public final class AObjOptionsEnd extends POptionsEnd
{
    private TOptObj _optObj_;
    private TQuote _right_;
    private PId _id_;
    private PNextOption _nextOption_;

    public AObjOptionsEnd()
    {
    }

    public AObjOptionsEnd(
        TOptObj _optObj_,
        TQuote _right_,
        PId _id_,
        PNextOption _nextOption_)
    {
        setOptObj(_optObj_);

        setRight(_right_);

        setId(_id_);

        setNextOption(_nextOption_);

    }
    public Object clone()
    {
        return new AObjOptionsEnd(
            (TOptObj) cloneNode(_optObj_),
            (TQuote) cloneNode(_right_),
            (PId) cloneNode(_id_),
            (PNextOption) cloneNode(_nextOption_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAObjOptionsEnd(this);
    }

    public TOptObj getOptObj()
    {
        return _optObj_;
    }

    public void setOptObj(TOptObj node)
    {
        if(_optObj_ != null)
        {
            _optObj_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _optObj_ = node;
    }

    public TQuote getRight()
    {
        return _right_;
    }

    public void setRight(TQuote node)
    {
        if(_right_ != null)
        {
            _right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _right_ = node;
    }

    public PId getId()
    {
        return _id_;
    }

    public void setId(PId node)
    {
        if(_id_ != null)
        {
            _id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _id_ = node;
    }

    public PNextOption getNextOption()
    {
        return _nextOption_;
    }

    public void setNextOption(PNextOption node)
    {
        if(_nextOption_ != null)
        {
            _nextOption_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _nextOption_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_optObj_)
            + toString(_right_)
            + toString(_id_)
            + toString(_nextOption_);
    }

    void removeChild(Node child)
    {
        if(_optObj_ == child)
        {
            _optObj_ = null;
            return;
        }

        if(_right_ == child)
        {
            _right_ = null;
            return;
        }

        if(_id_ == child)
        {
            _id_ = null;
            return;
        }

        if(_nextOption_ == child)
        {
            _nextOption_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_optObj_ == oldChild)
        {
            setOptObj((TOptObj) newChild);
            return;
        }

        if(_right_ == oldChild)
        {
            setRight((TQuote) newChild);
            return;
        }

        if(_id_ == oldChild)
        {
            setId((PId) newChild);
            return;
        }

        if(_nextOption_ == oldChild)
        {
            setNextOption((PNextOption) newChild);
            return;
        }

    }
}