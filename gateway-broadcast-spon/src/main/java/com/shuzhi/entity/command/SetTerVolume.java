package com.shuzhi.entity.command;

public class SetTerVolume {
    private Integer terid;
    private Integer talkinv;
    private Integer talkoutv;
    private Integer bcinv;
    private Integer bcoutv;

    public Integer getTerid() {
        return terid;
    }

    public void setTerid(Integer terid) {
        this.terid = terid;
    }

    public Integer getTalkinv() {
        return talkinv;
    }

    public void setTalkinv(Integer talkinv) {
        this.talkinv = talkinv;
    }

    public Integer getTalkoutv() {
        return talkoutv;
    }

    public void setTalkoutv(Integer talkoutv) {
        this.talkoutv = talkoutv;
    }

    public Integer getBcinv() {
        return bcinv;
    }

    public void setBcinv(Integer bcinv) {
        this.bcinv = bcinv;
    }

    public Integer getBcoutv() {
        return bcoutv;
    }

    public void setBcoutv(Integer bcoutv) {
        this.bcoutv = bcoutv;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5Bterid%5D=")
                .append(terid);
        sb.append("&jsondata%5Btalking%5D=")
                .append(talkinv);
        sb.append("&jsondata%5Btalkoutv%5D=")
                .append(talkoutv);
        sb.append("&jsondata%5Bbcinv%5D=")
                .append(bcinv);
        sb.append("&jsondata%5Bbcoutv%5D=")
                .append(bcoutv);
        return sb.toString();
    }
}
