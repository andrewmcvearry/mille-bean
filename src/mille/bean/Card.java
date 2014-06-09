package mille.bean;

import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public abstract class Card
{
    private final String name;
    private final Texture texture;
    
    public Card(String cardName, String path)
    {
        name = cardName;
        texture = loadTexture(path);
    }
    
    public String getName()
    {
        return name;
    }
    
    public Texture getTexture()
    {
        return texture;
    }
    
    private Texture loadTexture(String path)
    {
        try
        {
            return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
}
