package com.example.demo.Helpers;

import java.io.Serializable;

public class getModelHelper implements Serializable {
    private int modelId;



    public getModelHelper(int modelId) {
        this.modelId = modelId;
    }

    public getModelHelper() {
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int docId) {
        this.modelId = docId;
    }
}
