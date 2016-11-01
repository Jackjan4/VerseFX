/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.base;

import javafx.stage.Stage;

/**
 *
 * @author Jackjan
 */
public interface FXGame {
    
    
    public  void loadContent();
    
    public  void update(float deltaT);
    
    public void init(Stage stage);
}
