package task.usersDAO;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import task.users.Rate;

@Component
public class RateDAO {

    final private int numberOfRatesInAPI = 4;


    private Rate rates[]= new Rate[numberOfRatesInAPI];


    private String getFromAPI() {
        final RestTemplate restTemplate = new RestTemplate();
        final String stringPosts = restTemplate.getForObject("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5", String.class);

        return stringPosts;
    }


    private void parse() {
        String stringPosts = this.getFromAPI();
        int alreadyPassed = 0;

        for (int i = 0; i < numberOfRatesInAPI; i++) {
            int start = stringPosts.indexOf("{", alreadyPassed);
            int end = stringPosts.indexOf("}", alreadyPassed);
            alreadyPassed = end + 1;
            String sub = stringPosts.substring(start, end + 1);
            Gson g = new Gson();
            Rate r = g.fromJson(sub, Rate.class);
            rates[i] = r;
        }
    }


    public double getRate() {
        this.parse();

        double USDtoUAN = 0;
        double BTCtoUSD = 0;

        for (int i = 0; i < numberOfRatesInAPI; i++) {
            if (rates[i].getCcy().equals("USD")) {
                USDtoUAN = (rates[i].getBuy() + rates[i].getSale()) / 2;
            }
            if (rates[i].getCcy().equals("BTC")) {
                BTCtoUSD = (rates[i].getBuy() + rates[i].getSale()) / 2;
            }
        }

        return BTCtoUSD * USDtoUAN;
    }

}
