//package com.enwu.mb.config;
//
//import com.google.code.ssm.CacheFactory;
//import com.google.code.ssm.aop.CacheBase;
//import com.google.code.ssm.config.DefaultAddressProvider;
//import com.google.code.ssm.providers.CacheConfiguration;
//import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
///**
// * Created by user on 16/7/23.
// */
////@Configuration
//@PropertySource("classpath:spring/cache.properties")
//public class MemcacheConfig {
//
//    @Autowired
//    private Environment env;
//
////    @Bean
//    private DefaultAddressProvider getDefaultAddressProvider(){
////        return new DefaultAddressProvider(env.getProperty("memcache.servers"));
//        return new DefaultAddressProvider("localhost:11211");
//    }
//
////    @Bean
//    private CacheConfiguration getCacheConfiguration(){
//        CacheConfiguration cacheConfiguration = new CacheConfiguration();
//        cacheConfiguration.setConsistentHashing(true);
//        return cacheConfiguration;
//    }
//
////    @Bean
//    private MemcacheClientFactoryImpl getMemcacheClientFactoryImpl(){
//        return new MemcacheClientFactoryImpl();
//    }
//
////    @Autowired
////    @Bean
////    public CacheFactory getCacheFactory(CacheBase cacheBase){
////        CacheFactory cf = new CacheFactory();
////        cf.setAddressProvider(this.getDefaultAddressProvider());
////        cf.setConfiguration(this.getCacheConfiguration());
////        cf.setCacheClientFactory(this.getMemcacheClientFactoryImpl());
////
////        return cf;
////    }
//}
