package mille.bean;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.TrueTypeFont;
import mille.bean.CardTypes.*;

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
            if (!isGameOver(players, milleDeck))
            {
                for (int i = 0; i < 4; i++)
                {
                    players.get(i).takeCard(milleDeck);
                    updateDisplay(players, milleDeck);
                    players.get(i).makePlay(players);
                }
            }
            else
            {
                JFrame frame = new JFrame("FrameDemo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                int[] scores = new int[4];
                scores[0] = humanPlayer.getTotalPoints();
                scores[1] = computerPlayer1.getTotalPoints();
                scores[2] = computerPlayer2.getTotalPoints();
                scores[3] = computerPlayer3.getTotalPoints();

                int max = 0;
                int winner = 0;
                for (int i = 0; i < scores.length; i++)
                {
                    if (scores[i] > max)
                    {
                        max = scores[i];
                        winner = i;
                    }
                }
                
                String wName = "";
                if (winner == 0)
                {
                    wName = "Human player";
                }
                else if (winner == 1)
                {
                    wName = "1st computer player";
                }
                else if (winner == 2)
                {
                    wName = "2nd computer player";
                }
                else if (winner == 3)
                {
                    wName = "3rd computer player";
                }
                
                String results = "Human Player: " +  scores[0] + "\n" +
                                 "1st Computer Player:" + scores[1] + "\n" +
                                 "2nd Computer Player:" + scores[2] + "\n" +
                                 "3rd Computer Player:" + scores[3] + "\n\n" +
                                 wName + " wins";
                
                //Create and set up the window.
                JOptionPane.showMessageDialog(frame, results, "Results", JOptionPane.INFORMATION_MESSAGE);

                //Display the window.
                frame.pack();
                frame.setVisible(true);
                
                Display.destroy();
                System.exit(0);
            }
         }
        
        Display.destroy();
    }
    
    public static boolean isGameOver(ArrayList<Player> players, Deck deck)
    {
        if (deck.isEmpty())
        {
            return true;
        }
        
        else if (players.get(0).getTotalDistance() == 700 ||
                 players.get(1).getTotalDistance() == 700 ||
                 players.get(2).getTotalDistance() == 700 ||
                 players.get(3).getTotalDistance() == 700)
        {
            return true;
        }
        
        else
        {
            return false;
        }
    }
     
    public static void createDisplay(int width, int height)
    {
        try
        {
            Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(width, height));
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
    
    public static void updateDisplay(ArrayList<Player> players, Deck deck)
    {
        GL11.glClearColor(0.0f, 0.5f, 0.0f, 0.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        
        Texture text = null;
        try {
            text = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/home/andrew/Desktop/you.png"));
        } catch (IOException ex) {
            Logger.getLogger(MilleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        text.bind();
        
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(0, 0);
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f(text.getTextureWidth(), 0);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f(text.getTextureWidth(), text.getTextureHeight());
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f(0, text.getTextureHeight());
        GL11.glEnd();

        for (int i = 0; i < players.get(0).getHand().size(); i++)
        {
            Card nextCard = players.get(0).getHand().get(i);
            nextCard.getTexture().bind();
            
            Texture texture = nextCard.getTexture();
            
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0, 0);
                GL11.glVertex2f(i * 64, 0);
                GL11.glTexCoord2f(1, 0);
                GL11.glVertex2f(i * 64 + texture.getTextureWidth(), 0);
                GL11.glTexCoord2f(1, 1);
                GL11.glVertex2f(i * 64 + texture.getTextureWidth(), texture.getTextureHeight());
                GL11.glTexCoord2f(0, 1);
                GL11.glVertex2f(i * 64, texture.getTextureHeight());
            GL11.glEnd();
        }

        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	TrueTypeFont font = new TrueTypeFont(awtFont, false);
        font.drawString(524, 0, "Cards left in deck: " + deck.size());
        
        renderCardAreas(players.get(0), 0, 200);
        renderCardAreas(players.get(1), 300, 200);
        renderCardAreas(players.get(2), 600, 200);
        renderCardAreas(players.get(3), 900, 200);
        
        Display.update();
    }
    
    public static void renderCardAreas(Player player, int x, int y)
    {
        if (player.getLastBattleAreaCard() != null)
        {
            Texture battleTexture = player.getLastBattleAreaCard().getTexture();
            battleTexture.bind();
            
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0, 0);
                GL11.glVertex2f(x, y);
                GL11.glTexCoord2f(1, 0);
                GL11.glVertex2f(x + battleTexture.getTextureWidth(), y);
                GL11.glTexCoord2f(1, 1);
                GL11.glVertex2f(x + battleTexture.getTextureWidth(), y + battleTexture.getTextureHeight());
                GL11.glTexCoord2f(0, 1);
                GL11.glVertex2f(x, y + battleTexture.getTextureHeight());
            GL11.glEnd();
        }
        
        else
        {
            Texture emptyPile = null;
            try {
                emptyPile = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream((System.getProperty("user.dir") + "/images/EmptyPile.png")));
            } catch (IOException ex) {
                Logger.getLogger(MilleBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0, 0);
                GL11.glVertex2f(x, y);
                GL11.glTexCoord2f(1, 0);
                GL11.glVertex2f(x + emptyPile.getTextureWidth(), y);
                GL11.glTexCoord2f(1, 1);
                GL11.glVertex2f(x + emptyPile.getTextureWidth(), y + emptyPile.getTextureHeight());
                GL11.glTexCoord2f(0, 1);
                GL11.glVertex2f(x, y + emptyPile.getTextureHeight());
            GL11.glEnd();
            
        }
        
        if (player.getLastSpeedLimitAreaCard() != null)
        {
            Texture limitTexture = player.getLastSpeedLimitAreaCard().getTexture();
            limitTexture.bind();
            
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0, 0);
                GL11.glVertex2f(x + 128 + 6, y);
                GL11.glTexCoord2f(1, 0);
                GL11.glVertex2f(x + 128 + 6 + limitTexture.getTextureWidth(), y);
                GL11.glTexCoord2f(1, 1);
                GL11.glVertex2f(x + 128 + 6 + limitTexture.getTextureWidth(), y + limitTexture.getTextureHeight());
                GL11.glTexCoord2f(0, 1);
                GL11.glVertex2f(x + 128 + 6, y + limitTexture.getTextureHeight());
            GL11.glEnd();
        }
        
        else
        {
            Texture emptyPile = null;
            try {
                emptyPile = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream((System.getProperty("user.dir") + "/images/EmptyPile.png")));
            } catch (IOException ex) {
                Logger.getLogger(MilleBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0, 0);
                GL11.glVertex2f(x + 128 + 6, y);
                GL11.glTexCoord2f(1, 0);
                GL11.glVertex2f(x + 128 + 6 + emptyPile.getTextureWidth(), y);
                GL11.glTexCoord2f(1, 1);
                GL11.glVertex2f(x + 128 + 6 + emptyPile.getTextureWidth(), y + emptyPile.getTextureHeight());
                GL11.glTexCoord2f(0, 1);
                GL11.glVertex2f(x + 128 + 6, y + emptyPile.getTextureHeight());
            GL11.glEnd();
        }
        
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	TrueTypeFont font = new TrueTypeFont(awtFont, false);
	font.drawString(x, 200 + y, "Distance: " + player.getTotalDistance());
        
        ArrayList<String> immunities = new ArrayList<>();
        for (int i = 0; i < player.getSafetyCards().size(); i++)
        {
            if (player.getSafetyCards().get(i) instanceof DrivingAceCard)
            {
               immunities.add("Driving Ace");
            }
            
            else if (player.getSafetyCards().get(i) instanceof ExtraTankCard)
            {
                immunities.add("Extra Tank");
            }
            
            else if (player.getSafetyCards().get(i) instanceof PunctureProofCard)
            {
                immunities.add("Puncture Proof");
            }
            
            else if (player.getSafetyCards().get(i) instanceof RightOfWayCard)
            {
                immunities.add("Right of Way");
            }
        }
        
        if (player.getSafetyCards().isEmpty())
        {
            immunities.add("None");
        }
        
        font.drawString(x, 220 + y, "Immunities:");
        for (int i = 0; i < immunities.size(); i++)
        {
            font.drawString(x, 220 + 20 + (20 * i) + y, "    " + immunities.get(i));
        }

    }
}