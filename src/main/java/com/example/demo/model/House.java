package com.example.demo.model;

public class House<T> {
    String message;
    T t;
    String status;

    public House(String message, T t, String status) {
        this.message = message;
        this.t = t;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public House() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}