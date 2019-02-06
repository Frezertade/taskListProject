package util;

public enum Priority {
    URGENT(1),HIGH(2),MIDUME(3),LOW(4);

    private int value;
    private Priority(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
