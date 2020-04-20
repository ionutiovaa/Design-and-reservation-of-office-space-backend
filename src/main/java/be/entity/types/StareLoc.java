package be.entity.types;

import java.util.HashMap;
import java.util.Map;

public enum StareLoc {
    LIBER("LIBER"),
    REZERVAT("REZERVAT"),
    OCUPAT("OCUPAT");

    private String actualString;

    StareLoc(String actualString) {
        this.actualString = actualString;
    }

    public String getActualString() {
        return actualString;
    }

    //****** Reverse Lookup Implementation************//

    //Lookup table
    private static final Map<String, StareLoc> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static {
        for (StareLoc stareLoc : StareLoc.values()) {
            lookup.put(stareLoc.getActualString(), stareLoc);
        }
    }

    //This method can be used for reverse lookup purpose
    public static StareLoc get(String actualString) {
        return lookup.get(actualString);
    }
}
