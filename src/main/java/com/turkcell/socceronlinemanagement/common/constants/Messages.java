package com.turkcell.socceronlinemanagement.common.constants;

public class Messages {
    public static class Player {
        public static final String PLAYER_NOT_EXISTS = "PLAYER_NOT_EXISTS";
        public static final String EXISTS = "PLAYER_ALREADY_EXISTS";
    }

    public static class Team {
        public static final String NOT_EXISTS = "TEAM_NOT_EXISTS";
        public static final String EXISTS = "TEAM_ALREADY_EXISTS";

    }

    public static class Transfer {
        public static final String NOT_EXISTS = "TRANSFER_NOT_EXISTS"; //TRANSFER_BULUNAMADI mevcut değil
        public static final String EXISTS = "TRANSFER_ALREADY_EXISTS"; //TRANSFER_ZATEN_VAR
        public static final String PLAYER_EXISTS = "PLAYER_IS_CURRENTLY_UNDER_TRANSFER_LIST"; //OYUNCU_ZATEN_TRANSFER_LISTEDE
        public static final String PLAYER_NOT_EXISTS = "PLAYER_NOT_REGISTERED_FOR_TRANSFER_LIST";//OYUNCU_TRANSFER_LISTEDE_DEĞİL
    }


    public static class User {
        public static final String NOT_EXISTS = "USER_NOT_EXISTS";
        public static final String EXISTS = "USER_ALREADY_EXISTS";
        public static final String EMAIL_ALREADY_EXISTS = "EMAIL_ALREADY_EXISTS";
        public static final String EmailNotValid = "email number must match the pattern";

    }

    public static class Payment {
        public static final String NOT_FOUND = "PAYMENT_NOT_FOUND"; //ÖDEME_BULUNAMADI
        public static final String NOT_ENOUGHT_MONEY = "NOT_ENOUGH_MONEY"; //YETERSİZ PARA
        public static final String NOT_A_VALID_PAYMENT = "NOT_A_VALID_PAYMENT"; //Geçerli Bir Ödeme Değil
        public static final String FAILED = "PAYMENT_FAILED"; //ÖDEME BAŞARISIZ
    }
}