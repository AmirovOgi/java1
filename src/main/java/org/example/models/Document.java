package org.example.models;

import org.example.Components.DocumentType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class Document {
    abstract List<String> info();
    abstract void setData(String[] data);

    // getter
    abstract public DocumentType getDocumentType();
    abstract public int getId();
    abstract public int getNumber();
    abstract public Date getDate();

    // setter
    abstract public void setId(int id);
    abstract public void setNumber(int number);
    abstract public void setDate(Date date);

    protected void parseAndSetDate(String dateString) {
        try {
            String dateValue = dateString.split("=")[1];
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(dateValue);
            setDate(parsedDate);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + dateString, e);
        }
    }
}
