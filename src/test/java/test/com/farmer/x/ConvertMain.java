package test.com.farmer.x;

import com.farmer.x.utils.DecompilerUtils;

import java.io.*;

public class ConvertMain {


    private InputStreamReader isr = null;
    private OutputStreamWriter osw = null;
    private FileInputStream fis = null;
    private FileOutputStream fos = null;

    //对文件夹下的所有文件进行编译
    public void getAllFiles(String path, String suffix) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getAllFiles(f.getPath(),suffix);//递归
            } else {
                //对文件进行过滤
                if (f.getPath().endsWith(suffix)) {
                    changeCodeEcoding(f.getPath());
                }else{
                    //不进行转换直接复制
//                    System.out.println(f.getPath());
                    copyFile(f.getPath());
                }
            }
        }
    }

    //复制非后缀名的文件
    private void copyFile(String path) {
        try{
            fis = new  FileInputStream(path);
            String s = changePath(path);
            File file = new File(s);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                fos = new FileOutputStream(s);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = fis.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //将.class反编译为.java文件
    public void changeCodeEcoding(String path) {
        try {
            fis = new FileInputStream(path);
            String str = DecompilerUtils.decompile(fis);
            String s = changePath(path);
            //将path中的.class改为.java
            String replace = s.replace(".class", ".java");
            File file = new File(replace);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                fos = new FileOutputStream(replace);
                byte[] bytes = new byte[1024];
                bytes=str.getBytes();
                int b=bytes.length;   //是字节的长度，不是字符串的长度
                FileOutputStream fos=new FileOutputStream(file);
                fos.write(bytes,0,b);
                fos.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //将项目copy转换编码到同路径的文件夹下
    public String changePath(String path) {
        //建议替换项目的路径尽可能的长，来保证唯一性约束
        String newPath = path.replace("desktop\\contract", "desktop\\new_contract"); //将contract替换为new_contract
        System.out.println(newPath);
        return newPath;
    }

    public static void main(String[] args) {
        ConvertMain convertMain = new ConvertMain();
        convertMain.getAllFiles("D:\\desktop\\contract",".class");//反编译路径下的.class文件
    }
}
