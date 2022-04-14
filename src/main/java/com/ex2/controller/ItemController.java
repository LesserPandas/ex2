package com.ex2.controller;

import com.ex2.dto.ItemFormDto;
import com.ex2.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping(value = "admin/item/new")
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

}
