//package com.netty.imchat.context;
//
//import com.sun.rowset.internal.XmlErrorHandler;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXParseException;
//
///**
// * @author : Kevin
// * @Title : ContextConfig
// * @ProjectName netty-im-chat
// * @Description : TODO
// * @Time : Created in 2019/3/6 1:37
// * @Modifyed By :
// */
//public class ContextConfig {
//    protected void parseWebXml(InputSource source, WebXml dest,
//                               boolean fragment) {
//
//        if (source == null){
//            return;
//        }
//
//        XmlErrorHandler handler = new XmlErrorHandler();
//
//        Digester digester;
//        WebRuleSet ruleSet;
//        if (fragment) {
//            digester = webFragmentDigester;
//            ruleSet = webFragmentRuleSet;
//        } else {
//            digester = webDigester;
//            ruleSet = webRuleSet;
//        }
//
//        digester.push(dest);
//        digester.setErrorHandler(handler);
//
//        if(log.isDebugEnabled()) {
//            log.debug(sm.getString("contextConfig.applicationStart",
//                    source.getSystemId()));
//        }
//
//        try {
//            digester.parse(source);
//
//            if (handler.getWarnings().size() > 0 ||
//                    handler.getErrors().size() > 0) {
//                ok = false;
//                handler.logFindings(log, source.getSystemId());
//            }
//        } catch (SAXParseException e) {
//            log.error(sm.getString("contextConfig.applicationParse",
//                    source.getSystemId()), e);
//            log.error(sm.getString("contextConfig.applicationPosition",
//                    "" + e.getLineNumber(),
//                    "" + e.getColumnNumber()));
//            ok = false;
//        } catch (Exception e) {
//            log.error(sm.getString("contextConfig.applicationParse",
//                    source.getSystemId()), e);
//            ok = false;
//        } finally {
//            digester.reset();
//            ruleSet.recycle();
//            InputSourceUtil.close(source);
//        }
//    }
//}
