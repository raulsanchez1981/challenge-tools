package challenge.controllers;

import challenge.entities.Character;
import challenge.services.CharacterService;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@RestController
public class HelloController {

    @Autowired
    CharacterService characterService;

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
                Character heroe = new Character();
                heroe.setName(pageCharac.getElementByName("title").getAttribute("content"));
                if (null!= personalData && personalData.length>1) {
                    heroe.setAlterEgo(personalData[1]);
                }
                if (null!= description && description.length>1) {
                    heroe.setDescription(description[1]);
                }
                heroe.setImage(image);
                Random random = new Random();
                heroe.setStrength(5+random.nextInt(5));

                characterService.saveCharacter(heroe);
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
//            Result<CharacterCharacter> characters = restClient.getCharacters(new CharacterParameters());
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
