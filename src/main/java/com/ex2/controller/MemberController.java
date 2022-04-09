package com.ex2.controller;

import com.ex2.dto.MemberFormDto;
import com.ex2.entity.Member;
import com.ex2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public ModelAndView memberForm() {
        ModelAndView mav = new ModelAndView("member/memberForm");
        mav.addObject("memberFormDto", new MemberFormDto());
        return mav;
    }

    @PostMapping(value = "/new")
    public ModelAndView memberForm(@Valid MemberFormDto memberFormDto,
                                   BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("redirect:/");

        if (bindingResult.hasErrors()) {
            mav.setViewName("member/memberForm");
            return mav;
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            mav.addObject("errorMessage", e.getMessage());
            mav.setViewName("member/memberForm");
            return mav;
        }

        return mav;
    }

}
