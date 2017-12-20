package com.example.byhisson.fragmentex;

/**
 * Created by byhisson on 2017. 12. 18..
 * 데이터 이용에 알고리즘이 추가된다면 getter/setter 함수가 필요
 * 외부 요청에 단순히 데이터를 넘겨주는 정도이면 함수보다 변수 접근이 더 빠르므로
 * 성능상 함수를 이용하는 것보다 변수에 직접 접근하는 것이 좋지 않을까?
 */

public class PersonVO {

    public String name;
    public String address;
    public String hobby;
    public String nationality;
}
