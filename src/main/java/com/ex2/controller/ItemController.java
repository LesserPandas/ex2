package com.ex2.controller;

import com.ex2.dto.ItemFormDto;
import com.ex2.dto.ItemSearchDto;
import com.ex2.entity.Item;
import com.ex2.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/admin/item/new")
    public ModelAndView itemForm(ModelAndView mav) {
        mav.setViewName("item/itemForm");
        mav.addObject("itemFormDto", new ItemFormDto());
        return mav;
    }

    @PostMapping(value = "/admin/item/new")
    public ModelAndView itemNew(@Valid ItemFormDto itemFormDto,
                                BindingResult bindingResult, ModelAndView mav,
                                @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {

        mav.setViewName("redirect:/");

        if (bindingResult.hasErrors()) {
            mav.setViewName("item/itemForm");
        }

        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            mav.addObject("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            mav.setViewName("item/itemForm");
        }

        try {
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            mav.addObject("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            mav.setViewName("item/itemForm");
        }

        return mav;
    }

    @GetMapping(value = "/admin/item/{itemId}")
    public ModelAndView itemDtl(@PathVariable("itemId") Long itemId,
                                ModelAndView mav) {

        mav.setViewName("item/itemForm");
        try {
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            mav.addObject("itemFormDto", itemFormDto);
        } catch (EntityNotFoundException e) {
            mav.addObject("errorMessage", "존재하지 않는 상품 입니다.");
            mav.addObject("itemFormDto", new ItemFormDto());
            return mav;
        }

        return mav;
    }

    @PostMapping(value = "/admin/item/{itemId}")
    public ModelAndView itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                                @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,
                                ModelAndView mav) {

        mav.setViewName("redirect:/");

        if (bindingResult.hasErrors()) {
            mav.setViewName("item/itemForm");
        }

        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            mav.addObject("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            mav.setViewName("item/itemForm");
        }

        try {
            itemService.updateItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            mav.addObject("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            mav.setViewName("item/itemForm");
        }

        return mav;
    }

    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public ModelAndView itemManage(ItemSearchDto itemSearchDto,
                                   @PathVariable("page") Optional<Integer> page, ModelAndView mav) {
        mav.setViewName("item/itemMng");
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);
        mav.addObject("items", items);
        mav.addObject("itemSearchDto", itemSearchDto);
        mav.addObject("maxPage", 5);
        return mav;
    }

    @GetMapping(value = "/item/{itemId}")
    public ModelAndView itemDtl(ModelAndView mav, @PathVariable("itemId") Long itemId){
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        mav.setViewName("item/itemDtl");
        mav.addObject("item", itemFormDto);
        return mav;
    }
}
