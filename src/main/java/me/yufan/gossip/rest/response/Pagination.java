package me.yufan.gossip.rest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class Pagination<E extends Serializable> extends BaseApiResponse<List<E>>  {
    private static final long serialVersionUID = -2494442769959590624L;

    // The number to show in each page
    @Min(value = 1, message = "Page size should be bigger than 1")
    private Integer pageSize = 1;

    // The query page
    @Min(value = 1, message = "Page num should be positive value")
    @NotNull(message = "Please specified your query page number")
    private Integer currentPage;

    // The total count multiply page size, only act as a response
    private Integer totalPage;
}
