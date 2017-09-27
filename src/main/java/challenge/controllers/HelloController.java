package challenge.controllers;

import challenge.entities.Marvel;
import challenge.services.MarvelServices;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.codingricky.marvel.Proxy;
import com.github.codingricky.marvel.RestClient;
import com.github.codingricky.marvel.model.MarvelCharacter;
import com.github.codingricky.marvel.model.Result;
import com.github.codingricky.marvel.parameter.CharacterParameters;
import com.sun.deploy.net.URLEncoder;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

    @Autowired
    MarvelServices marvelServices;

    @RequestMapping(method= RequestMethod.GET, value="/holaGET")
    public String methodGet() {
        return "Holaaaa GET";
    }

    @RequestMapping(method= RequestMethod.GET, value="/web")
    public void scrapingWeb(String url) {

        String https = "https:";
        WebClient client = new WebClient();
        ProxyConfig proxyConfig = new ProxyConfig("10.129.8.100", 8080);
        client.getOptions().setProxyConfig(proxyConfig);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);




        try {
            HtmlPage page = client.getPage(url);
            DomNodeList<DomNode> domNodes = page.querySelectorAll(".row-item-image-url");
            for (DomNode nodo: domNodes) {
                String urlCharacter = nodo.getAttributes().getNamedItem("href").getNodeValue();
                HtmlPage pageCharac = client.getPage(https + urlCharacter);

                //page.querySelectorAll(".row-item-image-url").get(2).getAttributes().getNamedItem("href").getNodeValue();
                String[] personalData = null;
                if (null != pageCharac.querySelector(".featured-item-meta")) {
                    personalData = pageCharac.querySelector(".featured-item-meta").asText().split("\\r\\n");
                }
                String[] description = null;
                if (null != pageCharac.querySelector(".featured-item-desc")) {
                    description = pageCharac.querySelector(".featured-item-desc").asText().split("\\r\\n");

                }
                String image = null;
                if (null != pageCharac.querySelector(".character-image")){
                    image = pageCharac.querySelector(".character-image").getAttributes().getNamedItem("src").getNodeValue();
                }
                Marvel heroe = new Marvel();
                heroe.setHero(pageCharac.getElementByName("title").getAttribute("content"));
                if (null!= personalData && personalData.length>1) {
                    heroe.setAlterEgo(personalData[1]);
                }
                if (null!= description && description.length>1) {
                    heroe.setDescription(description[1]);
                }
                heroe.setImage(image);
                Random random = new Random();
                heroe.setStrength(5+random.nextInt(5));

                marvelServices.saveMarvel(heroe);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

//        String publicKey = "68be8ec799b1e8490f2cedd31352fdd1";
//        String privateKey = "4256f1a1bd9ddf3d41670bbd0b75ec080c490fcf";
//
//        Proxy proxy = new Proxy("10.129.8.100", 8080);
//        RestClient restClient = new RestClient(privateKey, publicKey, proxy);
//        try {
//            Result<MarvelCharacter> characters = restClient.getCharacters(new CharacterParameters());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @RequestMapping(method= RequestMethod.POST, value="/HolaPOST")
    public String methodPost(@RequestBody String prueba) {
        return "Hola POST";
    }

    @RequestMapping(method= RequestMethod.PUT, value="/holaPUT")
    public String methodPUT(@RequestBody String prueba) {
        return "Hola PUT";
    }

    @RequestMapping(method= RequestMethod.PATCH, value="/holaPATCH")
    public String methodPATCH(@RequestBody String prueba) {
        return "Hola PATCH";
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/holaDELETE")
    public void methodDELETE(@RequestHeader String prueba) {
    }
}
