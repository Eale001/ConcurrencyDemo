package com.eale.classload;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @Author HLD
 * @Date 2021/7/12 0012
 **/
public class ClassLoader {


    public static void main(String[] args) {

        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }

        System.out.println(ClassLoader.class.getClassLoader());

    }


}
