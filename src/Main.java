import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;

public class Main implements ActionListener
{
    private static Vector<ButtonInstance> allButtons;
    
    private static JButton newGame;
    
    private static ButtonInstance tempButton;
    
    private static JFrame windowMain = new JFrame();
    
    private static JPanel windowForButtons;
    private static JPanel windowsForTextToUser;
    private static JPanel windowsForNewGameButton;
    
    private static JLabel infoToUser;
    
    private static GridBagConstraints formForComponents;
    
    private static Font fontForText;
    
    private static boolean userInput;
    private static boolean condition1;
    private static boolean condition2;
    private static boolean condition3;
    private static boolean startNewGame;
    
    private static ArrayList<Boolean> booleanHolder;
    
    
    
    
    public Main()
    {
        /*If the player choses to start a new game this windows i closed.*/
        if(startNewGame)
        {
            windowMain.setVisible(false);
            windowMain.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            windowMain.dispatchEvent(new WindowEvent(windowMain, WindowEvent.WINDOW_CLOSING));
        
        }
        
        
        
        /*Initilize all classes (except for the JFrame) so they are fresh for every new game.*/
            allButtons = new Vector<ButtonInstance>(16);
            
            newGame = new JButton("Starta ett nytt spel!");
            
            windowForButtons = new JPanel();
            windowsForTextToUser = new JPanel();
            windowsForNewGameButton = new JPanel();
    
            infoToUser = new JLabel();
            
            booleanHolder = new ArrayList<Boolean>(3);
            
            
    
        /*Use another class to make a box appear where you decide the rules for the game*/
            booleanHolder = SetGameRules.main();
        
            condition1 = booleanHolder.get(0);
            condition2 = booleanHolder.get(1);
            condition3 = booleanHolder.get(2);
        
            
        
        /*Sets all method-spaning variables that can change to null, so that the game can be played many times
        without issue.*/
            windowMain = new JFrame();
            windowForButtons = new JPanel();
            infoToUser = new JLabel();
            booleanHolder = new ArrayList<Boolean>(3);
            windowsForNewGameButton = new JPanel();
            
            userInput = false;
            startNewGame = false;
            
    
            
        /*Creates variables of different types to be used within the main-method.*/
            Random click = new Random();
            
            String
                gameRules;
            
            /*This int is used to save the value of the height from the window the user sees, so that if it is
            optimized by the method pack() it can always be restored to its adjusted height.*/
                int
                    windowHeightSaver;
        
            /*These variables are used by the program to shuffle the discs in an order so they can later be solved by
             the player.*/
                int
                    indexForButtonPressedByProgram ,
                    valueForButtonPressedByProgram ,
                    indexForEmptyButtonByProgram;
                
                boolean
                    spotHolderForEmptySlot;
                
            /*This string is used to display the rules that have been choosen by the player to be in effect during the game.*/
                    gameRules = "Regler som gäller för detta spel:";
    
                    
                    
    
        /*This method decides in what order the buttons will be initialized and valued at. Rightn ow ony the parameters 1
         and 2 work.*/
            placeAndIntializeButtonsIntoList(1);
        
            
            
            
        /*To get the shapes and forms I wanted for my main window I had to use a class of the LayoutManager called
        GridBagLayout. In order to understand how to use it properly I had to borrow code and knowledge from the
        internet.
        
        Sources used:
        https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html ,
        https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java */
            windowMain.setSize(920, 1171);
            windowMain.setLayout(new GridBagLayout());
            windowMain.setLocationRelativeTo(null);
            windowMain.setResizable(false);
            windowMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
            formForComponents = new GridBagConstraints();
            
            formForComponents.fill = GridBagConstraints.HORIZONTAL;
            formForComponents.gridx = 0;
            formForComponents.gridy = 0;
            formForComponents.ipady = 9;
            formForComponents.weightx = 904;
            formForComponents.gridwidth = 904;
            windowMain.add(windowsForTextToUser, formForComponents);
        
        
            formForComponents = new GridBagConstraints();
        
            formForComponents.fill = GridBagConstraints.HORIZONTAL;
            formForComponents.gridx = 1;
            formForComponents.gridy = 1;
            formForComponents.ipady = 800;
            formForComponents.weightx = 904;
            formForComponents.gridwidth = 904;
            windowMain.add(windowForButtons, formForComponents);
        
        
            formForComponents = new GridBagConstraints();
        
            formForComponents.fill = GridBagConstraints.HORIZONTAL;
            formForComponents.gridx = 2;
            formForComponents.gridy = 2;
            formForComponents.ipady = 90;
            formForComponents.weightx = 904;
            formForComponents.gridwidth = 904;
            windowMain.add(windowsForNewGameButton, formForComponents);
            
            
        
        /*Sets the proper pattern for the windows with the buttons.*/
            windowForButtons.setLayout(new GridLayout(4, 4));
            windowForButtons.setSize(50,50);
    
       
        for(int i = 0; i < 16; i++)
        {
            windowForButtons.add(allButtons.get(i));
        
        }
    
    
        windowsForNewGameButton.add(newGame);
        windowsForNewGameButton.setLayout(new GridLayout(0,1));
    
    
        newGame.addActionListener(this);
        
        
        /*This part writes out the lines to the user of how the game rules work. I had to use outside sources for
        getting the text the way I wanted inside JLabel.
        Source: https://stackoverflow.com/questions/1090098/newline-in-jlabel*/
            windowsForTextToUser.add(infoToUser);
            
            if(condition1)
            {
                gameRules += "<br/>" + SetGameRules.victoryConditions1String;
               
            }
        
            if(condition2)
            {
                gameRules += "<br/>" + SetGameRules.victoryConditions2String;
                
            }
        
            if(condition3)
            {
                gameRules += "<br/>" + SetGameRules.victoryConditions3String;
                
            }
        
            gameRules = "<html>" + gameRules + "<html>";
            infoToUser.setText(gameRules);
            fontForText = new Font("Georgia", Font.PLAIN, 20);
            infoToUser.setFont(fontForText);
      
        
        
        /*Here the discs are shuffeled with 100k tries, where not all tries are a successful shuffle. Afterwords it
        is the players job to put them back in order*/
            for(int i1 = -0; i1 < 100000; i1++)
            {
                indexForButtonPressedByProgram = click.nextInt(15);
                
                valueForButtonPressedByProgram =  allButtons.get(indexForButtonPressedByProgram).getValue();
        
                
                indexForEmptyButtonByProgram = -1;
                
                for(int i2 = 0; i2 < 16; i2++)
                {
                    spotHolderForEmptySlot = allButtons.get(i2).isVisible();
                    
                    
                    if(!spotHolderForEmptySlot)
                    {
                        indexForEmptyButtonByProgram = i2;
                        break;
                        
                    }
                    
                }
        
                
                if(indexForEmptyButtonByProgram == -1)
                {
                    System.exit(0);
                    
                }
            
    
                checkForActions(indexForButtonPressedByProgram, valueForButtonPressedByProgram, indexForEmptyButtonByProgram);
            
            }
        
    
        windowMain.pack();
        windowHeightSaver = windowMain.getHeight();
        windowMain.setSize(920, windowHeightSaver);
        windowMain.setVisible(true);
        
    }
    
    
    
    
    public static void main(String[] args)
    {
        while(true)
        {
            Main game = new Main();
            
            
            while(!startNewGame)
            {
                
                try
                {
                    Thread.sleep(200);
        
                }
    
                catch(InterruptedException e)
                {}
                
            }
            
        }
        
    }
    
    
    
    
    public void placeAndIntializeButtonsIntoList(int nextValuePerIndex)
    {
        /*The ints are used to add, and also remove, indexes and elements of value and name as new objects of buttins
        into the List of
        buttons that the user can interact with.*/
            int
                valueForNameAndValueSetting ,
                valueForAdditionPerRow ,
                valueForCorrectTotalIterations ,
                valueForCorrectRowIterations ,
                countForExcessIndexes ,
                excessIndexValueGraphical ,
                excessIndexValueCode;
        
        
        /*Initilize buttons and add to List and ActionListener*/
            valueForCorrectRowIterations = 0;
        
            for(int i1 = 0; i1 < 4; i1++)
            {
                valueForCorrectTotalIterations = i1;
                valueForNameAndValueSetting = valueForCorrectTotalIterations + 1;
                valueForAdditionPerRow = valueForNameAndValueSetting * 4;
            
                for(int i2 = valueForCorrectRowIterations; i2 < valueForAdditionPerRow; i2++)
                {
                    if(i2 >= 15)
                    {
                        break;
                    
                    }
                
                    tempButton = new ButtonInstance();
                    tempButton.setNameAndValueAndIndex(valueForNameAndValueSetting , i2);
                    tempButton.setVisible(true);
                    tempButton.addActionListener(this);
                
                    allButtons.add(i2 , tempButton);
                    valueForNameAndValueSetting += nextValuePerIndex;
                
                }
                
                valueForCorrectRowIterations += nextValuePerIndex;
                ++valueForCorrectTotalIterations;
                
            }
            
            
            
        /*Empty button space*/
            tempButton = new ButtonInstance();
            tempButton.setVisible(false);
            tempButton.setIndexAndValueAndName(15, 0);
            allButtons.add(15, tempButton);
            
            
            
        /*This code is used if somehow the indexes of allButtons exced 16, which has happened with the more
        complicated system for initializing and adding buttons I have implemented. If it were to happen it
        wouldn't be seen by he user on the screen, but it would however cause illogical solutions in the code.
        Therefor I will keep this code even if i solve the issue with to many indexes for allButtons, just in
        case cause if it were to happen it would still be a real hassle.*/
            if(allButtons.size() > 16)
            {
                countForExcessIndexes = allButtons.size();
                
                while(countForExcessIndexes > 16)
                {
                    excessIndexValueGraphical = allButtons.size();
                    excessIndexValueCode = excessIndexValueGraphical -1;
                    
                    allButtons.remove(excessIndexValueCode);
                    countForExcessIndexes = allButtons.size();
                    
                }
                
            }
        
    }
    
    
    
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == newGame)
        {
            startNewGame = true;
            
        }
        
        else
        {
            userInput = true;
    
    
            ButtonInstance tempPress = new ButtonInstance();
    
            int buttonPressedIndex, buttonPressedValue, emptyButtonSlotIndex;
    
    
            tempPress = (ButtonInstance) e.getSource();
    
            buttonPressedIndex = tempPress.getIndex();
    
            buttonPressedValue = tempPress.getValue();
    
    
            emptyButtonSlotIndex = -1;
    
            for(int i = 0; i < 16; i++)
            {
                if(allButtons.get(i).isVisible())
                {}
        
                else
                {
                    emptyButtonSlotIndex = i;
            
                }
        
            }
    
    
            if(emptyButtonSlotIndex == -1)
            {
                System.exit(0);
        
            }
    
    
            checkForActions(buttonPressedIndex , buttonPressedValue , emptyButtonSlotIndex);
            
        }
        
    }
    
    
    
    
    public void checkForActions(int buttonPressedIndex, int buttonPressedValue, int emptySlotIndex)
    {
        boolean
            proceed;
        
        
        if(((buttonPressedIndex - 4) == emptySlotIndex) || ((emptySlotIndex - 4) == buttonPressedIndex))
        {
            replaceButtons(buttonPressedIndex, buttonPressedValue, emptySlotIndex);
            correctNewListIndex();
            initilizeJPanelAgain();
            
        }
    
        else if(((buttonPressedIndex - 1) == emptySlotIndex) || ((emptySlotIndex - 1) == buttonPressedIndex))
        {
            proceed = nextToFreeSpot(buttonPressedIndex, emptySlotIndex);
            
            if(proceed)
            {
                replaceButtons(buttonPressedIndex, buttonPressedValue, emptySlotIndex);
                correctNewListIndex();
                initilizeJPanelAgain();
                
            }
        
        }
    
    }
    
    
    
    public void replaceButtons(int buttonPressedIndex, int buttonPressedValue, int emptySlotIndex)
    {
        /*Variables to hold buttons to later replace the main button vector.*/
            Vector<ButtonInstance> holderList = new Vector <ButtonInstance>(16);
            ButtonInstance holderButton1 = new ButtonInstance();
            ButtonInstance holderButton2 = new ButtonInstance();
            
        
        
        /*Adds the moved button to the holder list.*/
            holderButton1.setNameAndValue(buttonPressedValue);
        
        
        /*Initilize the holder with the same values as the main list with the exception of the switched spots.*/
            for(int i = 0; i < 16; i++)
            {
                /*Switches the empty spot to the pressed spot.*/
                if(i == buttonPressedIndex)
                {
                    holderList.add(i,  holderButton2);
                    holderList.get(i).setVisible(false);
                    
                }
    
                /*Switches the pressed spot to the empty spot.*/
                else if(i == emptySlotIndex)
                {
                    holderList.add(i,  holderButton1);
                    holderList.get(i).setVisible(true);
                    holderList.get(i).addActionListener(this);
                    
                }
                
                else
                {
                    holderList.add(i, allButtons.get(i));
                    holderList.get(i).setVisible(true);
                    holderList.get(i).addActionListener(this);
                    
                }
                
            }
        
        
            for(int i = 0; i < 16; i++)
            {
                allButtons.remove(i);
                allButtons.add(i, holderList.get(i));
                
            }
        
    }
    
    
    
    static void correctNewListIndex()
    {
        ButtonInstance valueSaver = new ButtonInstance();
        
        int
            counter;
        
        
        counter = 0;
        
        for(ButtonInstance values : allButtons)
        {
            allButtons.get(counter).setIndex(counter);
            
            ++counter;
            
        }
        
    }
    
    
    
    static boolean nextToFreeSpot(int buttonPressedIndex, int emptySlotIndex)
    {
        boolean
            approve = true;
        
        int
            baseLapChecker ,
            firstLapChecker ,
        
            secondLapChecker ,
            lapCounter;
    
    
        firstLapChecker = -1;
        secondLapChecker = -1;
        
        
        for(int i = 0; i < 2; i++)
        {
            if(i == 0)
            {
                lapCounter = 0;
                baseLapChecker = buttonPressedIndex;
                
            }

            else
            {
                lapCounter = 0;
                baseLapChecker = emptySlotIndex;

            }
    
    
            while(true)
            {
                baseLapChecker -= 4;
        
                if(baseLapChecker <= -1)
                {
                    if(i == 0)
                    {
                        firstLapChecker = lapCounter;

                    }

                    else
                    {
                        secondLapChecker = lapCounter;

                    }

                    break;
            
                }
        
                else
                {
                    ++lapCounter;
            
                }
        
            }


            if(lapCounter <= 0)
            {
                while(true)
                {
                    baseLapChecker += 4;
            
                    if(baseLapChecker >= 16)
                    {
                        if(i == 0)
                        {
                            firstLapChecker = lapCounter;

                        }

                        else
                        {
                            secondLapChecker = lapCounter;

                        }

                        break;
                
                    }
            
                    else
                    {
                        ++lapCounter;
                
                    }

                }

            }
            
        }
        
        if((firstLapChecker != secondLapChecker) || (firstLapChecker == -1))
        {
            approve = false;
        
        
        }
        
        return approve;
        
    }
    
    
    
    /*Sets the grpahical interface to reflect the users choices.*/
    public void initilizeJPanelAgain()
    {
        boolean
            victory;
        
        int
            windowHeightSaver;
        
        
        /*Removes all elements from the JPanel. This had to be done because if it wasnt't then always some extra
        extra indexes (which I dont know what purpuse they fill) would be lifet which would make the optics go all
        wrong since more indexes meant it cant fit in a 4x4 form, so it forces itself out makeing the figure
        incomprehensible with extra rows and colums.*/
            windowForButtons.removeAll();
        
        /*Each single button has to be individualy added to the panel to fit into the 4x4 pattern.*/
            for(int i = 0; i < 16; i++)
            {
                windowForButtons.add(allButtons.get(i));
                
            }
    
            
        windowForButtons.revalidate();
        windowForButtons.repaint();
        
        
        /*The program decides if it is time to declare the program as finished and a win.*/
            if(userInput)
            {
                victory = checkForVictory();
                
                if(victory)
                {
                    infoToUser.setText("Du vann! Spela igen?");
                    fontForText = new Font("Javanese Text", Font.PLAIN, 40);
                    infoToUser.setFont(fontForText);
                    
                    
                    for(int i = 0; i < 16; i++)
                    {
                        allButtons.get(i).setEnabled(false);
                        
                    }
    
                    
                    windowMain.pack();
                    windowHeightSaver = windowMain.getHeight();
                    windowMain.setSize(920, windowHeightSaver);
                    windowMain.setVisible(true);
                    
                }
                
            }
        
    }
    
    
    
    
    public boolean checkForVictory()
    {
        boolean
            isVictory;
    
    
        isVictory = false;
    
        /*Checks victory for horizontal condition.*/
            if(!isVictory && condition1)
            {
                isVictory = isVictoryCustomValueComparisons(1, -100);
                
            }
    
        /*Checks victory for vertical condition.*/
            if(!isVictory && condition2)
            {
                isVictory = isVictoryCustomValueComparisons(4, 11);
            
            }
        
            
        return isVictory;
        
    }
    
    
    
    
    public boolean isVictoryCustomValueComparisons(int valueComparison1, int valueComparison2)
    {
        boolean
            checkerPosition ,
            checkerVictory ,
            blankSpotNotCounted1 ,
            blankSpotNotCounted2;
    
        int
            previousIndex ,
            currentIndex ,
            nextIndex ,
            positionIndexValue1 ,
            positionIndexValue2 ,
            indexForBlankSpot ,
            nextRowFromBlankSpot,
            nextRowTicker;
    
    
    
        nextRowTicker = 4;
        nextRowFromBlankSpot = -1;
        indexForBlankSpot = -1;
        checkerVictory = false;
        blankSpotNotCounted1 = true;
        blankSpotNotCounted2 = true;
        
        
        for(int i = 0; i < 16; i++)
        {
            previousIndex = i - 1;
            currentIndex = i;
            nextIndex = i + 1;
            
            
            /*This block makes sure (most) all correct solutions work if options 2 and 3 are selected for vicotry.
            Otherwise if a blank space is earlier then the last index in the loop it will cause a jam and the checker
            will fail when it reaches the index in the same colum on the next row (an additional value of 4). What
            this code does is make sure the ckecker will pass if it interacts this obstacle exactly 4 spaces from the 
            colum where the empty spot appeared on every row it passes.*/
                if(condition2 && condition3)
                {
                    if(indexForBlankSpot != -1 && blankSpotNotCounted1)
                    {
                        nextRowFromBlankSpot = indexForBlankSpot;
                        blankSpotNotCounted1 = false;
            
                    }
        
                    if(!blankSpotNotCounted1)
                    {
                        if(nextRowTicker == 4)
                        {
                            nextRowFromBlankSpot += 4;
                            nextRowTicker = 0;
                
                        }
            
                        else
                        {
                            ++nextRowTicker;
                
                        }
            
                    }
                    
                }
            
            
            
            positionIndexValue1 = allButtons.get(currentIndex).getValue();
            
            try
            {
                positionIndexValue2 = allButtons.get(nextIndex).getValue();
                
            }
        
            catch(ArrayIndexOutOfBoundsException e1)
            {
                positionIndexValue2 = allButtons.get(previousIndex).getValue();
                
            }
    
           
            
            if((positionIndexValue2 == positionIndexValue1 + valueComparison1) && (condition1 || condition2))
            {
                checkerPosition = true;
                
            }
    
            else if((positionIndexValue1 == positionIndexValue2 + valueComparison2) && condition2)
            {
                if(positionIndexValue1 == 0)
                {
                    indexForBlankSpot = currentIndex;
                    blankSpotNotCounted2 = false;
                    
                }
                
                else if(positionIndexValue2 == 0)
                {
                    indexForBlankSpot = nextIndex;
                    blankSpotNotCounted2 = false;
                    
                }
                
                checkerPosition = true;
        
            }
            
            else if(condition3)
            {
                if(blankSpotNotCounted2)
                {
                    if(positionIndexValue2 == 0)
                    {
                        indexForBlankSpot = nextIndex;
                        checkerPosition = true;
                        blankSpotNotCounted2 = false;
                        
                    }
                
                    else if(positionIndexValue1 == 0)
                    {
                        indexForBlankSpot = currentIndex;
                        checkerPosition = true;
                        blankSpotNotCounted2 = false;
                        
                    }
                
                    else
                    {
                        checkerPosition = false;
                     
                    }
                
                }
            
                else if(!blankSpotNotCounted2)
                {
                    if(positionIndexValue1 == 0)
                    {
                        indexForBlankSpot = currentIndex;
                        checkerPosition = true;
    
                    }
                    
                    else if(currentIndex == 15)
                    {
                        checkerPosition = true;
                        
                    }
                    
                    else if(nextIndex == nextRowFromBlankSpot)
                    {
                        checkerPosition = true;
                        
                    }
    
                    else
                    {
                        checkerPosition = false;
    
                    }
                    
                    
                }
            
                else
                {
                    checkerPosition = false;
                
                }
                
            }
        
            else if(blankSpotNotCounted2)
            {
                if(i == (allButtons.size() - 2) && condition1)
                {
                    blankSpotNotCounted2 = false;
                    checkerPosition = true;
                
                }
                
                else if(i == (allButtons.size() - 5) && condition2)
                {
                    blankSpotNotCounted2 = false;
                    checkerPosition = true;
                
                }
            
                else
                {
                    checkerPosition = false;
                
                }
    
            
            }

            else if(!blankSpotNotCounted2)
            {
                if(positionIndexValue1 == 0)
                {
                    indexForBlankSpot = currentIndex;
                    checkerPosition = true;
        
                }
    
                else
                {
                    checkerPosition = false;
                    
        
                }
                
            }
        
            else
            {
                checkerPosition = false;
              
            
            }
            
            
            if(!checkerPosition)
            {
                checkerVictory = false;
                break;
            
            }
        
            else
            {
                checkerVictory = true;
                
            }
            
        }
        
        
        return checkerVictory;
        
    }
    
}