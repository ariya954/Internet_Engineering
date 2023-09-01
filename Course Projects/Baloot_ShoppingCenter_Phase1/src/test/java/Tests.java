import org.example.Shopping_WebSite;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class Tests {

    @Test
    public void rate_commodity_Test() {
        Shopping_WebSite site = new Shopping_WebSite();

        site.add_user("user1" , "1234", "user@gmail.com", "1977-09-15", "address1", 1500);
        site.add_user("user2" , "1234", "user@gmail.com", "1977-09-15", "address1", 1600);
        site.add_user("user3" , "1266", "user@gmail.com", "1977-09-15", "address1", 1700);

        site.add_provider(1 , "provider1", "2023-09-15");
        site.add_provider(2 , "provider2", "2023-10-15");
        site.add_provider(3 , "provider3", "2023-11-15");

        List<String> categories = new ArrayList<String>();
        categories.add("Technology");
        categories.add("phone");
        List<String> categories2 = new ArrayList<String>();
        categories2.add("Technology");
        categories2.add("TV");
        List<String> categories3 = new ArrayList<String>();
        categories2.add("Technology");
        categories2.add("Computer");

        site.add_commodity(1 , "Headphone", 1, 35000, categories, 8.8f, 50);
        site.add_commodity(2 , "mobile", 2, 35000, categories2, 8.8f, 50);
        site.add_commodity(3 , "laptop", 3, 35000, categories3, 8.8f, 50);

        site.rate_commodity("user1", 2, 7);
        site.rate_commodity("user2", 2, 6);
        site.rate_commodity("user3", 2, 5);
        assert site.get_Commodity(2).get_rating() == (7 + 6 + 5) / 3 : "rate commodity assertion failed";
    }

    @Test
    public void get_Commodity_by_category_Test() {
        Shopping_WebSite site = new Shopping_WebSite();

        site.add_user("user1" , "1234", "user@gmail.com", "1977-09-15", "address1", 1500);
        site.add_user("user2" , "1234", "user@gmail.com", "1977-09-15", "address1", 1600);
        site.add_user("user3" , "1266", "user@gmail.com", "1977-09-15", "address1", 1700);

        site.add_provider(1 , "provider1", "2023-09-15");
        site.add_provider(2 , "provider2", "2023-10-15");
        site.add_provider(3 , "provider3", "2023-11-15");

        List<String> categories = new ArrayList<String>();
        categories.add("Technology");
        categories.add("phone");
        List<String> categories2 = new ArrayList<String>();
        categories2.add("Technology");
        categories2.add("TV");
        List<String> categories3 = new ArrayList<String>();
        categories2.add("Technology");
        categories2.add("Computer");

        site.add_commodity(1 , "Headphone", 1, 35000, categories, 8.8f, 50);
        site.add_commodity(2 , "mobile", 2, 35000, categories2, 8.8f, 50);
        site.add_commodity(3 , "laptop", 3, 35000, categories3, 8.8f, 50);

        site.get_Commodity_by_category(categories2);
    }

    @Test
    public void add_to_buy_list_Test() {
        Shopping_WebSite site = new Shopping_WebSite();

        site.add_user("user1" , "1234", "user@gmail.com", "1977-09-15", "address1", 1500);
        site.add_user("user2" , "1234", "user@gmail.com", "1977-09-15", "address1", 1600);
        site.add_user("user3" , "1266", "user@gmail.com", "1977-09-15", "address1", 1700);

        site.add_provider(1 , "provider1", "2023-09-15");
        site.add_provider(2 , "provider2", "2023-10-15");
        site.add_provider(3 , "provider3", "2023-11-15");

        List<String> categories = new ArrayList<String>();
        categories.add("Technology");
        categories.add("phone");
        List<String> categories2 = new ArrayList<String>();
        categories2.add("Technology");
        categories2.add("TV");
        List<String> categories3 = new ArrayList<String>();
        categories2.add("Technology");
        categories2.add("Computer");

        site.add_commodity(1 , "Headphone", 1, 35000, categories, 8.8f, 50);
        site.add_commodity(2 , "mobile", 2, 35000, categories2, 8.8f, 50);
        site.add_commodity(3 , "laptop", 3, 35000, categories3, 8.8f, 50);

        site.add_to_buy_list("user2", 2);
        site.add_to_buy_list("user2", 3);
        site.add_to_buy_list("user2", 1);
        assert site.get_User("user2").get_User_Buy_List().get(1).Commodity == 3 : "add to buy list assertion failed";
    }

    @Test
    public void get_Commodity_by_id_Test() {
        Shopping_WebSite site = new Shopping_WebSite();

        site.add_user("user1", "1234", "user@gmail.com", "1977-09-15", "address1", 1500);
        site.add_user("user2", "1234", "user@gmail.com", "1977-09-15", "address1", 1600);
        site.add_user("user3", "1266", "user@gmail.com", "1977-09-15", "address1", 1700);

        site.add_provider(1, "provider1", "2023-09-15");
        site.add_provider(2, "provider2", "2023-10-15");
        site.add_provider(3, "provider3", "2023-11-15");

        List<String> categories = new ArrayList<String>();
        categories.add("Technology");
        categories.add("phone");
        List<String> categories2 = new ArrayList<String>();
        categories2.add("Technology");
        categories2.add("TV");
        List<String> categories3 = new ArrayList<String>();
        categories2.add("Technology");
        categories2.add("Computer");

        site.add_commodity(1, "Headphone", 1, 35000, categories, 8.8f, 50);
        site.add_commodity(2, "mobile", 2, 35000, categories2, 8.8f, 50);
        site.add_commodity(3, "laptop", 3, 35000, categories3, 8.8f, 50);

        site.get_Commodity_by_id(2);
    }
}
