package com.carvalhodelucas.picpay_backend_challenge.wallet;

public enum WalletType {

    COMNUM(1), LOJISTA(2);

    private int value;

    private WalletType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
