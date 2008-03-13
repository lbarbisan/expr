/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.node;

import fr.umlv.ir2.compil.cmdlineparser.analysis.*;

public final class X1PId extends XPId
{
    private XPId _xPId_;
    private PId _pId_;

    public X1PId()
    {
    }

    public X1PId(
        XPId _xPId_,
        PId _pId_)
    {
        setXPId(_xPId_);
        setPId(_pId_);
    }

    public Object clone()
    {
        throw new RuntimeException("Unsupported Operation");
    }

    public void apply(Switch sw)
    {
        throw new RuntimeException("Switch not supported.");
    }

    public XPId getXPId()
    {
        return _xPId_;
    }

    public void setXPId(XPId node)
    {
        if(_xPId_ != null)
        {
            _xPId_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _xPId_ = node;
    }

    public PId getPId()
    {
        return _pId_;
    }

    public void setPId(PId node)
    {
        if(_pId_ != null)
        {
            _pId_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _pId_ = node;
    }

    void removeChild(Node child)
    {
        if(_xPId_ == child)
        {
            _xPId_ = null;
        }

        if(_pId_ == child)
        {
            _pId_ = null;
        }
    }

    void replaceChild(Node oldChild, Node newChild)
    {
    }

    public String toString()
    {
        return "" +
            toString(_xPId_) +
            toString(_pId_);
    }
}