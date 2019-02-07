package util;

public enum Priority {
    HIGH(2),MEDIUM(3),LOW(4);

    private int value;
    private Priority(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
