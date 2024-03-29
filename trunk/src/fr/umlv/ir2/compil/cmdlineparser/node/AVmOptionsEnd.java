/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.node;

import java.util.*;
import fr.umlv.ir2.compil.cmdlineparser.analysis.*;

public final class AVmOptionsEnd extends POptionsEnd
{
    private TOptVm _optVm_;
    private TQuote _right_;
    private POptionsStart _optionsStart_;

    public AVmOptionsEnd()
    {
    }

    public AVmOptionsEnd(
        TOptVm _optVm_,
        TQuote _right_,
        POptionsStart _optionsStart_)
    {
        setOptVm(_optVm_);

        setRight(_right_);

        setOptionsStart(_optionsStart_);

    }
    public Object clone()
    {
        return new AVmOptionsEnd(
            (TOptVm) cloneNode(_optVm_),
            (TQuote) cloneNode(_right_),
            (POptionsStart) cloneNode(_optionsStart_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVmOptionsEnd(this);
    }

    public TOptVm getOptVm()
    {
        return _optVm_;
    }

    public void setOptVm(TOptVm node)
    {
        if(_optVm_ != null)
        {
            _optVm_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _optVm_ = node;
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

    public POptionsStart getOptionsStart()
    {
        return _optionsStart_;
    }

    public void setOptionsStart(POptionsStart node)
    {
        if(_optionsStart_ != null)
        {
            _optionsStart_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _optionsStart_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_optVm_)
            + toString(_right_)
            + toString(_optionsStart_);
    }

    void removeChild(Node child)
    {
        if(_optVm_ == child)
        {
            _optVm_ = null;
            return;
        }

        if(_right_ == child)
        {
            _right_ = null;
            return;
        }

        if(_optionsStart_ == child)
        {
            _optionsStart_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_optVm_ == oldChild)
        {
            setOptVm((TOptVm) newChild);
            return;
        }

        if(_right_ == oldChild)
        {
            setRight((TQuote) newChild);
            return;
        }

        if(_optionsStart_ == oldChild)
        {
            setOptionsStart((POptionsStart) newChild);
            return;
        }

    }
}
