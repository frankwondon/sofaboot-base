package com.module.common.constant;

/**
 * @author wangdong
 * @date: 2019/7/15 9:50
 */
public class OrderEnum {
    public  enum PayChannel{
        NOT(0,"无需支付"),
        ALIPAY(1,"支付宝支付"),
        WXPAY(2,"微信支付");
        private int key;
        private String desc;
        PayChannel(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }



    public  enum OrderType{
        PARENT(1,"父订单"),
        CHILD(0,"子订单");
        private int key;
        private String desc;
        OrderType(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }


    public enum RecordOperationUserType{
        USER(1,"用户"),
        SYSTEM(0,"后台");
        private int key;
        private String desc;
        RecordOperationUserType(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }


    public enum RecordOperationType{
        CLOSE_ORDER(0,"关闭订单");
        private int key;
        private String desc;
        RecordOperationType(int key,String desc){
            this.key=key;
            this.desc=desc;
        }
        public int key(){
            return key;
        }
    }
}
