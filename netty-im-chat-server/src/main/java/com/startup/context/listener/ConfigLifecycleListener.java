/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.startup.context.listener;

import com.startup.context.annotation.Listener;
import com.startup.context.subject.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;

/**
 * 读取配置文件的生命周期的具体监听器
 */
@Listener
public class ConfigLifecycleListener implements LifecycleListener {

    private static final Logger log = LoggerFactory.getLogger(ConfigLifecycleListener.class);

    public ConfigLifecycleListener(){

    }

    @Override
    public void lifecycleEvent(LifecycleEvent event) {

        try {
//            context = (Context) event.getLifecycle();
        } catch (ClassCastException e) {
//            log.error(sm.getString("contextConfig.cce", event.getLifecycle()), e);
            return;
        }

        if (event.getType().equals(Lifecycle.START_EVENT)) {
            //TODO
        } else if (event.getType().equals(Lifecycle.INIT_EVENT)) {

        } else if (event.getType().equals(Lifecycle.CONFIG)) {
            //初始化配置
            initConfig();
            //只初始化一次 -- 移除观察者
            event.getLifecycle().removeLifecycleListener(this);
        } else if (event.getType().equals(Lifecycle.STOP_EVENT)) {
            //TODO
        } else {
            // boom
            // TODO
        }

    }

    private void initConfig() {
    }

    private void parseConfigXml(){
        //1、获取一个SAXParserFactory的实例
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            //2、通过factory获取SAXParser的实例
            SAXParser parser = factory.newSAXParser();
            //3、创建SAXParserHandler对象
            SAXParserHandler handler = new SAXParserHandler();
            parser.parse("book.xml", handler);
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
    }

    protected InputSource getContextSource() {
        //TODO
        return new FileInputStream();
    }

//    protected void parseContextXml(InputSource source, ContextXml dest) {
//
//    }

    public class SAXParserHandler extends DefaultHandler {
        int bookindex = 1;
        //定义全局变量是为了使book对象和value值可以被多个方法访问
        Book book = null;
        String value = null;
        private ArrayList<Book> bookList = new ArrayList<>();

        public ArrayList<Book> getBookList() {
            return bookList;
        }

        /**
         * 用来遍历xml文件的开始标签
         */
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            //调用DefaultHandler类的startElement方法
            super.startElement(uri, localName, qName, attributes);
            //开始解析book元素的属性
            if (qName.equals("book")) {
                //每一次遇到book就建立新的book对象
                book = new Book();
                System.out.println("------------现在开始遍历第" + bookindex + "本书---------");
                //已知book元素下属性的名称，根据名称获取属性值
                String value = attributes.getValue("id");
                System.out.println("book的属性值是：" + value);
                //不知道book元素下属性的名称以及个数，如何获取元素名称及属性
                int num = attributes.getLength();
                for (int i = 0; i < num; i++) {
                    System.out.print("第" + (i + 1) + "个book元素的属性名是" + attributes.getQName(i));
                    System.out.println("----book元素的属性值是" + attributes.getValue(i));
                    if (attributes.getQName(i) == "id") {
                        book.setId(attributes.getValue(i));
                    }
                }
            } else if (!qName.equals("book") && !qName.equals("bookstore")) {
                System.out.print("节点名是" + qName);
            }
        }

        /**
         * 用来遍历xml文件的结束标签
         */
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            // TODO Auto-generated method stub
            super.endElement(uri, localName, qName);
            if (qName.equals("book")) {
                //把信息存入book对象之后，加到ArrayList之中
                bookList.add(book);
                //将book对象清空
                book = null;
                System.out.println("------------结束遍历第" + bookindex++ + "本书---------");
            } else if (qName.equals("name")) {
                book.setName(value);
            } else if (qName.equals("id")) {
                book.setId(value);
            } else if (qName.equals("year")) {
                book.setYear(value);
            } else if (qName.equals("money")) {
                book.setMoney(value);
            } else if (qName.equals("color")) {
                book.setColor(value);
            } else if (qName.equals("auther")) {
                book.setAuther(value);
            }
        }

        /**
         * 用来表示解析开始
         */
        @Override
        public void startDocument() throws SAXException {
            // TODO Auto-generated method stub
            super.startDocument();
            System.out.println("SAX解析开始");
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
            if (!value.trim().equals(""))
                System.out.println("节点值为" + value);
        }
    }
}

