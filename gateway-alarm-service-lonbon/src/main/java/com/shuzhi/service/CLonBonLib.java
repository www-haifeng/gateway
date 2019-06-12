package com.shuzhi.service;

import com.shuzhi.service.impl.Action_param;
import com.shuzhi.service.impl.TerminalInfo;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import org.springframework.stereotype.Service;

@Service
public interface CLonBonLib extends Library{

    /**
     * 加载库
     */
//    CLonBonLib INSTANCE = Native.load((Platform.isWindows() ? "lb_sdk_universal" : "c"), CLonBonLib.class);
//    CLonBonLib INSTANCE = Native.load("E:/lb_sdk_universal.dll", CLonBonLib.class);
    /**
     * 初始化sdk
     * @param serverIp 对讲服务器 IP 地址(地址盒地址)
     * @param svrPort  对讲服务器监听端口（默认 5160 端口）
     * @return  0 成功， -1 或者其他失败。
     */
    int lb_initialServer(String serverIp, int svrPort);

    /**
     * 释放 sdk 资源
     * @return 0 成功， -1 或其他失败
     */
    int lb_releaseServer();

    /**
     * 主机事件回调
     * @param action_callback  回调函数地址
     * @param userData  用户自定义的信息
     * @return  0 成功、 -1 失败。
     */
    int lb_CallActionNotify(ACTION_CALLBACK action_callback, Pointer userData);

    /**
     * 在线状态回调
     * @param callback  回调函数
     * @param userData  用户自定义的信息
     * @return  0 成功、 -1 失败。
     */
    int lb_onlineState_set_callBack(ONLINE_STATE_CALLBACK callback, Pointer userData);

    /**
     * 主机事件回调
     */
    public interface ACTION_CALLBACK extends Callback {

        /**
         * 主机事件回调
         * @param userEvent  反馈给用户的事件，见枚举类型lb_event_message_e
         * @param wParam  用户可以获知的事件信息参数，包括呼叫编号、接收编号等。
         * @param userData  注册回调时传入的用户自定义信息
         */
        void invoke(int userEvent, Action_param.ByReference wParam, Pointer userData);
    }

    /**
     * 在线状态回调
     */
    public interface ONLINE_STATE_CALLBACK extends Callback {

        /**
         * 在线状态回调
         * @param userEvent  参见枚举类型lb_callback_status_e
         * @param displayNum 设备编号
         * @param userData  注册回调时传入的用户自定义信息
         */
        void invoke(int userEvent, int displayNum, Pointer userData);
    }

    /**
     * 发起呼叫
     * @param svrIp  对讲服务器 IP 地址
     * @param hostNum  对讲主机编号
     * @param terNum  对讲终端编号
     * @return  0 成功，其他失败
     */
    int lb_call(String svrIp, int hostNum, int terNum);

    /**
     * 接听
     * @param svrIp  对讲服务器 IP 地址
     * @param hostNum  对讲主机编号
     * @param terNum  对讲终端编号
     * @return
     */
    int lb_answer(String svrIp, int hostNum, int terNum);

    /**
     * 挂断
     * @param svrIp  对讲服务器 IP 地址
     * @param hostNum  对讲主机编号
     * @param slaveNums  通话设备编号
     * @param slaveCnt  通话设备数量
     * @return  0 成功，其他失败
     */
    int lb_hangUp(String svrIp, int hostNum, IntByReference slaveNums, int slaveCnt);

    /**
     * 广播
     * @param svrIp  对讲服务器 IP 地址
     * @param hostNum  广播主机编号
     * @param groupNum  终端编号列表（整型数组）
     * @param count  终端编号数量
     * @return  0 成功，其他失败
     */
    int lb_start_broadcast(String svrIp, int hostNum, int[] groupNum, int count);

    /**
     * 断开广播
     * @param svrIp  对讲服务器 IP 地址
     * @param hostNum  发起广播的主机编号
     * @return  0 成功，其他失败
     */
    int lb_stop_broadcast(String svrIp, int hostNum);

    /**
     * 获取对讲服务器终端信息
     * @param svrIp  对讲服务器 IP 地址
     * @param terminalInfo  返回的对讲终端信息
     * @param count  对讲终端的数量，调用后返回实际的数量
     * @return  0 成功，其他失败。（如果对讲系统中对讲数量比 count 多，那么函数返回失败，且 count 返回实际的数量。）
     */
    int lb_getTerminalInfos(String svrIp, TerminalInfo[] terminalInfo, IntByReference count);

    /**
     * 获取指定终端信息
     * @param svrIp  对讲服务器 IP 地址
     * @param terNum  指定终端的编号
     * @param terminalInfo  返回的指定的对讲终端信息
     * @return  0 成功，其他失败
     */
    int lb_getTerminalInfo(String svrIp, int terNum, TerminalInfo.ByReference terminalInfo);

    /**
     * 获取对讲服务器主机终端数量
     * @param svrIp  对讲服务器的 IP 地址
     * @return  大于等于 0 表示返回的主机终端数量，否则失败。
     */
    int lb_get_all_master_count(String svrIp);

    /**
     * 获取对讲服务器主机终端列表
     * @param svrIp  对讲服务器的 IP 地址
     * @param mstList 整型数组，用于存编号列表，需预先分配空间
     * @param n_size  编号列表 mstList 的数组长度
     * @return  大于等于 0 表示返回的编号列表中实际的终端数量，否则失败
     */
    int lb_get_all_master(String svrIp, int[] mstList, int n_size);

    /**
     * 获取指定主机下属所有终端数量
     * @param svrIp  对讲服务器的 IP 地址
     * @param hostNum  指定主机终端编号
     * @return  大于等于 0 表示返回该主机下所有分机终端的数量，其他失败
     */
    int lb_get_terminal_from_master_count(String svrIp, int hostNum);

    /**
     * 获取指定主机下属所有分终端列表
     * @param svrIp  对讲服务器的 IP 地址
     * @param hostNum  指定主机终端编号
     * @param terList  整型数组，用于缓存所有分机终端列表，需预先分配空间
     * @param n_size  编号列表 terList 的数组长度
     * @return  大于等于 0 表示返回的编号列表中实际的分机终端的数量，其他失败
     */
    int lb_get_terminal_from_master(String svrIp, int hostNum, int[] terList, int n_size);

    /**
     * 获取在线主机终端列表
     * @param svrIp  对讲服务器的 IP 地址
     * @return  大于等于 0 表示返回在线主机终端数量，其他失败
     */
    int lb_get_online_master_count(String svrIp);

    /**
     * 获取在线主机终端列表
     * @param svrIp  对讲服务器的 IP 地址
     * @param mstList  整型数组，用于缓存在线主机编号列表，需预先分配空间
     * @param n_size  编号列表 mstList 的数组长度
     * @return  大于等于 0 表示返回的编号列表中实际的在线主机终端数量，其他失败
     */
    int lb_get_online_master(String svrIp, int[] mstList, int n_size);


    /**
     * 获取指定主机下所有在线分终端数量
     * @param svrIp  对讲服务器的 IP 地址
     * @param hostNum  指定主机终端编号
     * @return  大于等于 0 表示返回该主机下所有在线分机终端的数量，其他失败
     */
    int lb_get_terminal_online_from_master_count(String svrIp, int hostNum);

    /**
     * 获取指定主机下所有在线分列表
     * @param svrIp  对讲服务器的网络地址
     * @param hostNum  指定主机终端编号
     * @param terList  整型数组，用于缓存所有在线分机终端列表，需预先分配空间
     * @param n_size  编号列表 terList 的数组长度
     * @return  大于等于 0 表示返回的终端列表中实际的在线分机终端数量，其他失败
     */
    int lb_get_terminal_online_from_master(String svrIp, int hostNum, int[] terList, int n_size);

    /**
     * 获取指定终端在线状态
     * @param svrIp  对讲服务器的网络地址
     * @param displayNum  指定设备终端的编号
     * @return  非 0 表示在线， 0 表示不在线。
     */
    int lb_get_state_from_terminal(String svrIp, int displayNum);


    /**
     * 获取错误信息
     * @param errorId  错误 Id，由以上接口返回。
     * @param strErr  错误输出缓冲区
     * @param errlen  缓冲区大小
     * @return
     */
    int lb_get_error_info(int errorId, byte[] strErr, int errlen);

    /**
     * 开启语音文件广播
     * @param svrIp  对讲服务器 IP 地址。
     * @param hostNum  广播主机编号。
     * @param groupNum  终端编号列表（整型数组）。
     * @param count  终端编号数量。
     * @param fileList  音频文件列表。
     * @param fileCnt   文件数量。
     * @param nCycCnt  循环播放次数， 0 表示无限循环播放
     * @return  0 成功，其他失败。
     */
    int lb_start_broadcast_file(String svrIp, int hostNum, int[] groupNum, int count, String[] fileList, int fileCnt, int nCycCnt);

    /**
     * 关闭语音文件广播
     * @param svrIp  对讲服务器 IP 地址。
     * @param hostNum  发起广播的主机编号。
     * @param bcId  广播组序号。
     * @return  返回 0 成功，其他失败。
     */
    int lb_stop_broadcast_file(String svrIp, int hostNum, String bcId);

}
