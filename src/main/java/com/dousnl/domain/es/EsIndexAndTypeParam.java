package com.dousnl.domain.es;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/7/3 11:23
 */
@Data
public class EsIndexAndTypeParam {

    @NotNull
    private String index;

    private String type;

    private String id;
}
