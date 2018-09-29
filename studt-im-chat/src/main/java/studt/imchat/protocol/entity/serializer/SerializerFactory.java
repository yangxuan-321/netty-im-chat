//package studt.imchat.protocol.entity.serializer;
//
///**
// * @author Kevin
// * @Title: SerializerFactory
// * @ProjectName studyjava
// * @Description: TODO
// * @date 2018/9/29 20:11
// */
//public class SerializerFactory {
//    private static Object initLock = new Object();
//    private static String className = "com.jivesoftware.forum.database.DbForumFactory";
//    private static ForumFactory factory = null;
//    public static ForumFactory getInstance(Authorization authorization) {
//        //If no valid authorization passed in, return null.
//        if (authorization == null) {
//            return null;
//        }
//        //以下使用了Singleton单态模式
//        if (factory == null) {
//            synchronized(initLock) {
//                if (factory == null) {
//                    try {
//                        //动态转载类
//                        Class c = Class.forName(className);
//                        factory = (ForumFactory)c.newInstance();
//                    }catch (Exception e) {
//                        return null;
//                    }
//                }
//            }
//        }
//        //Now, 返回 proxy.用来限制授权对forum的访问
//        return new ForumFactoryProxy(authorization, factory,
//                factory.getPermissions(authorization));
//    }
//    //真正创建forum的方法由继承forumfactory的子类去完成.
//    public abstract Forum createForum(String name, String description)
//            throws UnauthorizedException, ForumAlreadyExistsException;
//}
