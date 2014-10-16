
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;



public class ButtonDemoPanel extends JPanel{
	
	private JButton bDuck,bImage, bLlama, start; //creates all nesisary JButtons
	private JLabel label= new JLabel();
	private JLabel label2 = new JLabel();
	private JPanel subPanel = new JPanel(); //creates the subpanel that will house duck/llama/start buttons
	private JPanel subPanel2 = new JPanel();//creates subpanel that will house directions and score
	private int numC= 0; //counts number Correct consecutive button presses
	private Timer t;
	private boolean gameOn = false;//variable that keeps track of the status of the game
	private Double rand; //random variable used in the llama duck icon changes
	private final int DELAY= 1000;//sets the timer to go off every 1 second

 /**
  * Class constructor creates and defines the buttons.
  */
   public ButtonDemoPanel(){
	   this.setLayout(new BorderLayout());
	   final ImageIcon duckButtonIcon = createImageIcon("images/duck.gif");
	   final ImageIcon duckButtonIcon2 = createImageIcon("images/duck2.jpeg");
       final ImageIcon lammaButtonIcon = createImageIcon("images/lamma.gif");
       final ImageIcon lammaButtonIcon2 = createImageIcon("images/llama2.png");
       final ImageIcon mainScreenIcon = createImageIcon("images/MainScreen.png");
       final ImageIcon loseScreenIcon = createImageIcon("images/losingScreen.jpeg");
       
       
       subPanel.setLayout(new GridLayout(2,2));//sets the subPanel layout to grid
       subPanel2.setLayout(new GridLayout(2,1));// sets the subPanel2 layout to grid

       bDuck = new JButton("Duck"); //create a button for duck
       bDuck.setVerticalTextPosition(AbstractButton.CENTER);
       bDuck.setMnemonic(KeyEvent.VK_D); //??
       bDuck.setActionCommand("disable");

       bImage = new JButton(mainScreenIcon);
       bImage.setVerticalTextPosition(AbstractButton.BOTTOM);
       bImage.setHorizontalTextPosition(AbstractButton.CENTER);
     

       bLlama = new JButton("Llama");//create a button for Llama
       
       
       
       //Use the default text position of CENTER, TRAILING (RIGHT).
       bLlama.setActionCommand("enable");
       bLlama.setMnemonic(KeyEvent.VK_L);    
       
       start = new JButton("start"); //create a button that restarts the counter
       
       label.setText("Press start to begin, you have one second to");
       label2.setText("determine if you are looking at a llama or duck");
       
      /**
       * Actionlistener that dictates the behavior of the duck button
       * it is only active when gameOn is true, checks if a picture 
       * of a duck is currently being displayed. If so, numC is incremented
       * by 1, timer is restarted and image is updated randomly (however the same
       * picture will not be displayed twice in a row. If a duck is not being 
       * displayed, gameOn is set to false, top information is subpanel2 is updated
       * displayed, the timer is set to false, the lose screen is brought up, and 
       * sets the start buttons text to "Restart"
       */
       bDuck.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent arg0) {
    		   if(gameOn==true){
    			   if((bImage.getIcon()==duckButtonIcon)||(bImage.getIcon()==duckButtonIcon2)){
    				   numC++;
    				   t.restart();
    				   if(bImage.getIcon()==duckButtonIcon){
    					   updateIcon(lammaButtonIcon,duckButtonIcon2);
    				   }
    				   else {
    					   updateIcon(lammaButtonIcon, duckButtonIcon);
    				   }
    			   }
    			   else {
    				   
    				   gameOn=false;
    				   updateLabel();
    				   t.stop();
    				   bImage.setIcon(loseScreenIcon);
    				   start.setText("Restart");
    			   }
    		   }
    	   }
       });
    		   
    		   
       /**
        * Actionlistener that dictates the behavior of the llama button
        * it is only active when gameOn is true, checks if a picture 
        * of a llama is currently being displayed. If so, numC is incremented
        * by 1, timer is restarted and image is updated randomly (however the same
        * picture will not be displayed twice in a row. If a llama is not being 
        * displayed, gameOn is set to false, top information is subpanel2 is updated
        * displayed, the timer is set to false, the lose screen is brought up, and 
        * sets the start buttons text to "Restart"
        */
       bLlama.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent arg0) {
    		   if(gameOn==true){
    			   if(bImage.getIcon()==(lammaButtonIcon)||(bImage.getIcon()==lammaButtonIcon2) ){
    				   numC++;
    				   //updateLabel();
    				   t.restart();
    				   if(bImage.getIcon()==lammaButtonIcon){
    					   updateIcon(lammaButtonIcon2, duckButtonIcon);
    				   }
    				   else{
    					   updateIcon(lammaButtonIcon,duckButtonIcon);
    				   }
    				   
    			   }
    			   else {
    				   gameOn=false;
    				   t.stop();
    				   updateLabel();
    				   bImage.setIcon(loseScreenIcon);
    				   start.setText("Restart");
    			   }
    		   }
    	   }
       });
    		   
    	/**
    	 * Actionlistener added to start button,
    	 * Initially, when gameOn is false, 
    	 * gameOn is set to true, numC is set to 0, all
    	 *  text is set, llama/duck pictures are updated 
    	 *  and timer is initiated and started
    	 * 
    	 * if the text in start is "Start", it is changed
    	 * to ""
    	 * 
    	 * if the text in start is "Restart", the text is changed to
    	 * "", numC is set to 0, information at the top of the 
    	 * screen is updated and start button text is set to ""
    	 */
       start.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent arg0) {
    		   if(gameOn==false){
    			   gameOn=true;
    			   numC=0;
    			   label.setText("");
    			   label2.setText("");
    			   updateIcon(lammaButtonIcon, duckButtonIcon);
    			   
    			   /**
    			    * Timer that calls an actionlistener if 
    			    * a player takes more than the time in milliseconds
    			    * DELAY(currently set to 1000ms or s second)
    			    * to determine if they are looking at a llama or
    			    * a duck
    			    * 
    			    * if the actionlistener is triggered, gameOn is set
    			    * to false, the timer is stopped, the losing Screen
    			    * is displayed, information at the top of the screen is 
    			    * updated and the start button text is set to "Restart"
    			    */
    			   t = new Timer(DELAY, new
    					   ActionListener()
    			   {
    				   public void actionPerformed(ActionEvent event)
    				   {
    					   gameOn=false;
    					   t.stop();
    					   bImage.setIcon(loseScreenIcon);
    					   updateLabel();
    					   start.setText("Restart");
    		   		    	   
    				   }
    		   		       
    			   });
    		   		    
    			   t.start();
    		   }
    		   if(start.getText()=="Start"){
    			   start.setText("");

    		   }
    		   else if(start.getText()=="restart"){
    			   numC = 0;
    			   updateLabel();
    			   start.setText("");
    		   }
    	   }
    	   
       });
       
       
       
       //Panels are added to the greater BorderLayout
       
       //adds panel which holds all the displayed images (Center)
       add(bImage, java.awt.BorderLayout.CENTER);
       //adding panel that holds the duck, llama, and start buttons (South)
       add(subPanel, java.awt.BorderLayout.SOUTH);
       subPanel.add(bDuck);
       subPanel.add(bLlama);
       subPanel.add(start);
       //adding Panel that hold information about score and directions (North)
       add(subPanel2, java.awt.BorderLayout.NORTH);
       subPanel2.add(label);
       subPanel2.add(label2);
       

   }
   
   /** Returns an ImageIcon, or null if the path was invalid. 
    * A good encapsulation for the validation and retrival of 
    * the image file. error 
   */
   private static ImageIcon createImageIcon(String path) {
       java.net.URL imgURL = ButtonDemo.class.getResource(path);
       if (imgURL != null) {
           return new ImageIcon(imgURL);
       } else {
           System.err.println("Couldn't find file: " + path);
           return null;
       }
   }
   
   /**
    * updates the text in label and label2 based on the number 
    * of times the duck and llama buttons have been pressed
    * also displays the restart button instructions
    */
   public void updateLabel() {
	   label.setText("Score: "+ numC);
	   label2.setText("Press the Restart button to try again.");
   }
   
   /**
    * this method takes 2 Images and randomly decides
    * which image will be assigned to bImage
    * @param l
    * 		an image
    * @param d
    * 		an image
    */
   public void updateIcon(ImageIcon l, ImageIcon d) {
	   rand = Math.floor(2*(Math.random()));
	   if(rand==0){
		   bImage.setIcon(l);
	   }
	   else {
		   bImage.setIcon(d);
	   }
   }
}

