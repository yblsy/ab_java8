package ab.stream;

import java.util.Arrays;
import java.util.List;


public class Dish {
    private final String name;
    private final boolean vegetarion;
    private final int calories;
    private final Type type;

    public Dish(String name,boolean vegetarion,int calories,Type type){
        this.name = name;
        this.vegetarion = vegetarion;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarion() {
        return vegetarion;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public static List<Dish> getMenu(){
        return Arrays.asList(
                new Dish("pork",false,100,Type.MEAT),
                new Dish("beef",false,200,Type.MEAT),
                new Dish("chicken",true,300,Type.MEAT),
                new Dish("french fries",false,400,Type.FISH),
                new Dish("rice",false,500,Type.OTHER),
                new Dish("season",true,600,Type.FISH),
                new Dish("pizza",true,700,Type.MEAT),
                new Dish("prawns",true,800,Type.FISH),
                new Dish("salmon",false,900,Type.OTHER),
                new Dish("rice2",true,1000,Type.FISH),
                new Dish("rice3",false,1100,Type.OTHER)
        );
    }
}
