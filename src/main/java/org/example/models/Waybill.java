package org.example.models;

import org.example.Components.DocumentType;

import java.util.Date;
import java.util.List;

public class Waybill extends Document{
    private int id;
    private int number;
    private Date date;
    private DocumentType documentType;
    private String waybillName;

    public Waybill() {
        this.documentType = DocumentType.WAYBILL;
    }

    public Waybill(int id, int number, Date date, String waybillName) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.waybillName = waybillName;
        this.documentType = DocumentType.WAYBILL;
    }

    @Override
    public List<String> info() {
        return List.of(
                String.valueOf(id),
                String.valueOf(number),
                String.valueOf(date),
                String.valueOf(waybillName)
        );
    }

    @Override
    public void setData(String[] data) {
        setId(Integer.parseInt(data[0].split("=")[1]));
        setNumber(Integer.parseInt(data[1].split("=")[1]));
        parseAndSetDate(data[2]);
        setWaybillName(data[3].split("=")[1]);
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
        return "Waybill{" +
                "id=" + id +
                ", number=" + number +
                ", date=" + date +
                ", documentType=" + documentType +
                ", waybillName='" + waybillName + '\'' +
                '}';
    }

    public String getWaybillName() {
        return waybillName;
    }

    public void setWaybillName(String waybillName) {
        this.waybillName = waybillName;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}
