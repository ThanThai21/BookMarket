package com.esp.bookmarket.network;

public class API {

    public static <ServiceClass> ServiceClass createServicce(Class<ServiceClass> serviceClass) {
        return RetrofitClient.getClient().create(serviceClass);
    }



}
