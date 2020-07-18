package com.shard.jdbc.config;

public class DataSourceConstant {
    public static Integer DATABASE_COUNT=3;
    public static Integer TABLE_COUNT=23;
    public static String get(){
        String s="ds_${0..%s}.table_one_${0..%s}";
        String endD=String.valueOf(DATABASE_COUNT-1);
        String table=String.valueOf(TABLE_COUNT-1);
        System.out.println(String.format(s,endD,table));
        return String.format(s,endD,table);
    }
}
