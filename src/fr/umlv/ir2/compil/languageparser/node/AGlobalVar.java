/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class AGlobalVar extends PGlobalVar
{
    private TId _id_;
    private TType _type_;

    public AGlobalVar()
    {
    }

    public AGlobalVar(
        TId _id_,
        TType _type_)
    {
        setId(_id_);

        setType(_type_);

    }
    public Object clone()
    {
        return new AGlobalVar(
            (TId) cloneNode(_id_),
            (TType) cloneNode(_type_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGlobalVar(this);
    }

    public TId getId()
    {
        return _id_;
    }

    public void setId(TId node)
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

    public String toString()
    {
        return ""
            + toString(_id_)
            + toString(_type_);
    }

    void removeChild(Node child)
    {
        if(_id_ == child)
        {
            _id_ = null;
            return;
        }

        if(_type_ == child)
        {
            _type_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(_type_ == oldChild)
        {
            setType((TType) newChild);
            return;
        }

    }
}
