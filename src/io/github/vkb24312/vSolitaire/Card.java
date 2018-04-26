package io.github.vkb24312.vSolitaire;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class Card{

    public Card(int suit, int number) throws URISyntaxException, IOException{
        if(number < 1) number = 1;
        if(number >13) number = 13;
        if(suit>3) suit = 3;
        this.suit = suit;
        this.number = number;

        File image;

        try {
            image = new File(getClass().getResource("card-deck.png").toURI());
        } catch (NullPointerException e){
            System.out.println("Next time, don't delete the images that come with the program");
            image = null;
        }

        System.out.println(image.getAbsolutePath());

        try {
            bi = ImageIO.read(image).getSubimage((number - 1) * (960 / 13+1), (suit) * (460 / 4), (960 / 13)-1, (460 / 4));
            invisible = ImageIO.read(image).getSubimage(2 * (960/13+1), 4 * (460 / 4), (960 / 13)-1, (460 / 4));
        } catch (RasterFormatException e){
            System.out.println("Next time, don't resize the image files");
        }

        this.getClass();
    }

    int suit;
    int number;

    //<editor-fold desc="Suit names">
    static final int SUIT_SPADES = 3;
    static final int SUIT_HEARTS = 2;
    static final int SUIT_DIAMONDS = 1;
    static final int SUIT_CLUBS = 0;

    static final int SUIT_TILES = SUIT_DIAMONDS;
    static final int SUIT_CLOVERS = SUIT_CLUBS;
    static final int SUIT_PIKES = SUIT_SPADES;
    //</editor-fold>

    //<editor-fold desc="Card names">
    static final int CARD_ACE = 1;
    static final int CARD_JACK = 11;
    static final int CARD_QUEEN = 12;
    static final int CARD_KING = 13;
    //</editor-fold>

    BufferedImage bi;
    BufferedImage invisible;
    //153 234
    //1920×1150

    boolean shown = true;

    BufferedImage getImage(){
        if(shown){
            return bi;
        } else {
            return invisible;
        }
    }
}
