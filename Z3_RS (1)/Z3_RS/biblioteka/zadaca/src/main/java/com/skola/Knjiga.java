package com.skola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Knjiga
{
    private int id;
    private String naziv;
    private String autor;
    private int godinaIzdanja;

    private BookStatus status;
    private LocalDate datumIzdavanja;
    @Override
    public String toString()
    {
        String datum = (datumIzdavanja == null) ? "N/A" : datumIzdavanja.toString();//Jer na pocetku nisam ovo unosio bio tj datumIzdavanja
        return "Knjiga: {" +
                "Naziv='" + naziv + '\'' +
                ", Autor='" + autor + '\'' +
                ", Godina izdanja=" + godinaIzdanja +
                ", Datum izdavanja=" + datum +
                ", ID=" + id +
                ", Status=" + status +
                '}';
    }

    public Knjiga(int id, String naziv, String autor, int godinaIzdanja, BookStatus status, LocalDate datumIzdavanja)
    {
        this.id = id;
        this.naziv = naziv;
        this.autor = autor;
        this.godinaIzdanja = godinaIzdanja;
        this.status = status;
        this.datumIzdavanja = datumIzdavanja;
    }

    public Knjiga()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNaziv()
    {
        return naziv;
    }

    public void setNaziv(String naziv)
    {
        this.naziv = naziv;
    }

    public String getAutor()
    {
        return autor;
    }

    public void setAutor(String autor)
    {
        this.autor = autor;
    }

    public int getGodinaIzdanja()
    {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja)
    {
        this.godinaIzdanja = godinaIzdanja;
    }

    public BookStatus getStatus()
    {
        return status;
    }

    public void setStatus(BookStatus status)
    {
        this.status = status;
    }

    public LocalDate getDatumIzdavanja()
    {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDate datumIzdavanja)
    {
        this.datumIzdavanja = datumIzdavanja;
    }
}