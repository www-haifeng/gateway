package com.shuzhi.service.impl;

import com.shuzhi.entity.Terminal;
import com.shuzhi.service.CLonBonLib;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service("LonBon")
public class LonBon {
    public CLonBonLib INSTANCE;
    @Value("${jna.serverIp}")
    private String serverIp;
    @Value("${jna.serverPort}")
    private int serverPort;
    @Value("${jna.dllpath}")
    private String dllpath;


    public LonBon() {
    }

    public void getInstance() {
        init();
    }

    @PostConstruct
    private void init() {
        INSTANCE = Native.load(dllpath, CLonBonLib.class);
//        INSTANCE = Native.load((Platform.isWindows() ? "lb_sdk_universal" : "c"), CLonBonLib.class);
        int res = INSTANCE.lb_initialServer(serverIp, serverPort);
        System.out.println("LonBon 的init 方法=========" + res);
    }

    @PreDestroy
    private void destroy() {
        int res = INSTANCE.lb_releaseServer();
        System.out.println("LonBon destroy 方法======" + res);
    }

    /**
     * 初始化sdk
     *
     * @param serverIp 对讲服务器 IP 地址(地址盒地址)
     * @param svrPort  对讲服务器监听端口（默认 5160 端口）
     * @return 0 成功， -1 或者其他失败。
     */
    public int lb_initialServer(String serverIp, int svrPort) {
        return INSTANCE.lb_initialServer(serverIp, svrPort);
    }

    /**
     * 释放 sdk 资源
     *
     * @return 0 成功， -1 或其他失败
     */
    public int lb_releaseServer() {
        return INSTANCE.lb_releaseServer();
    }

    /**
     * 发起呼叫
     *
     * @param svrIp   对讲服务器 IP 地址
     * @param hostNum 对讲主机编号
     * @param terNum  对讲终端编号
     * @return 0 成功，其他失败
     */
    public int lb_call(String svrIp, int hostNum, int terNum) {
        return INSTANCE.lb_call(svrIp, hostNum, terNum);
    }

    /**
     * 接听
     *
     * @param svrIp   对讲服务器 IP 地址
     * @param hostNum 对讲主机编号
     * @param terNum  对讲终端编号
     * @return
     */
    public int lb_answer(String svrIp, int hostNum, int terNum) {
        return INSTANCE.lb_answer(svrIp, hostNum, terNum);
    }

    /**
     * 挂断
     *
     * @param svrIp    对讲服务器 IP 地址
     * @param hostNum  对讲主机编号
     * @param terNum   通话设备编号
     * @param slaveCnt 通话设备数量
     * @return 0 成功，其他失败
     */
    public int lb_hangUp(String svrIp, int hostNum, int terNum, int slaveCnt) {
        IntByReference slaveNums = new IntByReference();
        slaveNums.setValue(terNum);
        return INSTANCE.lb_hangUp(svrIp, hostNum, slaveNums, slaveCnt);
    }

    /**
     * 广播
     *
     * @param svrIp    对讲服务器 IP 地址
     * @param hostNum  广播主机编号
     * @param groupNum 终端编号列表（整型数组）
     * @param count    终端编号数量
     * @return 0 成功，其他失败
     */
    public int lb_start_broadcast(String svrIp, int hostNum, int[] groupNum, int count) {
        return INSTANCE.lb_start_broadcast(svrIp, hostNum, groupNum, count);
    }

    /**
     * 断开广播
     *
     * @param svrIp   对讲服务器 IP 地址
     * @param hostNum 发起广播的主机编号
     * @return 0 成功，其他失败
     */
    public int lb_stop_broadcast(String svrIp, int hostNum) {
        return INSTANCE.lb_stop_broadcast(svrIp, hostNum);
    }

    /**
     * 获取对讲服务器终端信息
     *
     * @param svrIp   对讲服务器 IP 地址
     * @param terList 返回的对讲终端信息
     * @return 0 成功，其他失败。（如果对讲系统中对讲数量比 count 多，那么函数返回失败，且 count 返回实际的数量。）
     */
    public int lb_getTerminalInfos(String svrIp, List<Terminal> terList, int maxCount) throws UnsupportedEncodingException {
        TerminalInfo terminalInfo = new TerminalInfo();
        TerminalInfo[] terminalInfos = (TerminalInfo[]) terminalInfo.toArray(maxCount);
        IntByReference count = new IntByReference();
        count.setValue(0);
        int tmpSta = INSTANCE.lb_getTerminalInfos(svrIp, terminalInfos, count);
        int status = INSTANCE.lb_getTerminalInfos(svrIp, terminalInfos, count);
        if (status == 0) {
            int terCount = count.getValue();
            for (int index = 0; index < terCount; ++index) {
                Terminal terminal = new Terminal();
                terminal.setTerminalType(terminalInfos[index].terminalType);
                terminal.setDisplayNum(terminalInfos[index].displayNum);
                terminal.setNetAddr(new String(terminalInfos[index].netAddr, "gbk"));
                terminal.setName(new String(terminalInfos[index].name, "gbk"));
                terminal.setModel(new String(terminalInfos[index].model, "gbk"));
                terList.add(terminal);
            }
        }

        return status;
    }

    /**
     * 获取指定终端信息
     *
     * @param svrIp    对讲服务器 IP 地址
     * @param terNum   指定终端的编号
     * @param terminal 返回的指定的对讲终端信息
     * @return 0 成功，其他失败
     */
    public int lb_getTerminalInfo(String svrIp, int terNum, Terminal terminal) throws UnsupportedEncodingException {
        TerminalInfo.ByReference terminalInfo = new TerminalInfo.ByReference();
        int ret = INSTANCE.lb_getTerminalInfo(svrIp, terNum, terminalInfo);
        if (ret == 0) {
            terminal.setTerminalType(terminalInfo.terminalType);
            terminal.setDisplayNum(terminalInfo.displayNum);
            terminal.setNetAddr(new String(terminalInfo.netAddr, "gbk"));
            terminal.setName(new String(terminalInfo.name, "gbk"));
            terminal.setModel(new String(terminalInfo.model, "gbk"));
        }

        return ret;
    }

    /**
     * 获取对讲服务器主机终端数量
     *
     * @param svrIp 对讲服务器的 IP 地址
     * @return 大于等于 0 表示返回的主机终端数量，否则失败。
     */
    public int lb_get_all_master_count(String svrIp) {
        return INSTANCE.lb_get_all_master_count(svrIp);
    }

    /**
     * 获取对讲服务器主机终端列表
     *
     * @param svrIp   对讲服务器的 IP 地址
     * @param mstList 整型数组，用于存编号列表，需预先分配空间
     * @param n_size  编号列表 mstList 的数组长度
     * @return 大于等于 0 表示返回的编号列表中实际的终端数量，否则失败
     */
    public int lb_get_all_master(String svrIp, int[] mstList, int n_size) {
        return INSTANCE.lb_get_all_master(svrIp, mstList, n_size);
    }

    /**
     * 获取指定主机下属所有终端数量
     *
     * @param svrIp   对讲服务器的 IP 地址
     * @param hostNum 指定主机终端编号
     * @return 大于等于 0 表示返回该主机下所有分机终端的数量，其他失败
     */
    public int lb_get_terminal_from_master_count(String svrIp, int hostNum) {
        return INSTANCE.lb_get_terminal_from_master_count(svrIp, hostNum);
    }

    /**
     * 获取指定主机下属所有分终端列表
     *
     * @param svrIp   对讲服务器的 IP 地址
     * @param hostNum 指定主机终端编号
     * @param terList 整型数组，用于缓存所有分机终端列表，需预先分配空间
     * @param n_size  编号列表 terList 的数组长度
     * @return 大于等于 0 表示返回的编号列表中实际的分机终端的数量，其他失败
     */
    public int lb_get_terminal_from_master(String svrIp, int hostNum, int[] terList, int n_size) {
        return INSTANCE.lb_get_terminal_from_master(svrIp, hostNum, terList, n_size);
    }

    /**
     * 获取在线主机终端列表
     *
     * @param svrIp 对讲服务器的 IP 地址
     * @return 大于等于 0 表示返回在线主机终端数量，其他失败
     */
    public int lb_get_online_master_count(String svrIp) {
        return INSTANCE.lb_get_online_master_count(svrIp);
    }

    /**
     * 获取在线主机终端列表
     *
     * @param svrIp   对讲服务器的 IP 地址
     * @param mstList 整型数组，用于缓存在线主机编号列表，需预先分配空间
     * @param n_size  编号列表 mstList 的数组长度
     * @return 大于等于 0 表示返回的编号列表中实际的在线主机终端数量，其他失败
     */
    public int lb_get_online_master(String svrIp, int[] mstList, int n_size) {
        return INSTANCE.lb_get_online_master(svrIp, mstList, n_size);
    }

    /**
     * 获取指定主机下所有在线分终端数量
     *
     * @param svrIp   对讲服务器的 IP 地址
     * @param hostNum 指定主机终端编号
     * @return 大于等于 0 表示返回该主机下所有在线分机终端的数量，其他失败
     */
    public int lb_get_terminal_online_from_master_count(String svrIp, int hostNum) {
        return INSTANCE.lb_get_terminal_online_from_master_count(svrIp, hostNum);
    }

    /**
     * 获取指定主机下所有在线分列表
     *
     * @param svrIp   对讲服务器的网络地址
     * @param hostNum 指定主机终端编号
     * @param terList 整型数组，用于缓存所有在线分机终端列表，需预先分配空间
     * @param n_size  编号列表 terList 的数组长度
     * @return 大于等于 0 表示返回的终端列表中实际的在线分机终端数量，其他失败
     */
    public int lb_get_terminal_online_from_master(String svrIp, int hostNum, int[] terList, int n_size) {
        return INSTANCE.lb_get_terminal_online_from_master(svrIp, hostNum, terList, n_size);
    }

    /**
     * 获取指定终端在线状态
     *
     * @param svrIp      对讲服务器的网络地址
     * @param displayNum 指定设备终端的编号
     * @return 非 0 表示在线， 0 表示不在线。
     */
    public int lb_get_state_from_terminal(String svrIp, int displayNum) {
        return INSTANCE.lb_get_state_from_terminal(svrIp, displayNum);
    }

    /**
     * 获取错误信息
     *
     * @param errorId 错误 Id，由以上接口返回。
     * @param strErr  错误输出缓冲区
     * @param errlen  缓冲区大小
     * @return
     */
    public int lb_get_error_info(int errorId, byte[] strErr, int errlen) {
        return INSTANCE.lb_get_error_info(errorId, strErr, errlen);
    }

    /**
     * 开启语音文件广播
     *
     * @param svrIp    对讲服务器 IP 地址。
     * @param hostNum  广播主机编号。
     * @param groupNum 终端编号列表（整型数组）。
     * @param count    终端编号数量。
     * @param fileList 音频文件列表。
     * @param fileCnt  文件数量。
     * @param nCycCnt  循环播放次数， 0 表示无限循环播放
     * @return 0 成功，其他失败。
     */
    public int lb_start_broadcast_file(String svrIp, int hostNum, int[] groupNum, int count, String[] fileList, int fileCnt, int nCycCnt) {
        return INSTANCE.lb_start_broadcast_file(svrIp, hostNum, groupNum, count, fileList, fileCnt, nCycCnt);
    }

    /**
     * 关闭语音文件广播
     *
     * @param svrIp   对讲服务器 IP 地址。
     * @param hostNum 发起广播的主机编号。
     * @param bcId    广播组序号。
     * @return 返回 0 成功，其他失败。
     */
    public int lb_stop_broadcast_file(String svrIp, int hostNum, String bcId) {
        return INSTANCE.lb_stop_broadcast_file(svrIp, hostNum, bcId);
    }

}
