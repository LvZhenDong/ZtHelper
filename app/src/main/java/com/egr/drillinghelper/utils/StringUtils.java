package com.egr.drillinghelper.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

import com.egr.drillinghelper.common.MyConstants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {


    /**
     * 验证是否为手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (mobiles.isEmpty() || mobiles.length() != 11) {
            return false;
        }

        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 验证是否为身份证号
     * @param idCardNo
     * @return
     */
    public static  boolean isIdCard(String idCardNo){

        if (idCardNo.isEmpty()) {
            return false;
        }
        String regexp = "(^d{15}$)|(^d{17}([0-9]|X)$)";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(idCardNo);

        return m.matches();
    }


    public static String decimalFormat(double price) {
        DecimalFormat df = new DecimalFormat("#########0.00");
        return df.format(price);

    }


    public static SpannableString getPriceSpannableString(double price) {


        String value = "￥" + decimalFormat(price);
        SpannableString ss = new SpannableString(value);
        if (value.contains(".")) {
            String[] temp = value.split("\\.");
            ss.setSpan(new RelativeSizeSpan(1.6f), 1, temp[0].length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return ss;

    }

    /**
     * 获取指定HTML标签的指定属性的值
     * @param source 要匹配的源文本
     * @param element 标签名称
     * @param attr 标签的属性名称
     * @return 属性值列表
     */
    public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    /**
     * @param htmlStr  html文本
     * @param searchTag  要修改的目标标签
     * @param searchAttrib  目标标签中的属性
     */
    public static String updateHtmlTag(String htmlStr, String searchTag,
                                       String searchAttrib) {
        String regxpForTag ="<\\s*" + searchTag + "\\s+([^>]*)\\s*>";
        String regxpForTagAttrib = searchAttrib + "\\s*=\\s*[\"|']http://([^\"|']+)[\"|']";//"=[\"|']([^[\"|']]+)[\"|']";
        Pattern patternForTag = Pattern.compile(regxpForTag);
        Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
        Matcher matcherForTag = patternForTag.matcher(htmlStr);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        while (result) {
            StringBuffer sbreplace = new StringBuffer("<"+searchTag +" ");
            System.out.println(matcherForTag.group(1));
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag
                    .group(1));

            if (matcherForAttrib.find()) {
                String path=matcherForAttrib.group(1);
                String[] strs=path.split("/");
                String name= MyConstants.PATH+strs[strs.length-1];
                matcherForAttrib.appendReplacement(sbreplace, searchAttrib+"=\"" +"file://"+name+"\"");
            }
//            matcherForTag.appendReplacement(sb, sbreplace.toString());
            matcherForAttrib.appendTail(sbreplace);
            matcherForTag.appendReplacement(sb, sbreplace.toString()+">");
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }

}
