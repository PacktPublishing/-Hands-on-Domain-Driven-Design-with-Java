package expert.os.books.ddd.chapter07.hotels.nosql;

class RoomNoSQL {

    private Long number;

    private GuestNoSQL guest;

    RoomNoSQL(Long room, GuestNoSQL guestNoSQL) {
        this.number = Long.valueOf(room);
        this.guest = guestNoSQL;
    }

    public RoomNoSQL() {
    }


    public void setGuest(GuestNoSQL guest) {
        this.guest = guest;
    }

    public void cleanRoom() {
        this.guest = null;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public GuestNoSQL getGuest() {
        return guest;
    }
}
