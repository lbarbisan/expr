/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.node;

import fr.umlv.ir2.compil.cmdlineparser.analysis.*;

public final class X2PS extends XPS
{
    private PS _pS_;

    public X2PS()
    {
    }

    public X2PS(
        PS _pS_)
    {
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
            toString(_pS_);
    }
}