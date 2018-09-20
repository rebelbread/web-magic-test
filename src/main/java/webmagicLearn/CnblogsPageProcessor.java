package webmagicLearn;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author zhiwj
 * @date 2018/9/10
 */
public class CnblogsPageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

    private static String url = "http://www.lianhanghao.com/index.php/Index/index/p/1.html";

    @Override
    public void process(Page page) {
/*        List<String> urls = new ArrayList<>();
        for (int i = 2; i <= 200; i++) {
            urls.add(url + "/#p" + i);
        }
        page.addTargetRequests(urls);*/

//        page.putField("body", );

        List<String> all = page.getHtml().xpath("table/tbody/tr/td/text()").all();
        page.putField("all",all);



/*        List<String> all = page.getHtml().links().regex("http://www\\.gs12333\\.gov\\.cn/consult/details*").all();
        page.putField("all",all);*/

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CnblogsPageProcessor())
                //.addUrl(urls)
                .addUrl(url)
                .addPipeline(new ConsolePipeline())
                .thread(5)
                .run();
        System.out.println("结束");
    }
}
