package org.sopt.dto.Request;

public record PagingDto(
        Integer pageNumber,
        Integer size,
        String sort,
        String standard
) {
}
