package com.mediasoftstage.biblio.entities;

public enum STATES {
    PRETE("PRÊTÉ"),
    RESERVE("RÉSERVÉ"),
    DISPONIBLE("DISPONIBLE"),
    REPARATION("EN RÉPARATION"),
    NON_RETOURNE("NON RETOURNÉ"),
    EXCLU("EXCLU");

    private String state;
    
    public String getState() {
        return state;
    }

    STATES(String state) {
        this.state = state;
    }

}
