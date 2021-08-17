package morning.cat.encode;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @describe: 编码
 * @author: morningcat.zhang
 * @date: 2021/8/17 下午10:25
 */
public class EncodeTest1 {
    public static void main(String[] args) throws IOException {

        /**
         * gb2312 < gbk < gb18030
         * 即 gbk 兼容 gb2312 ，gb18030 兼容 gbk
         */
        String gb2312 = "gb2312.txt";
        String gbk = "gbk.txt";
        String gb18030 = "gb18030.txt";

        /**
         * unicode 编码 （万国码）
         * 其中汉字部分可查询 {http://www.chi2ko.com/tool/CJK.htm}
         * 你好 4F60 597D
         *
         * unicode 转换为 utf-8 {https://www.ruanyifeng.com/blog/2007/10/ascii_unicode_and_utf-8.html}
         * 你好 E4BDA0 E5A5BD
         * 转换过程 查看readme.md
         */
        String utf8 = "utf8.txt"; //
        String utf16 = "utf16.txt"; // FEFF 开头，表示为大端模式
        String utf16le = "utf16le.txt"; // FFFE 小端

        // 编码 ： 由 【字符流或对象】 转换成 【字节流】 的过程
        System.out.println(Hex.encodeHexString(FileUtils.readFileToByteArray(new File(gb2312))).toUpperCase());
        System.out.println(Hex.encodeHexString(FileUtils.readFileToByteArray(new File(gbk))).toUpperCase());
        System.out.println(Hex.encodeHexString(FileUtils.readFileToByteArray(new File(gb18030))).toUpperCase());
        //
        byte[] bytes = FileUtils.readFileToByteArray(new File(utf8));
        System.out.println(Hex.encodeHexString(bytes).toUpperCase());
        //
        System.out.println(Hex.encodeHexString(FileUtils.readFileToByteArray(new File(utf16))).toUpperCase());
        System.out.println(Hex.encodeHexString(FileUtils.readFileToByteArray(new File(utf16le))).toUpperCase());

        // 解码 ： 由 【字节流】转换成 【其他格式】 的过程
        String str = new String(bytes, Charset.forName("utf-8"));
        System.out.println(str);
        System.out.println(new String(FileUtils.readFileToByteArray(new File(gbk)), Charset.forName("gbk")));


    }
}
