/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.node;

import fr.umlv.ir2.compil.cmdlineparser.analysis.*;

public final class X1PS extends XPS
{
    private XPS _xPS_;
    private PS _pS_;

    public X1PS()
    {
    }

    public X1PS(
        XPS _xPS_,
        PS _pS_)
    {
        setXPS(_xPS_);
        setPS(_pS_);
    }

    public Object clone()
    {
        throw new RuntimeException("Unsupported Operation");
    }

    public void apply(Switch sw)
    {
        throw new RuntimeException("Switch not supported.");
    }

    public XPS getXPS()
    {
        return _xPS_;
    }

    public void setXPS(XPS node)
    {
        if(_xPS_ != null)
        {
            _xPS_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _xPS_ = node;
    }

    public PS getPS()
    {
        return _pS_;
    }

    public void setPS(PS node)
    {
        if(_pS_ != null)
        {
            _pS_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _pS_ = node;
    }

    void removeChild(Node child)
    {
        if(_xPS_ == child)
        {
            _xPS_ = null;
        }

        if(_pS_ == child)
        {
            _pS_ = null;
        }
    }

    void replaceChild(Node oldChild, Node newChild)
    {
    }

    public String toString()
    {
        return "" +
            toString(_xPS_) +
            toString(_pS_);
    }
}
