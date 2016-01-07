/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Main.Matrix4f;
import Main.Vector2f;
import Main.Vector4f;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Lime
 */
public class TrueTypeFont {

    /** Array that holds necessary information about the font characters */
    private IntObject[] charArray = new IntObject[256];

    /** Map of user defined font characters (Character <-> IntObject) */
    private Map customChars = new HashMap();

    /** Boolean flag on whether AntiAliasing is enabled or not */
    private boolean antiAlias;

    /** Font's size */
    private int fontSize = 0;

    /** Font's height */
    private int fontHeight = 0;

    /** Default font texture width */
    private int textureWidth = 512;

    /** Default font texture height */
    private int textureHeight = 512;

    /** Texture used to cache the font 0-255 characters */
    private Texture fontTexture;

    /** A reference to Java's AWT Font that we create our font texture from */
    private java.awt.Font font;

    /** The font metrics for our Java AWT font */
    private FontMetrics fontMetrics;

    /**
     * This is a special internal class that holds our necessary information for
     * the font characters. This includes width, height, and where the character
     * is stored on the font texture.
     */
    private class IntObject {
        /** Character's width */
        public int width;

        /** Character's height */
        public int height;

        /** Character's stored x position */
        public int storedX;

        /** Character's stored y position */
        public int storedY;
    }

    /**
     * Constructor for the TrueTypeFont class Pass in the preloaded standard
     * Java TrueType font, and whether you want it to be cached with
     * AntiAliasing applied.
     * 
     * @param font
     *            Standard Java AWT font
     * @param antiAlias
     *            Whether or not to apply AntiAliasing to the cached font
     * @param additionalChars
     *            Characters of font that will be used in addition of first 256 (by unicode).
     */
    public TrueTypeFont(java.awt.Font font, boolean antiAlias, char[] additionalChars) {

        this.font = font;
        this.fontSize = font.getSize();
        this.antiAlias = antiAlias;

        createSet( additionalChars );
    }
    public TrueTypeFont(String fileName,String locationFileName) {
        this.fontTexture = new Texture(fileName);
        
        try {
            BufferedReader fr = new BufferedReader(new FileReader(new File(locationFileName)));
            String line;
            int i = 0;
            while((line = fr.readLine()) != null) {
                String[] splitted = line.split(":");
                charArray[i] = new IntObject();
                charArray[i].storedX = Integer.parseInt(splitted[0]);
                charArray[i].storedY = Integer.parseInt(splitted[1]);
                charArray[i].width = Integer.parseInt(splitted[2]);
                charArray[i].height = Integer.parseInt(splitted[3]);
                i++;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(TrueTypeFont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Constructor for the TrueTypeFont class Pass in the preloaded standard
     * Java TrueType font, and whether you want it to be cached with
     * AntiAliasing applied.
     * 
     * @param font
     *            Standard Java AWT font
     * @param antiAlias
     *            Whether or not to apply AntiAliasing to the cached font
     */
    public TrueTypeFont(java.awt.Font font, boolean antiAlias) {
        this( font, antiAlias, null );
    }

    /**
     * Create a standard Java2D BufferedImage of the given character
     * 
     * @param ch
     *            The character to create a BufferedImage for
     * 
     * @return A BufferedImage containing the character
     */
    private BufferedImage getFontImage(char ch) {
        // Create a temporary image to extract the character's size
        BufferedImage tempfontImage = new BufferedImage(1, 1,
                        BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) tempfontImage.getGraphics();
        if (antiAlias == true) {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
        }
        g.setFont(font);
        fontMetrics = g.getFontMetrics();
        int charwidth = fontMetrics.charWidth(ch);

        if (charwidth <= 0) {
                charwidth = 1;
        }
        int charheight = fontMetrics.getHeight();
        if (charheight <= 0) {
                charheight = fontSize;
        }

        // Create another image holding the character we are creating
        BufferedImage fontImage;
        fontImage = new BufferedImage(charwidth, charheight,
                        BufferedImage.TYPE_INT_ARGB);
        Graphics2D gt = (Graphics2D) fontImage.getGraphics();
        if (antiAlias == true) {
                gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
        }
        gt.setFont(font);

        gt.setColor(Color.WHITE);
        int charx = 0;
        int chary = 0;
        gt.drawString(String.valueOf(ch), (charx), (chary)
                        + fontMetrics.getAscent());

        return fontImage;

    }

    /**
     * Create and store the font
     * 
     * @param customCharsArray Characters that should be also added to the cache.
     */
    private void createSet( char[] customCharsArray ) {
        // If there are custom chars then I expand the font texture twice		
        if(customCharsArray != null && customCharsArray.length > 0) {
                textureWidth *= 2;
        }

        // In any case this should be done in other way. Texture with size 512x512
        // can maintain only 256 characters with resolution of 32x32. The texture
        // size should be calculated dynamicaly by looking at character sizes. 

        BufferedImage imgTemp = new BufferedImage(textureWidth, textureHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) imgTemp.getGraphics();

        g.setColor(new Color(255,255,255,1));
        g.fillRect(0,0,textureWidth,textureHeight);

        int rowHeight = 0;
        int positionX = 0;
        int positionY = 0;

        int customCharsLength = ( customCharsArray != null ) ? customCharsArray.length : 0;

        for (int i = 0; i < 256 + customCharsLength; i++) {

            // get 0-255 characters and then custom characters
            char ch = ( i < 256 ) ? (char) i : customCharsArray[i-256];

            BufferedImage fontImage = getFontImage(ch);

            IntObject newIntObject = new IntObject();

            newIntObject.width = fontImage.getWidth();
            newIntObject.height = fontImage.getHeight();
            if (positionX + newIntObject.width >= textureWidth) {
                positionX = 0;
                positionY += rowHeight;
                rowHeight = 0;
            }

            newIntObject.storedX = positionX;
            newIntObject.storedY = positionY;

            if (newIntObject.height > fontHeight) {
                fontHeight = newIntObject.height;
            }

            if (newIntObject.height > rowHeight) {
                rowHeight = newIntObject.height;
            }

            // Draw it here
            g.drawImage(fontImage, positionX, positionY, null);

            positionX += newIntObject.width;
            

            if( i < 256 ) { // standard characters
                charArray[i] = newIntObject;
            } else { // custom characters
                customChars.put(ch, newIntObject );
            }
        }
//        try {
//            File out = new File("chars.png");
//            ImageIO.write(imgTemp, "png", out);
//            PrintWriter pw = new PrintWriter(new File("charLocations.loc"));
//            for(IntObject io : charArray) {
//                pw.println(io.storedX+":"+io.storedY+":"+io.width+":"+io.height);
//            }
//            pw.close();
//        } catch (IOException ex) {
//            Logger.getLogger(TrueTypeFont.class.getName()).log(Level.SEVERE, null, ex);
//        }
        fontTexture = new Texture(imgTemp);
    }
    public void drawString(float x, float y, String whatchars,Vector4f c) {
            drawString(x,y,whatchars,c,0,whatchars.length()-1);
    }
    public void drawString(float x, float y, String whatchars,Vector4f c, int startIndex, int endIndex) {
        
        IntObject intObject;
        int charCurrent;
        
        ArrayList<Vector2f> coords = new ArrayList<>();
        ArrayList<Vector2f> textCoords = new ArrayList<>();
        ArrayList<Byte> indices = new ArrayList<>();
        
        int totalwidth = 0;
        for (int i = 0; i < whatchars.length(); i++) {
            charCurrent = whatchars.charAt(i);
            if (charCurrent < 256) {
                intObject = charArray[charCurrent];
            } else {
                intObject = (IntObject)customChars.get((char) charCurrent);
            } 

            if( intObject != null ) {
                if ((i >= startIndex) || (i <= endIndex)) {
                    
                    coords.add(new Vector2f(x + totalwidth,y));
                    coords.add(new Vector2f(x + totalwidth + intObject.width,y));
                    coords.add(new Vector2f(x + totalwidth,y + intObject.height));
                    coords.add(new Vector2f(x + totalwidth + intObject.width,y + intObject.height));
                    float minX = intObject.storedX;
                    float maxX = intObject.storedX + intObject.width;
                    float minY = intObject.storedY;
                    float maxY = intObject.storedY + intObject.height;
                    textCoords.add(new Vector2f(minX / textureWidth, minY / textureHeight));
                    textCoords.add(new Vector2f(maxX / textureWidth, minY / textureHeight));
                    textCoords.add(new Vector2f(minX / textureWidth, maxY / textureHeight));
                    textCoords.add(new Vector2f(maxX / textureWidth, maxY / textureHeight));
                    indices.add((byte)(i*4+0));
                    indices.add((byte)(i*4+2));
                    indices.add((byte)(i*4+3));
                    indices.add((byte)(i*4+0));
                    indices.add((byte)(i*4+3));
                    indices.add((byte)(i*4+1));
                }
                totalwidth += intObject.width;
            }
        }
        float[] coordArr = new float[coords.size()*3];
        float[] textCoordArr = new float[textCoords.size()*2];
        byte[] indexArr = new byte[indices.size()];
        for (int i = 0; i < coords.size(); i++) {
            coordArr[i*3] = coords.get(i).getX();
            coordArr[i*3+1] = coords.get(i).getY();
            coordArr[i*3+2] = 0;
        }
        for (int i = 0; i < textCoords.size(); i++) {
            textCoordArr[i*2] = textCoords.get(i).getX();
            textCoordArr[i*2+1] = textCoords.get(i).getY();
        }
        for(int i = 0; i < indices.size(); i++) {
            indexArr[i] = indices.get(i);
        }
        VertexArray va = new VertexArray(coordArr,indexArr,textCoordArr);
        
        fontTexture.bind();
        Shader.defShader.enable();
        Shader.defShader.setUniformMat4f("ml_matrix", Matrix4f.identity());
        Shader.defShader.setUniform4f("inColor", c);
        va.render();
        Shader.defShader.disable();
        fontTexture.unbind();
    }
}
