/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class AGrammar extends PGrammar
{
    private final LinkedList _declarations_ = new TypedLinkedList(new Declarations_Cast());

    public AGrammar()
    {
    }

    public AGrammar(
        List _declarations_)
    {
        {
            this._declarations_.clear();
            this._declarations_.addAll(_declarations_);
        }

    }

    public AGrammar(
        XPDeclarations _declarations_)
    {
        if(_declarations_ != null)
        {
            while(_declarations_ instanceof X1PDeclarations)
            {
                this._declarations_.addFirst(((X1PDeclarations) _declarations_).getPDeclarations());
                _declarations_ = ((X1PDeclarations) _declarations_).getXPDeclarations();
            }
            this._declarations_.addFirst(((X2PDeclarations) _declarations_).getPDeclarations());
        }

    }
    public Object clone()
    {
        return new AGrammar(
            cloneList(_declarations_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGrammar(this);
    }

    public LinkedList getDeclarations()
    {
        return _declarations_;
    }

    public void setDeclarations(List list)
    {
        _declarations_.clear();
        _declarations_.addAll(list);
    }

    public String toString()
    {
        return ""
            + toString(_declarations_);
    }

    void removeChild(Node child)
    {
        if(_declarations_.remove(child))
        {
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        for(ListIterator i = _declarations_.listIterator(); i.hasNext();)
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

    }

    private class Declarations_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PDeclarations node = (PDeclarations) o;

            if((node.parent() != null) &&
                (node.parent() != AGrammar.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != AGrammar.this))
            {
                node.parent(AGrammar.this);
            }

            return node;
        }
    }
}
