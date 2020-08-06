package com.keven.rpc;

//声明这个注解之后，自动发布服务（非注解方式需去掉注解）
@KevenRemoteService
public class OrderServiceImpl implements IOrderService{

    @Override
    public String queryOrderList() {
        return "EXECUTE QUERYORDERLIST METHOD";
    }

    @Override
    public String orderById(String id) {
        return "EXECUTE ORDER_BY_ID METHOD";
    }
}
