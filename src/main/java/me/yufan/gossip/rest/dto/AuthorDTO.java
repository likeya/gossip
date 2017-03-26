package me.yufan.gossip.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.yufan.gossip.rest.support.Pagination;

/**
 * Comment author is auto create when he first comments on gossip
 * {@code AuthorDTO} is only for the information present and admin api
 * <p>
 * <strong>Shouldn't be used by public comment api</strong>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AuthorDTO extends Pagination {
    private static final long serialVersionUID = -2494442769959590623L;

    private Long id;

    private String name;

    private String email;

    private String homepage;

    private Integer authorType;
}
