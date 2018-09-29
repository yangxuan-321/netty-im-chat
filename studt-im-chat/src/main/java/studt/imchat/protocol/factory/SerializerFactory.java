package studt.imchat.protocol.factory;

import studt.imchat.protocol.entity.serializer.Serializer;
import studt.imchat.protocol.enums.SerializerEnum;
import studt.imchat.util.AppException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * @Title: SerializerFactory
 * @ProjectName studyjava
 * @Description: TODO  工厂
 * @date 2018/9/29 20:11
 */
public class SerializerFactory {

    private static final Map<SerializerEnum, Serializer> serializerMap = new HashMap<SerializerEnum, Serializer>();

    private static Object initLock = new Object();

    /**
     * 返回指定
     * @param serializerEnum
     * @return
     */
    public static Serializer createSeralizer(SerializerEnum serializerEnum){
        if(null == serializerEnum){
            throw new AppException();
        }

        synchronized(initLock) {
            Serializer serializer = serializerMap.get(serializerEnum);
            if(null != serializer){
                return serializer;
            }

            Class<? extends Serializer> clazz = serializerEnum.getClazz();
            try {
                serializer = clazz.newInstance();
                serializerMap.put(serializerEnum, serializer);
            }catch (Exception e){
                e.printStackTrace();
            }
            return serializer;
        }
    }

    /**
     * 返回默认
     * @return
     */
    public static Serializer defaultSeralizer(){
        synchronized(initLock) {
            Serializer serializer = serializerMap.get(SerializerEnum.JSON);
            if(null != serializer){
                return serializer;
            }

            try {
                serializer = SerializerEnum.JSON.getClazz().newInstance();
                serializerMap.put(SerializerEnum.JSON, serializer);
            }catch (Exception e){
                e.printStackTrace();
            }
            return serializer;
        }
    }

}
