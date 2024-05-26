import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SILab2Test{



    @Test
    void testEveryBranch(){
        Item item1=new Item(null,null,200,0.3f);
        List<Item> list1=new ArrayList<>();
        list1.add(item1);
        Item item2=new Item(null,"31231s4",200,0.3f);
        List<Item> list2=new ArrayList<>();
        list2.add(item2);
        list1.add(item1);
        RuntimeException ex;

        ex=assertThrows(RuntimeException.class , ()-> SILab2.checkCart(null,100));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        ex=assertThrows(RuntimeException.class,()-> SILab2.checkCart(list1,100));
        assertTrue(ex.getMessage().contains("No barcode!"));

        List<Item> finalList = list2;
        ex=assertThrows(RuntimeException.class,()-> SILab2.checkCart(finalList,100));
        System.out.println(ex.getMessage());
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        Item first=new Item("andrej","312314",100,0);
        list2=new ArrayList<>();
        list2.add(first);
        assertTrue(SILab2.checkCart(list2,200));
        first=new Item("andrej","312314",300,0.8f);
        list2=new ArrayList<>();
        list2.add(first);
        assertFalse(SILab2.checkCart(list2,100));
    }


    @Test
    void MultipleCondition(){
        // F && X && X
        Item item=new Item("Gel","0321",200,0.5f);
        assertFalse(item.getPrice()>300);
        // T && F && X
        item=new Item("Gel","12312",350,0);
        assertFalse(item.getDiscount()>0);
        // T && T && F
        item=new Item("Gel"," 1231123",350, 0.5f);
        assertNotEquals('0', item.getBarcode().charAt(0));
        //T && T && T
        item=new Item("Gel","01231123",350, 0.5f);
        assertTrue(item.getPrice() > 300);
        assertTrue(item.getDiscount() > 0);
        assertEquals('0', item.getBarcode().charAt(0));
    }
}