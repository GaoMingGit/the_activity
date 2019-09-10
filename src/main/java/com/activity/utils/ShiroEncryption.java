package com.activity.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroEncryption {


        /**
         * 用shiro中提供的散列加密方法为用户的密码进行加密
         * @param originalPwd 代表是用户在注册表单输入的原始密码
         * @param username 代表盐值，盐值要唯一，由于现实开发中用户的账号是唯一的，所以这里的盐值用用户的账号
         * @return 返回加密之后的密码，存进数据库
         */
        public static String shiroEncryptionPwd(String originalPwd,String username){
            //散列算法需要四个参数
            //  1.指明算法的名称是  MD5
            //  2.需要用户注册表单填写的原始密码
            //  3.盐值，由于不同用户一旦拥有相同的秘密，加密之后的字段会一样，为了避免这个情况，加入了用户账号这个盐值
            //  4.加密次数相当于 md5(md5(md5("")))进行1024次,=================>这个参数可以写定1024次，也可以用户自己定义次数
            //用用户账号生产盐值
            Object salt = ByteSource.Util.bytes(username);
            SimpleHash simpleHash = new SimpleHash("MD5",originalPwd,salt,1024);
            String endPwd = simpleHash.toString();
            return endPwd;
        }


}
