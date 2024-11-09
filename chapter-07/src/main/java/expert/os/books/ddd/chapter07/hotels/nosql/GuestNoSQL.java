package expert.os.books.ddd.chapter07.hotels.nosql;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class GuestNoSQL {

    @BsonProperty("documentNumber")
    private String documentNumber;

    @BsonProperty("name")
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
