package io;

import base.IO.log.Log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * 图像管理器，每个图像管理器包含文件输入流和一张表
 *
 * Created by zyvis on 2017/3/5.
 */
public class ImageManager {


    public enum Type{
        ACTOR("actor"),TAG("tag"),BASE("base");
        String name;
        Type(String s){
            name=s;
        }
        public String getName(){
            return name;
        }
    }

    private HashMap<String,String> proxyTable;
    private String[] picsFormatFilter;

    public Hashtable<String, BufferedImage> getImageTable() {
        return imageTable;
    }

    private Hashtable<String, BufferedImage> imageTable=new Hashtable<>();
    private FileInputStream fileInputStream;

    public ImageManager() {
        proxyTable=new HashMap<>();
        proxyTable.put(Type.ACTOR.getName(),"actor");
        proxyTable.put(Type.TAG.getName(),"tag");
        proxyTable.put(Type.BASE.getName(),"base");
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
                if(key==null)key=getName(fileString);
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
            return imageTable.get(proxyTable.get(Key));
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
    public void changeTypePoint(Type type,String key){
        proxyTable.put(type.getName(),key);
    }
    public String getName(String filepath){
        int start=0;
        for(int i=0;i<filepath.length();i++){
            if(filepath.charAt(i)=='/'||filepath.charAt(i)=='\\'){
                start=i;
            }
        }
        int end=filepath.length();
        if(filepath.contains("."))end=filepath.indexOf(".");
        return filepath.substring(++start,end);
    }
}
