package com.netty.imchat.common.splitpack;

import com.netty.imchat.common.util.PacketCodeUtil;
import com.netty.imchat.util.constant.Constant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author Kevin
 * @Title: SplitPackage
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2019/1/21 20:41
 */
public class SplitPackageDecoder extends LengthFieldBasedFrameDecoder {
    /**
     * 自定义 描述宝长度 的位置 字节偏移量 ：
     *  魔数(4) + 版本(1) + 算法协议(1) + 命令(1) = 7
     *  具体请看 PacketCodeUtil 这个类
     */
    private static final int LENGTH_FIELD_OFFSET = 7;
    /**
     * 描述包的长度 的大小  是一个int  所以 = 4
     */
    private static final int LENGTH_FIELD_LENGTH = 4;

    /**
     * 制定拆包的规则(因为有了包的长度，如果发现包的长度不够，就等待下一个ByteBuf的到来)
     */
    public SplitPackageDecoder(){
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端 -- 如果协议版本不对直接关闭连接
        if (in.getInt(in.readerIndex()) != PacketCodeUtil.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
