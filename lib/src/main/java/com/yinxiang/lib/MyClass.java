package com.yinxiang.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {

    public static void main(String[] args) {
//        String s1 = "<a>111111</a><b>zwerwr423</b>";
        String s1 = "是看结束[t1]你好[/t1]asdasd[t2]你好你好f[/t2]观看";
        Pattern p = Pattern.compile("<(\\w+)([^\\[\\]]*)>([^\\[\\]]+)</\\1>");
        Matcher m = p.matcher(s1);
        while(m.find()){
            System.out.println(m.group(3));
        }

//        String mText = "";
//        Spanned spanned = Html.fromHtml(mText, new MyImageGetter(), new MyTagHandler());

    }
}
