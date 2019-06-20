package com.module.common.constant;

/**
 * @author wangdong
 * @date: 2019/6/18 10:00
 */
public class ProductEnum {

    public  enum shelfType{
        ATONCE(0,"立即上架"),
        PAUSE(1,"暂时不上架"),
        SCHEDULE(2,"立即上架");
        private int key;
        private String desc;
        shelfType(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }


    public  enum shelfStatus{
        SHELF(0,"上架"),
        OBTAINED(1,"下架");
        private int key;
        private String desc;
        shelfStatus(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }

}
