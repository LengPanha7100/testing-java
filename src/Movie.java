public class Movie {
    private int id;
    private String name;
    private String type;
    private int duration;
    private int hall;
    private int seat;


    public Movie(int id, String name, String type, int duration, int hall , int seat) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.hall = hall;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", duration=" + duration +
                ", hall=" + hall +
                ", seat=" + seat +
                '}';
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getHall() {
        return hall;
    }
    public int getSeat() {
        return seat;
    }



}


