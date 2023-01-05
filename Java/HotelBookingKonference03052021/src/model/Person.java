package model;

public abstract class Person {
    private String navn;
    private String telefonNr;
    private String email;

    Person(String navn,
           String telefonNr,
           String email) {
        this.navn = navn;
        this.telefonNr = telefonNr;
        this.email = email;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(String telefonNr) {
        this.telefonNr = telefonNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

