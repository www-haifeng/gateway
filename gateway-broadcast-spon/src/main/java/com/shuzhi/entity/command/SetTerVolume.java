package com.shuzhi.entity.command;

public class SetTerVolume {
    private int terid;
    private int talkinv;
    private int talkoutv;
    private int bcinv;
    private int bcoutv;

    public int getTerid() {
        return terid;
    }

    public void setTerid(int terid) {
        this.terid = terid;
    }

    public int getTalkinv() {
        return talkinv;
    }

    public void setTalkinv(int talkinv) {
        this.talkinv = talkinv;
    }

    public int getTalkoutv() {
        return talkoutv;
    }

    public void setTalkoutv(int talkoutv) {
        this.talkoutv = talkoutv;
    }

    public int getBcinv() {
        return bcinv;
    }

    public void setBcinv(int bcinv) {
        this.bcinv = bcinv;
    }

    public int getBcoutv() {
        return bcoutv;
    }

    public void setBcoutv(int bcoutv) {
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
