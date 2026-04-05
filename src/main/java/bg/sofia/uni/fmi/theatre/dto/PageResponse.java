package bg.sofia.uni.fmi.theatre.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponse<T> {
    private final List<T> content;
    private final int page;
    private final int size;
    private final long totalElements;

    public PageResponse(List<T> items, int page, int size, long totalElements) {
        this.content = items;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
    }
}
