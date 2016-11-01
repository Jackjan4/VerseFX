/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.base;

/**
 *
 * @author Jackjan
 */
public interface Timeable {
    
    public boolean hasTimeStamp();
    
    public TimeStamp getTimeStamp();
    
    public void saveTimeStamp(TimeStamp stamp);
    
    public void reverseTime(int millis);
}
