package com.scaffold.simple.admin.utils;

import com.alibaba.fastjson.JSON;
import org.jsoup.internal.StringUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 已经投入1800*3=5400   住了1.5个月，押金是1800，预计能退1800  等价于消耗3600元，最坏的方式是 退不了，等价与1.5月，每月房租2500
 * 公寓：五月3600+六月1800+七月1800+八月1800+九月1800+十月1800+十一月1800+十二月1800+一月1800+二月1800+三月1800+四月1800
 * 3600+11*1800=23400  总：23400
 *
 * 次卧，已交5530元，7月交1300，8月交3900，11月交3900，2月交3900 6月结束   累计：5530+1300+3900+3900+3900=18530
 *
 * 公寓 23400  次卧 18530  差价 4870 元  平均到每月节约 405元
 *
 * 换次卧的成本，违约。押金要不回来，粗略估计要回来一半。也就是900元，然后当月没有住满，21日开始租的房，相当于损失了20天的房租。沉默成本是1200元
 * 1200+900=2100元。也就是说如果这次换了次卧，损失2100元现金。换算到月175的沉默成本。等价于次卧的房租是1475
 * 换次卧之后每月405元利益-换房的沉默成本175=既得利益230元。
 *
 * 运气好的情况下要回来全部押金1800，损失20天的房租1200元，沉默成本是1200，换房子损失1200元现金，换算到月就是100元的沉默成本。等价于换次卧之后每月房租是1400元。
 *
 * 结论：换次卧之后，相对与公寓，保底每月既得利益230-330元之间。等价于每天收入8-11元。或者每天少花8-11元。损失项：公用卫生间，公用厨房.
 *
 * @Author tianjl
 * @Date 2021/6/25 18:10
 * @Discription disc
 */
public class AdPositionUtils {

    /**
     * wap前缀
     * ad_类型_位置
     */
    public static final String adRedisWapPrefix="ad_wap_";

    /**
     *web端的位置信息
     */
    public static final String adRedisWebPrefix="ad_web_";

    /**
     * 顶部大图
     */
    public static final String adRedisTopPic="ad_pic_top_";

    /**
     * 词条与推广位的关联
     */
    public static final String adLemmaPrefix="ad_lemma";

    /**
     * 限定返回给前端得列表
     */
    static class AdNode{
        /**
         * 所在页面的id
         */
        private Integer aId;
        /**
         * 页面的名称
         */
        private String name;

        /**
         * 页面可选的位置
         */
        private List<PositionNode> son;

        /**
         * 默认构造函数
         * @param aId id
         * @param name 名称
         * @param son 子节点
         */
        public AdNode(Integer aId, String name, List<PositionNode> son) {
            this.aId = aId;
            this.name = name;
            this.son = son;
        }

        public Integer getaId() {
            return aId;
        }

        public void setaId(Integer aId) {
            this.aId = aId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<PositionNode> getSon() {
            return son;
        }

        public void setSon(List<PositionNode> son) {
            this.son = son;
        }
    }

    static class PositionNode{
        /**
         * 展示的位置id
         */
        private int pId;

        /**
         * 展示位置的名称
         */
        private String name;

        /**
         * 展示位置的分隔
         */
        private int style;

        /**
         * 默认构造函数
         * @param pId 位置id
         * @param name 名称
         * @param style 风格
         */
        public PositionNode(int pId, String name, int style) {
            this.pId = pId;
            this.name = name;
            this.style = style;
        }

        public int getpId() {
            return pId;
        }

        public void setpId(int pId) {
            this.pId = pId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStyle() {
            return style;
        }

        public void setStyle(int style) {
            this.style = style;
        }
    }
    /**
     * 广告页面信息
     * 0:WAP首页,1:WAP词条页,2:PC首页，3：PC词条页，4:PC任务详情页，
     */
    public static final Map<Integer,String> adPage=new HashMap<>();

    /**
     * 广告页和展示位置
     */
    public static final Map<Integer,Map<Integer,String>> adPagePostion=new HashMap<>();

    /**
     * 指定返回给前端得列表
     */
    public static List<AdNode> list=new ArrayList<>();

    /**
     * 静态块初始化000876
     */
    static {
        /**
         * 初始化页面信息
         */
        adPage.put(1,"WAP首页");
        adPage.put(2,"WAP词条页");
        adPage.put(3,"PC首页");
        adPage.put(4,"PC词条页");
        adPage.put(5,"PC任务详情页");

        /**
         * WAP 1-50
         * PC  >50
         * 初始化Wap-首页
         */
        List<PositionNode> map=new ArrayList<>();
        map.add(new PositionNode(1,"首页banner",1));
        map.add(new PositionNode(2,"首页底部文字链",2));
        list.add(new AdNode(1,"WAP首页",map));


        /**
         * 初始化WAP-词条页
         */
        map=new ArrayList<>();
        map.add(new PositionNode(3,"词条页顶部摘要图上方文字链",2));
        map.add(new PositionNode(4,"词条页悬浮icon",1));
        map.add(new PositionNode(5,"词条页底部banner",1));
        map.add(new PositionNode(6,"词条页底部文字链",2));
        list.add(new AdNode(2,"WAP词条页",map));


        /**
         * 初始化PC-首页位置信息
         */
        map=new ArrayList<>();
        map.add(new PositionNode(51,"首页底部通栏推广位",1));
        list.add(new AdNode(3,"PC首页",map));



        /**
         * 初始化PC-词条页
         */
        map=new ArrayList<>();
        map.add(new PositionNode(52,"词条页推广图1（右侧）",1));
        map.add(new PositionNode(53,"词条页推广图2（右侧）",1));
        map.add(new PositionNode(54,"词条页左侧悬浮广告",1));
        map.add(new PositionNode(55,"词条页搜索框右侧推广图",1));
        map.add(new PositionNode(56,"词条页百科消息1（右侧）",2));
        map.add(new PositionNode(57,"词条页百科消息2（右侧）",2));
        map.add(new PositionNode(58,"词条页百科消息3（右侧）",2));
        map.add(new PositionNode(59,"词条页头部大图",3));
        list.add(new AdNode(4,"PC词条页",map));

        /**
         * PC任务详情页
         */
        map=new ArrayList<>();
        map.add(new PositionNode(60,"任务左侧推广位",1));
        map.add(new PositionNode(61,"任务右侧推广位",1));
        list.add(new AdNode(5,"PC任务详情页",map));
    }

    /**
     * 过滤一下位置西悉尼
     * @param position 传入得位置
     * @return 地址编码信息
     */
    public static List<Integer> positionFilter(String position){
        List<Integer> positions=new ArrayList<>(16);
        if (StringUtil.isBlank(position)){
            return positions;
        }
        for (AdNode adNode : list) {
            adNode.son.forEach((c->{
                if (c.getName().contains(position)){
                    positions.add(c.getpId());
                }
            }));
        }
        return positions.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 数字转化为汉字地址信息
     * @param pageAdPosition 位置得数字信息
     * @return 位置得汉字描述
     */
    public static String parsePosition(Integer pageAdPosition) {
        if (Objects.isNull(pageAdPosition)) {
            return null;
        }
        AtomicReference<String> value = new AtomicReference<String>();
        for (AdNode adNode : list) {
            adNode.son.forEach(c-> {
                if (pageAdPosition.equals(c.getpId())) {
                    value.set(c.getName());
                }
            });
        }
        return value.get();
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(list));
    }
}