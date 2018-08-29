package com.amgen.datamarketplace.types;

import java.util.List;

public class DocumentAttributes {

    private List<String> content;
    private Integer numFiles;


    public void setContent(List<String> content){
        this.content = content;
    }

    public void setNumFiles (Integer numFiles){
        this.numFiles = numFiles;
    }


}
