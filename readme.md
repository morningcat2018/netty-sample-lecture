


## 映射表

```
 Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
   
```

[上述信息来自 RFC3629](https://www.ietf.org/rfc/rfc3629.txt)

## 【你好】的转换过程

你 [4F60] 好 [597D]

---

参照映射表得出：

0100111101100000 ==> `1110`0100 `10`111101 `10`100000

0101100101111101 ==> `1110`0101 `10`100101 `10`111101

再转换成十六进制：你 [E4BDA0] 好 [E5A5BD]


[转换过程参考:字符编码笔记：ASCII，Unicode 和 UTF-8](https://www.ruanyifeng.com/blog/2007/10/ascii_unicode_and_utf-8.html)