package org.frisbeemall.controller;

import org.frisbeemall.domain.Shelf;
import org.frisbeemall.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shelves")
public class ShelfController {
    @Autowired
    private ShelfService shelfService;

    @GetMapping
    public List<Shelf> getAllShelves() {
        return shelfService.getAllShelves();
    }

    @PostMapping
    public int addShelf(@RequestBody Shelf shelf) {
        return shelfService.addShelf(shelf);
    }

    @PutMapping("/{shid}/freeze")
    public int freezeShelf(@PathVariable int shid, @RequestBody Shelf shelf) {
        shelf.setShid(shid);
        shelf.setShstate(1);
        return shelfService.updateShelf(shelf);
    }

    @PutMapping("/{shid}/unfreeze")
    public int unfreezeShelf(@PathVariable int shid, @RequestBody Shelf shelf) {
        shelf.setShid(shid);
        shelf.setShstate(0);
        return shelfService.updateShelf(shelf);
    }
}