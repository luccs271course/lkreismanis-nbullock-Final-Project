package ShapeShooterArcade;
/* 
 * File Name: UserPanel.java
 * Authors: Larisa Kreismanis, Nathan Bullock
 * Description: User panel of Shape Shooter Arcade game.
 * Date Created: 11/17/17
 * INCOMPLETE
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class UserPanel extends JPanel implements KeyListener, ActionListener, MouseListener, ArcadeFriendly  {
    private ArrayList<Shooter> shooters = new ArrayList<Shooter>(5);
    private ArrayList<Outline> outline = new ArrayList<Outline>(13);
    private ArrayList<Integer> highScore = new ArrayList <Integer>();
    
    private javax.swing.Timer timer;
    private Shooter myMovingShooter;
    private int shapesFilled = 0;
    private int timeCounter = 0;
    int addSec = 0;
    int currentHighScore;
    int currentScore;
    
    private boolean starts = false;
    
    public UserPanel(int width, int height){
        Color backColor = Color.WHITE;
        
        addMouseListener(this);
//------------------------------------------------------------------------------        
        //BACKGROUND OUTLINE of pattern
        outline.add(new TriangleOutline()); //UP Triangle Outline
        outline.add(new TriangleOutline()); //UP Triangle Outline 
        outline.add(new TriangleOutline()); //UP Triangle Outline 
        
        outline.add(new TriangleOutline()); //DOWN Triangle Outline 
        outline.add(new TriangleOutline()); //DOWN Triangle Outline 
        outline.add(new TriangleOutline()); //DOWN Triangle Outline
        
        outline.add(new TriangleOutline()); //RIGHT Triangle Outline 
        outline.add(new TriangleOutline()); //RIGHT Triangle Outline 
        outline.add(new TriangleOutline()); //RIGHT Triangle Outline 
        
        outline.add(new TriangleOutline()); //LEFT Triangle Outline 
        outline.add(new TriangleOutline()); //LEFT Triangle Outline 
        outline.add(new TriangleOutline()); //LEFT Triangle Outline 
        
        outline.add(new SquareOutline()); //Square Outline
//------------------------------------------------------------------------------        
        //SHOOTERS
        int triangleShooterHeight, triangleShooterWidth;
        int squareShooterHeight, squareShooterWidth;
        
        triangleShooterHeight = height;
        triangleShooterWidth = width;
        squareShooterHeight = height;
        squareShooterWidth = width;
        
        //UP Triangle Shooter
        shooters.add(new TriangleShooter());
        //DOWN Triangle Shooter
        shooters.add(new TriangleShooter());
        //RIGHT Triangle Shooter
        shooters.add(new TriangleShooter());
        //LEFT Triangle Shooter
        shooters.add(new TriangleShooter());
        //Square Shooter
        shooters.add(new SquareShooter());
//------------------------------------------------------------------------------        
        //TIMER status is checked every 50 milliseconds
        timer = new javax.swing.Timer(50, this);
        
        addKeyListener(this); //used for key controls
        addMouseMotionListener(new PanelMotionListener());  //used to listen to mouse events
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(backColor);
        setPreferredSize(new Dimension(width, height));
    } //end UserPanel constructor
    
//------------------------------------------------------------------------------    
    //INTERFACE METHODS
    public boolean running(){
        if(start){
            return true;
        }
        else{
            return false;
        }
    }
    public void startGame(){
        start = true;
        timer.start();
    }
    public void pauseGame(){
        start = false;
        timer.stop();
    }
    public void stopGame(){
        start = false;
        timer.stop();
        shapesFilled = 0;
    }
    public void endGame(){
        shapesFilled = 13;
    }
    public int getPoints(){
        return addSec;
    }
    public String getGameName(){
        return "SHAPE SHOOTER ARCADE GAME";
    }
    public String getInstructions(){
        return "Objective: " +
            '\n'+"Fill in the outlined shapes!"+
            '\n'+"Instructions: "+
            '\n'+"1.) Click a color shooter once."+
            '\n'+"2.) Hold and drag the shooter to aim."+
            '\n'+"3.) Press the spacebar to shoot."+
            '\n'+"4.) Match the shape bullet to the outline.";
    }
    public String getCredits(){
        return "Developed by Larisa Kreismanis and Nathan Bullock";
    }
    public String getHighScore(){
        try{
            Scanner readerFile = new Scanner(new File("FILENAME.txt"));
            String oldHSString = readerFile.nextLine();
            int oldHighScore = Integer.parseInt(oldHSString);
            String hs = oldHighScore + " ";
            return hs;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
//------------------------------------------------------------------------------
    
    
    
    //return null;
} //end class
