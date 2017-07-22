package io;

import base.IO.log.Log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 图像管理器，每个图像管理器包含文件输入流和一张表
 *
 * Created by zyvis on 2017/3/5.
 */
public class ImageManager {

    private String[] picsFormatFilter;

    public Hashtable<String, BufferedImage> getImageTable() {
        return imageTable;
    }

    private Hashtable<String, BufferedImage> imageTable=new Hashtable<>();
    private FileInputStream fileInputStream;

    public ImageManager() {
        picsFormatFilter=new String[]{".png",".jpg"};
    }

    /**
     * 从文件系统中读取文件，同文件夹下有一个默认图像文件
     * 在数据结构中储存为一系列的键值对
     *
     * @param key           key（可以为空）
     * @param fileString    文件路径 : src/io/imgsrc/actor.jpg
     * @throws IOException
     */
    public synchronized void importImage(String key,String fileString) throws IOException {
        for(String e:picsFormatFilter) {
            if(fileString.endsWith(e)){
                fileInputStream=new FileInputStream(fileString);
                BufferedImage tmp= ImageIO.read(fileInputStream);
                if(key==null)key=fileString;
                imageTable.put(key,tmp);
                //待修改
                fileInputStream.close();
                Log.d("imageTable.size()"+imageTable.size());
                return;

            }
        }
    }
    public synchronized void importImage(String fileString) throws IOException {
        this.importImage(null,fileString);
    }
    public BufferedImage get(String Key){
        try{
            return imageTable.get(Key);
        }
        catch (NullPointerException e){
            return null;
        }
    }
    public void changeKeyName(String origin,String newer)throws Exception{
        if(!imageTable.containsKey(origin))throw new Exception("origin key no found");
        if(origin.equals(newer))return;
        imageTable.put(newer,imageTable.get(origin));
        imageTable.remove(origin);

    }
}
