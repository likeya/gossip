package me.yufan.gossip.rest.integration;

import org.junit.BeforeClass;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Rest integration test class for gossip
 * Would bootstrap a gossip system on demands
 */
public abstract class ResourceTestHelper {

    private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(JacksonConverterFactory.create()).build();

    protected <T> T buildConnector(Class<T> tClass) {
        return retrofit.create(tClass);
    }

    @BeforeClass
    public static void bootstrapGossip() {
    }
}
