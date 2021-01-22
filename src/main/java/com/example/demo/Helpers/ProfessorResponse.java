package com.example.demo.Helpers;
import com.example.demo.Model.Professor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

public class ProfessorResponse<T> implements Serializable {
    private T data;
    private String mesazhi;
    private int statusi;
    private String errori;

    public ProfessorResponse(ProfessorResponseBuilder profResponseBuilder){
        this.data=(T)profResponseBuilder.data;
        this.mesazhi=profResponseBuilder.mesazhi;
        this.statusi=profResponseBuilder.statusi;
        this.errori=profResponseBuilder.errori;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMesazhi() {
        return mesazhi;
    }

    public void setMesazhi(String mesazhi) {
        this.mesazhi = mesazhi;
    }

    public int getStatusi() {
        return statusi;
    }

    public void setStatusi(int statusi) {
        this.statusi = statusi;
    }

    public String getErrori() {
        return errori;
    }

    public void setErrori(String errori) {
        this.errori = errori;
    }

    public static class ProfessorResponseBuilder<T>{
        private T data;
        private String mesazhi;
        private int statusi;
        private String errori;
        public ProfessorResponseBuilder(int statusi){
            this.statusi = statusi;
        }
        public ProfessorResponseBuilder setData(T data){
            this.data = data;
            return this;
        }
        public ProfessorResponseBuilder setMesazhin(String mesazhi){
            this.mesazhi = mesazhi;
            return this;
        }
        public ProfessorResponseBuilder setErrorin(String errori){
            this.errori = errori;
            return this;
        }
        public ProfessorResponse build(){
            return new ProfessorResponse(this);
        }

    }


}