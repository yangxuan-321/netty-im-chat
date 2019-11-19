package com.startup.contextfilter.parse;

import com.alibaba.fastjson.JSON;
import com.startup.contextfilter.pojo.FilterDescribe;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kevin
 * @Title : ContextXMLParser
 * @ProjectName netty-im-chat
 * @Description : TODO
 * @Time : Created in 2019/11/20 1:00
 * @Modifyed By :
 */
public class ContextFilterXMLParser extends DefaultHandler {

    int bookindex = 1;
    public static List<FilterDescribe> filterDescribes = null;
    //定义全局变量是为了使book对象和value值可以被多个方法访问
    FilterDescribe filterDescribe = null;
    String value = null;


    /**
     * 用来遍历xml文件的开始标签
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        //调用DefaultHandler类的startElement方法
        super.startElement(uri, localName, qName, attributes);
        //开始解析book元素的属性
        if (qName.equals("filter")) {
            filterDescribe = new FilterDescribe();
        }
    }

    /**
     * 用来遍历xml文件的结束标签
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
        if (qName.equals("filter")) {
            //把信息存入book对象之后，加到ArrayList之中
            filterDescribes.add(filterDescribe);
            //将book对象清空
            System.out.println(JSON.toJSON(filterDescribes));
            filterDescribe = null;
        }
        else if (qName.equals("display-name")) {
            filterDescribe.setFilterName(value);
        } else if (qName.equals("filter-name")) {
            filterDescribe.setDisplayName(value);
        } else if (qName.equals("filter-class")) {
            filterDescribe.setFilterClass(value);
        } else if (qName.equals("url-pattern")) {
            filterDescribe.setUrlPattern(value);
        }
    }

    /**
     * 用来表示解析开始
     */
    @Override
    public void startDocument() throws SAXException {
        filterDescribes = new ArrayList<FilterDescribe>();
    }

    /**
     * 用来表示解析结束
     */
    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
        System.out.println("SAX解析结束");
    }

    /**
     * 用来求得节点值
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value = new String(ch, start, length);
    }
}
