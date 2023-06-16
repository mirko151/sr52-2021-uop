package models;

public enum Uloga {
    ADMINISTRATOR,
    TURISTICKI_AGENT,
    TURISTA;

    public static Uloga fromString(String ulogaString) {
        switch (ulogaString.toUpperCase()) {
            case "ADMINISTRATOR":
                return ADMINISTRATOR;
            case "TURISTICKI_AGENT":
                return TURISTICKI_AGENT;
            case "TURISTA":
                return TURISTA;
            default:
                throw new IllegalArgumentException("Invalid uloga value: " + ulogaString);
        }
    }
}

