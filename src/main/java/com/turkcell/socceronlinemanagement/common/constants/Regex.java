package com.turkcell.socceronlinemanagement.common.constants;

public class Regex {
    public final static String Email = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; //User
    public final static String Age = "^(1[8-9]|[2-3][0-9]|4[0-8])$"; //player bu olmaz mfls rndm mtd ii gnlrs
    public final static String Name = "^[abcçdefgğhıijklmnoöprsştuüvwqyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVWQYZ ]{2,50}"; // player name-surname
    public static final String Height = "^(1[.](([6-9]\\d)|(5[5-9]))|[2-9]\\d{2}(\\.0)?)$"; // player
    public static final String Weight = "^(?:[4-9]\\d|1\\d{2}|200)$"; // player


}
