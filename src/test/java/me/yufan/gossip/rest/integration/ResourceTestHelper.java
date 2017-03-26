package me.yufan.gossip.rest.integration;

import com.google.common.collect.Lists;
import me.yufan.gossip.Gossip;
import org.junit.BeforeClass;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.nio.file.Paths;
import java.util.List;

import static me.yufan.gossip.utils.RandomEntityGenerator.randomId;

/**
 * Rest integration test class for gossip
 * Would bootstrap a gossip system on demands
 */
public abstract class ResourceTestHelper {

    private static final Long port = randomId();

    private static Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://127.0.0.1:" + port)
        .addConverterFactory(JacksonConverterFactory.create()).build();

    protected <T> T buildConnector(Class<T> tClass) {
        return retrofit.create(tClass);
    }

    @BeforeClass
    public static void bootstrapGossip() {
        String configPath = Paths.get("src/test/resources").toAbsolutePath().toString();
        List<String> args = Lists.newArrayList("-c", configPath, "-p", port.toString(), "-f", "gossip-test.properties");
        Gossip.main(args.toArray(new String[args.size()]));
    }
}
