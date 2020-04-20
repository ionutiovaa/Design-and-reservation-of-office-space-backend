package be.entity.types;

import java.util.HashMap;
import java.util.Map;

public enum UserType {
    ADMINISTRATOR("ADMINISTRATOR"),
    MANAGER("MANAGER"),
    ANGAJAT("ANGAJAT");

    private String actualString;

    UserType(String actualString) {
        this.actualString = actualString;
    }

    public String getActualString() {
        return actualString;
    }

    //****** Reverse Lookup Implementation************//

    //Lookup table
    private static final Map<String, UserType> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static {
        for (UserType userType : UserType.values()) {
            lookup.put(userType.getActualString(), userType);
        }
    }

    //This method can be used for reverse lookup purpose
    public static UserType get(String actualString) {
        return lookup.get(actualString);
    }
}
