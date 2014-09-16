package learningresourcefinder.service.crawler;

import java.io.IOException;
import learningresourcefinder.repository.ResourceRepository;
import learningresourcefinder.repository.UrlResourceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class CrawlerSoutien67 
{
    @Autowired  ResourceRepository resourceRepository ;
    @Autowired  UrlResourceRepository urlResourceRepository ;

    public void crawlerFr1() throws IOException {
         
        Document doc1 = Jsoup.connect("http://soutien67.free.fr/francais/niv01/franniv01.htm#abc").timeout(10000).get();
        Elements ligne1 = doc1.select("table > tbody > tr > td > font, table > tbody > tr > td > a");
        String titre = null;
        for (Element ligne : ligne1) {
            if (!ligne.text().startsWith("..."))
            {
                if(ligne.tagName().equals("a"))
                {     
                    if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                    {
                        if(ligne.select("img").size()==0)
                        {
                            String lien = ligne.attr("href");
                            if(!lien.startsWith("#"))
                                System.out.println(titre+" : http://soutien67.free.fr/francais/niv01/"+ligne.attr("href"));
                            
                        }
                    }    
                }
                else  if(ligne.tagName().equals("font"))
                {                    
                    titre = ligne.text();
                }
            }
        }
     
    }
    public void crawlerFr2() throws IOException {
          
         Document doc1 = Jsoup.connect("http://soutien67.free.fr/francais/niv02/franniv02.htm").timeout(10000).get();
         Elements ligne1 = doc1.select("table > tbody > tr > td");
         String titre = null;
         for (Element ligne : ligne1) {
             if (!ligne.text().startsWith("..."))
             {
                 Elements elems = ligne.select("a");
                 if(elems.size()==0)
                 {
                     ligne = ligne.select("font").first();
                     if(ligne!=null)
                     {                    
                         titre = ligne.text();
                     }
                 }
                 else 
                 {    
                     ligne = elems.first();
                     if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                     {
                         if(ligne.select("img").size()==0)
                         {
                             String lien = ligne.attr("href");
                             if(!lien.startsWith("#"))
                                 System.out.println(titre+" : http://soutien67.free.fr/francais/niv02/"+ligne.attr("href"));
                             
                         }
                     }    
                 }
             }
         }
    }

         
         public void crawlerFr3() throws IOException {
          
              Document doc1 = Jsoup.connect("http://soutien67.free.fr/francais/niv03/franniv03.htm#abc").timeout(10000).get();
              Elements ligne1 = doc1.select("table > tbody > tr > td");
              String titre = null;
              for (Element ligne : ligne1) {
                  if (!ligne.text().startsWith("...")|| ligne.text().endsWith(")"))
                  {
                      Elements elems = ligne.select("a");
                      if(elems.size()==0)
                      {
                          ligne = ligne.select("font").first();
                          if(ligne!=null)
                          {                    
                              titre = ligne.text();
                          }
                      }
                      else 
                      {    
                          ligne = elems.first();
                          if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                          {
                              if(ligne.select("img").size()==0)
                              {
                                  String lien = ligne.attr("href");
                                  if(!lien.startsWith("#"))
                                      System.out.println(titre+" : http://soutien67.free.fr/francais/niv03/"+ligne.attr("href"));
                                  
                              }
                          }    
                      }
                  }
              }
         }
         
         
         public void crawlerFr4() throws IOException {
        
              Document doc1 = Jsoup.connect("http://soutien67.free.fr/francais/niv04/franniv04.htm").timeout(10000).get();
              Elements ligne1 = doc1.select("table > tbody > tr > td");
              String titre = null;
              for (Element ligne : ligne1) {
                  if (!ligne.text().startsWith("...")|| ligne.text().endsWith(")"))
                  {
                      Elements elems = ligne.select("a");
                      if(elems.size()==0)
                      {
                          ligne = ligne.select("font").first();
                          if(ligne!=null)
                          {                    
                              titre = ligne.text();
                          }
                      }
                      else 
                      {    
                          ligne = elems.first();
                          if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                          {
                              if(ligne.select("img").size()==0)
                              {
                                  String lien = ligne.attr("href");
                                  if(!lien.startsWith("#"))
                                      System.out.println(titre+" : http://soutien67.free.fr/francais/niv04/"+ligne.attr("href"));
                                  
                              }
                          }    
                      }
                  }
              }
         }
public void crawlerMath1() throws IOException {
             
             Document doc1 = Jsoup.connect("http://soutien67.free.fr/math/niv01/mathematique_exercices_01.htm").timeout(10000).get();
             Elements ligne1 = doc1.select("table > tbody > tr > td");
             String titre = null;
             for (Element ligne : ligne1) {
                 if (!ligne.text().startsWith("..."))
                 {
                     Elements elems = ligne.select("a");
                     if(elems.size()==0)
                     {
                         ligne = ligne.select("font").first();
                         if(ligne!=null)
                         {                    
                             titre = ligne.text();
                         }
                     }
                     else 
                     {    
                         ligne = elems.first();
                         if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                         {
                             if(ligne.select("img").size()==0)
                             {
                                 String lien = ligne.attr("href");
                                 if(!lien.startsWith("#"))
                                     System.out.println(titre+" : http://soutien67.free.fr/math/niv01/"+ligne.attr("href"));
                                
                             }
                         }    
                     }
                 }
             }
        }

public void crawlerMath2() throws IOException {
    
    Document doc1 = Jsoup.connect("http://soutien67.free.fr/math/niv02/mathematique_exercices_02.htm").timeout(10000).get();
    Elements ligne1 = doc1.select("table > tbody > tr > td");
    String titre = null;
    for (Element ligne : ligne1) {
        if (!ligne.text().startsWith("..."))
        {
            Elements elems = ligne.select("a");
            if(elems.size()==0)
            {
                ligne = ligne.select("font").first();
                if(ligne!=null)
                {                    
                    titre = ligne.text();
                }
            }
            else 
            {    
                ligne = elems.first();
                if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                {
                    if(ligne.select("img").size()==0)
                    {
                        String lien = ligne.attr("href");
                        if(!lien.startsWith("#"))
                            System.out.println(titre+" : http://soutien67.free.fr/math/niv02/"+ligne.attr("href"));
                       
                    }
                }    
            }
        }
    }
}

public void crawlerMath3() throws IOException {
    
    Document doc1 = Jsoup.connect("http://soutien67.free.fr/math/niv03/mathematique_exercices_03.htm").timeout(10000).get();
    Elements ligne1 = doc1.select("table > tbody > tr > td");
    String titre = null;
    for (Element ligne : ligne1) {
        if (!ligne.text().startsWith("..."))
        {
            Elements elems = ligne.select("a");
            if(elems.size()==0)
            {
                ligne = ligne.select("font").first();
                if(ligne!=null)
                {                    
                    titre = ligne.text();
                }
            }
            else 
            {    
                ligne = elems.first();
                if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                {
                    if(ligne.select("img").size()==0)
                    {
                        String lien = ligne.attr("href");
                        if(!lien.startsWith("#"))
                            System.out.println(titre+" : http://soutien67.free.fr/math/niv03/"+ligne.attr("href"));
                        
                    }
                }    
            }
        }
    }
}
         
         public void crawlerMath4() throws IOException {
             
             Document doc1 = Jsoup.connect("http://soutien67.free.fr/math/niv04/mathematique_exercices_04.htm").timeout(10000).get();
             Elements ligne1 = doc1.select("table > tbody > tr > td");
             String titre = null;
             for (Element ligne : ligne1) {
                 if (!ligne.text().startsWith("..."))
                 {
                     Elements elems = ligne.select("a");
                     if(elems.size()==0)
                     {
                         ligne = ligne.select("font").first();
                         if(ligne!=null)
                         {                    
                             titre = ligne.text();
                         }
                     }
                     else 
                     {    
                         ligne = elems.first();
                         if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                         {
                             if(ligne.select("img").size()==0)
                             {
                                 String lien = ligne.attr("href");
                                 if(!lien.startsWith("#"))
                                     System.out.println(titre+" : http://soutien67.free.fr/math/niv04/"+ligne.attr("href"));
                                 
                             }
                         }    
                     }
                 }
             }
        }
         
         
         
         public void crawlerHistoire() throws IOException {
             
             Document doc1 = Jsoup.connect("http://soutien67.free.fr/histoire/histoire_fiches_01.htm").timeout(10000).get();
             Elements ligne1 = doc1.select("table > tbody > tr > td");
             String titre = null;
             for (Element ligne : ligne1) {
                 if (!ligne.text().startsWith("..."))
                 {
                     Elements elems = ligne.select("a");
                     if(elems.size()==0)
                     {
                         ligne = ligne.select("font").first();
                         if(ligne!=null)
                         {                    
                             titre = ligne.text();
                         }
                     }
                     else 
                     {    
                         ligne = elems.first();
                         if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                         {
                             if(ligne.select("img").size()==0)
                             {
                                 String lien = ligne.attr("href");
                                 if(!lien.startsWith("#"))
                                     System.out.println(titre+" : http://soutien67.free.fr/histoire/"+ligne.attr("href"));
                                 
                             }
                         }    
                     }
                 }
             }
        }
         
        
public void crawlerGeographie() throws IOException {
             
             Document doc1 = Jsoup.connect("http://soutien67.free.fr/geographie/geographie_ressources_01.htm").timeout(10000).get();
             Elements ligne1 = doc1.select("table > tbody > tr > td");
             String titre = null;
             for (Element ligne : ligne1) {
                 if (!ligne.text().startsWith("..."))
                 {
                     Elements elems = ligne.select("a");
                     if(elems.size()==0)
                     {
                         ligne = ligne.select("font").first();
                         if(ligne!=null)
                         {                    
                             titre = ligne.text();
                         }
                     }
                     else 
                     {    
                         ligne = elems.first();
                         if (!ligne.attr("href").endsWith(".doc") && titre!=null)
                         {
                             if(ligne.select("img").size()==0)
                             {
                                 String lien = ligne.attr("href");
                                 if(!lien.startsWith("#"))
                                     System.out.println(titre+" : http://soutien67.free.fr/geographie/"+ligne.attr("href"));
                                 
                             }
                         }    
                     }
                 }
             }
        }  

    public void crawlerScience ()  throws IOException{
        Document doc1 = Jsoup.connect("http://soutien67.free.fr/svt/sciences.htm").timeout(10000).get();
        Elements ligne1 = doc1.select("table > tbody > tr > td > p > b > a, table > tbody > tr > td > p > a, table > tbody > tr > td > p > font > b > a");
        for (Element ligne : ligne1) {
            String titre = ligne.text();
            titre = titre.replace("...", "");
            if (!titre.equals("")){
            String link = ligne.attr("href");
            System.out.println(titre + " : http://soutien67.free.fr/svt/"+link);}
        }
    }
             
    public static void main(String[] args) throws IOException {
        CrawlerSoutien67 cs = new CrawlerSoutien67();
        cs.crawlerScience();
    }
}