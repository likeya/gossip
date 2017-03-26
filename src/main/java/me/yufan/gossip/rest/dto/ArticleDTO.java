package me.yufan.gossip.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.yufan.gossip.rest.support.Pagination;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Null;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.NONE;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ArticleDTO extends Pagination {
    private static final long serialVersionUID = 657199462340722189L;

    @Null(message = "Using key as the article's identity")
    private Long id;

    @SafeHtml(whitelistType = NONE, message = "No html tags, only support plain text")
    @NotEmpty(message = "Need article identity")
    private String key;

    @SafeHtml(whitelistType = NONE, message = "No html tags, only support plain text")
    @NotEmpty(message = "Empty article name")
    private String name;

    // TODO support custom config in db for url validate
    @URL(regexp = "^http[s]?://.*", message = "Illegal article link")
    private String url;
}
