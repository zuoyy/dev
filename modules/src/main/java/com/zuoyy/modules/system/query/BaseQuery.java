package com.zuoyy.modules.system.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class BaseQuery {

    private Integer startIndex;

    private Integer pageIndex;

    private int pageSize = 10;

    private int draw = 1;

    private String column;

    private String sort;

    private Integer page;

    private Integer size;

    private String keyword;

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageIndex() {
        if (pageIndex != null) {
            return pageIndex;
        } else if (page != null) {
            return page - 1;
        }

        if (startIndex == null) {
            pageIndex = 0;
        } else {
            pageIndex = this.startIndex / this.pageSize;
        }
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return size == null ? pageSize : size;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public String getColumn() {
        if (!StringUtils.isEmpty(column)) {
            return column;
        } else {
            return getDefaultColumn();
        }
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Sort.Direction getSort() {
        return sort == null ? Sort.Direction.DESC : getDirection(sort);
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDefaultColumn() {
        return "createDate";
    }

    public Pageable getPageable() {
        Sort sort = new Sort(getSort(), getColumn());
        Pageable pageable = PageRequest.of(getPageIndex(), getPageSize(), sort);
        return pageable;
    }

    public Pageable getPageableForColumn(String column) {
        Sort sort = new Sort(Sort.Direction.DESC, column);
        Pageable pageable = PageRequest.of(getPageIndex(), getPageSize(), sort);
        return pageable;
    }

    public static Sort.Direction getDirection(String sort) {
        if (!StringUtils.isEmpty(sort)) {
            if (sort.equals("asc")) {
                return Sort.Direction.ASC;
            } else {
                return Sort.Direction.DESC;
            }
        }
        return null;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
