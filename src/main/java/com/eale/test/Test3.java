package com.eale.test;

/**
 * @Author Administrator
 * @Date 2021/4/8
 * @Description //
 *
 * 写一个转换函数，能将一串阿拉伯数字转换成中文表达形式，要符合中文的习惯；比如，对
 * 于1002，习惯上一般说一千零二，而不是一千零零二
 * @Version 1.0
 **/
public class Test3 {

    /**
     * 中文数字
     */
    private static final String[] CN_NUM = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    /**
     * 中文数字单位
     */
    private static final String[] CN_UNIT = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};



    public String transformCn(long number){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while(number > 0) {
            sb.insert(0, CN_NUM[(int) (number % 10)] + CN_UNIT[count]);
            number = number / 10;
            count++;
        }
        return sb.toString().replaceAll("零[千百十]", "零").replaceAll("零+万", "万")
                .replaceAll("零+亿", "亿").replaceAll("亿万", "亿零")
                .replaceAll("零+", "零").replaceAll("零$", "")
                .replace("一十万","十万").replace("一十亿","十亿");
    }


}
