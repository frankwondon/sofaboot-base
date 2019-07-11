package com.module.common.constant;

/**
 * @author wangdong
 * @date: 2019/6/18 10:00
 */
public class ProductEnum {

    /**
     * 上架操作标识
     */
    public  enum ShelfType {
        ATONCE(0,"立即上架"),
        PAUSE(1,"暂时不上架"),
        SCHEDULE(2,"定时上架");
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


    /**
     * 上架状态
     */
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


    /**
     * 商品上下架搜索标识
     */
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

    public  enum ShipType {
        NEED(0,"快递"),
        NO(1,"无需物流");
        private int key;
        private String desc;
        ShipType(int key, String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }

}
