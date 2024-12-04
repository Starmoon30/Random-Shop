package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.domain.Category;
import com.example.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cat")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/get_cid_by_parent")
    public int[] get_cid_by_parent(@RequestBody Map<String, Object> catMap) {
        Object idObject = catMap.get("id");
        if (idObject == null) {
            throw new IllegalArgumentException("The 'id' field is required.");
        }
        // 确保 idObject 是数组类型
        if (!(idObject instanceof List)) {
            throw new IllegalArgumentException("The 'id' field must be an array of integers.");
        }
        List<Integer> idList = (List<Integer>) idObject;
        int[] id = idList.stream().mapToInt(i -> i).toArray();
        ArrayList<Integer> rs = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Category::getCparentid, id[i]);
            List<Category> categories = categoryService.list(queryWrapper);
            for (Category category : categories) {
                rs.add(category.getCid());
            }
        }
        int[] resultArray = new int[rs.size()];
        for (int i = 0; i < rs.size(); i++) {
            resultArray[i] = rs.get(i);
        }
        return resultArray;
    }
    @RequestMapping("/get_all_child")
    public int[] get_all_child(@RequestBody Map<String, Object> catMap) {
        Object idObject = catMap.get("id");
        if (idObject == null) {
            throw new IllegalArgumentException("The 'id' field is required.");
        }
        // 确保 idObject 是数组类型
        if (!(idObject instanceof List)) {
            throw new IllegalArgumentException("The 'id' field must be an array of integers.");
        }
        List<Integer> idList = (List<Integer>) idObject;
        int[] id = idList.stream().mapToInt(i -> i).toArray();
        return categoryService.get_all_child(id);
    }
    @RequestMapping("/list")
    public List<Category> list() {return categoryService.list();}
    @RequestMapping("/creat")
    public boolean creat(@RequestBody Category category) {
        return categoryService.save(category);
    }
    @RequestMapping("/delete/{id}")
    public boolean delete(@PathVariable int id) {
        return categoryService.removeById(id);
    }
}
