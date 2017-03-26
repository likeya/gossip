package me.yufan.gossip.mybatis.entity.enums;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AuthorTypeTest {

    @Test
    public void null_arguments_for_covert() throws Exception {
        final AuthorType authorType = AuthorType.convert(null);
        assertThat(authorType, nullValue());
    }

    @Test
    public void get_a_not_existed_type() throws Exception {
        final AuthorType convert = AuthorType.convert("113434");
        assertThat(convert, is(AuthorType.GUEST));
    }

    @Test
    public void get_admin_type() throws Exception {
        final AuthorType authorType = AuthorType.convert("1000");
        assertThat(authorType, is(AuthorType.ADMINISTRATOR));
    }
}
