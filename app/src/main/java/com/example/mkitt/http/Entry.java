package com.example.mkitt.http;

import java.io.Serializable;
import java.util.List;

/**
 * https://www.kuaidi100.com/query?type=yuantong&postid=11111111111
 */
public class Entry implements Serializable {

    /**
     * message : ok
     * nu : 11111111111
     * ischeck : 0
     * condition : 00
     * com : yuantong
     * status : 200
     * state : 0
     * data : [{"time":"2019-12-18 01:54:04","ftime":"2019-12-18 01:54:04","context":"运输中，离开【广州转运中心】，下一部门【西安转运中心】","location":"广州市"},{"time":"2019-12-17 21:43:27","ftime":"2019-12-17 21:43:27","context":"运输中，到达广州转运中心","location":"广州市"},{"time":"2019-12-17 20:20:03","ftime":"2019-12-17 20:20:03","context":"运输中，离开【广州白云区永平街道快递分部】，下一部门【广州转运中心】","location":"广州市"},{"time":"2019-12-17 17:07:28","ftime":"2019-12-17 17:07:28","context":"您的订单已被收件员揽收,【广州白云区永平街道快递分部】库存中","location":"广州市"}]
     */

    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * time : 2019-12-18 01:54:04
         * ftime : 2019-12-18 01:54:04
         * context : 运输中，离开【广州转运中心】，下一部门【西安转运中心】
         * location : 广州市
         */

        private String time;
        private String ftime;
        private String context;
        private String location;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return "time: "+time+"ftime: "+ftime+"context: "+context+"location: "+location;
        }
    }
}
