package me.yufan.gossip;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

public class SplitterTest {

    @Test
    public void split() throws Exception {
        String test = "aaa${bbb${ccc";
        final List<String> strings = Splitter.on("${").splitToList(test);
        System.out.print(strings);
    }
}
