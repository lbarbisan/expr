/* This file was generated by SableCC (http://www.sablecc.org/). */

package fr.umlv.ir2.compil.cmdlineparser.parser;

import fr.umlv.ir2.compil.cmdlineparser.node.*;

public class ParserException extends Exception
{
    Token token;

    public ParserException(Token token, String  message)
    {
        super(message);
        this.token = token;
    }

    public Token getToken()
    {
        return token;
    }
}
