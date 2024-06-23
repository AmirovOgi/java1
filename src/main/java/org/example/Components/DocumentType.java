package org.example.Components;

public enum DocumentType {
    ORDER("order"),
    WAYBILL("waybill"),
    PAYMENT("payment"),
    INVOICE("invoice");

    private final String className;

    DocumentType(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
