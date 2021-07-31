package com.dousnl.enums;

public enum Suggestion {

    PASS(1),BLOCK(2),REVIEW(3);

    private int code;

    private Suggestion(int code){
        this.code = code;
    }

    public static void main(String[] args) {
        try{
            System.out.println(Suggestion.valueOf("PASS"));
            System.out.println(Suggestion.PASS.name());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(1111);
        }

    }
}
