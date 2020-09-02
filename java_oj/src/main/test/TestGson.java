import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class TestGson {
    static class Hero{
        public String name;
        public String skill1;
        public String skill3;
        public String skill2;
        public String skill4;
    }
    public static void main(String[] args) {
        //将对象变为json字符串
        Hero hero = new Hero();
        hero.name = "曹操";
        hero.skill1="s";
        hero.skill4="d";
        hero.skill3="w";
        hero.skill2="5";

        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(hero);
        // System.out.println(jsonString);
        //将json的格式的对象转变为对象

        Hero hero1 = gson.fromJson(jsonString,Hero.class);
        System.out.println(hero1.name);
        System.out.println(hero1.skill1);
        System.out.println(hero1.skill2);
        System.out.println(hero1.skill3);
        System.out.println(hero1.skill4);
    }
}
