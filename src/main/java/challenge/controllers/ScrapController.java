package challenge.controllers;

import challenge.entities.Character;
import challenge.repositories.CharacterRepository;
import challenge.services.CharacterService;
import challenge.enums.Power;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@PropertySource("classpath:scrapingUrls.properties")
public class ScrapController {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    CharacterService characterService;

    @Value("${marvelHeroes.urls}")
    private String marvelCharactersUrls;


    @RequestMapping(method= RequestMethod.GET, value="/holaGET")
    public String methodGet() {
        return "Holaaaa GET";
    }

    @RequestMapping(method= RequestMethod.GET, value="/properties")
    public void scrapingProperties() {
        Stream.of(marvelCharactersUrls.split(",")).forEach(this::scrapingWeb);
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

                String powers = this.emptyIfNull(personalData, 7);
                String abilities = this.emptyIfNull(personalData, 10);
                List<String> listPowers = Stream.of(Power.values()).filter(p -> StringUtils.containsIgnoreCase(powers, p.getValue()) || StringUtils.containsIgnoreCase(abilities,p.getValue())).map(Power::getValue).collect(Collectors.toList());
                //7
                //10
                heroe.setPowers(listPowers);
                heroe.setImage(image);
                Random random = new Random();
                heroe.setStrength(5+random.nextInt(5));
                Integer date = 1980 + random.nextInt(15);
                heroe.setBirthDate(LocalDate.parse("01/01/"+date.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                characterService.saveCharacter("scrap", heroe);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String emptyIfNull(String[] array, int index ) {
        String value = "";
        if (null != array && array.length > 1 && null != array[index]) {
            value = array[index];
        }
        return  value;
    }

}
