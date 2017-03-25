package me.yufan.gossip.rest.support;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;

@Data
@Accessors(chain = true)
public abstract class Pagination {
    private static final long serialVersionUID = -2494442769959590624L;

    // The number to show in each page
    @Min(value = 1, message = "Page size should be bigger than 1")
    private Integer pageSize;

    // The query page
    @Min(value = 1, message = "Page num should be positive value")
    private Integer currentPage;

    // The total count multiply page size, only act as a response
    @Null(message = "Argument [totalPage] is not valid")
    private Integer totalPage;
}
