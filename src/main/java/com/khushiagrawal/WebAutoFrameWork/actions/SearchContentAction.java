package com.khushiagrawal.WebAutoFrameWork.actions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchContentAction {
    private String input;

    public SearchContentAction init(){
        return SearchContentAction.builder()
                .input("Top")
                .build();
    }

    public SearchContentAction stellTop(){
        return SearchContentAction.builder()
                .input("Stell Top")
                .build();
    }

    public SearchContentAction soldOutProduct(){
        return SearchContentAction.builder()
                .input("12 Ti Xelium Skis")
                .build();
    }

    public String selectDress(){
        return "Stell Top";
    }
}
