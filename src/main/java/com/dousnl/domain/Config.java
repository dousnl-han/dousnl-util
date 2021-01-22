package com.dousnl.domain;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Table(name = "t_config")
public class Config {
    @Id
    private Integer id;

    private String seq;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return seq
     */
    public String getSeq() {
        return seq;
    }

    /**
     * @param seq
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }
}
