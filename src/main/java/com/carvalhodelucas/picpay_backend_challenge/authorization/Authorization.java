package com.carvalhodelucas.picpay_backend_challenge.authorization;

public record Authorization(String message) {

    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }

}
