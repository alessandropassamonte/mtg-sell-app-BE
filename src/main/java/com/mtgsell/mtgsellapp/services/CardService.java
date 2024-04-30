package com.mtgsell.mtgsellapp.services;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.repositories.CardRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public Page<Card> findPaginated(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        String basicLand = "%basic land%";
        String terraBase = "%terra base%";
        String token = "%token%";
        return cardRepository.findAllCards(basicLand.toLowerCase(), terraBase.toLowerCase(), token.toLowerCase(), pageRequest);
    }

    public Page<Card> findByNamePaginated(int page, int size, String search) {
        PageRequest pageRequest = PageRequest.of(page, size);
        String basicLand = "%basic land%";
        String terraBase = "%terra base%";
        String token = "%token%";
        return cardRepository.findAllCardsByName(basicLand.toLowerCase(), terraBase.toLowerCase(), token.toLowerCase(), search, pageRequest);
    }

    public Card findById(String id) {
        return cardRepository.findByCardId(id).orElseThrow();
    }

    public List<Card> findAutocomplete(String search) {
        String basicLand = "%basic land%";
        String terraBase = "%terra base%";
        String token = "%token%";
        return cardRepository.findAutocomplete(basicLand.toLowerCase(), terraBase.toLowerCase(), token.toLowerCase(), search).orElseThrow();
    }

    public String scraping(String setName, String cardName) {

        System.setProperty("webdriver.chrome.driver", "C:\\ProgramData\\chocolatey\\lib\\chromedriver\\tools\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://www.cardmarket.com/it/Magic/Products/Singles/" + setName + "/" + cardName + "?sellerCountry=17&language=1,5&minCondition=3";
        driver.get(url);
        WebElement table = driver.findElement(By.className("table"));
        List<WebElement> results = table.findElements(By.cssSelector(".color-primary.small.text-end.text-nowrap.fw-bold"));
        String resultItem = results.get(1).getText();
        for (WebElement result : results) {
            System.out.println(result.getText());
        }

        driver.quit();

        return resultItem;
    }




}
