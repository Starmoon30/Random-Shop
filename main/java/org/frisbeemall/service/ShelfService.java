package org.frisbeemall.service;

import org.frisbeemall.dao.ShelfDao;
import org.frisbeemall.domain.Shelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShelfService {
    @Autowired
    private ShelfDao shelfDao;

    public List<Shelf> getAllShelves() {
        return shelfDao.selectAll();
    }

    public Shelf getShelfById(int shid) {
        return shelfDao.selectById(shid);
    }

    @Transactional
    public int addShelf(Shelf shelf) {
        return shelfDao.insert(shelf);
    }

    @Transactional
    public int updateShelf(Shelf shelf) {
        return shelfDao.updateById(shelf);
    }
}