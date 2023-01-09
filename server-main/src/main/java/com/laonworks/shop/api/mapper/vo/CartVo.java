package com.laonworks.shop.api.mapper.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartVo {

    public String userid; // 유저 아이디

    public int productNum; // 상품 번호

    public String price; // 상품 가격

    public String productName; // 상품 이름

    public int cnt; // 상품 갯수

}
