package cn.wdidada.mit6824translate.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: WuCheng
 * @create: 2020-04-26 15:42
 **/

public class StringUtils {

    public boolean isChinese(String l){
        if(isEmpty(l)){
            return false;
        }
        l = l.replaceAll(" ", l);



        return isAllChinese(l);
    }

    public boolean isAllChinese(String str) {
        if(isEmpty(str)){
            return false;
        }
        for(Character c:str.toCharArray()){

            if(!isChinese(c)){
                return false;
            }
        }
        return true;
    }

    public  boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public boolean isEmpty(String pre) {
        if(pre == null || pre.length()==0){
            return true;
        }
        return false;
    }
}
