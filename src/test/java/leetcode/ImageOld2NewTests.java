//package leetcode;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.sogou.baike.mobile.common.CommonConfigManager;
//import com.sogou.baike.mobile.controller.AjaxController;
//import com.sogou.baike.mobile.service.lemma.CommonLemmaService;
//import com.sogou.baike.mobile.utils.HttpUtil;
//
//import java.util.List;
//
///**
// * @Author tianjl
// * @Date 2021/9/27 11:24
// * @Discription disc
// */
//public class ImageOld2NewTests {
//    // 由于此类只会在这里用到，所以定义为内部类
//    public static class UrlInfo {
//
//        private String height;
//        private String status;
//        private String width;
//        private String url;
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public String getHeight() {
//            return height;
//        }
//
//        public void setHeight(String height) {
//            this.height = height;
//        }
//
//        public String getWidth() {
//            return width;
//        }
//
//        public void setWidth(String width) {
//            this.width = width;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//        @Override
//        public String toString() {
//            return "UrlInfo{" +
//                    "height=" + height +
//                    ", status='" + status + '\'' +
//                    ", width=" + width +
//                    ", url='" + url + '\'' +
//                    '}';
//        }
//
//        public String toPicString() {
//            return "<a class=\"ed_image_link\" title=\"点击查看大图\" href=\"" + url + "\" target=\"_blank\"><img title=\"\" alt=\"\" src=\"" + url + "\" width=\"" + width + "\" height=\"" + height + "\" class=\"\"/></a>";
//        }
//
//        public String toPicGroupString(String dataId, String info, int num) {
//            return "<img class=\"j-summary-album-img\" src=\"" + url + "\" width=\"" + width + "\" height=\"" + height + "\" data-total=\"" + num + "\" data-id=\"" + dataId + "\" alt=\"" + info + "\" />";
//        }
//
//    }
//
//    public static class ListUrl {
//        private String mark;
//
//        private String path;
//
//        private String thumbnail;
//
//        public ListUrl(String path, String thumbnail) {
//            this.path = path;
//            this.thumbnail = thumbnail;
//        }
//
//        public String getMark() {
//            return this.mark;
//        }
//
//        public void setMark(String mark) {
//            this.mark = mark;
//        }
//
//        public String getPath() {
//            return this.path;
//        }
//
//        public void setPath(String path) {
//            this.path = path;
//        }
//
//        public String getThumbnail() {
//            return this.thumbnail;
//        }
//
//        public void setThumbnail(String thumbnail) {
//            this.thumbnail = thumbnail;
//        }
//
//        @Override
//        public String toString() {
//            return "ListUrl{" +
//                    "mark='" + mark + '\'' +
//                    ", path='" + path + '\'' +
//                    ", thumbnail='" + thumbnail + '\'' +
//                    '}';
//        }
//    }
//
//    public static class ImageGroup {
//        private int code;
//
//        private int id;
//
//        private String info;
//
//        private boolean SelectedCover;
//        private String coverHeight;
//        private String coverUrl;
//        private String coverWidth;
//        private int total;
//        private List<ListUrl> list;
//
//        public int getTotal() {
//            return total;
//        }
//
//        public void setTotal(int total) {
//            this.total = total;
//        }
//
//        public boolean isSelectedCover() {
//            return SelectedCover;
//        }
//
//        public void setSelectedCover(boolean selectedCover) {
//            SelectedCover = selectedCover;
//        }
//
//        public String getCoverHeight() {
//            return coverHeight;
//        }
//
//        public void setCoverHeight(String coverHeight) {
//            this.coverHeight = coverHeight;
//        }
//
//        public String getCoverUrl() {
//            return coverUrl;
//        }
//
//        public void setCoverUrl(String coverUrl) {
//            this.coverUrl = coverUrl;
//        }
//
//        public String getCoverWidth() {
//            return coverWidth;
//        }
//
//        public void setCoverWidth(String coverWidth) {
//            this.coverWidth = coverWidth;
//        }
//
//        public int getCode() {
//            return this.code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public int getId() {
//            return this.id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getInfo() {
//            return this.info;
//        }
//
//        public void setInfo(String info) {
//            this.info = info;
//        }
//
//        public List<ListUrl> getList() {
//            return this.list;
//        }
//
//        public void setList(List<ListUrl> list) {
//            this.list = list;
//        }
//
//        @Override
//        public String toString() {
//            return "ImageGroup{" +
//                    "code=" + code +
//                    ", id=" + id +
//                    ", info='" + info + '\'' +
//                    ", SelectedCover=" + SelectedCover +
//                    ", coverHeight='" + coverHeight + '\'' +
//                    ", coverUrl='" + coverUrl + '\'' +
//                    ", coverWidth='" + coverWidth + '\'' +
//                    ", list=" + list +
//                    '}';
//        }
//    }
//    public static void main(String[] args) {
//        JSONObject tempJsonObject = new JSONObject();
////        {ids: "4317649,4316276"}
//        tempJsonObject.put("id", "4317649,4316276");
//        String picGroupStr = HttpUtil.httpPost(CommonConfigManager.IMAGE_GROUP_QUERY_URL, tempJsonObject, false, false);
//        JSONObject baikeResponse = JSONObject.parseObject(picGroupStr);
//        System.out.println(baikeResponse);
//
//        ImageGroup root = new ImageGroup();
//        root.setCode(baikeResponse.getIntValue("code"));
//        String picGroups = baikeResponse.getString("data");
//        root.setList(JSON.parseArray(picGroups, AjaxController.ListUrl.class));
//        System.out.println(JSON.toJSONString(root));
//    }
//}
