package hello.itemservice.domain.item;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA",10000,10);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findByItem = itemRepository.findById(item.getId());
        assertThat(findByItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 20);
        Item item2 = new Item("itemB", 50000, 10);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> itemList = itemRepository.findAll();

        //then
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(item1, item2);
        System.out.println("itemList = " + itemList);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("itemA", 10000, 20);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParams = new Item("itemB",1000,20);
        itemRepository.update(itemId, updateParams);
        //then
        Item findItem = itemRepository.findById(itemId);
        System.out.println("findItem = " + findItem);
        assertThat(findItem.getItemName()).isEqualTo(updateParams.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParams.getPrice());
        assertThat(findItem.getQuantity() ).isEqualTo(updateParams.getQuantity());
    }

}