package com.module.common.constant;

/**
 * @author wangdong
 * @date: 2019/6/18 10:00
 */
public class ProductEnum {

    public  enum ShelfType {
        ATONCE(0,"立即上架"),
        PAUSE(1,"暂时不上架"),
        SCHEDULE(2,"立即上架");
        private int key;
        private String desc;
        ShelfType(int key, String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }


    public  enum ShelfStatus {
        SHELF(0,"上架"),
        OBTAINED(1,"下架");
        private int key;
        private String desc;
        ShelfStatus(int key, String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }


    public  enum SearchShelfStatus{
        SHELF(0,"上架"),
        OBTAINED(1,"下架"),
        COUNTDOWN(2,"售完/将要售完");
        private int key;
        private String desc;
        SearchShelfStatus(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }

}
