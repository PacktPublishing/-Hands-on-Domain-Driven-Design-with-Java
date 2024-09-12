package expert.os.books.ddd.chapter03;

public class Player {

    private String name;

    private String position;

    private int age;

    public Player(String name, String position, int age) {
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }
}
