package com.csii.ljj.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

/**
 * @author egberd
 * @title: AESCBCUtils
 * @projectName loan-batch-parent
 * @description: AES可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 * @date 2019-11-14 20:11
 */
public class AESCBCUtils {
    
    private static Logger logger = LoggerFactory.getLogger(AESCBCUtils.class);
    // 加密方式
    public static final String KEY_ALGORITHM = "AES";
    // 加密模式为ECB，填充模式为NoPadding
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";
    // 字符集
    public static final String ENCODING = "UTF-8";
    
    /**
     * AES加密算法
     *
     * @param str  原文
     * @param key  16位key值
     * @param inIv 传来的向量
     * @return 错误：null 正常：加密后的String
     */
    public static String encrypt(String str, String key, String inIv) {
        try {
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(inIv) || key.length() != 16) {
                logger.warn("AES加密出错:key值[{}]或向量[{}]为空，或长度非16位",key,inIv);
                return null;
            }
            byte[] raw = key.getBytes(ENCODING);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(inIv.getBytes(ENCODING));
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] srawt = str.getBytes(ENCODING);
            int len = srawt.length;
            /* 计算补空格后的长度 */
            while (len % 16 != 0)
                len++;
            byte[] sraw = new byte[len];
            /* 在最后空格 */
            for (int i = 0; i < len; ++i) {
                if (i < srawt.length) {
                    sraw[i] = srawt[i];
                } else {
                    sraw[i] = 32;
                }
            }
            byte[] encrypted = cipher.doFinal(srawt);
            return formatString(new String(Base64.encodeBase64(encrypted), ENCODING));
        } catch (Exception ex) {
            logger.error("AES加密出错：" + ex.toString());
            return null;
        }
    }
    
    /**
     * AES解密算法
     *
     * @param str  原文
     * @param key  16位key值
     * @param inIv 传来的向量
     * @return 错误：null 正常：加密后的String
     */
    public static String decrypt(String str, String key, String inIv) {
        try {
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(inIv) || key.length() != 16) {
                logger.warn("AES解密出错:key值[{}]或向量[{}]为空，或长度非16位",key,inIv);
                return null;
            }
            byte[] raw = key.getBytes(ENCODING);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(inIv.getBytes(ENCODING));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] bytes = Base64.decodeBase64(str.getBytes(ENCODING));
            bytes = cipher.doFinal(bytes);
            return new String(bytes, ENCODING);
        } catch (Exception ex) {
            logger.error("AES解密出错：" + ex.toString());
            return null;
        }
    }
    
    private static String formatString(String sourceStr) {
        if (StringUtils.isEmpty(sourceStr)) {
            return null;
        }
        return sourceStr.replaceAll("\\r", "").replaceAll("\\n", "");
    }
    
    public static void main(String[] args) throws Exception {

        //
        String trnDt = "20250501";
        String sourceFilePath = "C:\\Users\\ljj\\Desktop\\BBG\\联合贷产品\\天翔20191030\\周璇\\test\\20191211测试数据\\"+trnDt;
        String targetFilepath = "C:\\Users\\ljj\\Desktop\\BBG\\联合贷产品\\天翔20191030\\周璇\\test\\20191211测试数据\\target\\"+trnDt;

        File targetDir = new File(targetFilepath);
        if (!targetDir.exists()){
            targetDir.mkdirs();
        }
        File dir = new File(sourceFilePath);
        if (dir.isDirectory()){
            System.out.println(true);
            String[] fileNames = dir.list();
//            for (String fileName : fileNames) {
//                System.out.println(fileName);
//            }

            File[] files = dir.listFiles();
            for (File file : files ) {
                System.out.println("======"+file.getName());
                // 写文件的全路径名
                String targetFile = String.format(targetFilepath+"\\"+"%s",file.getName());

                BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile,false),"utf-8"));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while((line = br.readLine())!=null){
                    System.out.println(line);
                    sb.append(line);

                }
                String txKeyIv = "8f01f2c963a44a7c";
                String decrypt = AESCBCUtils.decrypt(sb.toString(), txKeyIv, txKeyIv);
                sb.delete(0,sb.length());
                System.out.println(decrypt);

                bw.write(decrypt);
                bw.flush();
                bw.close();
                // 关闭该文件的流，清除StringBuffer
                br.close();

            }

        }




        String txFileJson = "fuXETbkH3aIX3VEWYxMdOohuBRTdD4rl9Yug/bTKl/qn9XExLZVBllJTz5UBbNUBAHC94cgpOk4RW6Bolop4iVkCg1ke3t6GcygTiKZzvcrDka50SF49RRp4EjkqBlZfe7HqiKBK+mOPnrw/yOocN/XEODXFgGH+xiUMrh+XBjIFTrIzTSsu5S2ukFYsAMgWHzvmkDHB2voinOlqrDbT/btSll0kCgAJ/yT8pzmSZK6ctaEqKi4mWhlLDUU1NyH85VzeYSzShL8NTfnScVLZO5t2Q1vgX9J8Y5Ik1P/kc2pUE4yTE9sIejEc6PoXOQpYO5J38j0GFUPrO/21qwnrt3XXWSdb2DzWU2msDQ7mQ+abGyYaYqbcJCgUHUoz4cfzPrJ8faJpDrb2JcAotMtxeQIDpguQmel13NKOtQgRME0Rf3eVtdoKQzTXn1p9sHs0hwceShuyt1eBWqMkVmpzXXvTTacYra9AjZt5+poaPCp6BChLh/3hNgw3I6ZG5hHNOvfbo8li3YoWd/wn7f5w2w==";

    }
}