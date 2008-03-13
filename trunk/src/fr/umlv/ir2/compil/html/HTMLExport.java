/*
 * Created on 9 févr. 2005
 */
package fr.umlv.ir2.compil.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.Stack;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLWriter;

/**
 * Export code in
 * @author Boitel Olivier
 * @author Laurent Barbisan
 */
public class HTMLExport
{
    private PrintWriter writer;
    private File file;
    private HTMLDocument doc; 
    private Element body;
    private int numberStyles = 0;
    private boolean alreadyNl = false;
    private int indent = 0;
    private Stack<String> styles = new Stack<String>();
    private StringBuffer buffer = new StringBuffer(100000);
    
    
    public HTMLExport(File file)
    {
        HTMLEditorKit kit = new HTMLEditorKit();
        doc = (HTMLDocument) kit.createDefaultDocument();
        Element eIndex;
        
        Element root =  doc.getDefaultRootElement();
        try
        {
            InputStream stream = HTMLExport.class.getResourceAsStream("stylesheet.css");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer(10000);
            String line;
            buffer.append("\n<style>");
            while((line=reader.readLine())!=null)
            {
                buffer.append("\n").append(line);
                
            }
            buffer.append("\n</style>");
            doc.insertAfterStart(root, buffer.toString());
                
        }
        catch (BadLocationException exception){exception.printStackTrace();}
        catch (IOException exception){exception.printStackTrace();}
        
        for (int i = 0; i < root.getElementCount(); i++)
        {            
            eIndex=root.getElement(i);
            if(eIndex.getName().compareToIgnoreCase("body")==0)
            {
                body = eIndex;
                try
                {
                    doc.setOuterHTML(body , "<body class=body> </body>");
                    styles.push("body");
                }
                catch (BadLocationException exception){exception.printStackTrace();}
                catch (IOException exception){exception.printStackTrace();}
            }
        }
        
        for (int i = 0; i < root.getElementCount(); i++)
        {            
            eIndex=root.getElement(i);
            if(eIndex.getName().compareToIgnoreCase("body")==0)
            {
                body = eIndex;
            }
        }
        
        this.file = file;
    }
    
    public void close()
    {
        try
        {
            doc.setInnerHTML(body, buffer.toString());
            writer =  new PrintWriter(file);
            HTMLWriter htmlWriter = new HTMLWriter(writer, doc);
            htmlWriter.write();
            writer.flush();
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        catch (BadLocationException e1)
        {
            e1.printStackTrace();
        } 
    }
    
    
    public void startStyle(String classStyle)
    {
        styles.push(classStyle);
        buffer.append("<span class=\"").append(classStyle).append("\">");
        numberStyles++;   
    }
    
    public void writeElement(String element)
    {
        alreadyNl = false;
        buffer.append(element);
    }
    
    
    public void writeln(int indent)
    {
        writeln(indent, true);
    }
    
    public void writeln(int indent, boolean alwaysDone)
    {
        if(alreadyNl==false || alwaysDone==false)
        {
            buffer.append("<br>");
            this.indent += indent;
            for(int index = 0; index <this.indent*2; index++)
            {
                buffer.append("&nbsp;");     
            }
            alreadyNl = true;
        }
    }
    
    public void setRelativeIndent(int indent)
    {
        alreadyNl = false;
        this.indent += indent;
    }
    
    public void writeStyledElement(String style, String element)
    {
        this.startStyle(style);
        this.writeElement(element);
        this.cancelStyle();
    }
    
    public void cancelStyle()
    {
        styles.pop();
        buffer.append("</span>");
    }
}
