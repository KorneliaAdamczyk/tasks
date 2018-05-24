package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCard;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();

       /*trelloBoards.stream()
                .filter(boards -> boards.getName()!=null&boards.getId()!=null)
                        .filter(boards1->boards1.getName().contains("Kodilla"))
                        .forEach( boards2-> System.out.println(boards2.getName() + "-" + boards2.getId()));*/

       /* trelloBoards.forEach(trelloBoardDto -> {

            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());

            System.out.println("This board contains lists: ");

            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));

        });*/
    }
        @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
                public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
            return trelloClient.createNewCard(trelloCardDto);

        }

         }


