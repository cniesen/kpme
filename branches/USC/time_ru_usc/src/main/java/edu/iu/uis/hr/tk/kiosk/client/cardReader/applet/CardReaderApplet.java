package edu.iu.uis.hr.tk.kiosk.client.cardReader.applet;

/**
 * @(#)CardReaderApplet.java
 * by Scott Martin 7/31/2000
 * adapted from SerialDemo and associated classes,
 * by Sun Microsystems, Inc. 1998
 *
 * Calls the javascript functions
 *		processCardData(track1,track2,track3)
 *		invalidSwipe()
 *		errorMessage(msg)
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

public class CardReaderApplet extends java.applet.Applet {
    private JSObject mainwin;
    private SerialParameters parameters;
    private SerialConnection connection;
    private Properties props = null;
    private String cardString = "";

    // Indexes 1 through 3
    //	for corresponding tracks;
    //	index 0 could be for entire
    //	card string.
    private String[] track = new String[4];

    //	0	not reading valid data
    //	1	reading track 1
    //	2	reading track 2
    //	3	reading track 3
    private int readingState = 0;

    public void init() {
    	

        for (int i = 0; i < 4; i++) {
            track[i] = "";
        }
        try {
            mainwin = JSObject.getWindow(this);
        } catch (JSException e) {
            System.out.println(" in other: "+e.getMessage());
        }
        parameters = new SerialParameters();
        connection = new SerialConnection(this, parameters);
       

    }

    public void stop() {
        connection.closeConnection();
    }

    /**
     * Input is a string from the card reader
     */
    public void cardRead(String string) {
        if (string.equals(""))
            return;
        switch (readingState) {
        case 0:
            if (string.equals("\n")) // end of cardstring
                cardStringAcquired();
            else {
                int first = scanForStart(string);
                if (first >= 0) // start character present
                    switch (string.charAt(first)) {
                    case '%':
                        readingState = 1;
                        cardRead(string.substring(first));
                        break;
                    case ';':
                        readingState = 2;
                        cardRead(string.substring(first));
                        break;
                    case '+':
                        readingState = 3;
                        cardRead(string.substring(first));
                        break;
                    }
            }
            break;
        case 1:
        case 2:
        case 3:
            int last = string.indexOf('?');
            if (last >= 0) {
                cardString += string.substring(0, last + 1);
                track[readingState] += string.substring(0, last + 1);
                readingState = 0;
                if (string.length() - 1 > last)
                    cardRead(string.substring(last + 1));
            } else {
                cardString += string;
                track[readingState] += string;
            }
            break;
        default:
            showStatus("Invalid reading state!");
        }
    }

    public void sendMessage(String msg) {
        String[] temp = new String[1];
        temp[0] = msg;
        mainwin.call("sendMessage", temp);
    }

    public void setupError() {
        try {
            getAppletContext().showDocument(new URL("file://129.79.6.43/uis/groups/hrmssis/hrms/time/technicalteam/deployment/cardreader/card_reader_setup.htm"));
        } catch (MalformedURLException e) {
            sendMessage(e.getMessage());
        }
    }

    /**
     * Called when cardString and track[] contain complete card
     * strings
     */
    public void cardStringAcquired() {
        for (int i = 0; i < 4; i++)
            if (invalidTrackData(track[i])) {
                invalidCardSwipe();
                return;
            }
        if (validCardString(cardString)) {
            Object[] temp = new Object[3];
            for (int i = 0; i < 3; i++)
                temp[i] = track[i + 1];
            mainwin.call("processCardData", temp);
        } else {
            cardString = "";
        }
    }

    /**
     * Check the input for invalid track data;
     * For magtek readers, this is a track whose
     * data consists of a single character "E" (e.g. "%E?")
     */
    public boolean invalidTrackData(String data) {
        return ((data.length() == 3) && (data.charAt(1) == 'E'));
    }

    /**
     * Prompt the user to reswipe
     */
    public void invalidCardSwipe() {
        for (int i = 0; i < 4; i++)
            track[i] = "";
        mainwin.call("invalidSwipe", new Object[1]);
    }

    /**
     * Returns position of first start character,
     * or -1 if none.
     */
    public int scanForStart(String string) {
        if (string == "")
            return -1;
        int start1 = string.indexOf('%');
        int start2 = string.indexOf(';');
        int start3 = string.indexOf('+');
        int temp = start1;
        if ((temp < 0) || ((start2 < temp) && (start2 >= 0)))
            temp = start2;
        if ((temp < 0) || ((start3 < temp) && (start3 >= 0)))
            temp = start3;
        return temp;
    }

    public boolean validCardString(String string) {
        return ((string.indexOf('%') >= 0) || (string.indexOf(';') >= 0));
    }
}