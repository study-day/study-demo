package com.jin.eledger.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

public class EledgerUtils {

    /**
     * 		var shapeMap = new Map();
     * 		shapeMap.set("fangguanliao", [ {
     * 			"flag" : "changdu",
     * 			"name" : "长度"
     *                }, {
     * 			"flag" : "kuandu",
     * 			"name" : "宽度"
     *        }, {
     * 			"flag" : "gaodu",
     * 			"name" : "高度"
     *        }, {
     * 			"flag" : "neikuan",
     * 			"name" : "内宽"
     *        }, {
     * 			"flag" : "neigao",
     * 			"name" : "内高"
     *        } ]);
     * 		shapeMap.set("yuanbangliao", [ {
     * 			"flag" : "changdu",
     * 			"name" : "长度"
     *        }, {
     * 			"flag" : "waijing",
     * 			"name" : "外径"
     *        } ]);
     * 		//圆管料
     * 		shapeMap.set("yuanguanliao", [ {
     * 			"flag" : "changdu",
     * 			"name" : "长度"
     *        }, {
     * 			"flag" : "waijing",
     * 			"name" : "外径"
     *        }, {
     * 			"flag" : "neijing",
     * 			"name" : "内径"
     *        }, {
     * 			"flag" : "bihou",
     * 			"name" : "壁厚"
     *        } ]);
     * 		//板料
     * 		shapeMap.set("banliao", [ {
     * 			"flag" : "changdu",
     * 			"name" : "长度"
     *        }, {
     * 			"flag" : "kuandu",
     * 			"name" : "宽度"
     *        }, {
     * 			"flag" : "houdu",
     * 			"name" : "厚度"
     *        } ]);
     * 		//六角棒
     * 		shapeMap.set("liujiaobang", [ {
     * 			"flag" : "changdu",
     * 			"name" : "长度"
     *        }, {
     * 			"flag" : "duibian",
     * 			"name" : "对边"
     *        } ]);
     *
     *
     * 		var xingzhuangCodeMap = new Map();
     * 		xingzhuangCodeMap.set("yuanbangliao","圆棒料");
     * 		xingzhuangCodeMap.set("yuanguanliao","圆管料");
     * 		xingzhuangCodeMap.set("banliao","板料");
     * 		xingzhuangCodeMap.set("fangguanliao","方管");
     * 		xingzhuangCodeMap.set("liujiaobang","六角棒");
     *
     * 		private String  changdu;
     * 	private String  kuandu;
     * 	private String  gaodu;
     * 	private String  neikuan;
     * 	private String  neigao;
     * 	private String  waijing;
     * 	private String  neijing;
     * 	private String  bihou;
     * 	private String  houdu;
     * 	private String  duibian;
     */


    public static String  guige_convert(String guigestr){
        String resultData = "";
        Map<String,Object> guigeMap = JSON.parseObject(guigestr, Map.class);
        Set<Map.Entry<String, Object>> entries = guigeMap.entrySet();
        for (Map.Entry<String, Object> obj : entries){
            if("changdu".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(长度),";
                continue;
            }
            if("kuandu".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(宽度),";
                continue;
            }if("gaodu".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(高度),";
                continue;
            } if("neikuan".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(内宽),";
                continue;
            } if("neigao".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(内高),";
                continue;
            } if("waijing".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(外径),";
                continue;
            } if("neijing".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(内径),";
                continue;
            } if("bihou".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(壁厚),";
                continue;
            } if("duibian".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(对边),";
                continue;
            }if("houdu".equals(obj.getKey())&&obj.getValue()!=null){
                resultData = resultData+obj.getValue()+"(厚度),";
                continue;
            }

        }

        if(resultData.endsWith(",")){
            resultData = resultData.substring(0,resultData.length()-1);
        }
        return resultData;
    }
}
