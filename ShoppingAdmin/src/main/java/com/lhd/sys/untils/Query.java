package com.lhd.sys.untils;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0 2017-03-14
 */
public class Query<T> extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int currPage = 1;
    /**
     * 开始位置
     */
    private int offset;
    /**
     * 每页条数
     */
    private int limit = 10;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        if(params.get("page") != null){
            currPage = Integer.parseInt((String)params.get("page"));
        }
        if(params.get("limit") != null){
            limit = Integer.parseInt((String)params.get("limit"));
        }

        offset = (currPage - 1) * limit;
        this.put("offset", offset);
        this.put("page", currPage);
        this.put("limit", limit);

        //mybatis-plus分页
        this.page = new Page<>(currPage, limit);

    }

    public Page<T> getPage() {
        return page;
    }

    public Page<T> getPage(String orderByField, boolean isAsc) {
        return new Page<>(currPage, limit, orderByField, isAsc);
    }

    public int getCurrPage() {
        return currPage;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}


