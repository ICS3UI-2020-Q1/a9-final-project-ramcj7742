import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main implements Runnable, ActionListener{

  // Class Variables  
  JPanel startPanel;
  JPanel gamePanel;
  JPanel mainPanel;
  JPanel loserPanel;
  JPanel winnerPanel;

  JButton upButton;
  JButton downButton;
  JButton leftButton;
  JButton rightButton;
  JButton neButton;
  JButton nwButton;
  JButton seButton;
  JButton swButton;
  JButton startButton;
  JButton restart;
  JButton teleport;
  JButton playAgain;

  Font biggerFont;

  CardLayout screens;

  ImageIcon userCharacter;
  ImageIcon enemy;
  ImageIcon superMan;

  JLabel picture;
  JLabel bad1;
  JLabel bad2;
  JLabel bad3;
  JLabel bad4;
  JLabel winTxt;
  JLabel loseTxt;
  JLabel powerUp;

  int userLocationX = 375;
  int userLocationY = 250;
  int upLocationX = 375;
  int upLocationY = 225;
  int downLocationX = 375;
  int downLocationY = 275;
  int leftLocationX = 350;
  int leftLocationY = 250;
  int rightLocationX = 400;
  int rightLocationY = 250;
  int nwLocationX = 350;
  int nwLocationY = 225;
  int neLocationX = 400;
  int neLocationY = 225;
  int swLocationX = 350;
  int swLocationY = 275;
  int seLocationX = 400;
  int seLocationY = 275;

  Random rand;

  int enemy1X;
  int enemy1Y;
  int enemy2X;
  int enemy2Y;
  int enemy3X;
  int enemy3Y;
  int enemy4X;
  int enemy4Y;
  int powerX;
  int powerY;
  int elim1 = 0;
  int elim2 = 0;
  int elim3 = 0;
  int elim4 = 0;
  int movesAmount = 0;

  int isPowerUp = 0;



  // Method to assemble our GUI
  public void run(){
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("Title");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(800,600);
    // shows the window
    frame.setVisible(true);

    //initialize the game panel 
    gamePanel = new JPanel();
    gamePanel.setLayout(null);

    //initialize the start panel 
    startPanel = new JPanel();
    startPanel.setLayout(null);

    //creates a bigger Font
    biggerFont = new Font("arial", Font.PLAIN, 30);

    //create start button
    startButton = new JButton("START GAME");
    startButton.setBounds(200, 175, 400, 200);
    startButton.setFont(biggerFont);
    startButton.addActionListener(this);
    startButton.setActionCommand("start");

    //initialize losing screen
    loserPanel = new JPanel();
    loserPanel.setLayout(null);

    //initialize winning screen
    winnerPanel = new JPanel();
    winnerPanel.setLayout(null);

    //create restart button
    restart = new JButton("Restart");
    restart.addActionListener(this);
    restart.setActionCommand("restart");
    restart.setFont(biggerFont);

    //create play again button
    playAgain = new JButton("Play Again?");
    playAgain.addActionListener(this);
    playAgain.setActionCommand("playAgain");
    playAgain.setFont(biggerFont);

    //sets size and location for restart and play again button
    restart.setBounds(200, 175, 400, 200);
    playAgain.setBounds(200, 175, 400, 200);

    //initialize the main panel  and create panel manager
    screens = new CardLayout();
    mainPanel = new JPanel();
    mainPanel.setLayout(screens);

    //adds screens to main panel 
    mainPanel.add(startPanel, "titleScreen");
    mainPanel.add(gamePanel, "gameScreen");
    mainPanel.add(loserPanel, "losingScreen");
    mainPanel.add(winnerPanel, "winningScreen");

    //initialize win text
    winTxt = new JLabel("You Won!");
    winTxt.setFont(biggerFont);
    winTxt.setBounds(325, 0, 300, 200);

    //initialize lose text 
    loseTxt = new JLabel("You Lost!");
    loseTxt.setFont(biggerFont);
    loseTxt.setBounds(325, 0, 300, 200);


    //initialize teleport button
    teleport = new JButton("Teleport");
    teleport.addActionListener(this);
    teleport.setActionCommand("teleport");
    teleport.setBounds(0, 0, 200, 20);
    teleport.setEnabled(true);

    //set up enemy
    enemy = new ImageIcon("moai.png");
    bad1 = new JLabel(enemy);
    bad2 = new JLabel(enemy);
    bad3 = new JLabel(enemy);
    bad4 = new JLabel(enemy);

    //set up powerup
    superMan = new ImageIcon("SpongeBob's_Sad_Nose.png");
    powerUp = new JLabel(superMan);
  
    //initialize the random number generator
    rand = new Random();

    //gets a random location for powerup
    do{
      powerX = rand.nextInt(775);
    }while(powerX % 25 != 0);
    do{
      powerY = rand.nextInt(575);
    }while(powerY % 25 != 0);

    //puts power up in its random location
    powerUp.setBounds(powerX, powerY, 25, 25);



    /*
    * Below creates enemies and places them around
    * the map
    */

    //get new random positions for enemys
    do{
      enemy1X = rand.nextInt(800);
      //makes sure position is a multiple of 25
    }while(enemy1X % 25 != 0);
    do{
      enemy1Y = rand.nextInt(600);
    }while(enemy1Y % 25 != 0);
    do{
      enemy2X = rand.nextInt(800);
    }while(enemy2X % 25 != 0);
    do{
      enemy2Y = rand.nextInt(600);
    }while(enemy2Y % 25 != 0);
    do{
      enemy3X = rand.nextInt(800);
    }while(enemy3X % 25 != 0);
    do{
      enemy3Y = rand.nextInt(600);
    }while(enemy3Y % 25 != 0);
    do{
      enemy4X = rand.nextInt(800);
    }while(enemy4X % 25 != 0);
    do{
      enemy4Y = rand.nextInt(600);
    }while(enemy4Y % 25 != 0);

    //set enemys and random positions
    bad1.setBounds(enemy1X, enemy1Y, 25, 25);
    bad2.setBounds(enemy2X, enemy2Y, 25, 25);
    bad3.setBounds(enemy3X, enemy3Y, 25, 25);
    bad4.setBounds(enemy4X, enemy4Y, 25, 25);

    //create up button
    upButton = new JButton("");
    upButton.addActionListener(this);
    upButton.setActionCommand("up");

    //create down button
    downButton = new JButton("");
    downButton.addActionListener(this);
    downButton.setActionCommand("down");

    //create left button
    leftButton = new JButton("");
    leftButton.addActionListener(this);
    leftButton.setActionCommand("left");

    //create right button
    rightButton = new JButton("");
    rightButton.addActionListener(this);
    rightButton.setActionCommand("right");

    //create north east button
    neButton = new JButton("");
    neButton.addActionListener(this);
    neButton.setActionCommand("ne");

    //create north west button 
    nwButton = new JButton("");
    nwButton.addActionListener(this);
    nwButton.setActionCommand("nw");

    //create south east button
    seButton = new JButton("");
    seButton.addActionListener(this);
    seButton.setActionCommand("se");

    //create south west button
    swButton = new JButton("");
    swButton.addActionListener(this);
    swButton.setActionCommand("sw");

    //set up users character 
    userCharacter = new ImageIcon("NoP1pte.png");
    picture = new JLabel(userCharacter);
    picture.setBounds(375, 250, 25, 25);

    //set button locations
    upButton.setBounds(375, 225, 25, 25);
    downButton.setBounds(375, 275, 25, 25);
    leftButton.setBounds(350, 250, 25, 25);
    rightButton.setBounds(400, 250, 25, 25);
    nwButton.setBounds(350, 225, 25, 25);
    neButton.setBounds(400, 225, 25, 25);
    seButton.setBounds(400, 275, 25, 25);
    swButton.setBounds(350, 275, 25, 25);

    //set user at start location
    picture.setBounds(375, 250, 25, 25);

    //add everything to game panel 
    gamePanel.add(upButton);
    gamePanel.add(downButton);
    gamePanel.add(leftButton);
    gamePanel.add(rightButton);
    gamePanel.add(nwButton);
    gamePanel.add(neButton);
    gamePanel.add(seButton);
    gamePanel.add(swButton);
    gamePanel.add(picture);
    gamePanel.add(bad1);
    gamePanel.add(bad2);
    gamePanel.add(bad3);
    gamePanel.add(bad4);
    gamePanel.add(teleport);
    gamePanel.add(powerUp);

    //add start button to start panel
    startPanel.add(startButton);

    //add restart to losing and play again winning screen 
    loserPanel.add(restart);
    winnerPanel.add(playAgain);

    //add win and lose text to win and lose screen
    loserPanel.add(loseTxt);
    winnerPanel.add(winTxt);

    //adds main panel to frame and shows title screen
    frame.add(mainPanel);
    screens.show(mainPanel, "titleScreen");



  }

  //method that determines new location based on user buttonpress
  public void movement(int cond1, int cond2){

    //determines new x and y coordinate for elements
    userLocationX = upLocationX + cond1;
    userLocationY = userLocationY + cond2;
    upLocationX = upLocationX + cond1;
    upLocationY = upLocationY + cond2;
    downLocationX = downLocationX + cond1;
    downLocationY = downLocationY + cond2;
    leftLocationX = leftLocationX + cond1;
    leftLocationY = leftLocationY + cond2;
    rightLocationX = rightLocationX + cond1;
    rightLocationY = rightLocationY + cond2;
    nwLocationX = nwLocationX + cond1;
    nwLocationY = nwLocationY + cond2;
    neLocationX = neLocationX + cond1;
    neLocationY = neLocationY + cond2;
    swLocationX = swLocationX + cond1;
    swLocationY = swLocationY + cond2;
    seLocationX = seLocationX + cond1;
    seLocationY = seLocationY + cond2;

    //sets new locations
    picture.setBounds(userLocationX, userLocationY, 25, 25);
    upButton.setBounds(upLocationX, upLocationY, 25, 25);
    downButton.setBounds(downLocationX, downLocationY, 25, 25);
    leftButton.setBounds(leftLocationX, leftLocationY, 25, 25);
    rightButton.setBounds(rightLocationX, rightLocationY, 25, 25);
    nwButton.setBounds(nwLocationX, nwLocationY, 25, 25);
    neButton.setBounds(neLocationX, neLocationY, 25, 25);
    swButton.setBounds(swLocationX, swLocationY, 25, 25);
    seButton.setBounds(seLocationX, seLocationY, 25, 25);

    /*
    * Below determines movement for all enemies
    *
    */
    if(userLocationX > enemy1X){
      enemy1X = enemy1X + 25;
    }else if(userLocationX < enemy1X){
      enemy1X = enemy1X - 25;
    }

    if(userLocationY > enemy1Y){
      enemy1Y = enemy1Y + 25;
    }else if(userLocationY < enemy1Y){
      enemy1Y = enemy1Y - 25;
    }

    if(userLocationX > enemy2X){
      enemy2X = enemy2X + 25;
    }else if(userLocationX < enemy2X){
      enemy2X = enemy2X - 25;
    }

    if(userLocationY > enemy2Y){
      enemy2Y = enemy2Y + 25;
    }else if(userLocationY < enemy2Y){
      enemy2Y = enemy2Y - 25;
    }

    if(userLocationX > enemy3X){
      enemy3X = enemy3X + 25;
    }else if(userLocationX < enemy3X){
      enemy3X = enemy3X - 25;
    }

    if(userLocationY > enemy3Y){
      enemy3Y = enemy3Y + 25;
    }else if(userLocationY < enemy3Y){
      enemy3Y = enemy3Y - 25;
    }

    if(userLocationX > enemy4X){
      enemy4X = enemy4X + 25;
    }else if(userLocationX < enemy4X){
      enemy4X = enemy4X - 25;
    }

    if(userLocationY > enemy4Y){
      enemy4Y = enemy4Y + 25;
    }else if(userLocationY < enemy4Y){
      enemy4Y = enemy4Y - 25;
    }

    //sets enemies new location
    bad1.setBounds(enemy1X, enemy1Y, 25, 25);
    bad2.setBounds(enemy2X, enemy2Y, 25, 25);
    bad3.setBounds(enemy3X, enemy3Y, 25, 25);
    bad4.setBounds(enemy4X, enemy4Y, 25, 25);

    //checks if user is collecting powerup
    if(userLocationX == powerX && userLocationY == powerY){
      powerUp.setEnabled(false);
      powerUp.setBounds(0, 0, 0, 0);
      isPowerUp = 1;
    }

    /*
    * If player is powered up, this will eliminate the
    * enemies instead of the player losing the game
    */

    if(isPowerUp == 1){
      if(enemy1X == userLocationX && enemy1Y == userLocationY){
        bad1.setEnabled(false);
        bad1.setBounds(0, 0, 0, 0);
        elim1 = 1;
      }else if(enemy2X == userLocationX && enemy2Y == userLocationY){
        bad2.setEnabled(false);
        bad2.setBounds(0, 0, 0, 0);
        elim2 = 1;
      }else if(enemy3X == userLocationX && enemy3Y == userLocationY){
        bad3.setEnabled(false);
        bad3.setBounds(0, 0, 0, 0);
        elim3 = 1;
      }else if(enemy4X == userLocationX && enemy4Y == userLocationY){
        bad4.setEnabled(false);
        bad4.setBounds(0, 0, 0, 0);
        elim4 = 1;
      }
    //determines if player has lost
    }else if(isPowerUp == 0){
      if(enemy1X == userLocationX && enemy1Y == userLocationY){
        screens.show(mainPanel, "losingScreen");
      }else if(enemy2X == userLocationX && enemy2Y == userLocationY){
        screens.show(mainPanel, "losingScreen");
      }else if(enemy3X == userLocationX && enemy3Y == userLocationY){
        screens.show(mainPanel, "losingScreen");
      }else if(enemy4X == userLocationX && enemy4Y == userLocationY){
        screens.show(mainPanel, "losingScreen");
      }
    }
    
    

    //determines if enemies have died by hitting each other
    if(enemy1X == enemy2X && enemy1Y == enemy2Y){
      bad1.setEnabled(false);
      bad2.setEnabled(false);
      bad1.setBounds(0, 0, 0, 0);
      bad2.setBounds(0, 0, 0, 0);
      elim1 = 1;
      elim2 = 1;
    }else if(enemy1X == enemy3X && enemy1Y == enemy3Y){
      bad1.setEnabled(false);
      bad3.setEnabled(false);
      bad1.setBounds(0, 0, 0, 0);
      bad3.setBounds(0, 0, 0, 0);
      elim1 = 1;
      elim3 = 1;
    }else if(enemy1X == enemy4X && enemy1Y == enemy4Y){
      bad1.setEnabled(false);
      bad4.setEnabled(false);
      bad1.setBounds(0, 0, 0, 0);
      bad4.setBounds(0, 0, 0, 0);
      elim1 = 1;
      elim4 = 1;
    }else if(enemy2X == enemy3X && enemy2Y == enemy3Y){
      bad3.setEnabled(false);
      bad2.setEnabled(false);
      bad3.setBounds(0, 0, 0, 0);
      bad2.setBounds(0, 0, 0, 0);
      elim3 = 1;
      elim2 = 1;
    }else if(enemy2X == enemy4X && enemy2Y == enemy4Y){
      bad4.setEnabled(false);
      bad2.setEnabled(false);
      bad4.setBounds(0, 0, 0, 0);
      bad2.setBounds(0, 0, 0, 0);
      elim4 = 1;
      elim2 = 1;
    }else if(enemy3X == enemy4X && enemy3Y == enemy4Y){
      bad3.setEnabled(false);
      bad4.setEnabled(false);
      bad3.setBounds(0, 0, 0, 0);
      bad4.setBounds(0, 0, 0, 0);
      elim3 = 1;
      elim4 = 1;
    }else if(enemy1X == enemy2X && enemy1Y == enemy2Y && enemy1X == enemy3X && enemy1Y == enemy3Y){
      bad1.setEnabled(false);
      bad2.setEnabled(false);
      bad3.setEnabled(false);
      bad1.setBounds(0, 0, 0, 0);
      bad2.setBounds(0, 0, 0, 0);
      bad3.setBounds(0, 0, 0, 0);
      elim1 = 1;
      elim2 = 1;
      elim3 = 1;
    }else if(enemy2X == enemy3X && enemy2Y == enemy3Y && enemy2X == enemy4X && enemy2Y == enemy4Y){
      bad4.setEnabled(false);
      bad2.setEnabled(false);
      bad3.setEnabled(false);
      bad4.setBounds(0, 0, 0, 0);
      bad2.setBounds(0, 0, 0, 0);
      bad3.setBounds(0, 0, 0, 0);
      elim4 = 1;
      elim2 = 1;
      elim3 = 1;
    }else if(enemy1X == enemy2X && enemy1Y == enemy2Y && enemy1X == enemy4X && enemy1Y == enemy4Y){
      bad4.setEnabled(false);
      bad2.setEnabled(false);
      bad1.setEnabled(false);
      bad4.setBounds(0, 0, 0, 0);
      bad2.setBounds(0, 0, 0, 0);
      bad1.setBounds(0, 0, 0, 0);
      elim4 = 1;
      elim2 = 1;
      elim1 = 1;
    }else if(enemy1X == enemy3X && enemy1Y == enemy3Y && enemy1X == enemy4X && enemy1Y == enemy4Y){
      bad4.setEnabled(false);
      bad1.setEnabled(false);
      bad3.setEnabled(false);
      bad4.setBounds(0, 0, 0, 0);
      bad1.setBounds(0, 0, 0, 0);
      bad3.setBounds(0, 0, 0, 0);
      elim4 = 1;
      elim1 = 1;
      elim3 = 1;
    }else if(enemy1X == enemy2X && enemy1Y == enemy2Y && enemy1X == enemy2X && enemy1Y == enemy3Y && enemy1X == enemy4X && enemy1Y == enemy4Y){
      bad4.setEnabled(false);
      bad2.setEnabled(false);
      bad3.setEnabled(false);
      bad1.setEnabled(false);
      bad4.setBounds(0, 0, 0, 0);
      bad2.setBounds(0, 0, 0, 0);
      bad3.setBounds(0, 0, 0, 0);
      bad1.setBounds(0, 0, 0, 0);
      elim4 = 1;
      elim2 = 1;
      elim3 = 1;
      elim1 = 1;
    }

    //checks if all enemies are eliminated and sends user to win screen
    if(elim1 >= 1 && elim2 >= 1 && elim3 >= 1 && elim4 >= 1){
      screens.show(mainPanel, "winningScreen");
    }





  }

  //method that sets the game up and can be done when restarting
  public void gameSetup(){
    //initialize the random number generator
    rand = new Random();

    //puts user at initial "start" position 
    picture.setBounds(375, 250, 25, 25);
    upButton.setBounds(375, 225, 25, 25);
    downButton.setBounds(375, 275, 25, 25);
    leftButton.setBounds(350, 250, 25, 25);
    rightButton.setBounds(400, 250, 25, 25);
    nwButton.setBounds(350, 225, 25, 25);
    neButton.setBounds(400, 225, 25, 25);
    seButton.setBounds(400, 275, 25, 25);
    swButton.setBounds(350, 275, 25, 25);

    //get new random positions for enemys
    do{
      enemy1X = rand.nextInt(800);
      //makes sure position is a multiple of 25
    }while(enemy1X % 25 != 0);
    do{
      enemy1Y = rand.nextInt(600);
    }while(enemy1Y % 25 != 0);
    do{
      enemy2X = rand.nextInt(800);
    }while(enemy2X % 25 != 0);
    do{
      enemy2Y = rand.nextInt(600);
    }while(enemy2Y % 25 != 0);
    do{
      enemy3X = rand.nextInt(800);
    }while(enemy3X % 25 != 0);
    do{
      enemy3Y = rand.nextInt(600);
    }while(enemy3Y % 25 != 0);
    do{
      enemy4X = rand.nextInt(800);
    }while(enemy4X % 25 != 0);
    do{
      enemy4Y = rand.nextInt(600);
    }while(enemy4Y % 25 != 0);

    //sets elim values back to defualt 
    elim1 = 0;
    elim2 = 0;
    elim3 = 0;
    elim4 = 0;

    //set enemys at new random positions and re-enable
    bad1.setBounds(enemy1X, enemy1Y, 25, 25);
    bad2.setBounds(enemy2X, enemy2Y, 25, 25);
    bad3.setBounds(enemy3X, enemy3Y, 25, 25);
    bad4.setBounds(enemy4X, enemy4Y, 25, 25);
    bad1.setEnabled(true);
    bad2.setEnabled(true);
    bad3.setEnabled(true);
    bad4.setEnabled(true);

    //sets locaiton values back to normal
    userLocationX = 375;
    userLocationY = 250;
    upLocationX = 375;
    upLocationY = 225;
    downLocationX = 375;
    downLocationY = 275;
    leftLocationX = 350;
    leftLocationY = 250;
    rightLocationX = 400;
    rightLocationY = 250;
    nwLocationX = 350;
    nwLocationY = 225;
    neLocationX = 400;
    neLocationY = 225;
    swLocationX = 350;
    swLocationY = 275;
    seLocationX = 400;
    seLocationY = 275;

    //turns teleport button on
    teleport.setEnabled(true);

    //gets random locaiton for powerup
    do{
      powerX = rand.nextInt(775);
    }while(powerX % 25 != 0);
    do{
      powerY = rand.nextInt(575);
    }while(powerY % 25 != 0);

    //puts power up in its random location
    powerUp.setBounds(powerX, powerY, 25, 25);

    //re enables power up
    powerUp.setEnabled(true);

    //turns off powerup
    isPowerUp = 0;
  

  }



  // method called when a button is pressed
  public void actionPerformed(ActionEvent e){
    // get the command from the action
    String command = e.getActionCommand();

    //initialize the random number generator(for if user teleports)
    rand = new Random();

    //switches title screen to game screen
    if(command.equals("start")){
      screens.show(mainPanel, "gameScreen");
    }

    //restarts game if restart is pressed
    if(command.equals("restart")){
      gameSetup();
      screens.show(mainPanel, "gameScreen");
    }

    //restarts game if play again is pressed
    if(command.equals("playAgain")){
      gameSetup();
      screens.show(mainPanel, "gameScreen");
    }

    //counter that tracks amount of moves after hitting teleport
    movesAmount = movesAmount + 1;
    if(movesAmount >= 10){
      teleport.setEnabled(true);
    }

    //teleports user if teleport is selected
    if(command.equals("teleport")){
      do{
        userLocationX = rand.nextInt(775);
      }while(userLocationX % 25 != 0);
      do{
        userLocationY = rand.nextInt(575);
      }while(userLocationY % 25 != 0);

      //sets all location values to new location
      upLocationX = userLocationX;
      upLocationY = userLocationY - 25;
      downLocationX = userLocationX;
      downLocationY = userLocationY + 25;
      leftLocationX = userLocationX - 25;
      leftLocationY = userLocationY;
      rightLocationX = userLocationX + 25;
      rightLocationY = userLocationY;
      nwLocationX = userLocationX - 25;
      nwLocationY = userLocationY - 25;
      neLocationX = userLocationX + 25;
      neLocationY = userLocationY - 25;
      swLocationX = userLocationX - 25;
      swLocationY = userLocationY + 25;
      seLocationX = userLocationX + 25;
      seLocationY = userLocationY + 25;

      //sets user and all components at random location
      picture.setBounds(userLocationX, userLocationY, 25, 25);
      upButton.setBounds(upLocationX, upLocationY, 25, 25);
      downButton.setBounds(downLocationX, downLocationY, 25, 25);
      leftButton.setBounds(userLocationX - 25, userLocationY, 25, 25);
      rightButton.setBounds(rightLocationX, rightLocationY, 25, 25);
      nwButton.setBounds(nwLocationX, nwLocationY, 25, 25);
      neButton.setBounds(neLocationX, neLocationY, 25, 25);
      swButton.setBounds(swLocationX, swLocationY, 25, 25);
      seButton.setBounds(seLocationX, seLocationY, 25, 25);

      //turns off teleport button so it can only be used once every 10 moves
      teleport.setEnabled(false);

      //sets movement tracker to 0
      movesAmount = 0;
    }

    //determines if a movement button is pressed and moves character and enemies accordingly
    if(command.equals("up")){
      movement(0, -25);
    }else if(command.equals("down")){
      movement(0, 25);
    }else if(command.equals("left")){
      movement(-25, 0);
    }else if(command.equals("right")){
      movement(25, 0);
    }else if(command.equals("ne")){
      movement(25, -25);
    }else if(command.equals("nw")){
      movement(-25, -25);
    }else if(command.equals("se")){
      movement(25, 25);
    }else if(command.equals("sw")){
      movement(-25, 25);
    }



  }

  // Main method to start our program
  public static void main(String[] args){
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }
}
