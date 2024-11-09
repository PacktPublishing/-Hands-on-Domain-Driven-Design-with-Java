package expert.os.books.ddd.chapter07.hotels.nosql;

class GuestNoSQL {

    private String documentNumber;

    private String name;

    GuestNoSQL(String documentNumber, String name) {
        this.documentNumber = documentNumber;
        this.name = name;
    }

    public GuestNoSQL() {
    }


    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setName(String name) {
        this.name = name;
    }
}
