package org.example.models;

import org.example.Components.DocumentType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice extends Document{
    private int id;
    private int number;
    private Date date;
    private String clientName;
    private String address;
    private DocumentType documentType;

    public Invoice() {
        this.documentType = DocumentType.INVOICE;
    }

    public Invoice(int id, int number, Date date, String clientName, String address) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.clientName = clientName;
        this.address = address;
        this.documentType = DocumentType.INVOICE;
    }

    @Override
    public List<String> info() {
        return List.of(
                String.valueOf(id),
                String.valueOf(number),
                String.valueOf(date),
                String.valueOf(clientName),
                String.valueOf(address)
        );
    }

    @Override
    public void setData(String[] data) {
        setId(Integer.parseInt(data[0].split("=")[1]));
        setNumber(Integer.parseInt(data[1].split("=")[1]));
        parseAndSetDate(data[2]);
        setClientName(data[3].split("=")[1]);
        setAddress(data[4].split("=")[1]);
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
        return "Invoice{" +
                "id=" + id +
                ", number=" + number +
                ", date=" + date +
                ", clientName='" + clientName + '\'' +
                ", address='" + address + '\'' +
                ", documentType=" + documentType +
                '}';
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
