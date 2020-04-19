package com.eale.concurrent.example.publish;

import com.eale.concurrent.annoations.NotRecommend;
import com.eale.concurrent.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    // 推荐工厂方法和私有构造方法

    public Escape() {
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
