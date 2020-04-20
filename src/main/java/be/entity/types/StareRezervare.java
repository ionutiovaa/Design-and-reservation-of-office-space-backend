package be.entity.types;

import java.util.HashMap;
import java.util.Map;

public enum StareRezervare {
    INITIATA("INITIATA"),
    IN_CURS("IN_CURS"),
    APROBATA("APROBATA"),
    REFUZATA("REFUZATA"),
    RENUNTARE("RENUNTARE"),
    CONFIRMATA("CONFIRMATA");

    private String actualString;

    StareRezervare(String actualString) {
        this.actualString = actualString;
    }

    public String getActualString() {
        return actualString;
    }

    //****** Reverse Lookup Implementation************//

    //Lookup table
    private static final Map<String, StareRezervare> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static {
        for (StareRezervare stareRezervare : StareRezervare.values()) {
            lookup.put(stareRezervare.getActualString(), stareRezervare);
        }
    }

    //This method can be used for reverse lookup purpose
    public static StareRezervare get(String actualString) {
        return lookup.get(actualString);
    }
}
