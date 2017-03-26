package me.yufan.gossip.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * helper function for generate random database entity
 */
public abstract class RandomEntityGenerator {

    public static Long randomId() {
        return ThreadLocalRandom.current().nextLong(10000, 20000);
    }

    public static String randomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomUrl() {
        return "http://" + randomString(7).toLowerCase() + ".com";
    }
}
