package com.example.osaka_trip;

import java.io.Serializable;

public class Conversation implements Serializable {

    int idx;
    String Classification; // 분류
    String kor; // 한국어
    String jap; // 일본어
    String pronun; // 독음
    String expect; // 예상답변

    public Conversation(int idx, String classification, String kor, String jap, String pronun, String expect) {
        this.idx = idx;
        Classification = classification;
        this.kor = kor;
        this.jap = jap;
        this.pronun = pronun;
        this.expect = expect;
    }

    public Conversation(int idx, String classification, String kor, String jap, String pronun) {
        this.idx = idx;
        Classification = classification;
        this.kor = kor;
        this.jap = jap;
        this.pronun = pronun;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getClassification() {
        return Classification;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }

    public String getKor() {
        return kor;
    }

    public void setKor(String kor) {
        this.kor = kor;
    }

    public String getJap() {
        return jap;
    }

    public void setJap(String jap) {
        this.jap = jap;
    }

    public String getPronun() {
        return pronun;
    }

    public void setPronun(String pronun) {
        this.pronun = pronun;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }
}
