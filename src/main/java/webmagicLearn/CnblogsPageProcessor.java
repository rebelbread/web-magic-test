package webmagicLearn;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
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
    private static String url2 = "http://www.lianhanghao.com/index.php/Index/index/p/%d.html";

    @Override
    public void process(Page page) {
        List<String> urls = new ArrayList<>();
        for (int i = 2; i <= 14344; i++) {
            urls.add(String.format(url2, i));
        }
        page.addTargetRequests(urls);

//        page.putField("body", );

        List<String> list = page.getHtml().xpath("table[@class='t2']/tbody/tr/td/text()").all();
        for (int i = 0; i < list.size(); i++) {
            if (i % 4 == 0) {
                Tools.save(list.get(i), list.get(i + 1), list.get(i + 2), list.get(i + 3));
            }
        }

        page.putField("all", list);

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
