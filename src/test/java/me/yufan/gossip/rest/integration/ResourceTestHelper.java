package me.yufan.gossip.rest.integration;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Rest integration test for gossip
 */
abstract class ResourceTestHelper {

    protected static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(JacksonConverterFactory.create()).build();
}
