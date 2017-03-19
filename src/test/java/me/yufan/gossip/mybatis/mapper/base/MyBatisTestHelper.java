package me.yufan.gossip.mybatis.mapper.base;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.yufan.gossip.module.GossipConfigModule;
import me.yufan.gossip.module.GossipDataModule;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public abstract class MyBatisTestHelper {

    protected static Injector injector;

    @BeforeClass
    public static void initializeDatasource() throws FileNotFoundException, URISyntaxException {
        final URL url = MyBatisTestHelper.class.getClassLoader().getResource("gossip.properties");
        if (url == null) {
            throw new FileNotFoundException("Your should place the gossip.properties in your test resource directory");
        }
        final String path = Paths.get(url.toURI()).getParent().toString();
        injector = Guice.createInjector(new GossipConfigModule(path), new GossipDataModule());
    }

    protected Long randomId() {
        return null;
    }

    protected String randomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
