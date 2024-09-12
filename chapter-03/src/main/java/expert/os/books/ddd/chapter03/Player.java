package expert.os.books.ddd.chapter03;

public class Player {

    private final String name;

    private final String position;

    private final int age;

    public Player(String name, String position, int age) {
        this.name = name;
        this.position = position;
        this.age = age;
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
