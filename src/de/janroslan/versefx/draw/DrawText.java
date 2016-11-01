/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.draw;

import de.janroslan.versefx.base.BasicNode;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author jackjan
 */
public class DrawText extends BasicNode {
    
    private boolean isClicked;

    private EventHandler<? super MouseEvent> handler;
    

    private DrawText(String tag, Text txt, double startX, double starty, double size, String fontFamily, int layer)
    {
        super(tag, txt, 0);
        
        if (fontFamily != null) {
            getText().setFont(Font.font(fontFamily, size));
        } else {
            getText().setFont(Font.font(size));
        }
        
        isClicked = false;

        // Mouse click event
        getText().setOnMouseClicked(event -> {
            isClicked = true;
            if (handler != null) {
                handler.handle(event);
            }
        });
        
        // Mouse release event
        getText().setOnMouseReleased(event -> {
            isClicked = false;
            if (handler != null) {
                handler.handle(event);
            }
        });
        
        
    }
    
    public DrawText(String tag, String txt, double startX, double startY, double size, String fontFamily, int layer) {
        this(tag,new Text(startX, startY, txt), startX,  startY, size, fontFamily, layer);
        
    }
    
    
    public Text getText()
    {
        return (Text)getNode();
    }

    public void setOnMouseClick(EventHandler<? super MouseEvent> handler) {
        this.handler = handler;
    }
    
    public void setOnMouseHover(EventHandler<? super MouseEvent> handler) 
    {
        getText().setOnMouseEntered(handler);
    }
    
    
    public void setOnMouseHoverEnd(EventHandler<? super MouseEvent> handler) 
    {
        getText().setOnMouseExited(handler);
    }
    
    public void setUnderline(boolean val)
    {
        getText().setUnderline(val);
    }

    @Override
    public void update(float deltaT) {
        
    }

}
