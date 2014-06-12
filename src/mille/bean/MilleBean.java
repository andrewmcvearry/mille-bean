package mille.bean;

import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;

public class MilleBean {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createDisplay(1200, 700);
        
        Deck milleDeck = new Deck();
        milleDeck.shuffle();
        
        ArrayList<Player> players = new ArrayList<>();
        
        HumanPlayer humanPlayer = new HumanPlayer("");
        humanPlayer.take6Cards(milleDeck);
        ComputerPlayer computerPlayer1 = new ComputerPlayer("");
        computerPlayer1.take6Cards(milleDeck);
        ComputerPlayer computerPlayer2 = new ComputerPlayer("");
        computerPlayer2.take6Cards(milleDeck);
        ComputerPlayer computerPlayer3 = new ComputerPlayer("");
        computerPlayer3.take6Cards(milleDeck);
        
        players.add(humanPlayer);
        players.add(computerPlayer1);
        players.add(computerPlayer2);
        players.add(computerPlayer3);

        // MAIN LOOP
        while (!Display.isCloseRequested())
        {
            for (int i = 0; i < 4; i++)
            {
                players.get(i).takeCard(milleDeck);
                updateDisplay(players);
                players.get(i).makePlay(players);
            }
        }
        
        Display.destroy();
    }
    
    public static boolean isGameOver(Deck deck, ArrayList<Player> players)
    {
        if (deck.isEmpty())
        {
            return true;
        }      
        else if(/*game conditions*/ false)
        {
            return true;
        }
        
        return true;
    }
     
    public static void createDisplay(int width, int height)
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        }
        catch (LWJGLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);               

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          

        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glViewport(0,0,width,height);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    
    public static void updateDisplay(ArrayList<Player> players)
    {
        // Clear the screen and depth buffer
        GL11.glClearColor(0.0f, 0.5f, 0.0f, 0.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        
        for (int i = 0; i < players.get(0).getHand().size(); i++)
        {
            Card nextCard = players.get(0).getHand().get(i);
            nextCard.getTexture().bind();
            
            Texture texture = nextCard.getTexture();
            
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0, 0);
                GL11.glVertex2f(i * 100, 0);
                GL11.glTexCoord2f(1, 0);
                GL11.glVertex2f(i * 100 + texture.getTextureWidth(), 0);
                GL11.glTexCoord2f(1, 1);
                GL11.glVertex2f(i * 100 + texture.getTextureWidth(), texture.getTextureHeight());
                GL11.glTexCoord2f(0, 1);
                GL11.glVertex2f(i * 100, texture.getTextureHeight());
            GL11.glEnd();
        }
        
        Texture texture = players.get(0).getHand().get(0).getTexture();
        
        texture.bind();
        

        
        Display.update();
    }
}