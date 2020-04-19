package com.eale.concurrent.example.publish;

import com.eale.concurrent.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] status = {"a","b","c"};

    public String[] getStatus(){
        return status;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStatus()));

        unsafePublish.getStatus()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStatus()));
    }

}
