package com.arcsoft.arcfacedemo.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author logcat
 */
public class FileUtils {
    private List<String> contentList;

    private static final class Singleton {
        private static final FileUtils INSTANCE = new FileUtils();
    }

    public static FileUtils getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * 将字符串写入到文本文件中
     *
     * @param strcontent
     * @param filePath
     * @param fileName
     */
    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        strcontent = strcontent.replaceAll("\r", "");
        strcontent = strcontent.replaceAll("\n", "");
        String strFilePath = filePath + fileName;
        StringBuffer stringBuffer = new StringBuffer();
        File dirFile = new File(filePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            } 
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            stringBuffer.append("\r\n").append(strcontent);
            raf.write(stringBuffer.toString().getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    /**
     * 生成文件
     *
     * @param filePath
     * @param fileName
     * @return
     */
    private File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 生成文件夹
     *
     * @param filePath
     */
    private void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    /**
     * 读取指定目录下的所有TXT文件的文件内容
     *
     * @param file
     * @return
     */
    public List readContentList(File file, String suffix) {
        contentList = new ArrayList<>();
        //检查此路径名的文件是否是一个目录(文件夹)
        if (!file.isDirectory()) {
            //文件格式为""文件
            if (file.getName().endsWith(suffix)) {
                try {
                    InputStream instream = new FileInputStream(file);
                    if (instream != null) {
                        InputStreamReader inputreader
                                = new InputStreamReader(instream, "UTF-8");
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line = "";
                        //分行读取
                        while ((line = buffreader.readLine()) != null) {
                            contentList.add(line);
                        }
                        //关闭输入流
                        instream.close();
                    }
                } catch (FileNotFoundException e) {
                    Log.d("TestFile", "The File doesn't not exist.");
                } catch (IOException e) {
                    Log.d("TestFile", e.getMessage());
                }
            }
        }
        return contentList;
    }


    public String readContent(File file, String suffix) {
        String content = "";
        //检查此路径名的文件是否是一个目录(文件夹)
        if (!file.isDirectory()) {
            //文件格式为""文件
            if (file.getName().endsWith(suffix)) {
                try {
                    InputStream instream = new FileInputStream(file);
                    if (instream != null) {
                        InputStreamReader inputreader
                                = new InputStreamReader(instream, "UTF-8");
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line = "";
                        //分行读取
                        while ((line = buffreader.readLine()) != null) {
                            content += line + "\n";
                        }
                        //关闭输入流
                        instream.close();
                    }
                } catch (FileNotFoundException e) {
                    Log.d("TestFile", "The File doesn't not exist.");
                } catch (IOException e) {
                    Log.d("TestFile", e.getMessage());
                }
            }
        }
        return content;
    }
}
