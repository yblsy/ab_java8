package ab.stream;

import lombok.Getter;

public enum Type {
    MEAT("MEAT","肉"),
    FISH("FISH","鱼"),
    OTHER("OTHER","其他");

    @Getter
    private String key;
    @Getter
    private String value;

    Type(String key,String value){
        this.key = key;
        this.value = value;
    }
}
