/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.languageparser.node;

import java.util.*;

public class TypedLinkedList extends LinkedList
{
    Cast cast;

    public TypedLinkedList()
    {
        super();

        cast = NoCast.instance;
    }

    public TypedLinkedList(Collection c)
    {
        super();
        cast = NoCast.instance;
        this.addAll(c);
    }

    public TypedLinkedList(Cast cast)
    {
        super();

        this.cast = cast;
    }

    public TypedLinkedList(Collection c, Cast cast)
    {
        super();
        this.cast = cast;
        this.addAll(c);
    }

    public Cast getCast()
    {
        return cast;
    }

    public void add(int index, Object element)
    {
        super.add(index, cast.cast(element));
    }

    public boolean add(Object o)
    {
        return super.add(cast.cast(o));
    }

    public boolean addAll(Collection c)
    {
        for(Iterator i = c.iterator(); i.hasNext(); )
        {
            super.add(cast.cast(i.next()));
        }
        return true;
    }

    public boolean addAll(int index, Collection c)
    {
        int pos = index;
        for(Iterator i = c.iterator(); i.hasNext(); )
        {
            super.add(pos++, cast.cast(i.next()));
        }
        return true;
    }

    public void addFirst(Object o)
    {
        super.addFirst(cast.cast(o));
    }

    public void addLast(Object o)
    {
        super.addLast(cast.cast(o));
    }

    public ListIterator listIterator(int index)
    {
        return new TypedLinkedListIterator(super.listIterator(index));
    }

    private class TypedLinkedListIterator implements ListIterator
    {
        ListIterator iterator;

        TypedLinkedListIterator(ListIterator iterator)
        {
            this.iterator = iterator;
        }

        public boolean hasNext()
        {
            return iterator.hasNext();
        }

        public Object next()
        {
            return iterator.next();
        }

        public boolean hasPrevious()
        {
            return iterator.hasPrevious();
        }

        public Object previous()
        {
            return iterator.previous();
        }

        public int nextIndex()
        {
            return iterator.nextIndex();
        }

        public int previousIndex()
        {
            return iterator.previousIndex();
        }

        public void remove()
        {
            iterator.remove();
        }

        public void set(Object o)
        {
            iterator.set(cast.cast(o));
        }

        public void add(Object o)
        {
            iterator.add(cast.cast(o));
        }
    }
}
