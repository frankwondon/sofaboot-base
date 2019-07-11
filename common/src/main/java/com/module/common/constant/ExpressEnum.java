package com.module.common.constant;

/**
 * @author wangdong
 * @date: 2019/7/10 15:15
 */
public class ExpressEnum {
    public  enum Type{
        MERCHANT(0,"包邮"),
        USER(1,"自费");

        private int key;
        private String desc;
        Type(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }

    public  enum PriceRule{
        COUNT(0,"按件计费"),
        WEIGHT(1,"按KG计费");
        private int key;
        private String desc;
        PriceRule(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
        public static PriceRule byKey(int key){
            PriceRule[] values = PriceRule.values();
            for (PriceRule value : values) {
                if (key==value.key){
                    return value;
                }
            }
            return null;
        }
    }
    public enum DefaultType{
        NOT(0,"非默认"),
        DEFAULT(1,"默认");
        private int key;
        private String desc;
        DefaultType(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }

    public  enum DisplayType{
        MERCHANT(0,"包邮"),
        USER(1,"自费"),
        NO(2,"不需要物流");
        private int key;
        private String desc;
        DisplayType(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }
}
