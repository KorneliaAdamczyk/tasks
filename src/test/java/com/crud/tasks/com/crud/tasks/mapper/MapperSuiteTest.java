package com.crud.tasks.com.crud.tasks.mapper;


import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperSuiteTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void shouldMapToCard(){

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Learning", "Learning java on Wednesday", "abc", "1234");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("Learning", trelloCard.getName());
        Assert.assertEquals("Learning java on Wednesday",trelloCard.getDescription());
        Assert.assertEquals("abc",trelloCard.getPos());
        Assert.assertEquals("1234",trelloCard.getListId());
    }

    @Test
    public void shouldMapToCardDto(){

        //Given
        TrelloCard trelloCard = new TrelloCard("Learning", "Learning java on Wednesday", "abc", "1234");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("Learning", trelloCardDto.getName());
        Assert.assertEquals("Learning java on Wednesday",trelloCardDto.getDescription());
        Assert.assertEquals("abc",trelloCardDto.getPos());
        Assert.assertEquals("1234",trelloCardDto.getListId());
    }

    @Test
    public void shouldMapToList(){

        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        TrelloListDto trelloListDto3 = new TrelloListDto("124", "July",false);
        TrelloListDto trelloListDto2 = new TrelloListDto("123", "June",false);
        TrelloListDto trelloListDto1 = new TrelloListDto("122", "May",true);

        trelloListDto.add(trelloListDto1);
        trelloListDto.add(trelloListDto2);
        trelloListDto.add(trelloListDto3);

        //When
        List<TrelloList> testTrelloLists = trelloMapper.mapToList(trelloListDto);

        //Then
        Assert.assertEquals(3, testTrelloLists.size());
        Assert.assertEquals("July", testTrelloLists.get(2).getName());

    }

    @Test
    public void shouldMapToListDto() {

        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        TrelloList trelloList3 = new TrelloList("124", "July", false);
        TrelloList trelloList2 = new TrelloList("123", "June", false);
        TrelloList trelloList1 = new TrelloList("122", "May", true);

        trelloList.add(trelloList1);
        trelloList.add(trelloList2);
        trelloList.add(trelloList3);

        //When
        List<TrelloListDto> testTrelloListsDto = trelloMapper.mapToListDto(trelloList);

        //Then
        Assert.assertEquals(3, testTrelloListsDto.size());
        Assert.assertEquals("July", testTrelloListsDto.get(2).getName());

    }

    @Test
    public void shouldMapToBoards(){

        //Given
        List<TrelloListDto> inProgress = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("124", "July", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("123", "June", false);
        inProgress.add(trelloListDto1);
        inProgress.add(trelloListDto2);
        List<TrelloListDto> done = new ArrayList<>();
        TrelloListDto trelloListDto3 = new TrelloListDto("122", "May", true);
        TrelloListDto trelloListDto4 = new TrelloListDto("121", "April", true);
        done.add(trelloListDto3);
        done.add(trelloListDto4);
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("12","KodillaInProgress",inProgress);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("11", "KodillaDone", done);
        trelloBoardDto.add(trelloBoardDto1);
        trelloBoardDto.add(trelloBoardDto2);

        //When
        List<TrelloBoard> testTrelloBoards = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        Assert.assertEquals(2, testTrelloBoards.size());
        Assert.assertEquals("KodillaInProgress", testTrelloBoards.get(0).getName());
        Assert.assertEquals("April",testTrelloBoards.get(1).getLists().get(1).getName());
    }

    @Test
    public void shouldMapToBoardsDto(){

        //Given
        List<TrelloList> inProgress = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("124", "July", false);
        TrelloList trelloList2 = new TrelloList("123", "June", false);
        inProgress.add(trelloList1);
        inProgress.add(trelloList2);
        List<TrelloList> done = new ArrayList<>();
        TrelloList trelloList3 = new TrelloList("122", "May", true);
        TrelloList trelloList4 = new TrelloList("121", "April", true);
        done.add(trelloList3);
        done.add(trelloList4);
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("12","KodillaInProgress",inProgress);
        TrelloBoard trelloBoard2 = new TrelloBoard("11", "KodillaDone", done);
        trelloBoard.add(trelloBoard1);
        trelloBoard.add(trelloBoard2);

        //When
        List<TrelloBoardDto> testTrelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoard);

        //Then
        Assert.assertEquals(2, testTrelloBoardsDto.size());
        Assert.assertEquals("KodillaInProgress", testTrelloBoardsDto.get(0).getName());
        Assert.assertEquals("April",testTrelloBoardsDto.get(1).getLists().get(1).getName());
    }
}
