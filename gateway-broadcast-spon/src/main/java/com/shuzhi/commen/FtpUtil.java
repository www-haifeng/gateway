package com.shuzhi.commen;

import com.shuzhi.entity.command.AddMediaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
@Component
@Slf4j
public class FtpUtil {

        private FtpClient ftpClient;

        public void connectServer(String ip, int port, String user, String password, String path) {
            try {
                /* ******连接服务器的两种方法*******/
                ftpClient = FtpClient.create();
                try {
                    SocketAddress addr = new InetSocketAddress(ip, port);
                    ftpClient.connect(addr);
                    ftpClient.login(user, password.toCharArray());
                    log.info("FTP login success!");
                    if (path.length() != 0) {
                        //把远程系统上的目录切换到参数path所指定的目录
                        ftpClient.changeDirectory(path);
                    }
                } catch (FtpProtocolException e) {
                    e.printStackTrace();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }

        /**
         * 关闭连接
         */
        public void closeConnect() {
            try {
                ftpClient.close();
               log.info("disconnect success");
            } catch (IOException ex) {
                System.out.println("not disconnect");
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }

        /**
         * 上传文件
         *
         * @param localFile  本地文件
         * @param remoteFile 远程文件
         */
        public void upload(String localFile, String remoteFile) {
            File file_in = new File(localFile);
            try (OutputStream os = ftpClient.putFileStream(remoteFile);
                 FileInputStream is = new FileInputStream(file_in)) {
                byte[] bytes = new byte[1024];
                int c;
                while ((c = is.read(bytes)) != -1) {
                    os.write(bytes, 0, c);
                }
                System.out.println("upload success");
            } catch (IOException ex) {
                System.out.println("not upload");
                ex.printStackTrace();
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            }
        }

        /**
         * 下载文件。获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。
         *
         * @param remoteFile 远程文件路径(服务器端)
         * @param localFile  本地文件路径(客户端)
         */
        public void download(String remoteFile, String localFile) {
            File file_in = new File(localFile);
            try (InputStream is = ftpClient.getFileStream(remoteFile);
                 FileOutputStream os = new FileOutputStream(file_in)) {

                byte[] bytes = new byte[1024];
                int c;
                while ((c = is.read(bytes)) != -1) {
                    os.write(bytes, 0, c);
                }
                System.out.println("download success");
            } catch (IOException ex) {
                System.out.println("not download");
                ex.printStackTrace();
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            }

        }
        /**
         * @Description:上传媒体文件
         * @Author: YHF
         * @date 2019/7/31
         */
        public String uploadMediaFile(AddMediaData amd, String url) {
            String fileName = amd.getFile().substring(amd.getFile().lastIndexOf("/") + 1, amd.getFile().length());
            //下载文件到本地
            download(fileName,fileName);
            //上传文件到设备
            RestTemplate rest = new RestTemplate();
            FileSystemResource resource = new FileSystemResource(fileName);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("subpath", amd.getSubpath());
            param.add("unfomat", amd.getUnformat());
            param.add("file", resource);
            try {
                String resultStr = new String(rest.postForObject(url, param, String.class).getBytes("ISO-8859-1"), "UTF-8");
                deleteFile(fileName);
                closeConnect();
                return resultStr;
            } catch (UnsupportedEncodingException e) {
                closeConnect();
                log.error("请求出错", e);
                return "";

            }
        }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

        public static void main(String agrs[]) {
        FtpUtil fu = new FtpUtil();

        fu.connectServer("39.96.84.143",21,"appgate","IC5uGeIT2upEEU99AHjIKr2tg","/mp3");
        AddMediaData amd = new AddMediaData();
        amd.setFile("/mp3/beep.mp3");
        amd.setSubpath("");
        amd.setUnformat("1");
        //uploadMediaFile(amd,"http://192.168.7.71:90/php/addmediadata.php");
/*
        //下载测试
        String filepath = "huijia.mp3";
        String localfilepath = "D:/jd-gui.mp3";
            fu.download(filepath, localfilepath);

        //上传测试
        String localfile = "D:/回家-萨克斯.mp3";
        String remotefile = "回家-萨克斯.mp3";                //上传
        fu.upload(localfile, remotefile);
        fu.closeConnect();
*/

    }


}
