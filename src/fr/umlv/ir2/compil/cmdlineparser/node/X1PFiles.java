/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.node;

import fr.umlv.ir2.compil.cmdlineparser.analysis.*;

public final class X1PFiles extends XPFiles
{
    private XPFiles _xPFiles_;
    private PFiles _pFiles_;

    public X1PFiles()
    {
    }

    public X1PFiles(
        XPFiles _xPFiles_,
        PFiles _pFiles_)
    {
        setXPFiles(_xPFiles_);
        setPFiles(_pFiles_);
    }

    public Object clone()
    {
        throw new RuntimeException("Unsupported Operation");
    }

    public void apply(Switch sw)
    {
        throw new RuntimeException("Switch not supported.");
    }

    public XPFiles getXPFiles()
    {
        return _xPFiles_;
    }

    public void setXPFiles(XPFiles node)
    {
        if(_xPFiles_ != null)
        {
            _xPFiles_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _xPFiles_ = node;
    }

    public PFiles getPFiles()
    {
        return _pFiles_;
    }

    public void setPFiles(PFiles node)
    {
        if(_pFiles_ != null)
        {
            _pFiles_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _pFiles_ = node;
    }

    void removeChild(Node child)
    {
        if(_xPFiles_ == child)
        {
            _xPFiles_ = null;
        }

        if(_pFiles_ == child)
        {
            _pFiles_ = null;
        }
    }

    void replaceChild(Node oldChild, Node newChild)
    {
    }

    public String toString()
    {
        return "" +
            toString(_xPFiles_) +
            toString(_pFiles_);
    }
}