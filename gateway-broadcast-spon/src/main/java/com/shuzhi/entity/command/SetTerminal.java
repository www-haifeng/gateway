package com.shuzhi.entity.command;

public class SetTerminal {
    private int tid;
    private String tname;
    private int tbcoutv;
    private int tbcinv;
    private int ttalkoutv;
    private int ttalkinv;
    private int tbcttype;
    private int bctrecvtype;
    private int tterminallevel;
    private int tcalltimeout;
    private String tcalltarget1;
    private String tcalltarget2;
    private int tbctlevel;
    private int ttalklevel;
    private int tmonlevel;
    private int tmeetinglevel;
    private String tbctlist;
    private String ttalklist;
    private String tmonlist;
    private String tmeetinglist;
    private String trtspurl;
    private int tpass;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getTbcoutv() {
        return tbcoutv;
    }

    public void setTbcoutv(int tbcoutv) {
        this.tbcoutv = tbcoutv;
    }

    public int getTbcinv() {
        return tbcinv;
    }

    public void setTbcinv(int tbcinv) {
        this.tbcinv = tbcinv;
    }

    public int getTtalkoutv() {
        return ttalkoutv;
    }

    public void setTtalkoutv(int ttalkoutv) {
        this.ttalkoutv = ttalkoutv;
    }

    public int getTtalkinv() {
        return ttalkinv;
    }

    public void setTtalkinv(int ttalkinv) {
        this.ttalkinv = ttalkinv;
    }

    public int getTbcttype() {
        return tbcttype;
    }

    public void setTbcttype(int tbcttype) {
        this.tbcttype = tbcttype;
    }

    public int getBctrecvtype() {
        return bctrecvtype;
    }

    public void setBctrecvtype(int bctrecvtype) {
        this.bctrecvtype = bctrecvtype;
    }

    public int getTterminallevel() {
        return tterminallevel;
    }

    public void setTterminallevel(int tterminallevel) {
        this.tterminallevel = tterminallevel;
    }

    public int getTcalltimeout() {
        return tcalltimeout;
    }

    public void setTcalltimeout(int tcalltimeout) {
        this.tcalltimeout = tcalltimeout;
    }

    public String getTcalltarget1() {
        return tcalltarget1;
    }

    public void setTcalltarget1(String tcalltarget1) {
        this.tcalltarget1 = tcalltarget1;
    }

    public String getTcalltarget2() {
        return tcalltarget2;
    }

    public void setTcalltarget2(String tcalltarget2) {
        this.tcalltarget2 = tcalltarget2;
    }

    public int getTbctlevel() {
        return tbctlevel;
    }

    public void setTbctlevel(int tbctlevel) {
        this.tbctlevel = tbctlevel;
    }

    public int getTtalklevel() {
        return ttalklevel;
    }

    public void setTtalklevel(int ttalklevel) {
        this.ttalklevel = ttalklevel;
    }

    public int getTmonlevel() {
        return tmonlevel;
    }

    public void setTmonlevel(int tmonlevel) {
        this.tmonlevel = tmonlevel;
    }

    public int getTmeetinglevel() {
        return tmeetinglevel;
    }

    public void setTmeetinglevel(int tmeetinglevel) {
        this.tmeetinglevel = tmeetinglevel;
    }

    public String getTbctlist() {
        return tbctlist;
    }

    public void setTbctlist(String tbctlist) {
        this.tbctlist = tbctlist;
    }

    public String getTtalklist() {
        return ttalklist;
    }

    public void setTtalklist(String ttalklist) {
        this.ttalklist = ttalklist;
    }

    public String getTmonlist() {
        return tmonlist;
    }

    public void setTmonlist(String tmonlist) {
        this.tmonlist = tmonlist;
    }

    public String getTmeetinglist() {
        return tmeetinglist;
    }

    public void setTmeetinglist(String tmeetinglist) {
        this.tmeetinglist = tmeetinglist;
    }

    public String getTrtspurl() {
        return trtspurl;
    }

    public void setTrtspurl(String trtspurl) {
        this.trtspurl = trtspurl;
    }

    public int getTpass() {
        return tpass;
    }

    public void setTpass(int tpass) {
        this.tpass = tpass;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5Btid%5D=")
                .append(tid);
        sb.append("&jsondata%5Btname%5D=")
                .append(tname);
        sb.append("&jsondata%5Btbcoutv%5D=")
                .append(tbcoutv);
        sb.append("&jsondata%5Btbcinv%5D=")
                .append(tbcinv);
        sb.append("&jsondata%5Bttalkoutv%5D=")
                .append(ttalkoutv);
        sb.append("&jsondata%5Bttalkinv%5D=")
                .append(ttalkinv);
        sb.append("&jsondata%5Btbcttype%5D=")
                .append(tbcttype);
        sb.append("&jsondata%5Bbctrecvtype%5D=")
                .append(bctrecvtype);
        sb.append("&jsondata%5Btterminallevel%5D=")
                .append(tterminallevel);
        sb.append("&jsondata%5Btcalltimeout%5D=")
                .append(tcalltimeout);
        sb.append("&jsondata%5Btcalltarget1%5D=")
                .append(tcalltarget1);
        sb.append("&jsondata%5Btcalltarget2%5D=")
                .append(tcalltarget2);
        sb.append("&jsondata%5Btbctlevel%5D=")
                .append(tbctlevel);
        sb.append("&jsondata%5Bttalklevel%5D=")
                .append(ttalklevel);
        sb.append("&jsondata%5Btmonlevel%5D=")
                .append(tmonlevel);
        sb.append("&jsondata%5Btmeetinglevel%5D=")
                .append(tmeetinglevel);
        sb.append("&jsondata%5Btbctlist%5D=")
                .append(tbctlist);
        sb.append("&jsondata%5Bttalklist%5D=")
                .append(ttalklist);
        sb.append("&jsondata%5Btmonlist%5D=")
                .append(tmonlist);
        sb.append("&jsondata%5Btmeetinglist%5D=")
                .append(tmeetinglist);
        sb.append("&jsondata%5Btrtspurl%5D=")
                .append(trtspurl);
        sb.append("&jsondata%5Btpass%5D=")
                .append(tpass);
        return sb.toString();
    }
}
