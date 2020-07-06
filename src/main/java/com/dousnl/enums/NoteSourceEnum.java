package com.dousnl.enums;

public enum NoteSourceEnum {
    BOOK(1, "书籍解读"),
    SUPERMARKET(2, "知识超市"),
    DISCOVER(3, "发现板块"),
    FEIFANBOOK(4, "非凡书籍");

    private Integer code;
    private String name;

    private NoteSourceEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static NoteSourceEnum getByCode(Integer code) {
        NoteSourceEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            NoteSourceEnum iterable_element = var1[var3];
            if (iterable_element.getCode().equals(code)) {
                return iterable_element;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(BOOK.toString());
    }
}
