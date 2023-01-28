package RestfulBooker.vanillaWay;

import com.fasterxml.jackson.core.type.TypeReference;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import staticImport.Address;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConvertJSONToPojo {

    /*
     * Need to add
     * <dependency>
     * <groupId>com.fasterxml.jackson.core</groupId>
     * <artifactId>jackson-databind</artifactId>
     * <version>2.14.1</version>
     * </dependency>
     */
    @Test
    public void test() throws IOException {
        // first we need to Create a POJO Class ,and it should have getter and setter
        // Object Mapper Class From jackson
        // To Read JSON -> POJO(plain Old Java Object) and POJO-> JSON we will use the
        ObjectMapper objectMapper = new ObjectMapper(); // create an object of ObjectMapper Class
        // raed Value () method we used , which gets new File(address) and POJO.class and returns Object of POJO class
        Address address = objectMapper.readValue(new File(System.getProperty("user.dir") + "/src/main/resources/testData/pojo.json"), Address.class);
        System.out.println(address.getStreetName());
        address.setStreetName("10th Cross , MG Road");
        System.out.println(address.getStreetName());
        System.out.println(address.getHouseNo());
        address.setHouseNo(302);
        System.out.println(address.getHouseNo());

        // Write Object
        String s = objectMapper.writeValueAsString(address);
        System.out.println(s);

        s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address);
        System.out.println(s);
    }

    //update JSON without POJO
    @Test
    public void withoutPojo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> address = objectMapper.readValue(new File(System.getProperty("user.dir") + "/src/main/resources/testData/pojo.json"),
                new TypeReference<Map<String, Object>>() {
                });

        System.out.println(address.get("streetName"));
        address.put("streetName","10th Cross , BTM 2nd Stage");

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address));

    }

}
