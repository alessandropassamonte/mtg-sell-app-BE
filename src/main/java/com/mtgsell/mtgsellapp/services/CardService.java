package com.mtgsell.mtgsellapp.services;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.repositories.CardRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public Page<Card> findPaginated(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        String basicLand = "basic %";
        String token = "token%";
        return cardRepository.findAllCards(basicLand.toLowerCase(),  token.toLowerCase(), pageRequest);
    }

    public Page<Card> findByNamePaginated(int page, int size, String search) {

        PageRequest pageRequest = PageRequest.of(page < 0 ? 0 : page, size);
        String basicLand = "basic %";
        String token = "token%";
        return cardRepository.findAllCardsByName(basicLand.toLowerCase(), token.toLowerCase(), search, pageRequest);
    }

    public Card findById(String id, Boolean isFoil) throws IOException {
        Card card = cardRepository.findByCardId(id).orElseThrow();

        String cardName = card.getName();
        cardName = cardName.replaceAll("[^\\w\\s-]", "").replaceAll("\\s+", "-");
        cardName = cardName.replaceAll("^-|-$", "");
        String result = "";
        try {
            result = scraping(card.getEditions().get(0).getCardMarketName(), cardName, isFoil);
        } catch (Exception e) {
            result = scraping(card.getEditions().get(0).getCardMarketName() + "-Extras", cardName, isFoil);
        }

        card.setPriceCM(result);
        return card;
    }

    public List<Card> findAutocomplete(String search) {
        String basicLand = "basic %";
        String token = "token%";
        return cardRepository.findAutocomplete(basicLand.toLowerCase(),  token.toLowerCase(), search).orElseThrow();
    }


    public String executeScraping(Card card, Boolean isFoil) throws IOException {
        String cardName = card.getName();
        cardName = cardName.replaceAll("[^\\w\\s-]", "").replaceAll("\\s+", "-");
        cardName = cardName.replaceAll("^-|-$", "");
        String result = "";
        try {
            return result = scraping(card.getEditions().get(0).getCardMarketName(), cardName, isFoil);
        } catch (Exception e) {
            return result = scraping(card.getEditions().get(0).getCardMarketName() + "-Extras", cardName, isFoil);
        }

    }
    public String scraping(String setName, String cardName, Boolean isFoil) throws IOException {
        String chromeDriverPath;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            Resource resource = new ClassPathResource("chromedriver.exe");
            chromeDriverPath = resource.getFile().getAbsolutePath();
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        } else {
            Resource resource = new ClassPathResource("chromedriverLinux");
            chromeDriverPath = resource.getFile().getAbsolutePath();
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }

        String resultItem = "";
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        try {
            String url = "https://www.cardmarket.com/it/Magic/Products/Singles/" + setName + "/" + cardName + "?sellerCountry=17&language=1,5&minCondition=3" + (isFoil ? "&isFoil=Y" : "");
            System.out.println("URL: " + url);
            driver.get(url);
            WebElement table = driver.findElement(By.className("table"));
            List<WebElement> results = table.findElements(By.cssSelector(".color-primary.small.text-end.text-nowrap.fw-bold"));
            resultItem = results.get(1).getText();
            return resultItem;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return resultItem;
    }


}
