package learningresourcefinder.service.crawler;

import java.io.IOException;

import learningresourcefinder.repository.ResourceRepository;
import learningresourcefinder.repository.UrlResourceRepository;
import learningresourcefinder.search.SearchOptions.Format;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrawlerBrainPOP 
{
    @Autowired CrawlerService cs;
    // DONE 560 ressources
    public void crawler() throws IOException 
    {

        Document doc1 = Jsoup.connect("http://www.brainpop.fr/topics/").timeout(10000).get();
        Elements resources = doc1.select("tr > td > blockquote > div ");
        String categorie = "";
        for(Element resource : resources)
        {
            if(resource.select("b").size()>0)
            {
                categorie = resource.text();
            }
            else
            {
                String titre = ""+resource.select("a").get(1).text();
                String lien =  "http://www.brainpop.fr"+resource.select("a").get(1).attr("href");
                String description = resource.select("div > div > span.user_body").get(1).text();
                String temps = resource.select("div > div > div > span.user_body").get(0).text();
                String[] time = temps.split("[(:)]");
                int minutes = 0;
                if (time.length != 0) 
                {
                    
                    minutes = Integer.parseInt(time[1]);
                    int seconds = Integer.parseInt(time[2]);                    
                    minutes += (seconds>0)? 1:0;
                }

            
                if(cs!=null)
                    cs.persistRessource(titre,lien,categorie,description,minutes,"","", 0, "BrainPop",Format.VIDEOS);
            }
        }

    }

    public static void main(String[] args) throws IOException 
    {
        CrawlerBrainPOP cr = new CrawlerBrainPOP();
        cr.crawler();
    }
}
