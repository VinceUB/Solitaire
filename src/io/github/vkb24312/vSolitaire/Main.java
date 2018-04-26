package io.github.vkb24312.vSolitaire;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;
import java.net.URISyntaxException;

import java.util.*;

public class Main extends Frame{

    private static Card[][] pile = new Card[7][13];
    private static int[] foundation = new int[4];
    private static Card[] bigPile = new Card[52];
    static private Card[] otherBigPile = new Card[52];

    public static void main(String[] args) throws URISyntaxException, IOException{
        Main f = new Main();

        //<editor-fold desc="Card creation">
	    int i = -1;

        for (int suit = 0; suit < 4; suit++) {
            for (int cardNumber = 1; cardNumber < 14; cardNumber++) {
                i++;

                bigPile[i] = new Card(suit, cardNumber);
            }
        }

        System.out.println('\u0000');
        //</editor-fold>

        //<editor-fold desc="Card shuffling">
        ArrayList<Card> tempBigPile = new ArrayList<>();

        Collections.addAll(tempBigPile, bigPile);

        Collections.shuffle(tempBigPile);

        bigPile = tempBigPile.toArray(new Card[0]);

        System.out.println('\u0000');
        //</editor-fold>

        //<editor-fold desc="Frame setup">
        f.setTitle("Vincent's solitaire game");

        f.setVisible(true);
        f.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        System.exit(0);
                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                        f.dispose();
                    }
                }
        );
        f.setSize(300, 300);
        //</editor-fold>

        //<editor-fold desc="Card laying">
        int l = 0;

        for (int j = 0; j < pile.length; j++) {
            for (int k = 0; k < j+1; k++) {
                l++;
                pile[j][k] = bigPile[bigPile.length-l];
                bigPile[bigPile.length-l] = null;
                if(k==j){
                    pile[j][k].shown = true;
                } else {
                    pile[j][k].shown = false;
                }
            }
        }
        //</editor-fold>

        f.repaint();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(getTopCard(bigPile).getImage(), 0, 0, null); //FIXME: Figure out why this returns NullPointerException
    }

    private Card getTopCard(Card[] cards){
        int i = cards.length-1;
        while(cards[i]==null){
            i--;
        }

        return cards[i];
    }
}
