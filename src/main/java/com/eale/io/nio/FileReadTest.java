package com.eale.io.nio;

import org.apache.tomcat.jni.OS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author HLD
 * @Date 2021/4/22 0022
 * @Description //TODO
 * @Version 1.0
 **/
public class FileReadTest {

    // 开启log4j日志
    public static int count1 = 0;


    public String showDirectory(File file, String dataurl, String netPoint, String cusPoint, String bussinessPoint) {

        File[] files=file.listFiles();
        for(File a:files) {
//			Map<String,Object> mapParams = new HashMap<>();
            List<List<String>> aList = new ArrayList();
            if(a.isDirectory()) {
                showDirectory(a,dataurl,netPoint,cusPoint,bussinessPoint);
            }else {
                String layerId = "";
                List<String> title = new ArrayList<>();
                title.add("名称");
                title.add("地址");
//				title.add("时间");
                if (a.getName().contains("inst")){
                    layerId = netPoint;
                    title.add("开立时间");
                    title.add("机构级别");
//					title.add("主要业务");
                    title.add("网点编号");
                }else if (a.getName().contains("cstm")){
                    layerId = cusPoint;
                    title.add("开立时间");
                    title.add("客户编号");
                    title.add("机构号");
                    title.add("定期存款");
                    title.add("保险");
                    title.add("理财");
                    title.add("基金");
                    title.add("贵金属");
                    title.add("资产管理计划");
                    title.add("三方存管");
                    title.add("手机银行");
                    title.add("借记卡");
                    title.add("邮信通");
                    title.add("信用卡");
                    title.add("贷款");
                }else if (a.getName().contains("business")) {
                    layerId = bussinessPoint;
                    title.add("时间");
                    title.add("商户编号");
                    title.add("网点编号");
                    title.add("商户类型");
                    title.add("主要业务类型");
                    title.add("扩展1");
                    title.add("扩展2");
                    title.add("扩展3");
                    title.add("扩展4");
                    title.add("扩展5");
//					title.add("扩展6");
                } else {
                    continue;
                }

                List<String> list = FileReadTest.readFileNioByLine(a.getAbsolutePath(), ByteBuffer.allocate(8000),3000000);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int length = 0;
                for (String s : list) {
//                    split = new String[1024 * 500];
                    //数据处理
                    String[] split  = s.split("\\|\\+\\|");
//                    log.info("切割后的数组:" + split);
//                    log.info("解析数据每组的长度：" +split.length);
                    length = split.length;
                    List<String> stList = new ArrayList<>(Arrays.asList(split));
                    aList.add(stList);
                }

                //数据分割
                int wholesize = aList.size();
                int groupNum = wholesize/8000;
                int num = title.size() - length;

            }

        }
        return "";
    }


    /**
     * 使用nio 实现按行读取文件(大文件)
     *
     * @author Liuyang
     *
     * @param filepath
     *            文件路径
     * @param rbuffer
     *            每次缓冲字符区的大小
     * @param maxnumline
     *            最长行的字符个数
     * @return 每行数据的集合 发生异常返回null
     */
    public static List<String> readFileNioByLine(String filepath, ByteBuffer rbuffer, int maxnumline) {
        // 文件不存在或不是文件 返回null
        if (!new File(filepath).exists() || !new File(filepath).isFile()) {
            return null;
        }

        // 返回数据
        List<String> retStrList = new ArrayList<String>();
        try (FileInputStream fis = new FileInputStream(filepath);) {
            // 获取文件管道
            FileChannel fc = fis.getChannel();
            // 表示按行分割的标识 window:\r\n 13 10 linux/unix:\r 13 mac:\n 10
            byte changeLine = 10;
            if (OS.IS_LINUX) {
                changeLine = 10;
            }
            // 换行符前面字符组合，可解决乱码
            ByteBuffer elseBuffer = ByteBuffer.allocate(maxnumline * 3);
            // 将缓冲区中数据读到字符数组中
            byte[] bs = null;
            // 从管道中循环读取数据
            while (fc.read(rbuffer) != -1) {
                // 根据读取到的字节数确定即将获取的字节数组大小
                bs = new byte[rbuffer.position()];
                rbuffer.rewind();
                // 相对读，从position位置读取一个byte[]
                rbuffer.get(bs);
                // 清楚缓冲区，供下次使用
                rbuffer.clear();
                for (byte b : bs) {
                    // 遭遇换行符
                    if (b == changeLine) {
                        // 字符缓冲区位置，用于确定字节数组大小
                        int byteSize = elseBuffer.position();
                        // 确定出字节数组大小
                        byte[] byteLine = new byte[byteSize];
                        // position=0 准备读取数据
                        elseBuffer.rewind();
                        // 从position开始读取byteLine.length个字节
                        elseBuffer.get(byteLine);
                        // 如果是 剔除 /r 前的 /n 后转换为字符串
                        String line = new String(byteLine, 0, OS.IS_WIN64 ? byteLine.length - 1 : byteLine.length);
                        // 添加到返回集合中
                        retStrList.add(line);
                        elseBuffer.clear();
                        continue;
                    }
                    try {
                        // 缓存住未匹配上的数据
                        elseBuffer.put(b);
                        // 由于提前确定的字节缓冲区太小导致错误
                    } catch (BufferOverflowException e) {
                        return null;
                    }

                }
            }
            // 循环完毕，处理剩余数据,注释同上
            if (elseBuffer.position() > 0) {
                int byteSize = elseBuffer.position();
                byte[] byteLine = new byte[byteSize];
                elseBuffer.rewind();
                elseBuffer.get(byteLine);
                String line = new String(byteLine, 0, byteLine.length);
                retStrList.add(line);
                elseBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return retStrList;
    }
}
