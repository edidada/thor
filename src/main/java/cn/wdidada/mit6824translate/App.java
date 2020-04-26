package cn.wdidada.mit6824translate;

import cn.wdidada.mit6824translate.utils.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: WuCheng
 * @create: 2020-04-26 15:13
 **/

public class App {
    public static void main(String[] args) {

        StringUtils sUtils = new StringUtils();
        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
        File f = new File(".");
        System.out.println(f.getAbsolutePath());
        for(File subFile :f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname == null || !pathname.exists() || pathname.isFile() || pathname.getAbsolutePath() == null){
                    return false;
                }
                return pathname.getAbsolutePath().contains("lec");
            }
        })){

//            System.out.println(subFile.getName());
            for(File target:subFile.listFiles()){
                if(target.getName().endsWith(".srt")){
                    try {
                        List<String> lines = FileUtils.readLines(target, "UTF-8");

                        List<String> targetContent = new ArrayList<String>();
                        String pre= null;
                        for(String context : lines){
                            if(sUtils.isChinese(context)){
                                if(!sUtils.isEmpty(pre) && !pre.equals(context)){
                                    targetContent.add(context);
                                }
                            }
                            pre = context;
                        }
                        File targetChineseFile = new File(subFile.getAbsoluteFile() + File.separator + "chinese.txt");
                        if(targetChineseFile.exists()){
                            targetChineseFile.delete();
                        }

                        if(targetContent.size()>0){
                            FileUtils.writeLines(targetChineseFile,targetContent,true);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        }


    }
}
