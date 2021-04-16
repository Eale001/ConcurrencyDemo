package com.eale.test;

import javafx.beans.binding.StringBinding;

/**
 * @Author Administrator
 * @Date 2021/4/8
 * @Description //
 *
 * 3.一副牌在手里，每次从牌堆顶取一张放桌子上，再取一张放手中的牌堆底，直到手中没牌，
 * 最后桌子上的牌是int[n]，请编码实现通过 int[n]反推出原来手中纸牌的顺序的逻辑。
 * 比如：最后桌子上的牌是[1,2,3]，反推出原来手中的牌是[3,1,2]（数组顺序对应牌堆自上而
 * 下的顺序）
 * @Version 1.0
 **/
public class Test2 {


    public String backNumberOrder(int[] numbers){

        StringBuilder builder = new StringBuilder();
        StringBuilder backBuilder = new StringBuilder();

        int n = numbers.length;
        int b[] = new int[n];
        int t=n-1;//新牌堆顶数组索引
        int u=0;//原牌堆顶数组索引
        int m=0;//原牌堆底数组索引，为了实现循环数组
        //共l个步骤
        for(int l=0;l<n;l++) {
            b[t]= numbers[u];//将当前原牌堆顶的牌放入新牌堆顶
            t--;//新牌堆顶上移
            numbers[m]=numbers[(u+1)%n];//将当前原牌堆顶的牌放入原牌堆底，利用循环数组,节省空间。
            m++;
            u=(u+2)%n;
        }
        //按顺序输出新牌堆里，原牌堆的标记，同时计算出原牌堆对应的数值
        for(int h=0;h<n;h++) {
            numbers[b[h]-1]= h+1;
            builder.append(b[h]);
        }
        System.out.println();
        /*for(int p=n-1;p >=0 ;p--) {
            System.out.print(numbers[p]);
        }*/
        System.out.println();
        for (int number : numbers) {
            backBuilder.append(number);
        }
        return backBuilder.toString();
    }
}
