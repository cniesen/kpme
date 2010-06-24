package edu.iu.uis.hr.tk.entity;


public class CardSwipe {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CardSwipe.class);
    private String track1;
    private String track2;
    private String track3;
    private String cardID;

    public CardSwipe(String track1, String track2, String track3) {
        LOG.debug("new CardSwipe(String " + track1 + ", String " + track2 + ", String " + track3 + ")");
        if (track2 == null) {
            throw new IllegalArgumentException("CardSwipe(String track1, String track2, String track3) requires a non-null track2");
        }
        this.track1 = track1;
        this.track2 = track2;
        this.track3 = track3;
    }

    public String getCardID() {
        LOG.debug("getCardID()");
        if (cardID == null) {
            this.parseTrack2();
        }
        return cardID;
    }

    private void parseTrack2() {
        LOG.debug("parseTrack2()");
        int equalPos = track2.indexOf('=');
        if (equalPos > 0) {
          LOG.debug("Bloomington Case");
          cardID = track2.substring(0,equalPos);
          LOG.debug("Return BL Cardid " + this.cardID);
          return;
        }
        else {
          LOG.debug("IUPUI Case");
          LOG.debug("Return IUPUI Cardid " + track2);
          cardID = track2;
        }
    }
}