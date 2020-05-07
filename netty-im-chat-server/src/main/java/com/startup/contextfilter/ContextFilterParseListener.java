package com.startup.contextfilter;

import com.netty.imchat.util.exception.AppException;
import com.startup.contextfilter.parse.ContextFilterXMLParser;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Kevin
 * @Title: PropertiesListener
 * @ProjectName bus_platform
 * @Description: 加载配置文件内容
 * @date 2019/11/19 16:17
 */
public class ContextFilterParseListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        try {
            // 是创建一个Handler处理类，去逐个分析每一个节点，并且是顺序的

            //1.通过SAXParseFactory的静态方法newInstance()来获取SAXParseFactory的实例对象factory
            SAXParserFactory factory = SAXParserFactory.newInstance();

            //2.通过SAXParserFactory 静态方法NewSAXParser()方法返回一个SAXParser类的实例；
            ContextFilterXMLParser handler = new ContextFilterXMLParser();
            //3.重写startElement、endElement、startDocument、endDocument方法
            /*
             * 可以将其保存在继承了DefaultHandler类的SAXParserHandler类中
             * 然后创建SAXParserHandler类的对象并将其传入SAXParser类的对象parser的parser方法；
             */
            SAXParser parser=factory.newSAXParser();
            String path = ContextFilterXMLParser.class.getResource("context/context-filter.xml").getPath();
            parser.parse(path, handler);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }
}
