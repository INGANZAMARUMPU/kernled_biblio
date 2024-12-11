package com.mediasoftstage.biblio.entities;

public enum STATES {
    PRETE("PRÊTÉ"),
    RESERVE("RÉSERVÉ"),
    DISPONIBLE("DISPONIBLE"),
    REPARATION("EN RÉPARATION"),
    NON_RETOURNE("NON RETOURNÉ"),
    EXCLU("EXCLU");

    public String value;

    STATES(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

}
