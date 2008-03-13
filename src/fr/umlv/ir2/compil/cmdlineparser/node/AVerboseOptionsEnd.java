/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.node;

import java.util.*;
import fr.umlv.ir2.compil.cmdlineparser.analysis.*;

public final class AVerboseOptionsEnd extends POptionsEnd
{
    private TOptVerbose _optVerbose_;
    private TQuote _right_;
    private POptionsStart _optionsStart_;

    public AVerboseOptionsEnd()
    {
    }

    public AVerboseOptionsEnd(
        TOptVerbose _optVerbose_,
        TQuote _right_,
        POptionsStart _optionsStart_)
    {
        setOptVerbose(_optVerbose_);

        setRight(_right_);

        setOptionsStart(_optionsStart_);

    }
    public Object clone()
    {
        return new AVerboseOptionsEnd(
            (TOptVerbose) cloneNode(_optVerbose_),
            (TQuote) cloneNode(_right_),
            (POptionsStart) cloneNode(_optionsStart_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVerboseOptionsEnd(this);
    }

    public TOptVerbose getOptVerbose()
    {
        return _optVerbose_;
    }

    public void setOptVerbose(TOptVerbose node)
    {
        if(_optVerbose_ != null)
        {
            _optVerbose_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _optVerbose_ = node;
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
            + toString(_optVerbose_)
            + toString(_right_)
            + toString(_optionsStart_);
    }

    void removeChild(Node child)
    {
        if(_optVerbose_ == child)
        {
            _optVerbose_ = null;
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
        if(_optVerbose_ == oldChild)
        {
            setOptVerbose((TOptVerbose) newChild);
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