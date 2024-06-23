package org.example.models;

import org.example.Components.DocumentType;

import java.util.Date;
import java.util.List;

public class Payment extends Document{
    private int id;
    private int number;
    private Date date;
    private String paymentName;
    private DocumentType documentType;

    public Payment() {}

    public Payment(int id, int number, Date date, String paymentName) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.paymentName = paymentName;
        this.documentType = DocumentType.PAYMENT;
    }

    @Override
    public List<String> info() {
        return List.of(
                String.valueOf(id),
                String.valueOf(number),
                String.valueOf(date),
                String.valueOf(paymentName)
        );
    }

    @Override
    public void setData(String[] data) {
        setId(Integer.parseInt(data[0].split("=")[1]));
        setNumber(Integer.parseInt(data[1].split("=")[1]));
        parseAndSetDate(data[2]);
        setPaymentName(data[3].split("=")[1]);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public DocumentType getDocumentType() {
        return documentType;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", number=" + number +
                ", date=" + date +
                ", paymentName='" + paymentName + '\'' +
                ", documentType=" + documentType +
                '}';
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }
}
