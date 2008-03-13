package fr.umlv.ir2.compil.symbolstable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import fr.umlv.ir2.compil.cmdlineparser.Config;
/**
 * Use to identify visibility of all variable.
 * @author Olivier Boitel
 * @author Laurent Barbisan
 */
public class SymbolsTable implements Env
{
	
	/**
	 * HashMap use to implements hashtable for symbols
	 * 
	 * @author lbarbisa
	 */
	private HashMap<String, LinkedList<Object>> hashTable;
	
	/**
	 * Create a SymbolTable
	 *
	 */
	public SymbolsTable()
	{
		this.hashTable = new HashMap<String, LinkedList<Object>>();
	}
	
	
	/**
	 * push a new symbol int the table 
	 * @param symbolName
	 * @param symbol
	 */
	public void push(String symbolName, Object symbol)
	{
		LinkedList<Object> list = this.hashTable.get(symbolName); 
		if(Config.verbose==true) System.out.println("[SymbolTable]Ajout Element:" + symbolName);
		if(list == null)
		{
			//le symbol n'existe pas dans la table
			list = new LinkedList<Object>();
			list.addFirst(symbol);
			this.hashTable.put(symbolName,list);
		}
		else
		{
			//le symbole a déjà été déclaré
			list.addFirst(symbol);
		}
		
	}
	
	/**
	 * move a symbol out of the table .
	 * @param symbolName
	 * @throws NoSuchElementException, when the element doesn't exist in the table, this case should never arrived.
	 * */
	public void pop(String symbolName) throws NoSuchElementException 
	{
		LinkedList<Object> list =  hashTable.get(symbolName);
		
		/* Vérit que  le symbole existe */
		if(list!=null)
		{
			if(Config.verbose==true) System.out.println("[SymbolTable]Suppression Element:" + symbolName);
			if(list.size()<1)
			{
				throw new NoSuchElementException(symbolName);
			}
			else
			{
				list.removeFirst();
			}
		}
		else
		{
			throw new NoSuchElementException(symbolName);
		}
	}
	
	/**
	 * get the current value of the symbol
	 * @param symbolName
	 * @return
	 * @throws NoSuchElementException, when the element doesn't exist in the table.
	 */
	public Object get(String symbolName)
	{
		LinkedList<Object> list = hashTable.get(symbolName);
		
		if(list!=null)
		{
			if(list.size()>0)
			{
				return list.getFirst();
			}
			
		}
		return null;
	}
	
	/**
	 * Show the entire symbol table.
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		String result = new String("[SymbolTable]\n");
		Collection collection = hashTable.keySet();
		Iterator iSymbolName = collection.iterator();
		
		
		while(iSymbolName.hasNext())
		{
			String str = (String)iSymbolName.next();
			Iterator isymbol = ((LinkedList)hashTable.get(str)).iterator();
			result = result +  "Variable :" + str + "";
			while(isymbol.hasNext())
			{
				result += "\t" + isymbol.next().toString() + "\n";
			}
			
		}
		
		return result.toString();
	}
}
