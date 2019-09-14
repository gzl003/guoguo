package com.yinxiang.mylibrary;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;

import org.xml.sax.XMLReader;

public class StringTest {

    public static void main(String[] args) {
        String mText = "是看结束[t1]你好[/t1]asdasd[t2]你好你好f[/t2]观看";
        Spanned spanned = Html.fromHtml(mText, new GuoImgGetter(), new GueTagHandler());

    }

    static class GuoImgGetter implements Html.ImageGetter {

        /**
         * This method is called when the HTML parser encounters an
         * &lt;img&gt; tag.  The <code>source</code> argument is the
         * string from the "src" attribute; the return value should be
         * a Drawable representation of the image or <code>null</code>
         * for a generic replacement image.  Make sure you call
         * setBounds() on your Drawable if it doesn't already have
         * its bounds set.
         *
         * @param source
         */
        @Override
        public Drawable getDrawable(String source) {
            /*在这里根据source来加载图片，并返回*/
            /*简单测试，返回程序ic_lanucher*/
            /*网络图片需要异步加载，在此处发起异步加载线程，图片加载完成后再设置一次setText，
                当再次执行到此处时，将加载好的图片（应存放在缓存中）返回就行了*/
            Drawable drawable = null;
            if(Build.VERSION.SDK_INT < 21){
                drawable = getDrawable(String.valueOf(R.drawable.ic_launcher));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
            }else{
                drawable = getDrawable(String.valueOf(R.drawable.ic_launcher));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
            }

            return drawable;
        }
    }
    static class GueTagHandler implements Html.TagHandler{

        @Override
        public void handleTag(boolean b, String s, Editable editable, XMLReader xmlReader) {
            if(s.equalsIgnoreCase("[t1]")){
                System.out.println(s);
            }
            if(s.equalsIgnoreCase("[t2]")){
                System.out.println(s);
            }
        }
    }
}
