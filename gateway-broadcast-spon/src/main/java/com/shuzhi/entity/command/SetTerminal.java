package com.shuzhi.entity.command;

import com.shuzhi.commen.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SetTerminal {
    private Integer tid;
    private String tname;
    private Integer tbcoutv;
    private Integer tbcinv;
    private Integer ttalkoutv;
    private Integer ttalkinv;
    private Integer tbcttype;
    private Integer bctrecvtype;
    private Integer tterminallevel;
    private Integer tcalltimeout;
    private String tcalltarget1;
    private String tcalltarget2;
    private Integer tbctlevel;
    private Integer ttalklevel;
    private Integer tmonlevel;
    private Integer tmeetinglevel;
    private String tbctlist;
    private String ttalklist;
    private String tmonlist;
    private String tmeetinglist;
    private String trtspurl;
    private Integer tpass;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getTbcoutv() {
        return tbcoutv;
    }

    public void setTbcoutv(Integer tbcoutv) {
        this.tbcoutv = tbcoutv;
    }

    public Integer getTbcinv() {
        return tbcinv;
    }

    public void setTbcinv(Integer tbcinv) {
        this.tbcinv = tbcinv;
    }

    public Integer getTtalkoutv() {
        return ttalkoutv;
    }

    public void setTtalkoutv(Integer ttalkoutv) {
        this.ttalkoutv = ttalkoutv;
    }

    public Integer getTtalkinv() {
        return ttalkinv;
    }

    public void setTtalkinv(Integer ttalkinv) {
        this.ttalkinv = ttalkinv;
    }

    public Integer getTbcttype() {
        return tbcttype;
    }

    public void setTbcttype(Integer tbcttype) {
        this.tbcttype = tbcttype;
    }

    public Integer getBctrecvtype() {
        return bctrecvtype;
    }

    public void setBctrecvtype(Integer bctrecvtype) {
        this.bctrecvtype = bctrecvtype;
    }

    public Integer getTterminallevel() {
        return tterminallevel;
    }

    public void setTterminallevel(Integer tterminallevel) {
        this.tterminallevel = tterminallevel;
    }

    public Integer getTcalltimeout() {
        return tcalltimeout;
    }

    public void setTcalltimeout(Integer tcalltimeout) {
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

    public Integer getTbctlevel() {
        return tbctlevel;
    }

    public void setTbctlevel(Integer tbctlevel) {
        this.tbctlevel = tbctlevel;
    }

    public Integer getTtalklevel() {
        return ttalklevel;
    }

    public void setTtalklevel(Integer ttalklevel) {
        this.ttalklevel = ttalklevel;
    }

    public Integer getTmonlevel() {
        return tmonlevel;
    }

    public void setTmonlevel(Integer tmonlevel) {
        this.tmonlevel = tmonlevel;
    }

    public Integer getTmeetinglevel() {
        return tmeetinglevel;
    }

    public void setTmeetinglevel(Integer tmeetinglevel) {
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

    public Integer getTpass() {
        return tpass;
    }

    public void setTpass(Integer tpass) {
        this.tpass = tpass;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5Btid%5D=")
                .append(tid);
        sb.append("&jsondata%5Btname%5D=")
                .append(Utils.encodeUTF8(tname));
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
                .append(Utils.encodeUTF8(tcalltarget1));
        sb.append("&jsondata%5Btcalltarget2%5D=")
                .append(Utils.encodeUTF8(tcalltarget2));
        sb.append("&jsondata%5Btbctlevel%5D=")
                .append(tbctlevel);
        sb.append("&jsondata%5Bttalklevel%5D=")
                .append(ttalklevel);
        sb.append("&jsondata%5Btmonlevel%5D=")
                .append(tmonlevel);
        sb.append("&jsondata%5Btmeetinglevel%5D=")
                .append(tmeetinglevel);
        sb.append("&jsondata%5Btbctlist%5D=")
                .append(Utils.encodeUTF8(tbctlist));
        sb.append("&jsondata%5Bttalklist%5D=")
                .append(Utils.encodeUTF8(ttalklist));
        sb.append("&jsondata%5Btmonlist%5D=")
                .append(Utils.encodeUTF8(tmonlist));
        sb.append("&jsondata%5Btmeetinglist%5D=")
                .append(Utils.encodeUTF8(tmeetinglist));
        sb.append("&jsondata%5Btrtspurl%5D=")
                .append(Utils.encodeUTF8(trtspurl));
        sb.append("&jsondata%5Btpass%5D=")
                .append(tpass);
        return sb.toString();
    }
/* @Override
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
    }*/
}
