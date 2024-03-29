/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;
import fr.umlv.ir2.compil.languageparser.analysis.*;

public final class ATextExprSimple extends PExprSimple
{
    private TTexte _texte_;

    public ATextExprSimple()
    {
    }

    public ATextExprSimple(
        TTexte _texte_)
    {
        setTexte(_texte_);

    }
    public Object clone()
    {
        return new ATextExprSimple(
            (TTexte) cloneNode(_texte_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATextExprSimple(this);
    }

    public TTexte getTexte()
    {
        return _texte_;
    }

    public void setTexte(TTexte node)
    {
        if(_texte_ != null)
        {
            _texte_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _texte_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_texte_);
    }

    void removeChild(Node child)
    {
        if(_texte_ == child)
        {
            _texte_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_texte_ == oldChild)
        {
            setTexte((TTexte) newChild);
            return;
        }

    }
}
