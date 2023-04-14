package com.cydeo.pojo;

import java.util.List;

public class Search extends Spartan{

    private int totalElement;
    private List<Spartan> content;

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public List<Spartan> getContent() {
        return content;
    }

    public void setContent(List<Spartan> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Search{" +
                "totalElement=" + totalElement +
                ", content=" + content +
                '}';
    }
}
