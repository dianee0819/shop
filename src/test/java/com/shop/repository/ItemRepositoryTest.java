package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    //@Test
    //@DisplayName("상품저장테스트")
    public void createItemTest(){
        for(int i=1;i<=10;i++) {
            Item item = new Item();
            item.setItemNm("테스트상품"+i);
            item.setPrice(10000+i);
            item.setStockNumber(10+i);
            item.setItemDetail("테스트 상세 설명"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());

            Item savedItem = itemRepository.save(item);
        }
        //System.out.println(savedItem.toString());
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("테스트상품1");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품명 또는 상품상세설명으로 조회 테스트")
    public void findByItemNmOrItemDetailTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트상품1","테스트 상세 설명5");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품가격으로 조회 테스트")
    public void findByPriceLessThan(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품조회 테스트")
    public void findByItemDetailTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetail("테스트");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }




}